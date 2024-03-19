package bencoepp.livius.repositories.weather;

import bencoepp.livius.entities.weather.Identifier;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IdentifierRepository extends MongoRepository<Identifier, String> {
}
