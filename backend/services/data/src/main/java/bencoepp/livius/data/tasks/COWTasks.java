package bencoepp.livius.data.tasks;

import bencoepp.livius.utils.COWUtil;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.CompletableFuture;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

@Component
@Slf4j
public class COWTasks {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

    @Autowired
    private COWUtil util;

    /**
     * This method is triggered when the application is ready to start importing data.
     * It downloads the source files from correlatesofwar.org concurrently and saves them with specified filenames.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void startImport(){
        log.info("Downloading sources from correlatesofwar.org");

        Map<String, String> sources = util.getAllPropWithPrefix("livius.cow");
        log.info("Gathered urls of cow sources");

        // Use CompletableFuture to make downloadSource method be invoked concurrently.
        sources.forEach((key, value) -> CompletableFuture.runAsync(() -> {
            try {
                String filename = value.substring(value.lastIndexOf("/") + 1);
                downloadSource(value, key + filename.substring(filename.lastIndexOf(".")));
            } catch (IOException e) {
                log.error("Error occurred while downloading file from {}", value, e);
            }
        }));

        log.info("Finished importing cow data");
    }

    /**
     * Downloads the source file from the given URL and saves it with the specified filename.
     *
     * @param url      the URL of the source file to download
     * @param filename the filename to save the downloaded file as
     * @throws IOException if an I/O error occurs while downloading or saving the file
     */
    private void downloadSource(String url, String filename) throws IOException {
        try (InputStream in = new URL(url).openStream()) {
            Files.createDirectories(Path.of(System.getProperty("user.dir") + "/cow/downloaded"));
            Files.copy(in, Path.of(System.getProperty("user.dir") + "/cow/downloaded/" + filename), StandardCopyOption.REPLACE_EXISTING);
            log.info("Downloaded file from {}", url);
        }
    }
}