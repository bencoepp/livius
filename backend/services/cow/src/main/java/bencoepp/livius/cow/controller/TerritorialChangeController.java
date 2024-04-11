package bencoepp.livius.cow.controller;

import bencoepp.livius.entities.state.TerritorialChange;
import bencoepp.livius.repositories.state.TerritorialChangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

/**
 * The TerritorialChangeController class is a RESTful controller responsible for handling territorial change requests.
 */
@RestController
@RequestMapping("/api/v1/cow/territorial-change")
public class TerritorialChangeController {

    @Autowired
    private TerritorialChangeRepository territorialChangeRepository;

    /**
     * Retrieves all territorial changes.
     *
     * @return The response entity containing a list of territorial changes.
     */
    @GetMapping("/all")
    public ResponseEntity<List<TerritorialChange>> getAll() {
        return ResponseEntity.ok(territorialChangeRepository.findAll());
    }

    /**
     * Retrieves a territorial change by its ID.
     *
     * @param id The ID of the territorial change to retrieve.
     * @return The response entity containing the territorial change if found, or a not found response if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TerritorialChange> getById(@PathVariable String id) {
        Optional<TerritorialChange> optional = territorialChangeRepository.findById(id);
        return optional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    
    /**
     * Retrieves the total count of territorial changes.
     *
     * @return The response entity containing the total count of territorial changes.
     */
    @GetMapping("/count/total")
    public ResponseEntity<Long> getTotal() {
        return ResponseEntity.ok(territorialChangeRepository.count());
    }
    
    /**
     * Retrieves territorial changes based on the given territorial change number.
     *
     * @param id The territorial change number to search for.
     * @return The response entity containing a list of territorial changes with matching territorial change number.
     */
    @GetMapping("/territorial-nr/{id}")
    public ResponseEntity<List<TerritorialChange>> getTerritorialNumber(@PathVariable Integer id) {
        return ResponseEntity.ok(territorialChangeRepository.findByTerritorialChangeNumber(id));
    }
    
    /**
     * Retrieves territorial changes for a given year.
     *
     * @param year The year to retrieve territorial changes for.
     * @return The response entity containing a list of territorial changes for the given year.
     */
    @GetMapping("/year/{year}")
    public ResponseEntity<List<TerritorialChange>> getYear(@PathVariable Integer year) {
        return ResponseEntity.ok(territorialChangeRepository.findByYear(year));
    }
    
    /**
     * Retrieves territorial changes that occurred before or in the given year.
     *
     * @param year The year to retrieve territorial changes before or in.
     * @return The response entity containing a list of territorial changes before or in the given year.
     */
    @GetMapping("/year/before/{year}")
    public ResponseEntity<List<TerritorialChange>> getYearBefore(@PathVariable Integer year) {
        return ResponseEntity.ok(territorialChangeRepository.findByYearLessThanEqual(year));
    }

    /**
     * Retrieves territorial changes that occurred after the given year.
     *
     * @param year The year to retrieve territorial changes after.
     * @return The response entity containing a list of territorial changes that occurred after the given year.
     */
    @GetMapping("/year/after/{year}")
    public ResponseEntity<List<TerritorialChange>> getYearAfter(@PathVariable Integer year) {
        return ResponseEntity.ok(territorialChangeRepository.findByYearGreaterThanEqual(year));
    }

    /**
     * Retrieves territorial changes for a specific month.
     *
     * @param month The month to retrieve territorial changes for.
     * @return The response entity containing a list of territorial changes for the given month.
     */
    @GetMapping("/month/{month}")
    public ResponseEntity<List<TerritorialChange>> getMonth(@PathVariable String month) {
        return ResponseEntity.ok(territorialChangeRepository.findByMonth(month));
    }

    /**
     * Retrieves territorial changes for a specific year and month.
     *
     * @param year  The year for which to retrieve territorial changes.
     * @param month The month for which to retrieve territorial changes.
     * @return The response entity containing a list of territorial changes for the given year and month.
     */
    @GetMapping("/year/{year}/month/{month}")
    public ResponseEntity<List<TerritorialChange>> getYearMonth(@PathVariable Integer year, @PathVariable String month) {
        return ResponseEntity.ok(territorialChangeRepository.findByYearAndMonth(year, month));
    }

    /**
     * Retrieves territorial changes where the gaining side matches the given state.
     *
     * @param state The state to search for in the gaining side of territorial changes.
     * @return The response entity containing a list of territorial changes where the gaining side matches the given state.
     */
    @GetMapping("/gaining-side/{state}")
    public ResponseEntity<List<TerritorialChange>> getGainingSide(@PathVariable String state) {
        return ResponseEntity.ok(territorialChangeRepository.findByGainingSide_Id(state));
    }

    /**
     * Retrieves territorial changes where the losing side matches the given state.
     *
     * @param state The state to search for in the losing side of territorial changes.
     * @return The response entity containing a list of territorial changes where the losing side matches the given state.
     */
    @GetMapping("/losing-side/{state}")
    public ResponseEntity<List<TerritorialChange>> getLosingSide(@PathVariable String state) {
        return ResponseEntity.ok(territorialChangeRepository.findByLosingSide_Id(state));
    }

    /**
     * Retrieves territorial changes where the gaining side matches the given type.
     *
     * @param type The type to search for in the gaining side of territorial changes.
     * @return The response entity containing a list of territorial changes where the gaining side matches the given type.
     */
    @GetMapping("/gaining-side/type/{type}")
    public ResponseEntity<List<TerritorialChange>> getByTypeOfGainingSide(@PathVariable String type) {
        return ResponseEntity.ok(territorialChangeRepository.findByTypeOfChangeForGainingSide(type));
    }

    /**
     * Retrieves territorial changes where the losing side matches the given type.
     *
     * @param type The type to search for in the losing side of territorial changes.
     * @return The response entity containing a list of territorial changes where the losing side matches the given type.
     */
    @GetMapping("/losing-side/type/{type}")
    public ResponseEntity<List<TerritorialChange>> getByTypeOfLosingSide(@PathVariable String type) {
        return ResponseEntity.ok(territorialChangeRepository.findByTypeOfChangeForLosingSide(type));
    }

    /**
     * Retrieves territorial changes by the given procedure.
     *
     * @param procedure The procedure to retrieve territorial changes for.
     * @return The response entity containing a list of territorial changes with the matching procedure.
     */
    @GetMapping("/procedure/{procedure}")
    public ResponseEntity<List<TerritorialChange>> getByProcedure(@PathVariable String procedure) {
        return ResponseEntity.ok(territorialChangeRepository.findByProcedure(procedure));
    }

    /**
     * Retrieves territorial changes by the entity exchanged.
     *
     * @param entity The entity exchanged to search for.
     * @return The response entity containing a list of territorial changes with the matching entity exchanged.
     */
    @GetMapping("/entity/{entity}")
    public ResponseEntity<List<TerritorialChange>> getByEntityExchanged(@PathVariable String entity) {
        return ResponseEntity.ok(territorialChangeRepository.findByEntityExchanged(entity));
    }
}
