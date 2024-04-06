package bencoepp.livius.data.tasks.weather;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.weather.Weather;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.weather.WeatherRepository;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.Instant;

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

    @Override
    @EventListener(condition = "#event.type.equals('weather')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();
        job.setStatus(Job.STATUS_EXECUTING);
        job.setStarted(Instant.now());
        jobRepository.save(job);
        downloadCsvFiles(job.getUrl());
        super.run(event);
    }

    /**
     * Downloads CSV files from a given directory URL.
     *
     * @param url the url of the path
     */
    private void downloadCsvFiles(String url) {
        String completeUrl = url;

        try {
            Document doc = Jsoup.connect(completeUrl).get();

            Elements linksOnPage = doc.select("a[href]");
            log.debug("Number of links on the page: " + linksOnPage.size());

            for (Element page : linksOnPage) {
                String href = page.attr("href");
                if (href.endsWith(".csv")) {
                    log.debug("CSV file found: " + href);
                    streamCSVFileContent(completeUrl + href);
                }
            }
        } catch (IOException e) {
            log.error("Error occurred while fetching CSV files", e);
        }
    }

    /**
     * Streams the content of a CSV file from the given URL and creates and saves WeatherObject instances.
     *
     * @param csvUrl the URL of the CSV file to stream
     */
    private void streamCSVFileContent(String csvUrl) {
        log.debug("Processing CSV file: " + csvUrl);
        try (BufferedReader bufferedReader =
                     new BufferedReader(new InputStreamReader(new URL(csvUrl).openStream()))) {
            bufferedReader.lines().forEach(line -> createAndSaveWeatherObject(line, csvUrl));
        } catch (IOException e) {
            log.error("Error occurred while streaming CSV file content", e);
        }
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
