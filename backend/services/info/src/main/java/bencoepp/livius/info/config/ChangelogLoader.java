package bencoepp.livius.info.config;

import bencoepp.livius.entities.info.Changelog;
import bencoepp.livius.info.InfoService;
import bencoepp.livius.repositories.info.ChangelogRepository;
import bencoepp.livius.utils.SequenceGeneratorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The ChangelogLoader class is responsible for initializing changelog documents in the database
 * and retrieving the contents of resource files.
 * It uses the ChangelogRepository to interact with the "changelogs" collection in the database.
 */
@Service
public class ChangelogLoader {
    @Autowired
    private ChangelogRepository changelogRepository;
    @Autowired
    private SequenceGeneratorService sequenceGenerator;

    /**
     * This method initializes the changelog documents in the database.
     * It reads the changelogs from a JSON file, deletes any existing changelogs in the database, and saves the new changelogs.
     *
     * @throws JsonProcessingException If there is an error processing the JSON file
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initChangelogs() throws JsonProcessingException {
        String input = getResourceFileAsString("changelogs.json");
        ObjectMapper mapper = new ObjectMapper();
        List<Changelog> changelogs = mapper.readValue(input, new TypeReference<>() {
        });

        changelogRepository.deleteAll();
        for (Changelog changelog : changelogs) {
            changelog.setId(sequenceGenerator.getSequenceNumber(Changelog.SEQUENCE_NAME));
            changelogRepository.save(changelog);
        }
    }

    /**
     * This method retrieves the contents of a resource file as a string.
     *
     * @param fileName The name of the resource file
     * @return The contents of the resource file as a string
     * @throws RuntimeException If the resource file is not found
     */
    public static String getResourceFileAsString(String fileName) {
        InputStream is = getResourceFileAsInputStream(fileName);
        if (is != null) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        } else {
            throw new RuntimeException("resource not found");
        }
    }

    /**
     * Retrieves the resource file as an input stream.
     *
     * @param fileName The name of the resource file
     * @return The input stream of the resource file
     */
    public static InputStream getResourceFileAsInputStream(String fileName) {
        ClassLoader classLoader = InfoService.class.getClassLoader();
        return classLoader.getResourceAsStream(fileName);
    }
}
