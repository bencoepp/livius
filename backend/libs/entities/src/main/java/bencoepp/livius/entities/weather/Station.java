package bencoepp.livius.entities.weather;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.util.List;

@Getter
@Setter
@Document(collection = "weather_stations")
public class Station {
    @Transient
    public static final String SEQUENCE_NAME="weather_station_seq";
    @Id
    private String id;
    private String name;
    private String country;
    private String region;
    @DBRef
    private List<Identifier> identifiers;
    @DocumentReference(lazy = true)
    private Location location;
    private String timezone;
}
