package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.state.TerritorialChange;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.state.StateRepository;
import bencoepp.livius.repositories.state.TerritorialChangeRepository;
import bencoepp.livius.utils.COWUtil;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

/**
 * The TerritorialChangeTask class represents a task that is executed to process a territorial change job.
 * It extends the Task class.
 * It is responsible for unpacking a .zip file, processing a CSV file, and saving the territorial change data
 * to the TerritorialChangeRepository.
 */
@Component
@Slf4j
public class TerritorialChangeTask extends Task {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    private Job job;
    @Autowired
    private COWUtil util;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private TerritorialChangeRepository territorialChangeRepository;
    @Value("${livius.cow.load-tc}")
    private Boolean startJob;

    /**
     * Runs the job specified in the {@link JobEvent} event.
     *
     * @param event The job event containing the job ID.
     */
    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.tc.zip')")
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
        } else if(skip){
            log.info("Waiting for dependency to finish executing");
        } else {
            job.setStatus(Job.STATUS_EXECUTING);
            job.setStarted(Instant.now());
            jobRepository.save(job);

            log.info("Unpacking .zip File {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());

            try {
                List<File> files = util.unzipZipFile(job.getName());

                File file = files.stream().filter(data -> data.getName().contains("data-0.csv")).findAny().get();
                log.info("Processing CSV file {}", file.getName());

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    bufferedReader.lines().forEach(line -> {
                        if(!line.contains("year,")){
                            line = line.replace(",.,", ",,");
                            String[] data = line.split(",");
                            TerritorialChange change = new TerritorialChange();
                            change.setId(sequenceGeneratorService.getSequenceNumber(TerritorialChange.SEQUENCE_NAME));
                            change.setYear(Integer.parseInt(data[0]));
                            change.setMonth(data[1]);

                            change.setGainingSide(stateRepository.findByCowId(Integer.valueOf(data[2])));
                            change.setTypeOfChangeForGainingSide(util.findTerritoryGainType(Integer.valueOf(data[3])));

                            change.setProcedure(util.findTerritoryExchangeProcedure(Integer.valueOf(data[4])));
                            change.setEntityExchanged(data[5]);

                            change.setContiguityOfUnitExchangedToTheGainingState(data[6]);

                            change.setAreaOfUnitExchangedInSquareKilometers(data[7]);
                            change.setPopulationOfUnitExchanged(data[8]);
                            change.setPortionOfUnitExchanged(data[9]);

                            change.setLosingSide(stateRepository.findByCowId(Integer.valueOf(data[10])));
                            change.setTypeOfChangeForLosingSide(util.findTerritoryGainType(Integer.valueOf(data[11])));
                            change.setContiguityOfUnitExchangedToTheLosingState(data[12]);

                            change.setSystemEntry(Integer.valueOf(data[13]));
                            change.setSystemExit(Integer.valueOf(data[14]));
                            change.setTerritorialChangeNumber(Integer.valueOf(data[15]));

                            change.setIndependence(util.convert0or1ToBoolean(data[16]));
                            change.setConflict(Integer.valueOf(data[17]));

                            territorialChangeRepository.save(change);
                        }
                    });
                }catch (Exception e){
                    log.error("Error occurred while streaming CSV file content", e);
                }
            }catch (IOException e){
                log.error("Not able to unpack zip:", e);
            }
            super.run(event);
        }
    }
}
