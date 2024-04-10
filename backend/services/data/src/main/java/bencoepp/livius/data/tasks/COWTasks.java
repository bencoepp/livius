package bencoepp.livius.data.tasks;

import bencoepp.livius.entities.job.Job;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.utils.COWUtil;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.Map;

/**
 * This class represents the tasks to be performed in the application related to Correlates of War (COW) data.
 */
@Component
@Slf4j
public class COWTasks {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private COWUtil util;
    @Autowired
    private JobRepository jobRepository;

    /**
     * This method is triggered when the application is ready to start importing data.
     * It downloads the source files from correlatesofwar.org concurrently and saves them with specified filenames.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void run(){
        log.info("Downloading sources from correlatesofwar.org");

        Map<String, String> sources = util.getAllPropWithPrefix("livius.cow");
        log.info("Gathered urls of cow sources");

        sources.forEach((key, value) -> CompletableFuture.runAsync(() -> {
            String filename = value.substring(value.lastIndexOf("/") + 1);
            String file = key + filename.substring(filename.lastIndexOf("."));

            try {
                util.downloadSource(value, file);

                List<String> dependencies = new ArrayList<>();
                if(key.equals("livius.cow.majors")){
                    dependencies.add("livius.cow.states.csv");
                }

                if(key.equals("livius.cow.non-state-war") || key.equals("livius.cow.intra-state-war") || key.equals("livius.cow.extra-state-war")){
                    dependencies.add("livius.cow.states.csv");
                }

                if(key.equals("livius.cow.wrdv")){
                    dependencies.add("livius.cow.states.csv");
                }

                if(key.equals("livius.cow.de")){
                    dependencies.add("livius.cow.states.csv");
                }

                if(key.equals("livius.cow.tc")){
                    dependencies.add("livius.cow.states.csv");
                }

                Job job = new Job(
                        sequenceGeneratorService.getSequenceNumber(Job.SEQUENCE_NAME),
                        file,
                        value,
                        Job.TYPE_COW,
                        Job.STATUS_SCHEDULED,
                        dependencies,
                        Instant.now()
                );
                if(!jobRepository.existsByName(file)){
                    jobRepository.save(job);
                }
            } catch (IOException e) {
                log.error("An error has happened while downloading content");
            }
        }));
    }
}