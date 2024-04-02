package bencoepp.livius.data.tasks;

import bencoepp.livius.data.events.COWFileDownloadedEvent;
import bencoepp.livius.entities.state.Country;
import bencoepp.livius.repositories.state.CountryRepository;
import bencoepp.livius.utils.COWUtil;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;
import java.util.Map;

import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

@Component
@Slf4j
public class COWTasks {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private COWUtil util;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private CountryRepository countryRepository;

    /**
     * This method is triggered when the application is ready to start importing data.
     * It downloads the source files from correlatesofwar.org concurrently and saves them with specified filenames.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void run(){
        log.info("Downloading sources from correlatesofwar.org");

        Map<String, String> sources = util.getAllPropWithPrefix("livius.cow");
        log.info("Gathered urls of cow sources");

        // Use CompletableFuture to make downloadSource method be invoked concurrently.
        sources.forEach((key, value) -> CompletableFuture.runAsync(() -> {
            try {
                String filename = value.substring(value.lastIndexOf("/") + 1);
                // Check if the file was already downloaded
                if(!Files.exists(Path.of(System.getProperty("user.dir") + DOWNLOAD_DIR + filename))){
                    String file = key + filename.substring(filename.lastIndexOf("."));
                    util.downloadSource(value, file);
                    COWFileDownloadedEvent event = new COWFileDownloadedEvent(this, file);
                    applicationEventPublisher.publishEvent(event);
                }
            } catch (IOException e) {
                log.error("Error occurred while downloading file from {}", value, e);
            }
        }));
    }

    /**
     * Imports country codes from a CSV file into the system.
     *
     * <p>
     * This method is triggered when a {@link COWFileDownloadedEvent} is fired with a file name indicating that
     * the file to be processed is the "livius.cow.country-codes.csv" file.
     * </p>
     *
     * <p>
     * The method processes the CSV file line by line, skipping lines that contain the string ",CCode,".
     * For each line, a {@link Country} object is created using the line data, and it is checked whether a country
     * with the same code or cowId already exists in the system. If not, the country is saved to the database
     * with a generated ID, and the created and updated timestamps set to the current instant.
     * </p>
     *
     * <p>
     * Note: If an exception occurs while streaming the CSV file content, an error message will be logged.
     * </p>
     *
     * @param event the {@link COWFileDownloadedEvent} that triggered the method
     *
     * @see COWFileDownloadedEvent
     * @see Country
     * @see CountryRepository#existsByCodeOrCowId(String, Integer)
     * @see SequenceGeneratorService#getSequenceNumber(String)
     */
    @EventListener(condition = "#event.file.equals('livius.cow.country-codes.csv')")
    public void importCountryCodes(COWFileDownloadedEvent event){
        log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile()))) {
            bufferedReader.lines().forEach(line -> {
                if(!line.contains(",CCode,")){
                    Country country = new Country(line);
                    if(!countryRepository.existsByCodeOrCowId(country.getCode(), country.getCowId())){
                        country.setId(sequenceGeneratorService.getSequenceNumber(Country.SEQUENCE_NAME));
                        country.setCreated(Instant.now());
                        country.setUpdated(Instant.now());
                        countryRepository.save(country);
                    }
                }
            });
        } catch (Exception e) {
            log.error("Error occurred while streaming CSV file content", e);
        }
    }
}