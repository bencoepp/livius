package bencoepp.livius.cow.controller;

import bencoepp.livius.entities.state.State;
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
 * The StateController class is a RESTful controller that handles HTTP requests related to state data.
 * It provides endpoints for retrieving state information.
 * <p>
 * This class is responsible for mapping requests to the respective handler methods and returning
 * the appropriate response entities.
 * <p>
 * Example usage:
 * ```
 * StateController stateController = new StateController();
 * <p>
 * // Retrieve all states
 * ResponseEntity<List<State>> response = stateController.getAll();
 * ```
 */
@RestController
@RequestMapping("/api/v1/cow/state")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    /**
     * Retrieves all state objects from the database.
     *
     * @return A {@link ResponseEntity} containing a list of {@link State} objects. The response entity has a status code of 200 (OK) if successful,
     *         and the list of state objects in the response body. If no state objects are found in the database, an empty list is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     *
     */
    @GetMapping("/all")
    public ResponseEntity<List<State>> getAll() {
        return ResponseEntity.ok(stateRepository.findAll());
    }

    /**
     * Retrieves the total count of state documents in the "states" collection of the MongoDB database.
     *
     * @return A {@link ResponseEntity} containing the total count of state documents. The response entity has a status code of 200 (OK) if successful,
     *         and the total count of state documents in the response body.
     *
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     * @see StateRepository#count()
     *
     */
    @GetMapping("/count/total")
    public ResponseEntity<Long> getTotal() {
        return ResponseEntity.ok(stateRepository.count());
    }

    /**
     * Retrieves a state from the database by its ID.
     *
     * @param id The ID of the state to retrieve.
     * @return A {@link ResponseEntity} containing the state object. The response entity has a status code of 200 (OK) if the state is found in the database,
     *         and the state object in the response body. If the state is not found, a response entity with a status code of 404 (Not Found) is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     * @see ResponseEntity#notFound()
     * @see StateRepository#findById(Object)
     */
    @GetMapping("/{id}")
    public ResponseEntity<State> getById(@PathVariable String id) {
        Optional<State> state = stateRepository.findById(id);
        return state.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
