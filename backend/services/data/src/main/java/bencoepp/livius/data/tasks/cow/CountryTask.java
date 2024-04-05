package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.state.Country;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.state.CountryRepository;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

/**
 * The CountryTask class is responsible for processing a CSV file containing country information, and saving
 * the data to the MongoDB database.
 */
@Component
@Slf4j
public class CountryTask extends Task {

    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    private Job job;
    /**
     * Runs the task to process a CSV file containing country information and save the data to the MongoDB database.
     *
     * @param event The JobEvent object containing the event details.
     */
    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.country-codes.csv')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();
        job.setStatus(Job.STATUS_EXECUTING);
        job.setStarted(Instant.now());
        jobRepository.save(job);

        log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName()))) {
            bufferedReader.lines().forEach(line -> {
                if(!line.contains(",CCode,")){
                    Country country = new Country(line);
                    if(!countryRepository.existsByCodeOrCowId(country.getCode(), country.getCowId())){
                        country.setId(sequenceGeneratorService.getSequenceNumber(Country.SEQUENCE_NAME));
                        country.setCreated(Instant.now());
                        country.setUpdated(Instant.now());

                        country.setAccessTime(Instant.now());
                        country.setUrl("https://correlatesofwar.org/data-sets/cow-country-codes-2/");
                        countryRepository.save(country);
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
