package bencoepp.livius.entities.weather;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "weather_station_identifiers")
public class Identifier {
    @Transient
    public static final String SEQUENCE_NAME="weather_station_identifier_seq";
    @Id
    private String national;
    private String wmo;
    private String icao;
    private String iata;
}
