package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.state.MajorPower;
import bencoepp.livius.entities.state.State;
import bencoepp.livius.entities.war.War;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.state.StateRepository;
import bencoepp.livius.repositories.war.WarRepository;
import bencoepp.livius.utils.COWUtil;
import bencoepp.livius.utils.SequenceGeneratorService;
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
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

                        String warType = War.TYPE_NONE;

                        if(data[2].equals("2")){
                            warType = War.TYPE_COLONIAL_WAR;
                        }else if(data[2].equals("3")){
                            warType = War.TYPE_IMPERIAL_WAR;
                        }

                        List<State> sideA = new ArrayList<>();

                        if(util.checkIsNotUnknown(data[3])){
                            List<State> states = stateRepository.findByCowIdAndName(Integer.valueOf(data[3]), data[4]);
                            sideA.addAll(states);
                        }

                        List<State> sideB = new ArrayList<>();

                        if(util.checkIsNotUnknown(data[5])){
                            try{
                                List<State> states = stateRepository.findByCowIdAndName(Integer.valueOf(data[5]), data[6]);
                                sideA.addAll(states);
                            }catch (NumberFormatException e){
                                log.error("Exception was through for the following item: id->" + data[5] + " name->" + data[6]);
                            }
                        }

                        List<Date> start = new ArrayList<>();

                        start.add(util.getDateFromString(data[8], data[7], data[9]));
                        if(util.checkIsNotUnknown(data[15])){
                            start.add(util.getDateFromString(data[14], data[13], data[15]));
                        }

                        List<Date> end = new ArrayList<>();

                        end.add(util.getDateFromString(data[11], data[10], data[12]));
                        if(util.checkIsNotUnknown(data[18])){
                            end.add(util.getDateFromString(data[17], data[16], data[18]));
                        }

                        War war = new War(
                                sequenceGeneratorService.getSequenceNumber(War.SEQUENCE_NAME),
                                Integer.valueOf(data[0]),
                                data[1],
                                warType,
                                sideA,
                                sideB,
                                start,
                                end,
                                util.convert0or1ToBoolean(data[19]),
                                Integer.valueOf(data[23]),
                                Integer.valueOf(data[21]),
                                util.findWarOutcome(Integer.valueOf(data[22])),
                                util.convert0or1ToBoolean(data[20]),
                                data[24],
                                data[26],
                                Long.valueOf(data[25])
                        );

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
