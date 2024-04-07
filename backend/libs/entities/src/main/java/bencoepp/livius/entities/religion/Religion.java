package bencoepp.livius.entities.religion;

import bencoepp.livius.entities.state.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

/**
 * The {@code Religion} class represents a religion with different attributes and properties.
 * It is used for storing and managing religious data related to a specific year and location.
 *
 * This class is annotated with {@link @Getter}, {@link @Setter}, {@link @NoArgsConstructor},
 * {@link @AllArgsConstructor}, and {@link @Document} annotations to provide getter and setter methods,
 * default constructor, parameterized constructor, and document collection information respectively.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "religions")
public class Religion {
    @Transient
    private static final String SEQUENCE_NAME="weather_seq";
    @Id
    private String id;
    private Integer year;
    private Integer cowId;
    @DocumentReference
    private List<State> state;
    /**
     * Number of adherents belonging to the Christian Protestant religion.
     */
    private Integer christianProtestantNoOfAdherents;
    /**
     * Represents the number of adherents of Roman Catholics in the Christian religion.
     *
     * This variable stores an Integer value.
     */
    private Integer christianRomanCatholicsNoOfAdherents;
    /**
     * The number of adherents of Eastern Orthodox Christianity.
     */
    private Integer christianEasternOrthodoxNoOfAdherents;
    /**
     * The number of adherents to the Christian Anglican denomination.
     */
    private Integer christianAnglicanNoOfAdherents;
    /**
     * Number of adherents belonging to the category of Christian Others.
     */
    private Integer christianOthersNoOfAdherents;
    /**
     * The total number of adherents of Christianity.
     */
    private Integer christianTotalNoOfAdherents;
    /**
     * The number of adherents of Orthodox Judaism.
     */
    private Integer judaismOrthodoxNoOfAdherents;
    /**
     * The number of adherents of Judaism who identify as Conservatives.
     */
    private Integer judaismConservativesNoOfAdherents;
    /**
     * Number of adherents in Judaism who identify with the Reform movement.
     */
    private Integer judaismReformNoOfAdherents;
    private Integer judaismOthersNoOfAdherents;
    private Integer judaismTotalNoOfAdherents;
    private Integer islamSunniNoOfAdherents;
    private Integer islamShiaNoOfAdherents;
    private Integer islamIbadhiNoOfAdherents;
    private Integer islamNationOfIslamNoOfAdherents;
    private Integer islamAlawiteNoOfAdherents;
    private Integer islamAhmadiyyaNoOfAdherents;
    private Integer islamOtherNoOfAdherents;
    private Integer islamTotalNoOfAdherents;
    private Integer buddhismMahayanaNoOfAdherents;
    private Integer buddhismTheravadaNoOfAdherents;
    private Integer buddhismOtherNoOfAdherents;
    private Integer buddhismTotalNoOfAdherents;
    private Integer zoroastrianTotalNoOfAdherents;
    private Integer hinduTotalNoOfAdherents;
    private Integer sikhTotalNoOfAdherents;
    private Integer shintoTotalNoOfAdherents;
    private Integer bahaiTotalNoOfAdherents;
    private Integer taoismTotalNoOfAdherents;
    private Integer confucianismTotalNoOfAdherents;
    private Integer jainTotalNoOfAdherents;
    private Integer syncreticReligionsTotalNoOfAdherents;
    private Integer animistReligionsTotalNoOfAdherents;
    private Integer nonReligiousTotalNoOfAdherents;
    private Integer otherTotalNoOfAdherents;
    private Integer sumOfReligionAdherents;
    private Integer totalPopulation;
    private Double christianityProtestantsPctAdherents;
    private Double christianityRomanCatholicsPctAdherents;
    private Double christianityEasternOrthodoxPctAdherents;
    private Double christianityAnglicanPctAdherents;
    private Double christianityOthersPctAdherents;
    private Double christianityGeneralChrstPctAdherents;
    private Double judaismOrthodoxPctAdherents;
    private Double judaismConservativesPctAdherents;
    private Double judaismReformPctAdherents;
    private Double judaismOthersPctAdherents;
    private Double judaismGeneralJewishPctAdherents;
    private Double islamSunniPctAdherents;
    private Double islamShiaPctAdherents;
    private Double islamIbadhiPctAdherents;
    private Double islamNationofIslamPctAdherents;
    private Double islamAlawitePctAdherents;
    private Double islamAhmadiyyaPctAdherents;
    private Double islamOtherPctAdherents;
    private Double islamGeneralMuslimPctAdherents;
    private Double buddhismMahayanaPctAdherents;
    private Double buddhismTheravadaPctAdherents;
    private Double buddhismOtherPctAdherents;
    private Double buddhismGenBuddhistPctAdherents;
    private Double zoroastrianZoroPctAdherents;
    private Double hinduPctAdherents;
    private Double sikhPctAdherents;
    private Double shintoPctAdherents;
    private Double bahaiPctAdherents;
    private Double taoismPctAdherents;
    private Double jainPctAdherents;
    private Double confucianismPctAdherents;
    private Double syncreticReligionsPctAdherents;
    private Double animistReligionsPctAdherents;
    private Double nonReligiousPctAdherents;
    private Double otherReligionsPctAdherents;
    private Double sumPercentReligiousAdherents;
    private Double totalPopulationPercantageCheck;
    private Boolean dualReligion;
    private String dataType;
    public final static String TYPE_NONE = "none";
    public final static String TYPE_SINGLE_SOURCE = "single_source";
    public final static String TYPE_MULTIPLE_SOURCE = "multiple_source";
    public final static String TYPE_INTERPOLATED = "interpolated";
    public final static String TYPE_ADJUSTED = "adjusted";
    public final static String TYPE_SINGLE_SOURCE_INTERPOLATED = "single_source_interpolated";
    public final static String TYPE_SINGLE_SOURCE_ADJUSTED = "single_source_adjusted";
    public final static String TYPE_SINGLE_SOURCE_INTERPOLATED_ADJUSTED = "single_source_interpolated_adjusted";
    public final static String TYPE_MULTIPLE_SOURCE_INTERPOLATED = "multiple_source_interpolated";
    public final static String TYPE_MULTIPLE_SOURCE_ADJUSTED = "multiple_source_adjusted";
    public final static String TYPE_MULTIPLE_SOURCE_INTERPOLATED_ADJUSTED = "multiple_source_interpolated_adjusted";
    public final static String TYPE_INTERPOLATED_ADJUSTED = "interpolated_adjusted";
    private String reliabilityOfRecord;
    public final static String RELIABILITY_OF_RECORD_NONE = "none";
    public final static String RELIABILITY_OF_RECORD_VERY_HIGH = "very_high";
    public final static String RELIABILITY_OF_RECORD_VERY_LOW = "very_low";
    private String levelOfReliabilityOfRecord;
    public final static String LEVEL_OF_RELIABILITY_OF_RECORD_NONE = "none";
    public final static String LEVEL_OF_RELIABILITY_OF_RECORD_HIGH = "high";
    public final static String LEVEL_OF_RELIABILITY_OF_RECORD_MEDIUM = "medium";
    public final static String LEVEL_OF_RELIABILITY_OF_RECORD_LOW = "low";
    private String sourceCode;
}
