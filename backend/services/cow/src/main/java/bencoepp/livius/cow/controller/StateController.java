package bencoepp.livius.cow.controller;

import bencoepp.livius.entities.state.State;
import bencoepp.livius.repositories.state.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    /**
     * Retrieves a list of states from the database based on the provided code.
     *
     * @param code The code to search for in the database.
     * @return A {@link ResponseEntity} containing a list of {@link State} objects. The response entity has a status code of 200 (OK) if successful,
     *         and a list of state objects in the response body. If no state objects are found in the database, an empty list is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     * @see StateRepository#findByCode(String)
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<List<State>> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(stateRepository.findByCode(code));
    }

    /**
     * Retrieves a list of states from the "states" collection of the MongoDB database
     * based on the provided cowId.
     *
     * @param id The COW ID of the states to retrieve.
     * @return A {@link ResponseEntity} containing a list of {@link State} objects.
     *         The response entity has a status code of 200 (OK) if successful,
     *         and the list of state objects in the response body.
     *         If no state objects are found in the database, an empty list is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see StateRepository#findByCowId(Integer)
     */
    @GetMapping("/cow-id/{id}")
    public ResponseEntity<List<State>> getByCowId(@PathVariable Integer id) {
        return ResponseEntity.ok(stateRepository.findByCowId(id));
    }

    /**
     * Retrieves a list of states from the database based on the provided name.
     *
     * @param name The name to search for in the database.
     * @return A {@link ResponseEntity} containing a list of {@link State} objects. The response entity has a status code of 200 (OK) if successful,
     *         and a list of state objects in the response body. If no state objects are found in the database, an empty list is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     * @see StateRepository#findByName(String)
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<List<State>> getByName(@PathVariable String name) {
        return ResponseEntity.ok(stateRepository.findByName(name));
    }

    /**
     * Retrieves a list of states from the database based on the provided start date.
     *
     * @param date The start date in the format "dd.MM.yyyy".
     * @return A ResponseEntity containing a list of State objects. The response entity has
     *         a status code of 200 (OK) if successful, and a list of state objects in the
     *         response body. If no state objects are found in the database, an empty list
     *         is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     * @see StateRepository#findByStartDateBefore(Date)
     */
    @GetMapping("/start/{date}")
    public ResponseEntity<List<State>> getByStartDate(@PathVariable String date){
        try {
            Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(stateRepository.findByStartDate(startDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of states from the database based on the provided start date.
     *
     * @param date The start date in the format "dd.MM.yyyy".
     * @return A ResponseEntity containing a list of State objects. The response entity has
     *         a status code of 200 (OK) if successful, and a list of state objects in the
     *         response body. If no state objects are found in the database, an empty list
     *         is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     * @see StateRepository#findByStartDateBefore(Date)
     */
    @GetMapping("/start/before/{date}")
    public ResponseEntity<List<State>> getByBeforeStartDate(@PathVariable String date){
        try {
            Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(stateRepository.findByStartDateBefore(startDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of states from the database based on the provided start date.
     *
     * @param date The start date in the format "dd.MM.yyyy".
     * @return A ResponseEntity containing a list of State objects. The response entity has
     *         a status code of 200 (OK) if successful, and a list of state objects in the
     *         response body. If no state objects are found in the database, an empty list
     *         is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     * @see StateRepository#findByStartDateAfter(Date)
     */
    @GetMapping("/start/after/{date}")
    public ResponseEntity<List<State>> getByAfterStartDate(@PathVariable String date){
        try {
            Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(stateRepository.findByStartDateAfter(startDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of states from the database based on the provided end date.
     *
     * @param date The end date in the format "dd.MM.yyyy".
     * @return A ResponseEntity containing a list of State objects. The response entity has
     *         a status code of 200 (OK) if successful, and a list of state objects in the
     *         response body. If no state objects are found in the database, an empty list
     *         is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     * @see StateRepository#findByEndDateBefore(Date)
     */
    @GetMapping("/end/{date}")
    public ResponseEntity<List<State>> getByEndDate(@PathVariable String date){
        try {
            Date endDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(stateRepository.findByEndDate(endDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of states from the database based on the provided end date.
     *
     * @param date The end date in the format "dd.MM.yyyy".
     * @return A {@link ResponseEntity} containing a list of {@link State} objects. The response entity has a status code of 200 (OK) if successful,
     *         and a list of state objects in the response body. If no state objects are found in the database, an empty list is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     * @see StateRepository#findByEndDateBefore(Date)
     */
    @GetMapping("/end/before/{date}")
    public ResponseEntity<List<State>> getByBeforeEndDate(@PathVariable String date){
        try {
            Date endDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(stateRepository.findByEndDateBefore(endDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of states from the database based on the provided end date.
     *
     * @param date The end date in the format "dd.MM.yyyy".
     * @return A {@link ResponseEntity} containing a list of {@link State} objects. The response entity has a status code of 200 (OK) if successful,
     *         and a list of state objects in the response body. If no state objects are found in the database, an empty list is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     * @see StateRepository#findByEndDateAfter(Date)
     */
    @GetMapping("/end/after/{date}")
    public ResponseEntity<List<State>> getByAfterEndDate(@PathVariable String date){
        try {
            Date endDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(stateRepository.findByEndDateAfter(endDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of states from the database based on the provided start and end dates.
     *
     * @param start The start date in the format "dd.MM.yyyy".
     * @param end The end date in the format "dd.MM.yyyy".
     * @return A {@link ResponseEntity} containing a list of {@link State} objects. The response entity has a status code of 200 (OK) if successful,
     *         and a list of state objects in the response body. If no state objects are found in the database, an empty list is returned.
     *
     * @see State
     * @see ResponseEntity
     * @see ResponseEntity#ok(Object)
     * @see StateRepository#findByStartDateAfterAndEndDateBefore(Date, Date)
     */
    @GetMapping("/between/end/{end}/start/{start}")
    public ResponseEntity<List<State>> getByBetweenStartAndEndDates(@PathVariable String end, @PathVariable String start){
        try {
            Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse(start);
            Date endDate = new SimpleDateFormat("dd.MM.yyyy").parse(end);
            return ResponseEntity.ok(stateRepository.findByStartDateAfterAndEndDateBefore(startDate, endDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
