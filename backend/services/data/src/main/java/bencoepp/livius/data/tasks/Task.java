package bencoepp.livius.data.tasks;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.repositories.job.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Optional;

/**
 * The Task class represents a task that is executed when a JobEvent occurs.
 * It is responsible for updating the status and finished timestamp of a Job object
 * based on the jobId specified in the JobEvent.
 */
@Component
@Slf4j
public class Task {
    @Autowired
    private JobRepository jobRepository;

    /**
     * This method is responsible for updating the status and finished timestamp of a Job object based on the jobId specified in the JobEvent.
     *
     * @param jobEvent The JobEvent object representing the event data containing the jobId.
     */
    public void run(JobEvent jobEvent){
        log.info("Finished Task: {}", getClass().getName());
        Optional<Job> job = jobRepository.findById(jobEvent.getJobId());
        if(job.isPresent()){
            job.get().setFinished(Instant.now());
            job.get().setStatus(Job.STATUS_FINISHED);
        }
        jobRepository.save(job.get());
    }
}
