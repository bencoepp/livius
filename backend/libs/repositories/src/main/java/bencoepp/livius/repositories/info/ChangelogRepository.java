package bencoepp.livius.repositories.info;

import bencoepp.livius.entities.info.Changelog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * The ChangelogRepository interface is used to interact with the database collection "changelogs".
 * It extends the MongoRepository interface, providing CRUD (Create, Read, Update, Delete) operations for the Changelog class.
 */
public interface ChangelogRepository extends MongoRepository<Changelog, String> {

    List<Changelog> findByCreatedNotNullOrderByIdDesc();
}
