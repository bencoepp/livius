package bencoepp.livius.repositories.war;

import bencoepp.livius.entities.war.War;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * The WarRepository interface provides CRUD operations for the War class in the MongoDB database.
 * It extends the MongoRepository interface, which is part of the Spring Data MongoDB framework.
 * The MongoRepository interface provides methods for querying and manipulating data in the MongoDB database.
 * <p>
 * This interface does not define any additional methods because it inherits all the necessary methods from the MongoRepository interface.
 * <p>
 * Example usage:
 * ```
 * WarRepository warRepository = new WarRepositoryImpl();
 * War war = new War();
 * war.setName("World War II");
 * war.setType(2);
 * war.setRegion(3);
 * warRepository.save(war);
 * <p>
 * String warId = war.getId();
 * Optional<War> retrievedWar = warRepository.findById(warId);
 * <p>
 * retrievedWar.ifPresent(w -> {
 *     System.out.println("Name: " + w.getName());
 *     System.out.println("Type: " + w.getType());
 *     System.out.println("Region: " + w.getRegion());
 * });
 * ```
 *
 * @see MongoRepository
 * @see War
 */
public interface WarRepository extends MongoRepository<War, String> {
    /**
     * Returns a list of wars associated with the given cowId.
     *
     * @param cowId the identifier of the cow
     * @return a list of wars associated with the given cowId
     */
    List<War> findByCowId(Integer cowId);

    /**
     * Finds a list of wars with the given name.
     *
     * @param name the name of the war
     * @return a list of wars with the given name
     */
    List<War> findByName(String name);

    /**
     * Finds a list of wars with names containing the specified substring.
     *
     * @param name the substring to search for in war names
     * @return a list of wars with names containing the specified substring
     */
    List<War> findByNameContains(String name);

    /**
     * Retrieves a list of wars with the given type.
     *
     * @param type the type of war to be retrieved
     * @return a list of wars with the given type
     */
    List<War> findByType(String type);

    /**
     * Finds a list of wars with the given region.
     *
     * @param region the region to search for in war regions
     * @return a list of wars with the given region
     */
    List<War> findByRegion(String region);

    /**
     * Returns a list of wars associated with the given SideA id.
     *
     * @param id the identifier of the SideA
     * @return a list of wars associated with the given SideA id
     */
    List<War> findBySideA_Id(String id);

    /**
     * Returns a list of wars associated with the given SideB id.
     *
     * @param id the identifier of the SideB
     * @return a list of wars associated with the given SideB id
     */
    List<War> findBySideB_Id(String id);

    /**
     * Retrieves a list of wars initiated by the given initiator.
     *
     * @param initiator the initiator of the wars
     * @return a list of wars initiated by the given initiator
     */
    List<War> findByInitiator(Boolean initiator);

    /**
     * Finds a list of wars with the specified transTo value.
     *
     * @param transTo the value to search for in the transTo field of the wars
     * @return a list of wars with the specified transTo value
     */
    List<War> findByTransTo(Integer transTo);

    /**
     * Finds a list of wars with the specified transFrom value.
     *
     * @param transFrom the value to search for in the transFrom field of the wars
     * @return a list of wars with the specified transFrom value
     */
    List<War> findByTransFrom(Integer transFrom);

    /**
     * Returns a list of wars with the given outcome.
     *
     * @param outcome the outcome of the war
     * @return a list of wars with the given outcome
     */
    List<War> findByOutcome(String outcome);

    /**
     * Retrieves a list of wars with Side A deaths equal to or greater than the specified value.
     *
     * @param sideADeaths the number of Side A deaths to search for
     * @return a list of wars with Side A deaths equal to or greater than the specified value
     */
    List<War> findBySideADeaths(Long sideADeaths);

    /**
     * Retrieves a list of wars with Side A deaths equal to or greater than the specified value.
     *
     * @param sideADeaths the number of Side A deaths to search for
     * @return a list of wars with Side A deaths equal to or greater than the specified value
     */
    List<War> findBySideADeathsGreaterThanEqual(Long sideADeaths);

    /**
     * Retrieves a list of wars with Side A deaths less than or equal to the specified value.
     *
     * @param sideADeaths the number of Side A deaths to search for
     * @return a list of wars with Side A deaths less than or equal to the specified value
     */
    List<War> findBySideADeathsLessThanEqual(Long sideADeaths);

    /**
     * Returns a list of wars with Side B deaths equal to the specified value.
     *
     * @param sideBDeaths the number of Side B deaths to search for
     * @return a list of wars with Side B deaths equal to the specified value
     */
    List<War> findBySideBDeaths(Long sideBDeaths);

    /**
     * Retrieves a list of wars with Side B deaths equal to or greater than the specified value.
     *
     * @param sideBDeaths the number of Side B deaths to search for
     * @return a list of wars with Side B deaths equal to or greater than the specified value
     */
    List<War> findBySideBDeathsGreaterThanEqual(Long sideBDeaths);

    /**
     * Retrieves a list of wars with Side B deaths less than or equal to the specified value.
     *
     * @param sideBDeaths the number of Side B deaths to search for
     * @return a list of wars with Side B deaths less than or equal to the specified value
     */
    List<War> findBySideBDeathsLessThanEqual(Long sideBDeaths);

    /**
     * Retrieves a list of wars with total combat deaths equal to the specified value.
     *
     * @param totalCompatDeaths the number of total combat deaths to search for
     * @return a list of wars with total combat deaths equal to the specified value
     */
    List<War> findByTotalCompatDeaths(Long totalCompatDeaths);

    /**
     * Retrieves a list of wars with total combat deaths greater than or equal to the specified value.
     *
     * @param totalCompatDeaths the number of total combat deaths to search for
     * @return a list of wars with total combat deaths greater than or equal to the specified value
     */
    List<War> findByTotalCompatDeathsGreaterThanEqual(Long totalCompatDeaths);

    /**
     * Retrieves a list of wars with total combat deaths less than or equal to the specified value.
     *
     * @param totalCompatDeaths the number of total combat deaths to search for
     * @return a list of wars with total combat deaths less than or equal to the specified value
     */
    List<War> findByTotalCompatDeathsLessThanEqual(Long totalCompatDeaths);
}
