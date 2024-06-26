package bencoepp.livius.repositories.weather;

import bencoepp.livius.entities.weather.Weather;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * The WeatherRepository interface extends the MongoRepository interface.
 * It provides the CRUD operations for managing Weather objects in a MongoDB database.
 */
public interface WeatherRepository extends MongoRepository<Weather, String> {
    boolean existsByStation(String station);

    long countByStation(String station);

    Weather findFirstByIdNotNullOrderByIdDesc();

    long deleteByDateContains(String date);
}
