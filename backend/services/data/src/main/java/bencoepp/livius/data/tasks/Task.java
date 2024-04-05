package bencoepp.livius.data.tasks;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.repositories.job.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Optional;

@Component
@Slf4j
public class Task {
    @Autowired
    private JobRepository jobRepository;

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
