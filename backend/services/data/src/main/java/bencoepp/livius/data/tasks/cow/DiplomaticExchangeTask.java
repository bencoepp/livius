package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.state.DiplomaticExchange;
import bencoepp.livius.entities.state.MajorPower;
import bencoepp.livius.entities.state.State;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.state.DiplomaticExchangeRepository;
import bencoepp.livius.repositories.state.StateRepository;
import bencoepp.livius.utils.COWUtil;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import java.util.ArrayList;
import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

/**
 * The DiplomaticExchangeTask class represents a task that processes a CSV file containing diplomatic exchange data
 * and saves it to a MongoDB database. It extends the Task class, which updates the status and finished timestamp of a Job object.
 */
@Component
@Slf4j
public class DiplomaticExchangeTask extends Task {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    private Job job;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private DiplomaticExchangeRepository diplomaticExchangeRepository;
    @Autowired
    private COWUtil util;
    @Value("${livius.cow.load-de}")
    private Boolean startJob;

    /**
     * Executes the job based on the given event.
     *
     * @param event the JobEvent that triggered the execution
     */
    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.de.csv')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();

        boolean skip = false;

        for (String dependency : job.getDependencies()) {
            Job depJob = jobRepository.findByName(dependency);
            if (!depJob.getStatus().equals(Job.STATUS_FINISHED)) {
                skip = true;
            }
        }

        if(!startJob){
            log.info("Job will be skipped");
        } else if (skip) {
            log.info("Waiting for dependency to finish executing");
        } else {
            job.setStatus(Job.STATUS_EXECUTING);
            job.setStarted(Instant.now());
            jobRepository.save(job);

            log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName()))) {
                bufferedReader.lines().forEach(line -> {
                    if(!line.contains("ccode1")){
                        try {
                            String[] data = line.split(",");
                            DiplomaticExchange exchange = new DiplomaticExchange();
                            exchange.setId(sequenceGeneratorService.getSequenceNumber(DiplomaticExchange.SEQUENCE_NAME));
                            exchange.setYear(util.convertStringToInteger(data[2]));
                            exchange.setSideA(stateRepository.findByCowId(Integer.valueOf(data[0])));
                            exchange.setSideB(stateRepository.findByCowId(Integer.valueOf(data[1])));
                            exchange.setDiplomaticRepresentationLevelSideA(util.findDiplomaticRepresentationLevel(Integer.valueOf(data[3])));
                            exchange.setDiplomaticRepresentationLevelSideB(util.findDiplomaticRepresentationLevel(Integer.valueOf(data[4])));
                            exchange.setAnyDiplomaticExchange(util.findAnyDiplomaticExchange(Integer.valueOf(data[5])));

                            diplomaticExchangeRepository.save(exchange);
                        } catch (Exception e) {
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
