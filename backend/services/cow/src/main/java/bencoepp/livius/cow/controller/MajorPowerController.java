package bencoepp.livius.cow.controller;

import bencoepp.livius.entities.state.MajorPower;
import bencoepp.livius.entities.state.State;
import bencoepp.livius.repositories.state.MajorPowerRepository;
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
 * This class is a controller for the MajorPower API endpoints.
 */
@RestController
@RequestMapping("/api/v1/cow/major-power")
public class MajorPowerController {

    @Autowired
    private MajorPowerRepository majorPowerRepository;
    @Autowired
    private StateRepository stateRepository;

    /**
     * Retrieves all MajorPower entities from the database.
     *
     * @return a ResponseEntity containing a list of MajorPower entities
     */
    @GetMapping("/all")
    public ResponseEntity<List<MajorPower>> getAll() {
        return ResponseEntity.ok(majorPowerRepository.findAll());
    }

    /**
     * Retrieves the total count of MajorPower entities from the database.
     *
     * @return a ResponseEntity containing the total count of MajorPower entities
     */
    @GetMapping("/count/total")
    public ResponseEntity<Long> getTotal() {
        return ResponseEntity.ok(majorPowerRepository.count());
    }

    /**
     * Retrieves a MajorPower entity by its id.
     *
     * @param id the id of the MajorPower entity to retrieve
     * @return a ResponseEntity containing the MajorPower entity if it exists, or a not found response if it doesn't
     */
    @GetMapping("/{id}")
    public ResponseEntity<MajorPower> getById(@PathVariable String id) {
        Optional<MajorPower> majorPower = majorPowerRepository.findById(id);
        return majorPower.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves a list of states from the "states" collection of the MongoDB database,
     * where the wasMajorPower field is not empty.
     *
     * @return a ResponseEntity containing a list of State objects where the wasMajorPower field is not empty
     */
    @GetMapping("/states")
    public ResponseEntity<List<State>> getStatesThatAreMajorPowers() {
        return ResponseEntity.ok(stateRepository.findByWasMajorPowerNotEmpty());
    }

    /**
     * Retrieves a list of MajorPower entities by state.
     *
     * @param state the state to search for
     * @return a ResponseEntity containing a list of MajorPower entities matching the given state
     */
    @GetMapping("/state/{state}")
    public ResponseEntity<List<MajorPower>> getByState(@PathVariable String state) {
        List<MajorPower> majorPower = majorPowerRepository.findByState(state);
        return ResponseEntity.ok(majorPower);
    }

    /**
     * Retrieves a list of MajorPower entities by CowId.
     *
     * @param id the CowId to search for
     * @return a ResponseEntity containing a list of MajorPower entities matching the given CowId
     */
    @GetMapping("/cow-id/{id}")
    public ResponseEntity<List<MajorPower>> getByCowId(@PathVariable Integer id) {
        List<MajorPower> majorPower = majorPowerRepository.findByCowId(id);
        return ResponseEntity.ok(majorPower);
    }

    /**
     * Retrieves a list of MajorPower entities with a start date equal to the provided date.
     *
     * @param date the start date to search for in the format "dd.MM.yyyy"
     * @return a ResponseEntity containing a list of MajorPower entities with the provided start date,
     *         or a bad request response if the date is in an invalid format
     */
    @GetMapping("/start/{date}")
    public ResponseEntity<List<MajorPower>> getByStartDate(@PathVariable String date){
        try {
            Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(majorPowerRepository.findByStartDate(startDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of MajorPower entities with a start date before the provided date.
     *
     * @param date the start date to search for in the format "dd.MM.yyyy"
     * @return a ResponseEntity containing a list of MajorPower entities with the start date before the provided date,
     *         or a bad request response if the date is in an invalid format
     */
    @GetMapping("/start/before/{date}")
    public ResponseEntity<List<MajorPower>> getByBeforeStartDate(@PathVariable String date){
        try {
            Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(majorPowerRepository.findByStartDateBefore(startDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of MajorPower entities with a start date after the provided date.
     *
     * @param date the start date to search for in the format "dd.MM.yyyy"
     * @return a ResponseEntity containing a list of MajorPower entities with the start date after the provided date,
     *         or a bad request response if the date is in an invalid format
     */
    @GetMapping("/start/after/{date}")
    public ResponseEntity<List<MajorPower>> getByAfterStartDate(@PathVariable String date){
        try {
            Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(majorPowerRepository.findByStartDateAfter(startDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of MajorPower entities with an end date equal to the provided date.
     *
     * @param date the end date to search for in the format "dd.MM.yyyy"
     * @return a ResponseEntity containing a list of MajorPower entities with the provided end date,
     *         or a bad request response if the date is in an invalid format
     */
    @GetMapping("/end/{date}")
    public ResponseEntity<List<MajorPower>> getByEndDate(@PathVariable String date){
        try {
            Date endDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(majorPowerRepository.findByEndDate(endDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of MajorPower entities with an end date before the provided date.
     *
     * @param date the end date to search for in the format "dd.MM.yyyy"
     * @return a ResponseEntity containing a list of MajorPower entities with the end date before the provided date,
     *         or a bad request response if the date is in an invalid format
     */
    @GetMapping("/start/before/{date}")
    public ResponseEntity<List<MajorPower>> getByBeforeEndDate(@PathVariable String date){
        try {
            Date endDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(majorPowerRepository.findByEndDateBefore(endDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of MajorPower entities with an end date after the provided date.
     *
     * @param date the end date to search for in the format "dd.MM.yyyy"
     * @return a ResponseEntity containing a list of MajorPower entities with the end date after the provided date,
     *         or a bad request response if the date is in an invalid format
     */
    @GetMapping("/start/after/{date}")
    public ResponseEntity<List<MajorPower>> getByAfterEndDate(@PathVariable String date){
        try {
            Date endDate = new SimpleDateFormat("dd.MM.yyyy").parse(date);
            return ResponseEntity.ok(majorPowerRepository.findByEndDateAfter(endDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Retrieves a list of MajorPower entities that fall between the specified start and end dates.
     *
     * @param start the start date in the format "dd.MM.yyyy"
     * @param end the end date in the format "dd.MM.yyyy"
     * @return a ResponseEntity containing a list of MajorPower entities that fall between the specified start and end dates,
     *         or a bad request response if the dates are in an invalid format
     */
    @GetMapping("/between/end/{end}/start/{start}")
    public ResponseEntity<List<MajorPower>> getByBetweenStartAndEndDates(@PathVariable String end, @PathVariable String start){
        try {
            Date startDate = new SimpleDateFormat("dd.MM.yyyy").parse(start);
            Date endDate = new SimpleDateFormat("dd.MM.yyyy").parse(end);
            return ResponseEntity.ok(majorPowerRepository.findByStartDateAfterAndEndDateBefore(startDate, endDate));
        } catch (ParseException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
