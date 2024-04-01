package bencoepp.livius.repositories.state;

import bencoepp.livius.entities.state.State;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * StateRepository is an interface that extends the MongoRepository interface. It is responsible for defining
 * the operations that can be performed on the "states" collection in the MongoDB database.
 *
 * The StateRepository interface provides methods for basic CRUD operations (Create, Read, Update, Delete), as well as
 * additional methods for querying and sorting the state documents.
 *
 * Example usage:
 *     StateRepository stateRepository = new StateRepositoryImpl();
 *
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
 *
 *     // Find a state document by ID
 *     Optional<State> optionalState = stateRepository.findById("1");
 *     if (optionalState.isPresent()) {
 *         State retrievedState = optionalState.get();
 *         System.out.println(retrievedState);
 *     }
 *
 * Note: The State and MajorPower classes are not part of the documentation, but are necessary for understanding
 * the structure of the state documents in the database.
 */
public interface StateRepository extends MongoRepository<State, String> {
}
