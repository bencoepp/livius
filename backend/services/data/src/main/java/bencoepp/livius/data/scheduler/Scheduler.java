package bencoepp.livius.data.scheduler;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.repositories.job.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * The Scheduler class is responsible for periodically running jobs that are scheduled in the JobRepository.
 * It retrieves all Job objects with a status of "scheduled" and publishes a JobEvent for each job.
 */
@Component
@Slf4j
public class Scheduler {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    /**
     * This method is executed periodically according to the specified fixed delay of 5000 milliseconds.
     * It retrieves all Job objects from the jobRepository and publishes a JobEvent for each job
     * that has a status of "scheduled".
     *
     * <p>
     * Example usage:
     * </p>
     *
     * <pre>{@code
     * private void run(){
     *     for (Job job : jobRepository.findAll()){
     *         if(job.getStatus().equals(Job.STATUS_SCHEDULED)){
     *             applicationEventPublisher.publishEvent(new JobEvent(this, job.getName(), job.getId()));
     *         }
     *     }
     * }
     * }</pre>
     */
    @Scheduled(fixedRate = 5000)
    private void run(){
        for (Job job : jobRepository.findAll()){
            if(job.getStatus().equals(Job.STATUS_SCHEDULED)){
                applicationEventPublisher.publishEvent(new JobEvent(this, job.getName(), job.getId(), job.getType()));
            }
        }
    }
}
