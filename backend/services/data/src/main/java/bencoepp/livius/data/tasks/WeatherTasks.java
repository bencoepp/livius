package bencoepp.livius.data.tasks;

import bencoepp.livius.entities.weather.Weather;
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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
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

    /**
     * This method is responsible for importing weather data.
     * It is triggered on application startup by listening for the {@link ApplicationReadyEvent} event.
     * It will log the starting of the import process and fetch a list of subdirectories from the specified {@link #BASE_URL}.
     * The number of found subdirectories (years) will be logged as well.
     * For each subdirectory, it will download CSV files by calling the {@link #downloadCsvFiles(String)} method.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void importWeatherData() {
        log.info("Weather data import started");

        List<String> directories = getSubDirectories(BASE_URL);
        log.info("Number of years found: " + directories.size());

        if(weatherRepository.count() != 0){
            String lastYear = findLastYearImported();
        }else{
            directories.forEach(directory -> {
                log.debug("Processing directory: " + directory);
                downloadCsvFiles(directory);
            });
        }

        log.info("Weather data finished importing");
    }

    private String findLastYearImported() {

       return ";";
    }

    /**
     * Downloads CSV files from a given directory URL.
     *
     * @param directory the directory path relative to the BASE_URL
     */
    private void downloadCsvFiles(String directory) {
        String completeUrl = BASE_URL + directory;

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
            bufferedReader.lines().forEach(line -> createAndSaveWeatherObject(line));
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
    private void createAndSaveWeatherObject(String line) {
        if(line.contains("LATITUDE")){
            log.debug("Line contains 'LATITUDE'");
            return;
        }
        Weather weather = new Weather(line);
        weather.setId(sequenceGeneratorService.getSequenceNumber(Weather.SEQUENCE_NAME));
        log.debug("Created Weather object");
        weatherRepository.save(weather);
        log.debug("Weather object saved");
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