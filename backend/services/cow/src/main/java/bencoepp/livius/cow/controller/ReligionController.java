package bencoepp.livius.cow.controller;

import bencoepp.livius.entities.religion.Religion;
import bencoepp.livius.entities.state.State;
import bencoepp.livius.repositories.religion.ReligionRepository;
import bencoepp.livius.repositories.state.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * The {@code ReligionController} class is a REST controller that handles requests related to the {@link Religion} resource.
 * It exposes various endpoints for retrieving religious data, such as getting all religions, counting total religions,
 * retrieving religion by ID, retrieving religion by year, retrieving religion before a specific year,
 * retrieving religion after a specific year, retrieving religion by state ID, and retrieving religion by cow ID.
 * This controller uses the {@link ReligionRepository} and {@link StateRepository} for data access.
 *
 * @RestController - Indicates that this class serves as a RESTful controller.
 * @RequestMapping - Maps incoming requests to the specified URI.
 */
@RestController
@RequestMapping("/api/v1/cow/religion")
public class ReligionController {

    @Autowired
    private ReligionRepository religionRepository;
    @Autowired
    private StateRepository stateRepository;

    /**
     * Retrieves all religions.
     *
     * @return A ResponseEntity object containing a List of Religion objects. If successful, the response status code is 200 (OK); otherwise, the response status code is 500 (Internal
     *  Server Error).
     */
    @GetMapping("/all")
    public ResponseEntity<List<Religion>> getAllReligions() {
        return ResponseEntity.ok(religionRepository.findAll());
    }

    /**
     * Retrieves the total number of religions.
     *
     * @return A ResponseEntity object containing the total number of religions as a Long.
     *         If successful, the response status code is 200 (OK); otherwise, the response status code is 500 (Internal Server Error).
     */
    @GetMapping("/count/total")
    public ResponseEntity<Long> getTotalReligions() {
        return ResponseEntity.ok(religionRepository.count());
    }

    /**
     * Retrieves the Religion object associated with the provided ID.
     *
     * @param id The ID of the Religion object to retrieve.
     * @return A ResponseEntity object containing the Religion object if it exists, or a 404 (Not Found) response if it does not.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Religion> getReligionById(@PathVariable String id) {
        Optional<Religion> religion = religionRepository.findById(id);
        return religion.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a list of religions by year.
     *
     * @param year The year to retrieve religions for.
     * @return A ResponseEntity object containing a List of Religion objects. If successful, the response status code is 200 (OK);
     * otherwise, the response status code is 500 (Internal Server Error).
     */
    @GetMapping("/year/{year}")
    public ResponseEntity<List<Religion>> getReligionByYear(@PathVariable Integer year) {
        return ResponseEntity.ok(religionRepository.findByYear(year));
    }

    /**
     * Retrieves a list of religions by year, before the given year.
     *
     * @param year The year before which religions should be retrieved.
     * @return A ResponseEntity object containing a List of Religion objects. If successful, the response status code is
     * 200 (OK); otherwise, the response status code is 500 (Internal Server Error).
     */
    @GetMapping("/year/before/{year}")
    public ResponseEntity<List<Religion>> getReligionByYearBefore(@PathVariable Integer year) {
        return ResponseEntity.ok(religionRepository.findByYearLessThanEqual(year));
    }

    /**
     * Retrieves a list of religions by year, after the given year.
     *
     * @param year The year after which religions should be retrieved.
     * @return A ResponseEntity object containing a List of Religion objects. If successful, the response status code is 200 (OK);
     * otherwise, the response status code is 500 (Internal Server Error).
     */
    @GetMapping("/year/after/{year}")
    public ResponseEntity<List<Religion>> getReligionByYearAfter(@PathVariable Integer year) {
        return ResponseEntity.ok(religionRepository.findByYearGreaterThanEqual(year));
    }

    /**
     * Retrieves a list of religions based on the state ID.
     *
     * @param id The ID of the state.
     * @return A ResponseEntity object containing a List of Religion objects. If the state exists, the response status code is 200 (OK);
     *         otherwise, the response status code is 404 (Not Found).
     */
    @GetMapping("/state/{id}")
    public ResponseEntity<List<Religion>> getReligionByState(@PathVariable String id) {
        Optional<State> state = stateRepository.findById(id);
        return state.map(value -> ResponseEntity.ok(religionRepository.findByState_Id(value.getId()))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a list of religions based on the cow ID.
     *
     * @param id The ID of the cow.
     * @return A ResponseEntity object containing a List of Religion objects.
     *         If successful, the response status code is 200 (OK);
     *         otherwise, the response status code is 500 (Internal Server Error).
     */
    @GetMapping("/cow-id/{id}")
    public ResponseEntity<List<Religion>> getReligionByCowId(@PathVariable Integer id) {
        return ResponseEntity.ok(religionRepository.findByCowId(id));
    }

    /**
     * Retrieves a list of religions based on the given type.
     *
     * @param type The type of the religions to retrieve.
     * @return A ResponseEntity object containing a List of Religion objects. If successful, the response status code is 200 (OK);
     *      otherwise, the response status code is 500 (Internal Server Error).
     */
    @GetMapping("/type/{type}")
    public ResponseEntity<List<Religion>> getReligionByType(@PathVariable String type) {
        return ResponseEntity.ok(religionRepository.findByDataType(type));
    }

    /**
     * Retrieves a list of religions based on the reliability of their record.
     *
     * @param reliability The reliability of the record to retrieve religions for.
     * @return A ResponseEntity object containing a List of Religion objects. If successful, the response status code is 200 (OK);
     * otherwise, the response status code is 500 (Internal Server Error).
     */
    @GetMapping("/record-reliability/{reliability}")
    public ResponseEntity<List<Religion>> getReligionByReliability(@PathVariable String reliability) {
        return ResponseEntity.ok(religionRepository.findByReliabilityOfRecord(reliability));
    }

    /**
     * Retrieves a list of religions based on the level of reliability of their record.
     *
     * @param level The level of reliability of the record to retrieve religions for.
     * @return A ResponseEntity object containing a List of Religion objects. If successful, the response status code is 200 (OK);
     *         otherwise, the response status code is 500 (Internal Server Error).
     */
    @GetMapping("/level-reliability/{level}")
    public ResponseEntity<List<Religion>> getReligionByLevel(@PathVariable String level) {
        return ResponseEntity.ok(religionRepository.findByLevelOfReliabilityOfRecord(level));
    }
}
