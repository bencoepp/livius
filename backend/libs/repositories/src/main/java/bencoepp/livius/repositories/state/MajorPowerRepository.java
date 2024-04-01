package bencoepp.livius.repositories.state;

import bencoepp.livius.entities.state.MajorPower;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The MajorPowerRepository interface represents a repository for managing MajorPower documents in a MongoDB database.
 * It extends the MongoRepository interface, providing basic CRUD operations and additional methods.
 *
 * @param <MajorPower> The entity type of the repository, which is MajorPower in this case.
 * @param <String> The type of the identifier property of the entity, which is String in this case.
 */
public interface MajorPowerRepository extends MongoRepository<MajorPower, String> {
}
