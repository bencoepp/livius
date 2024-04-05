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
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

/**
 * The IntraStateWarTask class is a subclass of the Task class that represents a task for executing an intra-state war job.
 * It is responsible for unpacking a ZIP file, processing a CSV file, and saving the war data to the database.
 * <p>
 * This class is annotated with the Spring Framework's @Component annotation, which marks it as a Spring bean and allows it
 * to be automatically detected and instantiated by the Spring container.
 * <p>
 * This class contains several fields that are autowired using the Spring Framework's @Autowired annotation, which allows
 * them to be automatically injected by the Spring container. These fields include:
 * - jobRepository: A repository for accessing and manipulating job data in the database.
 * - sequenceGeneratorService: A service for generating unique sequence numbers.
 * - warRepository: A repository for accessing and manipulating war data in the database.
 * - util: A utility class for performing various operations.
 * - stateRepository: A repository for accessing and manipulating state data in the database.
 * <p>
 * This class overrides the run() method from the Task class and provides its own implementation. The run() method is executed
 * when a JobEvent with the condition 'livius.cow.intra-state-war.zip' is triggered. It performs the following steps:
 * 1. Retrieves the Job object associated with the event from the job repository.
 **/
@Component
@Slf4j
public class IntraStateWarTask extends Task {
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
     * Run method that executes the job when a JobEvent is triggered.
     *
     * @param event the JobEvent object
     */
    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.intra-state-war.zip')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();

        boolean skip = false;

        for (String dependency : job.getDependencies()) {
            Job depJob = jobRepository.findByName(dependency);
            if (!depJob.getStatus().equals(Job.STATUS_FINISHED)) {
                skip = true;
            }
        }

        if(skip){
            log.info("Waiting for dependency to finish executing");
        }else {
            job.setStatus(Job.STATUS_EXECUTING);
            job.setStarted(Instant.now());
            jobRepository.save(job);

            log.info("Unpacking .zip File {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());

            try {
                List<File> files = util.unzipZipFile(job.getName());

                File file = files.stream().filter(data -> data.getName().contains("data-1.csv")).findAny().get();
                log.info("Processing CSV file {}", file.getName());
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    bufferedReader.lines().forEach(line -> {
                        if (!line.contains("WarNum,WarName,V5Region,WarType,CcodeA,SideA,CcodeB,SideB,Intnl,StartMo1,StartDy1,StartYr1,EndMo1,EndDy1,EndYr1,")) {
                            String[] data = line.split(",");
                            War war = new War();
                            war.setId(sequenceGeneratorService.getSequenceNumber(War.SEQUENCE_NAME));
                            war.setName(data[1]);

                            switch (data[2]) {
                                case "1" -> war.setRegion(War.REGION_NORTH_AMERICA);
                                case "2" -> war.setRegion(War.REGION_SOUTH_AMERICA);
                                case "3" -> war.setRegion(War.REGION_EUROPE);
                                case "4" -> war.setRegion(War.REGION_SUB_SAHARAN_AFRIKA);
                                case "5" -> war.setRegion(War.REGION_MIDDLE_EAST);
                                case "6" -> war.setRegion(War.REGION_ASIA_AND_OCEANIA);
                            }

                            switch (data[3]) {
                                case "4" -> war.setType(War.TYPE_CIVIL_WAR_FOR_CENTRAL_CONTROL);
                                case "5" -> war.setType(War.TYPE_CIVIL_WAR_OVER_LOCAL_ISSUE);
                                case "6" -> war.setType(War.TYPE_REGIONAL_INTERNAL);
                                case "7" -> war.setType(War.TYPE_INTERCOMMUNAL);
                            }

                            war.setIntraStateWar(true);

                            war.setSideA(new ArrayList<>());
                            if(util.checkIsNotUnknown(data[4])){
                                war.getSideA().addAll(stateRepository.findByCowId(Integer.valueOf(data[4])));
                            }else{
                                war.getSideA().addAll(util.createNonStateEntity(data[4]));
                            }

                            war.setSideB(new ArrayList<>());
                            if(util.checkIsNotUnknown(data[6])){
                                war.getSideB().addAll(stateRepository.findByCowId(Integer.valueOf(data[6])));
                            }else{
                                war.getSideB().addAll(util.createNonStateEntity(data[6]));
                            }

                            war.setInternationalized(util.convert0or1ToBoolean(data[8]));

                            war.setStartDates(new ArrayList<>());
                            war.getStartDates().add(util.getDateFromString(data[10], data[9], data[11]));
                            war.getStartDates().add(util.getDateFromString(data[16], data[15], data[17]));
                            war.getStartDates().add(util.getDateFromString(data[22], data[21], data[23]));
                            war.getStartDates().add(util.getDateFromString(data[28], data[27], data[29]));

                            war.setEndDates(new ArrayList<>());
                            war.getEndDates().add(util.getDateFromString(data[13], data[12], data[14]));
                            war.getEndDates().add(util.getDateFromString(data[19], data[18], data[20]));
                            war.getEndDates().add(util.getDateFromString(data[25], data[24], data[26]));
                            war.getEndDates().add(util.getDateFromString(data[31], data[30], data[32]));

                            war.setNumberOfDaysTheWarLasted(data[33]);
                            war.setNumberOfMonthsTheWarLasted(data[34]);
                            war.setTransFrom(Integer.valueOf(data[35]));
                            //war.setInitiator(); TODO figure out how to find that

                            war.setOutcome(util.findWarOutcome(Integer.valueOf(data[37])));
                            try {
                                war.setTransTo(Integer.valueOf(data[38]));
                            }catch (Exception e){
                                log.error("A . was found in the id of the war", e);
                            }
                            war.setSideADeaths(Long.valueOf(data[39]));
                            war.setSideBDeaths(Long.valueOf(data[40]));
                            war.setTotalCompatDeaths(Long.valueOf(data[41]));
                            war.setSideAPeakTotalMilitaryForces(Integer.valueOf(data[42]));
                            war.setSideAPeakTheaterForces(Integer.valueOf(data[43]));
                            war.setSideBPeakTotalMilitaryForces(Integer.valueOf(data[44]));
                            war.setSideBPeakTheaterForces(Integer.valueOf(data[45]));

                            warRepository.save(war);
                        }
                    });

                } catch (Exception e) {
                    log.error("Error occurred while streaming CSV file content", e);
                }

            } catch (IOException e) {
                log.error("Not able to unpack zip:", e);
            }
            super.run(event);
        }
    }
}
