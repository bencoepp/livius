package bencoepp.livius.info.controller;

import bencoepp.livius.entities.info.Changelog;
import bencoepp.livius.repositories.info.ChangelogRepository;
import bencoepp.livius.utils.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

/**
 * The ChangelogController class handles the HTTP requests related to the changelog functionality.
 * It is a RESTful API controller that maps the URLs to the corresponding methods to retrieve changelog information from the database.
 */
@RestController
@RequestMapping("/api/v1/info/changelog")
public class ChangelogController {

    @Autowired
    private ChangelogRepository changelogRepository;

    /**
     * Retrieves all changelogs from the database.
     *
     * @return ResponseEntity<List < Changelog>> - The HTTP response containing a list of changelogs.
     */
    @GetMapping("/all")
    public ResponseEntity<List<Changelog>> getAll() {
        return ResponseEntity.ok(changelogRepository.findAll());
    }

    /**
     * Retrieves a changelog entry based on the provided ID.
     *
     * @param id The ID of the changelog entry
     * @return ResponseEntity<Changelog> - The HTTP response containing the changelog entry if found,
     *         otherwise a not found response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Changelog> getById(@PathVariable String id) {
        Optional<Changelog> changelog = changelogRepository.findById(id);
        return changelog.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves the newest changelogs based on the specified amount.
     *
     * @param amount The amount of newest changelogs to retrieve.
     * @return ResponseEntity<List < Changelog>> - The HTTP response containing a list of newest changelogs.
     */
    @GetMapping("/newest/{amount}")
    public ResponseEntity<List<Changelog>> getNewest(@PathVariable int amount) {
        List<Changelog> changelogs = changelogRepository.findByCreatedNotNullOrderByIdDesc();
        return ResponseEntity.ok(changelogs.subList(0, amount));
    }
}
