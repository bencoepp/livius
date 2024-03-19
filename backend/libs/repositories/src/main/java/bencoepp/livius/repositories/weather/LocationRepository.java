package bencoepp.livius.repositories.weather;

import bencoepp.livius.entities.weather.Location;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LocationRepository extends MongoRepository<Location, String> {
}
