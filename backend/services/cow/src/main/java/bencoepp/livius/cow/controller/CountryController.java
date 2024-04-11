package bencoepp.livius.cow.controller;

import bencoepp.livius.entities.state.Country;
import bencoepp.livius.repositories.state.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * The CountryController class is responsible for handling HTTP requests related to country data.
 *
 * <p>
 * This class is annotated with the @RestController and @RequestMapping annotations to indicate that it is a controller
 * class and to specify the base URL for all the request mappings in this class.
 * </p>
 *
 * <p>
 * This class contains methods for retrieving country data based on different search criteria such as ID, name, code, and cow ID.
 * It uses the CountryRepository interface to interact with the underlying MongoDB collection that stores country data.
 * </p>
 *
 * @see RestController
 * @see RequestMapping
 * @see CountryRepository
 */
@RestController
@RequestMapping("/api/v1/cow/country")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    /**
     * Retrieves a country by its ID.
     *
     * @param id the ID of the country to retrieve.
     * @return a ResponseEntity object containing the country if found, or a response with HTTP status 404 (Not Found).
     */
    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountry(@PathVariable String id) {
        Optional<Country> country = countryRepository.findById(id);
        return country.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Checks if a country exists by code or cow ID.
     *
     * @param code   The country code to check.
     * @param cowId  The cow ID to check.
     * @return true if a country with the given code or cow ID exists, false otherwise.
     */
    @GetMapping("/exists/{code}/{cowId}")
    public ResponseEntity<Boolean> existsByCodeOrCowId(@PathVariable String code, @PathVariable Integer cowId) {
        boolean exists = countryRepository.existsByCodeOrCowId(code, cowId);
        return ResponseEntity.ok(exists);
    }

    /**
     * Retrieves the country by the given name.
     *
     * <p>
     * This method is used to retrieve the country associated with the provided name. It makes use of
     * the {@link CountryRepository#findByName(String)} method to search for the country in the repository.
     * </p>
     *
     * <p>
     * The method takes a string parameter representing the name of the country and returns a {@link ResponseEntity}
     * object containing the country if found. If no country is found, it returns a response with HTTP status
     * 404 (Not Found).
     * </p>
     *
     * @param name the name of the country to search for
     * @return a ResponseEntity object containing the country if found, or a response with HTTP status 404 (Not Found)
     *
     * @see CountryRepository
     * @see Country
     * @see ResponseEntity
     */
    @GetMapping("/name/{name}")
    public ResponseEntity<Country> getCountryByName(@PathVariable String name) {
        Optional<Country> country = countryRepository.findByName(name);
        return country.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves the country by the given code.
     *
     * <p>
     * This method is used to retrieve the country associated with the provided code. It makes use of
     * the {@link CountryRepository#findByCode(String)} method to search for the country in the repository.
     * </p>
     * <p>
     * The method takes a string parameter representing the country code and returns a {@link ResponseEntity} object
     * containing the country if found. If no country is found, it returns a response with HTTP status 404 (Not Found).
     * </p>
     *
     * @param code the country code to search for the country.
     * @return a ResponseEntity object containing the country if found, or a response with HTTP status 404 (Not Found).
     *
     * @see CountryRepository
     * @see Country
     * @see ResponseEntity
     */
    @GetMapping("/code/{code}")
    public ResponseEntity<Country> getCountryByCode(@PathVariable String code) {
        Optional<Country> country = countryRepository.findByCode(code);
        return country.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves the country by the given cow ID.
     *
     * <p>
     * This method is used to retrieve the country associated with the provided cow ID. It makes use of
     * the {@link CountryRepository#findByCowId(Integer)} method to search for the country in the repository.
     * </p>
     * <p>
     * The method takes an integer parameter representing the cow ID and returns a {@link ResponseEntity} object
     * containing the country if found. If no country is found, it returns a response with HTTP status 404 (Not Found).
     * </p>
     *
     * @param id the cow ID to search for the country.
     * @return a ResponseEntity object containing the country if found, or a response with HTTP status 404 (Not Found).
     *
     * @see CountryRepository
     * @see Country
     * @see ResponseEntity
     */
    @GetMapping("/cow-id/{id}")
    public ResponseEntity<Country> getCountryByCowId(@PathVariable Integer id) {
        Optional<Country> country = countryRepository.findByCowId(id);
        return country.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Retrieves all countries from the country repository.
     *
     * <p>
     * This method is used to get a list of all countries available in the system. It makes use of the
     * {@link CountryRepository#findAll()} method to retrieve all the country objects stored in the database.
     * </p>
     * <p>
     * The method returns a {@link ResponseEntity} object containing a list of {@link Country} objects. The list
     * of countries is encapsulated in the response body of the ResponseEntity object. The HttpStatus of
     * the response is set to HttpStatus.OK (200) to indicate a successful request.
     * </p>
     *
     * @return a ResponseEntity object containing a list of Country objects.
     *
     * @see CountryController
     * @see Country
     * @see ResponseEntity
     * @see CountryRepository
     *
     * @apiNote The returned list may be empty if no countries are found in the database.
     **/
    @GetMapping("/all")
    public ResponseEntity<List<Country>> getAllCountry() {
        return ResponseEntity.ok(countryRepository.findAll());
    }
}
