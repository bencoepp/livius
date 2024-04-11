package bencoepp.livius.cow.controller;

import bencoepp.livius.entities.war.War;
import bencoepp.livius.repositories.war.WarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

/**
 * The WarController class is a REST controller that handles HTTP requests related to War objects.
 * It defines two API endpoints for retrieving War objects from a MongoDB database.
 *
 * @RestController - Indicates that this class is a REST controller and can handle HTTP requests.
 * @RequestMapping("/api/v1/cow/war") - Specifies the base URL path for the API endpoints defined in this class.
 */
@RestController
@RequestMapping("/api/v1/cow/war")
public class WarController {

    @Autowired
    private WarRepository warRepository;

    /**
     * Retrieves a War object by its ID.
     *
     * @param id the ID of the War object to retrieve
     * @return a ResponseEntity object containing the War object if found, or a not found status if the War object doesn't exist
     */
    @GetMapping("/{id}")
    public ResponseEntity<War> getWarById(@PathVariable String id) {
        Optional<War> war = warRepository.findById(id);
        return war.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves the total count of War objects in the MongoDB database.
     *
     * @return a ResponseEntity object containing the count of War objects
     *
     * @see WarRepository#count()
     */
    @GetMapping("/count/total")
    public ResponseEntity<Long> getWarCount() {
        return ResponseEntity.ok(warRepository.count());
    }
}
