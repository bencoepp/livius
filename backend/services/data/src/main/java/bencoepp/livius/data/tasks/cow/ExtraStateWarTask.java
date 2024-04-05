package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.state.State;
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
import java.util.Date;
import java.util.List;

import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

@Component
@Slf4j
public class ExtraStateWarTask extends Task {
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

    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.extra-state-war.csv')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();

        boolean skip = false;

        for (String dependency : job.getDependencies()){
            Job depJob = jobRepository.findByName(dependency);
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
                    if(!line.contains("WarNum,WarName,WarType,ccode1,SideA,ccode2,SideB,StartMonth1,StartDay1,StartYear1,EndMonth1,EndDay1,EndYear1,StartMonth2,StartDay2,StartYear2,EndMonth2,EndDay2 ,EndYear2,Initiator,Interven,TransFrom,Outcome,TransTo,WhereFought,BatDeath,NonStateDeaths,Version")){
                        String[] data = line.split(",");
                        War war = new War();
                        war.setId(sequenceGeneratorService.getSequenceNumber(War.SEQUENCE_NAME));

                        if(data[2].equals("2")){
                            war.setType(War.TYPE_COLONIAL_WAR);
                        }else if(data[2].equals("3")){
                            war.setType(War.TYPE_IMPERIAL_WAR);
                        }else{
                            war.setType(War.TYPE_NONE);
                        }

                        war.setSideA(new ArrayList<>());
                        if(util.checkIsNotUnknown(data[3])){
                            List<State> states = stateRepository.findByCowId(Integer.valueOf(data[3]));
                            war.getSideA().addAll(states);
                        }

                        war.setSideB(new ArrayList<>());
                        if(util.checkIsNotUnknown(data[5])){
                            List<State> states = stateRepository.findByCowId(Integer.valueOf(data[5]));
                            war.getSideB().addAll(states);
                        }

                        war.setStartDates(new ArrayList<>());
                        war.getStartDates().add(util.getDateFromString(data[8], data[7], data[9]));
                        war.getStartDates().add(util.getDateFromString(data[14], data[13], data[15]));

                        war.setEndDates(new ArrayList<>());
                        war.getEndDates().add(util.getDateFromString(data[11], data[10], data[12]));
                        war.getEndDates().add(util.getDateFromString(data[17], data[16], data[18]));

                        war.setInitiator(util.convert0or1ToBoolean(data[19]));
                        war.setIntervene(util.convert0or1ToBoolean(data[20]));
                        war.setTransFrom(Integer.valueOf(data[21]));
                        war.setOutcome(util.findWarOutcome(Integer.valueOf(data[22])));
                        war.setTransTo(Integer.valueOf(data[23]));

                        switch (data[24]) {
                            case "1" -> war.setRegion(War.REGION_WEST_HEMISPHERE);
                            case "2" -> war.setRegion(War.REGION_EUROPE);
                            case "4" -> war.setRegion(War.REGION_AFRIKA);
                            case "6" -> war.setRegion(War.REGION_MIDDLE_EAST);
                            case "7" -> war.setRegion(War.REGION_ASIA);
                            case "9" -> war.setRegion(War.REGION_OCEANIA);
                        }

                        war.setTotalCompatDeaths(Long.valueOf(data[25]));
                        war.setNonStateDeaths(data[26]);

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
