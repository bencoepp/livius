package bencoepp.livius.data.scheduler;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.repositories.job.JobRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Scheduler {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;


    @Scheduled(fixedDelay = 5000)
    private void run(){
        for (Job job : jobRepository.findAll()){
            if(job.getStatus().equals(Job.STATUS_SCHEDULED)){
                applicationEventPublisher.publishEvent(new JobEvent(this, job.getName()));
            }
        }
    }
}
