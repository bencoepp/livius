package bencoepp.livius.data.tasks;

import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.weather.Weather;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.weather.WeatherRepository;
import bencoepp.livius.utils.SequenceGeneratorService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * The WeatherTasks class is responsible for importing weather data.
 * It fetches data from a specified URL and saves it to a weather repository.
 */
@Component
@Slf4j
public class WeatherTasks {

    private static final String BASE_URL = "https://www.ncei.noaa.gov/data/global-hourly/access/";

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private JobRepository jobRepository;

    /**
     * This method is responsible for importing weather data.
     * It is triggered on application startup by listening for the {@link ApplicationReadyEvent} event.
     * It will log the starting of the import process and fetch a list of subdirectories from the specified {@link #BASE_URL}.
     * The number of found subdirectories (years) will be logged as well.
     * For each subdirectory, it will download CSV files by calling the {@link #downloadCsvFiles(String)} method.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void run(){
        log.info("Weather data import started");

        List<String> directories = getSubDirectories(BASE_URL);
        log.info("Number of years found: " + directories.size());

        for (String directory : directories) {
            Job job = new Job();
            job.setId(sequenceGeneratorService.getSequenceNumber(Job.SEQUENCE_NAME));
            job.setName(directory.replace("/",""));
            job.setUrl(BASE_URL + directory);
            job.setType(Job.TYPE_WEATHER);
            job.setStatus(Job.STATUS_SCHEDULED);
            job.setCreated(Instant.now());
            if(!jobRepository.existsByName(directory)){
                jobRepository.save(job);
            }
        }

        log.info("Finished creating weather jobs");
    }

    /**
     * Retrieves the subdirectories present in the given URL.
     *
     * @param url the URL that contains the subdirectories
     * @return a list of subdirectories found in the given URL
     */
    private List<String> getSubDirectories(String url) {
        List<String> directories = new ArrayList<>();

        try {
            Document doc = Jsoup.connect(url).get();

            Elements linksOnPage = doc.select("a[href]");
            log.debug("Number of links on the page: " + linksOnPage.size());

            for (Element page : linksOnPage) {
                String href = page.attr("href");
                if (href.endsWith("/") && !href.startsWith("/") && !href.equals(url)) {
                    directories.add(href);
                    log.debug("Subdirectory found: " + href);
                }
            }
        } catch (IOException e) {
            log.error("Error occurred while fetching subdirectories", e);
        }

        return directories;
    }
}