package bencoepp.livius.repositories.weather;

import bencoepp.livius.entities.weather.Station;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StationRepository extends MongoRepository<Station, String> {
}
