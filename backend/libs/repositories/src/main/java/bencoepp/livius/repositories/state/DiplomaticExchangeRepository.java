package bencoepp.livius.repositories.state;

import bencoepp.livius.entities.state.DiplomaticExchange;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * The DiplomaticExchangeRepository interface is responsible for managing the persistence and retrieval
 * of DiplomaticExchange objects in the database.
 *
 * It extends the MongoRepository interface, which provides basic CRUD operations for MongoDB collections,
 * with additional custom operations specific to DiplomaticExchange objects.
 *
 * This interface inherits methods to save, update, delete, and find DiplomaticExchange objects by their IDs.
 * It also inherits methods to find all DiplomaticExchange objects, count the number of DiplomaticExchange objects,
 * and check if a specific DiplomaticExchange object exists.
 *
 * Additional custom methods can be declared in this interface to provide more specific operations for manipulating
 * DiplomaticExchange objects in the database.
 */
public interface DiplomaticExchangeRepository extends MongoRepository<DiplomaticExchange, String> {
    List<DiplomaticExchange> findByYear(Integer year);

    List<DiplomaticExchange> findByYearLessThanEqual(Integer year);

    List<DiplomaticExchange> findByYearGreaterThanEqual(Integer year);

    List<DiplomaticExchange> findBySideA_Id(String id);

    List<DiplomaticExchange> findBySideB_Id(String id);

    List<DiplomaticExchange> findByDiplomaticRepresentationLevelSideA(String diplomaticRepresentationLevelSideA);

    List<DiplomaticExchange> findByDiplomaticRepresentationLevelSideB(String diplomaticRepresentationLevelSideB);

    List<DiplomaticExchange> findByAnyDiplomaticExchange(String anyDiplomaticExchange);
}
