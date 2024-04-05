package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.state.State;
import bencoepp.livius.repositories.job.JobRepository;
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
import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

/**
 * The StateTask class is responsible for executing a job for processing CSV files. It reads the contents of the CSV file,
 * creates State objects for each line, and saves them in the state repository.
 */
@Component
@Slf4j
public class StateTask extends Task {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    private Job job;
    @Autowired
    private StateRepository stateRepository;

    /**
     * Executes the job for processing CSV files. Reads the contents of the CSV file, creates State objects for each line
     * and saves them in the state repository.
     *
     * @param event The JobEvent object representing the event triggering the job execution.
     */
    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.states.csv')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();
        job.setStatus(Job.STATUS_EXECUTING);
        job.setStarted(Instant.now());
        jobRepository.save(job);

        log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());

        stateRepository.deleteAll();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName()))) {
            bufferedReader.lines().forEach(line -> {
                if(!line.contains(",ccode,statenme,")){
                    try {
                        State state = new State(line);
                        state.setId(sequenceGeneratorService.getSequenceNumber(State.SEQUENCE_NAME));
                        state.setCreated(Instant.now());
                        state.setUpdated(Instant.now());

                        state.setAccessTime(Instant.now());
                        state.setCitation("Correlates of War Project. 2017. “State System Membership List, v2016.” Online, http://correlatesofwar.org");
                        state.setAuthors(new String[]{
                                "Volker Krause, Eastern Michigan University",
                                "Phil Schafer, University of Michigan",
                                "Karen Ruth Adams, University of Montan"
                        });
                        state.setFaqEmail("vkrause@emich.edu");
                        stateRepository.save(state);
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
