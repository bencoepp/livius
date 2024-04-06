package bencoepp.livius.entities.religion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String name;
    private Integer christianProtestantNoOfAdherents;
    private Integer christianRomanCatholicsNoOfAdherents;
    private Integer christianEasternOrthodoxNoOfAdherents;
    private Integer christianAnglicanNoOfAdherents;
    private Integer christianOthersNoOfAdherents;
    private Integer christianTotalNoOfAdherents;
    private Integer judaismOrthodoxNoOfAdherents;
    private Integer judaismConservativesNoOfAdherents;
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
    private Integer syncreticReligionsTotalNoOfAdherents;
    private Integer animistReligionsTotalNoOfAdherents;
    private Integer nonReligiousTotalNoOfAdherents;
    private Integer otherTotalNoOfAdherents;
    private Integer sumOfReligionAdherents;
    private Integer totalPopulation;
    private Integer christianityProtestantsPctAdherents;
    private Integer christianityRomanCatholicsPctAdherents;
    private Integer christianityEasternOrthodoxPctAdherents;
    private Integer christianityAnglicanPctAdherents;
    private Integer christianityOthersPctAdherents;
    private Integer christianityGeneralChrstPctAdherents;
    private Integer judaismOrthodoxPctAdherents;
    private Integer judaismConservativesPctAdherents;
    private Integer judaismReformPctAdherents;
    private Integer judaismOthersPctAdherents;
    private Integer judaismGeneralJewishPctAdherents;
    private Integer islamSunniPctAdherents;
    private Integer islamShiaPctAdherents;
    private Integer islamIbadhiPctAdherents;
    private Integer islamNationofIslamPctAdherents;
    private Integer islamAlawitePctAdherents;
    private Integer islamAhmadiyyaPctAdherents;
    private Integer islamOtherPctAdherents;
    private Integer islamGeneralMuslimPctAdherents;
    private Integer buddhismMahayanaPctAdherents;
    private Integer buddhismTheravadaPctAdherents;
    private Integer buddhismOtherPctAdherents;
    private Integer buddhismGenBuddhistPctAdherents;
    private Integer zoroastrianZoroPctAdherents;
    private Integer hinduPctAdherents;
    private Integer sikhPctAdherents;
    private Integer shintoPctAdherents;
    private Integer bahaiPctAdherents;
    private Integer taoismPctAdherents;
    private Integer jainPctAdherents;
    private Integer confucianismPctAdherents;
    private Integer syncreticReligionsPctAdherents;
    private Integer animistReligionsPctAdherents;
    private Integer nonReligiousPctAdherents;
    private Integer otherReligionsPctAdherents;
    private Integer sumPercentReligiousAdherents;
    private Integer totalPopulationPercantageCheck;
    private Boolean dualReligion;
    private String dataType;
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
    public final static String RELIABILITY_OF_RECORD_VERY_HIGH = "very_high";
    public final static String RELIABILITY_OF_RECORD_VERY_LOW = "very_low";
    private String levelOfReliabilityOfRecord;
    public final static String LEVEL_OF_RELIABILITY_OF_RECORD_HIGH = "high";
    public final static String LEVEL_OF_RELIABILITY_OF_RECORD_MEDIUM = "medium";
    public final static String LEVEL_OF_RELIABILITY_OF_RECORD_LOW = "low";
    private String sourceCode;
}
