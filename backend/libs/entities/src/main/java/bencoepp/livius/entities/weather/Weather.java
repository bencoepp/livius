package bencoepp.livius.entities.weather;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

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
    private String reportType;
    private String callSign;
    private String qualityControl;
    private String wnd;
    private Float cig;
    private String vis;
    private String tmp;
    private String dew;
    private String slp;
    private String aa1;
    private String aa2;
    private String aa3;
    private String ab1;
    private String ad1;
    private String ae1;
    private String ah1;
    private String ah2;
    private String ah3;
    private String ah4;
    private String ah5;
    private String ah6;
    private String ai1;
    private String ai2;
    private String ai3;
    private String ai4;
    private String ai5;
    private String ai6;
    private String aj1;
    private String al1;
    private String an1;
    private String at1;
    private String at2;
    private String at3;
    private String at4;
    private String at5;
    private String at6;
    private String ga1;
    private String gd1;
    private String gd2;
    private String gd3;
    private String gd4;
    private String gf1;
    private String gj1;
    private String gk1;
    private String gp1;
    private String gq1;
    private String gr1;
    private String ka1;
    private String ka2;
    private String kb1;
    private String kb2;
    private String kb3;
    private String kd1;
    private String kd2;
    private String ke1;
    private String ma1;
    private String md1;
    private String mg1;
    private String mh1;
    private String mk1;
    private String mw1;
    private String mw2;
    private String mw3;
    private String mw4;
    private String mw5;
    private String oc1;
    private String oe1;
    private String oe2;
    private String oe3;
    private String rem;
    private String eqd;

    private String citation;
    private String accessDate;
    private Instant created;
    private Instant updated;
    private String datasetUrl;

    public Weather(String line) {
        String[] data = line.split(",");
        this.station = data[0].replaceAll("\"","");
        this.date = data[1].replaceAll("\"","");
        this.source = data[2].replaceAll("\"","");
        this.latitude = parseToFloat(data[3]);
        this.longitude = parseToFloat(data[4]);
        this.elevation = parseToFloat(data[5]);
        this.name = data[6].replaceAll("\"","");
        this.reportType = data[7].replaceAll("\"","");
        this.callSign = data[8].replaceAll("\"","");
        this.qualityControl = data[9].replaceAll("\"","");
        this.wnd = data[10].replaceAll("\"","");
        this.cig = parseToFloat(data[11]);
        this.vis = data[12].replaceAll("\"","");
        this.tmp = data[13].replaceAll("\"","");
        this.dew = data[14].replaceAll("\"","");
        this.slp = data[15].replaceAll("\"","");
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