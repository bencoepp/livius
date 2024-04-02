package bencoepp.livius.repositories.state;

import bencoepp.livius.entities.state.Country;
import org.springframework.data.mongodb.repository.MongoRepository;

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
}
