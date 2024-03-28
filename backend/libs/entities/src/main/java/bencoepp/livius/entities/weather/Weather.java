package bencoepp.livius.entities.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * The Weather class represents weather information.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "weather")
public class Weather {
    @Transient
    public static final String SEQUENCE_NAME="weather_seq";
    @Id
    private String id;
    private String station;
    private String date;
    private String source;
    private Float latitude;
    private Float longitude;
    private Float elevation;
    private String name;
    private String report_type;
    private String call_sign;
    private String quality_control;
    private String wind;
    private Float cig;
    private String temperature;
    private String dew;
    private String slp;

    public Weather(String line) {
        String[] data = line.split(",");
        this.station = data[0];
        this.date = data[1];
        this.source = data[2];
        this.latitude = parseToFloat(data[3]);
        this.longitude = parseToFloat(data[4]);
        this.elevation = parseToFloat(data[5]);
        this.name = data[6];
        this.report_type = data[7];
        this.call_sign = data[8];
        this.quality_control = data[9];
        this.wind = data[10];
        this.cig = parseToFloat(data[11]);
        this.temperature = data[12];
        this.dew = data[13];
        this.slp = data[14];
    }

    /**
     * This method is used to parse a String value to a float.
     *
     * @param value the String value to be parsed
     * @return the parsed float value
     */
    private float parseToFloat(String value){
        String sanitized = value.replaceAll("\"", ""); // remove all quotation marks
        return Float.parseFloat(sanitized);
    }
}