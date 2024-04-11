package bencoepp.livius.repositories.state;

import bencoepp.livius.entities.state.TerritorialChange;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * The TerritorialChangeRepository interface provides methods for performing CRUD operations on
 * instances of the TerritorialChange class.
 *
 * <p>
 * This interface extends the CrudRepository interface, which provides basic CRUD operations like
 * save, delete, and find.
 *
 * <p>
 * Example usage:
 * <pre>{@code
 * TerritorialChangeRepository repository = new TerritorialChangeRepositoryImpl();
 *
 * TerritorialChange territorialChange = new TerritorialChange();
 * territorialChange.setId("1");
 * territorialChange.setTerritory("New Territory");
 *
 * repository.save(territorialChange);
 *
 * TerritorialChange savedTerritorialChange = repository.findById("1");
 *
 * repository.delete(territorialChange);
 * }</pre>
 *
 * @param <TerritorialChange> the type of the territorial change entity
 * @param <String> the type of the identifier for the territorial change entity
 */
public interface TerritorialChangeRepository extends MongoRepository<TerritorialChange, String> {
    List<TerritorialChange> findByTerritorialChangeNumber(Integer territorialChangeNumber);

    /**
     * Finds all territorial changes that occurred in the given year.
     *
     * @param year the year to search for territorial changes
     * @return a list of territorial changes that occurred in the given year
     */
    List<TerritorialChange> findByYear(Integer year);

    /**
     * Finds all territorial changes that occurred in the year less than or equal to the given year.
     *
     * @param year the maximum year to search for territorial changes
     * @return a list of territorial changes that occurred in the year less than or equal to the given year
     */
    List<TerritorialChange> findByYearLessThanEqual(Integer year);

    /**
     * Finds all territorial changes that occurred in the year greater than or equal to the given year.
     *
     * @param year the minimum year to search for territorial changes
     * @return a list of territorial changes that occurred in the year greater than or equal to the given year
     */
    List<TerritorialChange> findByYearGreaterThanEqual(Integer year);

    /**
     * Finds all territorial changes that occurred in the given month.
     *
     * @param month the month to search for territorial changes
     * @return a list of territorial changes that occurred in the given month
     */
    List<TerritorialChange> findByMonth(String month);

    /**
     * Finds all territorial changes that occurred in the given year and month.
     *
     * @param year the year to search for territorial changes
     * @param month the month to search for territorial changes
     * @return a list of territorial changes that occurred in the given year and month
     */
    List<TerritorialChange> findByYearAndMonth(Integer year, String month);

    /**
     * Finds all territorial changes by the ID of the gaining side.
     *
     * @param id the ID of the gaining side to search for territorial changes
     * @return a list of territorial changes with the specified gaining side ID
     */
    List<TerritorialChange> findByGainingSide_Id(String id);

    /**
     * Finds all territorial changes by the ID of the losing side.
     *
     * @param id the ID of the losing side to search for territorial changes
     * @return a list of territorial changes with the specified losing side ID
     */
    List<TerritorialChange> findByLosingSide_Id(String id);

    /**
     * Finds all territorial changes with the specified type of change for the gaining side.
     *
     * @param typeOfChangeForGainingSide the type of change for the gaining side to search for territorial changes
     * @return a list of territorial changes with the specified type of change for the gaining side
     */
    List<TerritorialChange> findByTypeOfChangeForGainingSide(String typeOfChangeForGainingSide);

    /**
     * Finds all territorial changes with the specified procedure.
     *
     * @param procedure the procedure to search for territorial changes
     * @return a list of territorial changes with the specified procedure
     */
    List<TerritorialChange> findByProcedure(String procedure);

    /**
     * Finds all territorial changes with the specified type of change for the losing side.
     *
     * @param typeOfChangeForLosingSide the type of change for the losing side to search for territorial changes
     * @return a list of territorial changes with the specified type of change for the losing side
     */
    List<TerritorialChange> findByTypeOfChangeForLosingSide(String typeOfChangeForLosingSide);

    /**
     * Finds all territorial changes with the specified entity exchanged.
     *
     * @param entityExchanged the entity exchanged to search for territorial changes
     * @return a list of territorial changes with the specified entity exchanged
     */
    List<TerritorialChange> findByEntityExchanged(String entityExchanged);
}
