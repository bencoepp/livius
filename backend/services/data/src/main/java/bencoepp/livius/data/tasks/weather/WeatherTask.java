package bencoepp.livius.data.tasks.weather;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.state.Country;
import bencoepp.livius.entities.weather.Weather;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.weather.WeatherRepository;
import bencoepp.livius.utils.SequenceGeneratorService;
import bencoepp.livius.utils.WeatherUtil;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;

import static bencoepp.livius.utils.WeatherUtil.DOWNLOAD_DIR;

/**
 * The WeatherTask class processes weather-related events.
 * It extends the Task class and overrides the run method to perform the necessary operations for processing weather events.
 */
@Component
@Slf4j
public class WeatherTask extends Task {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    private Job job;
    @Autowired
    private WeatherRepository weatherRepository;
    private static final String BASE_URL = "https://www.ncei.noaa.gov/data/global-hourly/access/";
    @Autowired
    private WeatherUtil util;

    /**
     * Runs the job for processing weather-related events.
     *
     * @param event The JobEvent object containing event details.
     */
    @Override
    @EventListener(condition = "#event.type.equals('weather')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();
        job.setStatus(Job.STATUS_EXECUTING);
        job.setStarted(Instant.now());
        jobRepository.save(job);

        try {
            Document doc = Jsoup.connect(job.getUrl()).get();

            Elements linksOnPage = doc.select("a[href]");
            log.debug("Number of links on the page: " + linksOnPage.size());
            for (Element page : linksOnPage) {
                String href = page.attr("href");
                if (href.endsWith(".csv")) {
                    log.info("CSV file found: {}", job.getUrl() + href);
                    util.downloadCSV(job.getUrl() + href, job.getName(), href);
                    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName() + "/" + href))) {
                        bufferedReader.lines().forEach(line -> {
                            createAndSaveWeatherObject(line, job.getUrl());
                        });
                    } catch (Exception e) {
                        log.error("Error occurred while streaming CSV file content", e);
                    }
                }
            }
        } catch (IOException e) {
            log.error("Error occurred while fetching CSV files", e);
        }

        super.run(event);
    }

    /**
     * Creates a Weather object from the given line and saves it to the weather repository.
     * If the line contains 'LATITUDE', the method immediately returns without creating the object.
     *
     * @param line the line from which to create the Weather object
     */
    private void createAndSaveWeatherObject(String line, String url) {
        if(line.contains("LATITUDE")){
            log.debug("Line contains 'LATITUDE'");
            return;
        }
        Weather weather = new Weather(line);
        weather.setId(sequenceGeneratorService.getSequenceNumber(Weather.SEQUENCE_NAME));
        log.debug("Created Weather object");
        weather.setCreated(Instant.now());
        weather.setUpdated(Instant.now());
        weather.setAccessDate(Instant.now().toString());
        weather.setCitation("NOAA National Centers for Environmental Information (2001): Global Surface Hourly [indicate subset used]. NOAA National Centers for Environmental Information. " + weather.getAccessDate());
        weather.setDatasetUrl(url);
        weatherRepository.save(weather);
        log.debug("Weather object saved");
    }
}
