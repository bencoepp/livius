package bencoepp.livius.repositories.state;

import bencoepp.livius.entities.state.MajorPower;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The MajorPowerRepository interface represents a repository for managing MajorPower documents in a MongoDB database.
 * It extends the MongoRepository interface, providing basic CRUD operations and additional methods.
 */
public interface MajorPowerRepository extends MongoRepository<MajorPower, String> {
}
