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
    /**
     * Finds DiplomaticExchange objects by year.
     *
     * @param year the year to filter the DiplomaticExchange objects by
     * @return a list of DiplomaticExchange objects that match the specified year
     */
    List<DiplomaticExchange> findByYear(Integer year);

    /**
     * Finds DiplomaticExchange objects with a year less than or equal to the specified year.
     *
     * @param year the maximum year to filter the DiplomaticExchange objects by
     * @return a list of DiplomaticExchange objects that have a year less than or equal to the specified year
     */
    List<DiplomaticExchange> findByYearLessThanEqual(Integer year);

    /**
     * Finds DiplomaticExchange objects with a year greater than or equal to the specified year.
     *
     * @param year the minimum year to filter the DiplomaticExchange objects by
     * @return a list of DiplomaticExchange objects that have a year greater than or equal to the specified year
     */
    List<DiplomaticExchange> findByYearGreaterThanEqual(Integer year);

    /**
     * Finds DiplomaticExchange objects by the ID of Side A.
     *
     * @param id the ID of Side A to filter the DiplomaticExchange objects by
     * @return a list of DiplomaticExchange objects that have the specified ID of Side A
     */
    List<DiplomaticExchange> findBySideA_Id(String id);

    /**
     * Finds DiplomaticExchange objects by the ID of Side B.
     *
     * @param id the ID of Side B to filter the DiplomaticExchange objects by
     * @return a list of DiplomaticExchange objects that have the specified ID of Side B
     */
    List<DiplomaticExchange> findBySideB_Id(String id);

    /**
     * Finds DiplomaticExchange objects by the diplomatic representation level of Side A.
     *
     * @param diplomaticRepresentationLevelSideA the diplomatic representation level of Side A to filter the DiplomaticExchange objects by
     * @return a list of DiplomaticExchange objects that have the specified diplomatic representation level of Side A
     */
    List<DiplomaticExchange> findByDiplomaticRepresentationLevelSideA(String diplomaticRepresentationLevelSideA);

    /**
     * Finds DiplomaticExchange objects by the diplomatic representation level of Side B.
     *
     * @param diplomaticRepresentationLevelSideB the diplomatic representation level of Side B to filter the DiplomaticExchange objects by
     * @return a list of DiplomaticExchange objects that have the specified diplomatic representation level of Side B
     */
    List<DiplomaticExchange> findByDiplomaticRepresentationLevelSideB(String diplomaticRepresentationLevelSideB);

    /**
     * Finds DiplomaticExchange objects that match the given value in any diplomatic exchange field.
     *
     * @param anyDiplomaticExchange the value to filter the DiplomaticExchange objects by in any diplomatic exchange field
     * @return a list of DiplomaticExchange objects that have the specified value in any diplomatic exchange field
     */
    List<DiplomaticExchange> findByAnyDiplomaticExchange(String anyDiplomaticExchange);
}
