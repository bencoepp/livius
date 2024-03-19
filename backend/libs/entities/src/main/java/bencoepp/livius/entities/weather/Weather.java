package bencoepp.livius.entities.weather;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.sql.Timestamp;

/**
 * The Weather class represents weather data for a specific station and date.
 */
@Getter
@Setter
@Document(collection = "weather")
public class Weather {
    @Transient
    public static final String SEQUENCE_NAME="weather_seq";
    @Id
    private String id;
    /**
     * Represents the station associated with weather data.
     */
    private String station;
    /**
     * Represents the date associated with the weather data.
     * <p>
     * The `date` variable is of type `Timestamp` and is a private member of the `Weather` class. It stores the time and date information of the weather data.
     *
     * @see Weather
     */
    private Timestamp date;
    private String source;
    private Float latitude;
    private Float longitude;
    private Float elevation;
    private String name;
    private String report_type;
    private String call_sign;
    private String quality_control;
    private Float wind;
    private Float cig;
    private Float temperature;
    private Float dew;
    private Float slp;
}
