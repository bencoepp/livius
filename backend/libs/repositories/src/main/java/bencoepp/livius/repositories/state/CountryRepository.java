package bencoepp.livius.repositories.state;

import bencoepp.livius.entities.state.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * The CountryRepository interface is responsible for providing methods to interact with the MongoDB collection
 * that stores country data.
 *
 * <p>
 * This interface extends the MongoRepository interface, which provides all the basic CRUD operations
 * (Create, Read, Update, Delete) required for database operations. It also allows for querying and sorting
 * data based on various criteria.
 * </p>
 */
public interface CountryRepository extends MongoRepository<Country, String> {
    /**
     * Checks if a country exists by code or cow ID.
     *
     * @param code   The country code to check.
     * @param cowId  The cow ID to check.
     * @return true if a country with the given code or cow ID exists, false otherwise.
     */
    boolean existsByCodeOrCowId(String code, Integer cowId);

    /**
     * Retrieves the country by the given name.
     *
     * @param name The name of the country to search for.
     * @return An Optional object containing the country if found, or an empty Optional if not found.
     */
    Optional<Country> findByName(String name);

    /**
     * Finds a country by its code.
     *
     * @param code The code of the country to find.
     * @return An Optional object containing the country if found, or an empty Optional if not found.
     */
    Optional<Country> findByCode(String code);

    /**
     * Retrieves the country by the given cow ID.
     *
     * @param cowId the cow ID to search for the country.
     * @return an Optional object containing the country if found, or an empty Optional if not found.
     */
    Optional<Country> findByCowId(Integer cowId);
}
