package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.religion.Religion;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.religion.ReligionRepository;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

@Component
@Slf4j
public class ReligionTask extends Task {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    private Job job;
    @Autowired
    private ReligionRepository religionRepository;

    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.wrd.csv')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();
        job.setStatus(Job.STATUS_EXECUTING);
        job.setStarted(Instant.now());
        jobRepository.save(job);

        log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName()))) {
            bufferedReader.lines().forEach(line -> {
                if(!line.contains("year,state,name,chrstprot,chrstcat")) {
                    String[] data = line.split(",");
                    Religion religion = new Religion();
                }
            });
        } catch (Exception e) {
            log.error("Error occurred while streaming CSV file content", e);
        }

        log.info("Finished processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());
        super.run(event);
    }
}
