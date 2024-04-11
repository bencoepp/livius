package bencoepp.livius.cow.controller;

import bencoepp.livius.entities.state.DiplomaticExchange;
import bencoepp.livius.entities.state.State;
import bencoepp.livius.repositories.state.DiplomaticExchangeRepository;
import bencoepp.livius.repositories.state.StateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/cow/diplomatic-exchange")
public class DiplomaticExchangeController {

    @Autowired
    private DiplomaticExchangeRepository diplomaticExchangeRepository;
    @Autowired
    private StateRepository stateRepository;

    /**
     * Retrieves all diplomatic exchanges.
     *
     * @return A ResponseEntity object containing a list of DiplomaticExchange objects.
     */
    @GetMapping("/all")
    public ResponseEntity<List<DiplomaticExchange>> getAll() {
        return ResponseEntity.ok(diplomaticExchangeRepository.findAll());
    }

    /**
     * Retrieves the total count of DiplomaticExchange objects in the database.
     *
     * @return A ResponseEntity object containing the total count as a Long.
     */
    @GetMapping("/count/total")
    public ResponseEntity<Long> getTotal() {
        return ResponseEntity.ok(diplomaticExchangeRepository.count());
    }

    /**
     * Retrieves all diplomatic exchanges for a specific year.
     *
     * @param year The year to filter by.
     * @return A ResponseEntity object containing a list of DiplomaticExchange objects.
     */
    @GetMapping("/year/{year}")
    public ResponseEntity<List<DiplomaticExchange>> getByYear(@PathVariable int year) {
        return ResponseEntity.ok(diplomaticExchangeRepository.findByYear(year));
    }

    /**
     * Retrieves all diplomatic exchanges that occurred before the specified year.
     *
     * @param year The year to filter by. Only diplomatic exchanges that occurred before or in this year will be returned.
     * @return A ResponseEntity object containing a list of DiplomaticExchange objects.
     */
    @GetMapping("/year/before/{year}")
    public ResponseEntity<List<DiplomaticExchange>> getBeforeYear(@PathVariable int year) {
        return ResponseEntity.ok(diplomaticExchangeRepository.findByYearLessThanEqual(year));
    }

    /**
     * Retrieves all diplomatic exchanges that occurred after the specified year.
     *
     * @param year The year to filter by. Only diplomatic exchanges that occurred after or in this year will be returned.
     * @return A ResponseEntity object containing a list of DiplomaticExchange objects.
     */
    @GetMapping("/year/after/{year}")
    public ResponseEntity<List<DiplomaticExchange>> getAfterYear(@PathVariable int year) {
        return ResponseEntity.ok(diplomaticExchangeRepository.findByYearGreaterThanEqual(year));
    }

    /**
     * Retrieves all diplomatic exchanges for side A with the given ID.
     *
     * @param id The ID of the side A to filter the exchanges by.
     * @return A ResponseEntity object containing a list of DiplomaticExchange objects that belong to side A with the given ID.
     *         If the side A with the given ID exists, returns a ResponseEntity with an OK status code and the list of exchanges.
     *         If the side A with the given ID does not exist, returns a ResponseEntity with a Not Found status code.
     */
    @GetMapping("/side-a/{id}")
    public ResponseEntity<List<DiplomaticExchange>> getSideA(@PathVariable String id) {
        Optional<State> state = stateRepository.findById(id);
        return state.map(value -> ResponseEntity.ok(diplomaticExchangeRepository.findBySideA_Id(value.getId()))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all diplomatic exchanges for side B with the given ID.
     *
     * @param id The ID of the side B to filter the exchanges by.
     * @return A ResponseEntity object containing a list of DiplomaticExchange objects that belong to side B with the given ID.
     *         If the side B with the given ID exists, returns a ResponseEntity with an OK status code and the list of exchanges.
     *         If the side B with the given ID does not exist, returns a ResponseEntity with a Not Found status code.
     */
    @GetMapping("/side-b/{id}")
    public ResponseEntity<List<DiplomaticExchange>> getSideB(@PathVariable String id) {
        Optional<State> state = stateRepository.findById(id);
        return state.map(value -> ResponseEntity.ok(diplomaticExchangeRepository.findBySideB_Id(state.get().getId()))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all diplomatic exchanges for side A with the given representation level.
     *
     * @param level The representation level to filter the exchanges by.
     * @return A ResponseEntity object containing a list of DiplomaticExchange objects that belong to side A with the given representation level.
     *         If the representation level does not exist, returns a ResponseEntity with a Not Found status code.
     *         If the representation level exists, returns a ResponseEntity with an OK status code and the list of exchanges.
     */
    @GetMapping("/side-a/representation-level/{level}")
    public ResponseEntity<List<DiplomaticExchange>> getRepresentationLevelSideA(@PathVariable String level) {
        return ResponseEntity.ok(diplomaticExchangeRepository.findByDiplomaticRepresentationLevelSideA(level));
    }

    /**
     * Retrieves all diplomatic exchanges for side B with the given representation level.
     *
     * @param level The representation level to filter the exchanges by.
     * @return A ResponseEntity object containing a list of DiplomaticExchange objects that belong to side B with the given
     *         representation level. If the representation level does not exist, returns a ResponseEntity with a Not Found
     *         status code. If the representation level exists, returns a ResponseEntity with an OK status code and the list
     *         of exchanges.
     */
    @GetMapping("/side-b/representation-level/{level}")
    public ResponseEntity<List<DiplomaticExchange>> getRepresentationLevelSideB(@PathVariable String level) {
        return ResponseEntity.ok(diplomaticExchangeRepository.findByDiplomaticRepresentationLevelSideB(level));
    }

    /**
     * Retrieves all diplomatic exchanges with the given exchange.
     *
     * @param exchange The exchange value to filter the exchanges by.
     * @return A ResponseEntity object containing a list of DiplomaticExchange objects that match the given exchange value.
     */
    @GetMapping("/any-exchange/{exchange}")
    public ResponseEntity<List<DiplomaticExchange>> getAnyExchange(@PathVariable String exchange) {
        return ResponseEntity.ok(diplomaticExchangeRepository.findByAnyDiplomaticExchange(exchange));
    }
}
