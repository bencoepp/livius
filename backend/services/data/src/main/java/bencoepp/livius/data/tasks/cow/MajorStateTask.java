package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.state.MajorPower;
import bencoepp.livius.entities.state.State;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.state.MajorPowerRepository;
import bencoepp.livius.repositories.state.StateRepository;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.ParseException;
import java.time.Instant;
import java.util.List;

import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

/**
 * The MajorStateTask class extends the Task class and represents a task that executes a job when a JobEvent
 * is triggered with the condition "livius.cow.majors.csv". It reads a CSV file, creates MajorPower and State
 * instances, and saves them to the database. It checks for dependencies that are not finished executing and
 * waits until they are finished before running the job.
 */
@Component
@Slf4j
public class MajorStateTask extends Task {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    private Job job;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private MajorPowerRepository majorPowerRepository;

    /**
     * Executes the job when a JobEvent is triggered with the condition "livius.cow.majors.csv".
     * This method reads a CSV file, creates MajorPower and State instances, and saves them to the database.
     * If there are any dependencies that are not finished executing, the method will wait until they are finished.
     *
     * @param event The JobEvent triggering the execution of the job.
     */
    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.majors.csv')")
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
        }else{
            job.setStatus(Job.STATUS_EXECUTING);
            job.setStarted(Instant.now());
            jobRepository.save(job);

            log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName()))) {
                bufferedReader.lines().forEach(line -> {
                    if(!line.contains("stateabb,ccode,styear,stmonth,stday,endyear,endmonth,endday,version")){
                        try {
                            MajorPower power = new MajorPower(line);
                            power.setId(sequenceGeneratorService.getSequenceNumber(State.SEQUENCE_NAME));
                            power.setCreated(Instant.now());
                            power.setUpdated(Instant.now());

                            power.setAccessTime(Instant.now());
                            power.setCitation("Correlates of War Project. 2017. “State System Membership List, v2016.” Online, http://correlatesofwar.org");
                            power.setAuthors(new String[]{
                                    "Volker Krause, Eastern Michigan University",
                                    "Phil Schafer, University of Michigan",
                                    "Karen Ruth Adams, University of Montan"
                            });
                            power.setFaqEmail("vkrause@emich.edu");
                            majorPowerRepository.save(power);

                            List<State> states = stateRepository.findByCowIdAndCode(power.getCowId(), power.getState());
                            for (State state : states){
                                state.getWasMajorPower().add(power);
                                stateRepository.save(state);
                            }
                        } catch (ParseException e) {
                            log.error("Error occurred while creating a state instance", e);
                        }
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
