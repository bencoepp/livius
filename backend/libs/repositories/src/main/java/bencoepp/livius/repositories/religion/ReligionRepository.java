package bencoepp.livius.repositories.religion;

import bencoepp.livius.entities.religion.Religion;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * The {@code ReligionRepository} interface represents a repository for managing {@link Religion} objects.
 * It provides CRUD (Create, Read, Update, Delete) operations for the {@link Religion} objects.
 * This repository extends the {@link MongoRepository} interface and uses MongoDB for data persistence.
 */
public interface ReligionRepository extends MongoRepository<Religion, String> {
}
