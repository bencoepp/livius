package bencoepp.livius.entities.weather;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "weather_station_locations")
public class Location {
    @Transient
    public static final String SEQUENCE_NAME="weather_station_location_seq";
    @Id
    private String id;
    private Float latitude;
    private Float longitude;
    private Float elevation;
}
