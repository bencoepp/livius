package bencoepp.livius.repositories.state;

import bencoepp.livius.entities.state.State;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Date;
import java.util.List;

/**
 * StateRepository is an interface that extends the MongoRepository interface. It is responsible for defining
 * the operations that can be performed on the "states" collection in the MongoDB database.
 * <p>
 * The StateRepository interface provides methods for basic CRUD operations (Create, Read, Update, Delete), as well as
 * additional methods for querying and sorting the state documents.
 * <p>
 * Example usage:
 *     StateRepository stateRepository = new StateRepositoryImpl();
 * <p>
 *     // Save a new state document
 *     State state = new State();
 *     state.setId("1");
 *     state.setCowData(true);
 *     state.setCode("US");
 *     state.setCowId("745");
 *     state.setName("United States");
 *     state.setStartDate(new Date());
 *     state.setEndDate(null);
 *     state.setCowVersion("5.0");
 *     state.setWasMajorPower(new ArrayList<>());
 *     stateRepository.save(state);
 * <p>
 *     // Find a state document by ID
 *     Optional<State> optionalState = stateRepository.findById("1");
 *     if (optionalState.isPresent()) {
 *         State retrievedState = optionalState.get();
 *         System.out.println(retrievedState);
 *     }
 * <p>
 * Note: The State and MajorPower classes are not part of the documentation, but are necessary for understanding
 * the structure of the state documents in the database.
 */
public interface StateRepository extends MongoRepository<State, String> {
    /**
     * Check if a state document exists in the "states" collection of the MongoDB database
     * based on the given cowId and code.
     *
     * @param cowId the COW ID of the state to check
     * @param code the code associated with the state to check
     * @return true if a state document exists with the given cowId and code, false otherwise
     */
    boolean existsByCowIdAndCode(Integer cowId, String code);

    /**
     * Finds a list of states in the "states" collection of the MongoDB database
     * based on the given cowId and code.
     *
     * @param cowId the COW ID of the states to search for
     * @param code the code associated with the states to search for
     * @return a list of {@link State} objects matching the given cowId and code
     */
    List<State> findByCowIdAndCode(Integer cowId, String code);

    /**
     * Finds a list of states in the "states" collection of the MongoDB database
     * based on the given cowId.
     *
     * @param cowId the COW ID of the states to search for
     * @return a list of {@link State} objects matching the given cowId
     */
    List<State> findByCowId(Integer cowId);

    /**
     * Finds a list of states in the "states" collection of the MongoDB database
     * based on the given cowId and name.
     *
     * @param cowId the COW ID of the states to search for
     * @param name the name of the states to search for
     * @return a list of {@link State} objects matching the given cowId and name
     */
    List<State> findByCowIdAndName(Integer cowId, String name);

    /**
     * Check if a state document exists in the "states" collection of the MongoDB database
     * based on the given name.
     *
     * @param name the name of the state to check
     * @return true if a state document exists with the given name, false otherwise
     */
    boolean existsByName(String name);

    /**
     * Finds a list of states by name in the "states" collection of the MongoDB database.
     *
     * @param name the name of the states to search for
     * @return a list of {@link State} objects matching the given name
     */
    List<State> findByName(String name);

    /**
     * Finds a list of states in the "states" collection of the MongoDB database
     * based on the given code.
     *
     * @param code the code associated with the states to search for
     * @return a list of {@link State} objects matching the given code
     */
    List<State> findByCode(String code);

    /**
     * Finds a list of states in the "states" collection of the MongoDB database based on the given start date.
     *
     * @param startDate the start date to search for
     * @return a list of {@link State} objects matching the given start date
     */
    List<State> findByStartDate(Date startDate);

    /**
     * Finds a list of states in the "states" collection of the MongoDB database based on the given end date.
     *
     * @param endDate the end date to search for
     * @return a list of {@link State} objects matching the given end date
     */
    List<State> findByEndDate(Date endDate);

    /**
     * Finds a list of states in the "states" collection of the MongoDB database
     * based on the given start date after the specified date.
     *
     * @param startDate the start date to search for
     * @return a list of {@link State} objects matching the given start date
     */
    List<State> findByStartDateAfter(Date startDate);

    /**
     * Finds a list of states in the "states" collection of the MongoDB database
     * based on the given start date before the specified date.
     *
     * @param startDate the start date to search for
     * @return a list of {@link State} objects matching the given start date
     */
    List<State> findByStartDateBefore(Date startDate);

    /**
     * Finds a list of states in the "states" collection of the MongoDB database
     * based on the given end date before the specified date.
     *
     * @param endDate the end date to search for
     * @return a list of {@link State} objects matching the given end date
     */
    List<State> findByEndDateBefore(Date endDate);

    /**
     * Finds a list of states in the "states" collection of the MongoDB database
     * based on the given end date after the specified date.
     *
     * @param endDate the end date to search for
     * @return a list of {@link State} objects matching the given end date
     */
    List<State> findByEndDateAfter(Date endDate);

    /**
     * Finds a list of states in the "states" collection of the MongoDB database
     * based on the given start date after the specified date and end date before the specified date.
     *
     * @param startDate the start date to search for, not null
     * @param endDate the end date to search for, not null
     * @return a list of {@link State} objects matching the given start date after the specified date and end date before the specified date
     */
    List<State> findByStartDateAfterAndEndDateBefore(Date startDate, Date endDate);
}
