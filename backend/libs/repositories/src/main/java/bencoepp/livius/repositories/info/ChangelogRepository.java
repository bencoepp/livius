package bencoepp.livius.repositories.info;

import bencoepp.livius.entities.info.Changelog;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The ChangelogRepository interface extends the MongoRepository interface
 * and provides methods for accessing and manipulating Changelog documents in the database.
 */
public interface ChangelogRepository extends MongoRepository<Changelog, String> {
}
