package bencoepp.livius.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class COWUtil {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public static final String DOWNLOAD_DIR = "/cow/data/";

    /**
     * Retrieves all properties with a given prefix from the environment and returns them as a map.
     *
     * @param prefix the prefix to use for filtering the properties
     * @return a map containing the properties with the given prefix
     */
    public Map<String,String> getAllPropWithPrefix(String prefix) {
        BindResult<Map<String, String>> result = Binder.get(applicationContext.getEnvironment())
                .bind(prefix, Bindable.mapOf(String.class, String.class));
        if (!result.isBound() || result.get()==null) {
            return Collections.emptyMap();
        }
        return result.get().entrySet().stream().collect(Collectors.toMap(x->prefix+"."+x.getKey(), Map.Entry::getValue));
    }

    /**
     * Downloads the source file from the given URL and saves it with the specified filename.
     *
     * @param url      the URL of the source file to download
     * @param filename the filename to save the downloaded file as
     * @throws IOException if an I/O error occurs while downloading or saving the file
     */
    public void downloadSource(String url, String filename) throws IOException {
        try (InputStream in = new URL(url).openStream()) {
            Files.createDirectories(Path.of(System.getProperty("user.dir") + DOWNLOAD_DIR));
            Files.copy(in, Path.of(System.getProperty("user.dir") + DOWNLOAD_DIR + filename), StandardCopyOption.REPLACE_EXISTING);
            log.info("Downloaded file from {}", url);
        }
    }
}
