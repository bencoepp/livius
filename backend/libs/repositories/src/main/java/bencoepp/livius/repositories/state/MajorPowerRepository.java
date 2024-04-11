package bencoepp.livius.repositories.state;

import bencoepp.livius.entities.state.MajorPower;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * The MajorPowerRepository interface represents a repository for managing MajorPower documents in a MongoDB database.
 * It extends the MongoRepository interface, providing basic CRUD operations and additional methods.
 */
public interface MajorPowerRepository extends MongoRepository<MajorPower, String> {
    /**
     * Finds MajorPower entities by state.
     *
     * @param state the state to search for
     * @return a list of MajorPower entities matching the given state
     */
    List<MajorPower> findByState(String state);

    /**
     * Finds MajorPower entities by CowId.
     *
     * @param cowId the CowId to search for
     * @return a List of MajorPower entities matching the given CowId
     */
    List<MajorPower> findByCowId(Integer cowId);

    /**
     * Finds MajorPower entities by start date.
     *
     * @param startDate the start date to search for
     * @return a List of MajorPower entities with the provided start date
     */
    List<MajorPower> findByStartDate(Date startDate);

    /**
     * Retrieves a list of MajorPower entities with the specified end date.
     *
     * @param endDate the end date to search for
     * @return a list of MajorPower entities with the provided end date
     */
    List<MajorPower> findByEndDate(Date endDate);

    /**
     * Retrieves a list of MajorPower entities with a start date after the provided date.
     *
     * @param startDate the start date to search for
     * @return a list of MajorPower entities with the start date after the provided date
     */
    List<MajorPower> findByStartDateAfter(Date startDate);

    /**
     * Finds MajorPower entities with a start date before the provided date.
     *
     * @param startDate the start date to search for
     * @return a list of MajorPower entities with the start date before the provided date
     */
    List<MajorPower> findByStartDateBefore(Date startDate);

    /**
     * Finds MajorPower entities with an end date before the provided date.
     *
     * @param endDate the end date to search for
     * @return a List of MajorPower entities with the end date before the provided date
     */
    List<MajorPower> findByEndDateBefore(Date endDate);

    /**
     * Retrieves a list of MajorPower entities with an end date after the provided date.
     *
     * @param endDate the end date to search for
     * @return a list of MajorPower entities with the end date after the provided date
     */
    List<MajorPower> findByEndDateAfter(Date endDate);

    /**
     * Finds MajorPower entities that have a start date after the provided start date and an end date before the provided end date.
     *
     * @param startDate the start date to search for
     * @param endDate the end date to search for
     * @return a List of MajorPower entities that satisfy the conditions
     */
    List<MajorPower> findByStartDateAfterAndEndDateBefore(Date startDate, Date endDate);
}
