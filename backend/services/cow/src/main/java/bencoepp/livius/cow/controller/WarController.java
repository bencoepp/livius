package bencoepp.livius.cow.controller;

import bencoepp.livius.entities.war.War;
import bencoepp.livius.repositories.war.WarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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

    /**
     * Retrieves all wars from the MongoDB database.
     *
     * @return a ResponseEntity object containing a list of War objects, or an empty list if no wars are found
     *
     * @see WarRepository#findAll()
     */
    @GetMapping("/all")
    public ResponseEntity<List<War>> getAllWars() {
        return ResponseEntity.ok(warRepository.findAll());
    }

    /**
     * Retrieves a list of War objects by the ID of the cow.
     *
     * @param id the ID of the cow
     * @return a ResponseEntity object containing a list of War objects
     */
    @GetMapping("/cow-id/{id}")
    public ResponseEntity<List<War>> getWarsByCowId(@PathVariable Integer id) {
        return ResponseEntity.ok(warRepository.findByCowId(id));
    }

    /**
     * Retrieves a list of War objects by name.
     *
     * @param name the name to search for
     * @return a ResponseEntity object containing a list of War objects that match the given name
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<List<War>> getWarsByName(@PathVariable String name) {
        return ResponseEntity.ok(warRepository.findByName(name));
    }

    /**
     * Retrieves a list of War objects whose name contains the provided text.
     *
     * @param text the text to search for in the names of the War objects
     * @return a ResponseEntity object containing a list of War objects that have names containing the provided text
     */
    @GetMapping("/name/contains/{text}")
    public ResponseEntity<List<War>> getWarsByNameContains(@PathVariable String text) {
        return ResponseEntity.ok(warRepository.findByNameContains(text));
    }

    /**
     * Retrieves a list of War objects by the type of the war.
     *
     * @param type the type of the war
     * @return a ResponseEntity object containing a list of War objects that have the provided type
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<War>> getWarsByType(@PathVariable String type) {
        return ResponseEntity.ok(warRepository.findByType(type));
    }

    /**
     * Retrieves a list of War objects by the region.
     *
     * @param region the region to search for
     * @return a ResponseEntity object containing a list of War objects that are from the provided region
     */
    @GetMapping("/region/{region}")
    public ResponseEntity<List<War>> getWarsByRegion(@PathVariable String region) {
        return ResponseEntity.ok(warRepository.findByRegion(region));
    }

    /**
     * Retrieves a list of War objects by the state of Side A.
     *
     * @param state the state of Side A to search for
     * @return a ResponseEntity object containing a list of War objects that have the provided state for Side A
     */
    @GetMapping("/side-a/{state}")
    public ResponseEntity<List<War>> getWarsByStateSideA(@PathVariable String state) {
        return ResponseEntity.ok(warRepository.findBySideA_Id(state));
    }

    /**
     * Retrieves a list of War objects by the state of Side B.
     *
     * @param state the state of Side B to search for
     * @return a ResponseEntity object containing a list of War objects that have the provided state for Side B
     */
    @GetMapping("/side-b/{state}")
    public ResponseEntity<List<War>> getWarsByStateSideB(@PathVariable String state) {
        return ResponseEntity.ok(warRepository.findBySideB_Id(state));
    }

    /**
     * Retrieves a list of War objects where the initiator is on side A.
     *
     * @return a ResponseEntity object containing a list of War objects where the initiator is on side A
     *
     * @see WarRepository#findByInitiator(boolean)
     */
    @GetMapping("/initiator/side-a")
    public ResponseEntity<List<War>> getWarsByInitiatorSideA() {
        return ResponseEntity.ok(warRepository.findByInitiator(true));
    }

    /**
     * Retrieves a list of War objects where the initiator is on Side B.
     *
     * @return a ResponseEntity object containing a list of War objects where the initiator is on Side B
     *
     * @see WarRepository#findByInitiator(boolean)
     */
    @GetMapping("/initiator/side-b")
    public ResponseEntity<List<War>> getWarsByInitiatorSideB() {
        return ResponseEntity.ok(warRepository.findByInitiator(false));
    }

    /**
     * Retrieves a list of War objects by the ID of the transaction to.
     *
     * @param id the ID of the transaction to search for
     * @return a ResponseEntity object containing a list of War objects that have the provided transaction to ID
     */
    @GetMapping("/trans/to/{id}")
    public ResponseEntity<List<War>> getWarsByTransToId(@PathVariable Integer id) {
        return ResponseEntity.ok(warRepository.findByTransTo(id));
    }

    /**
     * Retrieves a list of War objects by the ID of the transaction from.
     *
     * @param id the ID of the transaction from
     * @return a ResponseEntity object containing a list of War objects that have the provided transaction from ID
     */
    @GetMapping("/trans/from/{id}")
    public ResponseEntity<List<War>> getWarsByTransFromId(@PathVariable Integer id) {
        return ResponseEntity.ok(warRepository.findByTransFrom(id));
    }

    /**
     * Retrieves a list of War objects by the outcome of the war.
     *
     * @param outcome the outcome of the war to search for
     * @return a ResponseEntity object containing a list of War objects that have the provided outcome
     */
    @GetMapping("/outcome/{outcome}")
    public ResponseEntity<List<War>> getWarsByOutcome(@PathVariable String outcome) {
        return ResponseEntity.ok(warRepository.findByOutcome(outcome));
    }

    /**
     * Retrieves a list of War objects that have the provided number of deaths on Side A.
     *
     * @param amount the number of deaths on Side A to search for
     * @return a ResponseEntity object containing a list of War objects that have the provided number of deaths on Side A
     */
    @GetMapping("/side-a/deaths/{amount}")
    public ResponseEntity<List<War>> getWarsBySideADeaths(@PathVariable Long amount) {
        return ResponseEntity.ok(warRepository.findBySideADeaths(amount));
    }

    /**
     * Retrieves a list of War objects that have the number of deaths on Side A above the given amount.
     *
     * @param amount the number of deaths on Side A to search for
     * @return a ResponseEntity object containing a list of War objects that have the number of deaths on Side A above the given amount
     */
    @GetMapping("/side-a/deaths/above/{amount}")
    public ResponseEntity<List<War>> getWarsBySideADeathsAbove(@PathVariable Long amount) {
        return ResponseEntity.ok(warRepository.findBySideADeathsGreaterThanEqual(amount));
    }

    /**
     * Retrieves a list of War objects that have the number of deaths on Side A below the given amount.
     *
     * @param amount the number of deaths on Side A to search for
     * @return a ResponseEntity object containing a list of War objects that have the number of deaths on Side A below the given amount
     */
    @GetMapping("/side-a/deaths/below/{amount}")
    public ResponseEntity<List<War>> getWarsBySideADeathsBelow(@PathVariable Long amount) {
        return ResponseEntity.ok(warRepository.findBySideADeathsLessThanEqual(amount));
    }

    /**
     * Retrieves a list of War objects that have the number of deaths on Side B equal to the given amount.
     *
     * @param amount the number of deaths on Side B to search for
     * @return a ResponseEntity object containing a list of War objects that have the number of deaths on Side B equal to the given amount
     */
    @GetMapping("/side-b/deaths/{amount}")
    public ResponseEntity<List<War>> getWarsBySideBDeaths(@PathVariable Long amount) {
        return ResponseEntity.ok(warRepository.findBySideBDeaths(amount));
    }

    /**
     * Retrieves a list of War objects that have the number of deaths on Side B above the given amount.
     *
     * @param amount the number of deaths on Side B to search for
     * @return a ResponseEntity object containing a list of War objects that have the number of deaths on Side B above the given amount
     */
    @GetMapping("/side-b/deaths/above/{amount}")
    public ResponseEntity<List<War>> getWarsBySideBDeathsAbove(@PathVariable Long amount) {
        return ResponseEntity.ok(warRepository.findBySideBDeathsGreaterThanEqual(amount));
    }

    /**
     * Retrieves a list of War objects that have the number of deaths on Side B below the given amount.
     *
     * @param amount the number of deaths on Side B to search for
     * @return a ResponseEntity object containing a list of War objects that have the number of deaths on Side B below the given amount
     */
    @GetMapping("/side-b/deaths/below/{amount}")
    public ResponseEntity<List<War>> getWarsBySideBDeathsBelow(@PathVariable Long amount) {
        return ResponseEntity.ok(warRepository.findBySideBDeathsLessThanEqual(amount));
    }

    /**
     * Retrieves a list of War objects that have the provided number of total combat deaths.
     *
     * @param amount the number of total combat deaths to search for
     * @return a ResponseEntity object containing a list of War objects that have the provided number of total combat deaths
     */
    @GetMapping("/total-combat-deaths/{amount}")
    public ResponseEntity<List<War>> getWarsByTotalCombatDeaths(@PathVariable Long amount) {
        return ResponseEntity.ok(warRepository.findByTotalCompatDeaths(amount));
    }

    /**
     * Retrieves a list of War objects that have the number of total combat deaths above the given amount.
     *
     * @param amount the number of total combat deaths to search for
     * @return a ResponseEntity object containing a list of War objects that have the number of total combat deaths above the given amount
     */
    @GetMapping("/total-combat-deaths/above/{amount}")
    public ResponseEntity<List<War>> getWarsByTotalCombatDeathsAbove(@PathVariable Long amount) {
        return ResponseEntity.ok(warRepository.findByTotalCompatDeathsGreaterThanEqual(amount));
    }

    /**
     * Retrieve a list of wars with total combat deaths below a specified amount.
     *
     * @param amount The maximum amount of total combat deaths for the wars to be retrieved.
     * @return A {@link ResponseEntity} object containing a list of {@link War} objects that have total combat deaths below the specified amount,
     *         or an empty list if no wars meet the criteria. The HTTP status code of the response is 200 (OK).
     */
    @GetMapping("/total-combat-deaths/below/{amount}")
    public ResponseEntity<List<War>> getWarsByTotalCombatDeathsBelow(@PathVariable Long amount) {
        return ResponseEntity.ok(warRepository.findByTotalCompatDeathsLessThanEqual(amount));
    }
}
