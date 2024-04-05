package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.war.War;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.state.StateRepository;
import bencoepp.livius.repositories.war.WarRepository;
import bencoepp.livius.utils.COWUtil;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import java.util.ArrayList;

import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

/**
 * The NonStateWarTask class is responsible for executing non-state war-related jobs.
 * It extends the Task class, overriding the run() method to provide the specific implementation for non-state war tasks.
 *
 * This class is annotated with @Component, indicating that it is a Spring component and can be managed by the Spring container.
 * It is also annotated with @Slf4j to enable logging through the use of the SLF4J logging facade.
 */
@Component
@Slf4j
public class NonStateWarTask extends Task {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    private Job job;
    @Autowired
    private WarRepository warRepository;
    @Autowired
    private COWUtil util;
    @Autowired
    private StateRepository stateRepository;
    /**
     * Runs the job specified by the JobEvent.
     * If the job has dependencies that are not finished, it waits for them to finish executing before processing the job.
     * After processing the job, it updates the job status, start time, and saves the job to the repository.
     * It then reads the CSV file specified by the job name and processes its content to create and save War objects to the repository.
     *
     * @param event the JobEvent specifying the job to run
     */
    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.non-state-war.csv')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();

        boolean skip = false;

        for (String dependency : job.getDependencies()){
            Job depJob = jobRepository.findByName(dependency);
            if(depJob == null){
                log.info("No jobs found for the specified dependency: " + dependency);
            }
            if(!depJob.getStatus().equals(Job.STATUS_FINISHED)){
                skip = true;
            }
        }

        if(skip){
            log.info("Waiting for dependency to finish executing");
        }else {
            job.setStatus(Job.STATUS_EXECUTING);
            job.setStarted(Instant.now());
            jobRepository.save(job);

            log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName()))) {
                bufferedReader.lines().forEach(line -> {
                    if(!line.contains("WarNum,WarName,WarType,WhereFought,SideA1,SideA2,SideB1,SideB2,SideB3,SideB4,SideB5,StartYear,StartMonth,StartDay,EndYear,EndMonth,EndDay,Initiator,TransFrom,TransTo,Outcome,SideADeaths,SideBDeaths,TotalCombatDeaths,Version")){
                        String[] data = line.split(",");

                        War war = new War();
                        war.setId(sequenceGeneratorService.getSequenceNumber(War.SEQUENCE_NAME));
                        war.setName(data[1]);

                        if(data[2].equals("8")){
                            war.setType(War.TYPE_WARS_BETWEEN_NON_STATE_IN_NON_STATE_TERRITORY);
                        }else if(data[2].equals("9")){
                            war.setType(War.TYPE_WARS_BETWEEN_NSA_ACROSS_BORDER);
                        }else{
                            war.setType(War.TYPE_NONE);
                        }

                        switch (data[3]) {
                            case "1" -> war.setRegion(War.REGION_WEST_HEMISPHERE);
                            case "2" -> war.setRegion(War.REGION_EUROPE);
                            case "4" -> war.setRegion(War.REGION_AFRIKA);
                            case "6" -> war.setRegion(War.REGION_MIDDLE_EAST);
                            case "7" -> war.setRegion(War.REGION_ASIA);
                            case "9" -> war.setRegion(War.REGION_OCEANIA);
                        }

                        war.setNonStateWar(true);

                        war.setSideA(new ArrayList<>());
                        if(!data[4].isBlank()){
                            war.getSideA().addAll(util.createNonStateEntity(data[4]));
                        }
                        if(util.checkIsNotUnknown(data[5])){
                            war.getSideA().addAll(util.createNonStateEntity(data[5]));
                        }

                        war.setSideB(new ArrayList<>());
                        if(!data[6].isBlank()){
                            war.getSideA().addAll(util.createNonStateEntity(data[6]));
                        }
                        if(util.checkIsNotUnknown(data[7])){
                            war.getSideA().addAll(util.createNonStateEntity(data[7]));
                        }
                        if(util.checkIsNotUnknown(data[8])){
                            war.getSideA().addAll(util.createNonStateEntity(data[8]));
                        }
                        if(util.checkIsNotUnknown(data[9])){
                            war.getSideA().addAll(util.createNonStateEntity(data[9]));
                        }
                        if(util.checkIsNotUnknown(data[10])){
                            war.getSideA().addAll(util.createNonStateEntity(data[10]));
                        }

                        war.setStartDates(new ArrayList<>());
                        war.getStartDates().add(util.getDateFromString(data[13], data[12], data[11]));

                        war.setEndDates(new ArrayList<>());
                        war.getEndDates().add(util.getDateFromString(data[16], data[15], data[14]));

                        war.setInitiator(util.initiatorIsA(data[17]));
                        war.setTransFrom(Integer.valueOf(data[18]));
                        war.setTransTo(Integer.valueOf(data[19]));
                        war.setOutcome(util.findWarOutcome(Integer.valueOf(data[20])));
                        war.setSideADeaths(Long.valueOf(data[21]));
                        war.setSideBDeaths(Long.valueOf(data[22]));
                        war.setTotalCompatDeaths(Long.valueOf(data[23].replaceAll("\"","")));

                        warRepository.save(war);
                    }
                });
            } catch (Exception e) {
                log.error("Error occurred while streaming CSV file content", e);
            }

            log.info("Finished processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());

            super.run(event);
        }
    }
}
