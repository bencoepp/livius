package bencoepp.livius.entities.war;

import bencoepp.livius.entities.state.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.util.Date;
import java.util.List;

/**
 * The War class represents a war document in the "wars" collection of a MongoDB database.
 * It contains various properties that describe the characteristics of a war.
 *
 * This class is annotated with the following annotations:
 * - @Getter: Generates getter methods for all non-static fields of the class.
 * - @Setter: Generates setter methods for all non-static fields of the class.
 * - @NoArgsConstructor: Generates a no-argument constructor for the class.
 * - @AllArgsConstructor: Generates a constructor with arguments for all fields of the class.
 * - @Document(collection = "wars"): Specifies that objects of this class will be stored in the "wars" collection
 *   in the MongoDB database.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "wars")
public class War {
    @Transient
    public static final String SEQUENCE_NAME="war_seq";
    @Id
    private String id;
    private Integer cowId;
    private String name;
    private String type;
    public static final String TYPE_NONE = "none";
    public static final String TYPE_COLONIAL_WAR = "colonial_war";
    public static final String TYPE_IMPERIAL_WAR = "imperial_war";
    private String region;
    public static final String REGION_NORTH_AMERICA = "north_america";
    public static final String REGION_SOUTH_AMERICA = "south_america";
    public static final String REGION_EUROPE = "europe";
    public static final String REGION_SUB_SAHARAN_AFRIKA = "sub_saharan_afrika";
    public static final String REGION_MIDDLE_EAST = "middle_east";
    public static final String REGION_NORTH_AFRIKA = "north_afrika";
    public static final String REGION_ASIA_AND_OCEANIA = "asia_and_oceania";
    /**
     * The sideA variable represents a list of State objects.
     * <p>
     * The sideA variable is a private instance variable of type List<State> in the War class.
     * It holds a list of State objects that belong to the side A of the war.
     * <p>
     * The State class represents a state document in a MongoDB database and contains properties that describe the characteristics of a state.
     * <p>
     * Example usage:
     * <pre>{@code
     * War war = new War();
     * List<State> sideA = war.getSideA();
     *
     * for (State state : sideA) {
     *     System.out.println(state.getName());
     *     System.out.println(state.getCode());
     *     // Access other properties of the state object...
     * }
     * }</pre>
     *
     * @see War
     * @see War#getSideA()
     * @see State
     */
    @DocumentReference
    private List<State> sideA;
    /**
     * Represents the "sideB" variable in the War class.
     * <p>
     * This variable is a private instance variable of type List<State>.
     * It is used to store the list of states on side B of the war.
     * <p>
     * It is recommended to use the getter and setter methods provided by the War class to access or modify the value of this variable.
     * <p>
     * Example usage:
     * <pre>{@code
     * War war = new War();
     * war.setSideB(states);
     * List<State> sideB = war.getSideB();
     * }</pre>
     *
     * @see War
     * @see War#getSideB()
     * @see War#setSideB(List)
     */
    @DocumentReference
    private List<State> sideB;
    private List<Date> startDates;
    private List<Date> endDates;
    private Boolean initiator;
    private Integer transTo;
    private Integer transFrom;
    private String outcome;
    public static final String OUTCOME_NONE = "none";
    public static final String OUTCOME_SIDE_A_WIN = "side_a_win";
    public static final String OUTCOME_SIDE_B_WIN = "side_b_win";
    public static final String OUTCOME_COMPROMISE = "compromise";
    public static final String OUTCOME_TRANSFORM_INTO_WAR = "transform_into_war";
    public static final String OUTCOME_CONTINUING = "continuing";
    public static final String OUTCOME_STALEMATE = "stalemate";
    public static final String OUTCOME_CONTINUE_BELOW_WAR_LEVEL = "continue_below_war_level";
    /**
     * The sideADeaths variable represents the number of deaths on side A in a war.
     * <p>
     * It is a private Long variable in the War class.
     * <p>
     * The value of sideADeaths represents the number of deaths that occurred on side A during the course of the war.
     * It is used to track the casualties and fatalities on side A.
     * <p>
     * Example usage:
     * <pre>{@code
     * War war = new War();
     * war.setSideADeaths(1000L);
     * Long deaths = war.getSideADeaths();
     * }</pre>
     *
     * @see War
     * @see War#getSideADeaths()
     * @see War#setSideADeaths(Long)
     */
    private Long sideADeaths;
    /**
     * Represents the number of deaths on side B during a war.
     * <p>
     * This variable is of type Long and is marked as private. It holds the total number of deaths that occurred on
     * side B during a war. The value is not accessible outside the class it belongs to.
     * <p>
     * It is recommended to use the getter and setter methods provided by the class to access or modify the value of
     * this variable, as it is marked as private.
     * <p>
     * Example usage:
     * <pre>{@code
     * War war = new War();
     * war.setSideBDeaths(10000L);
     * Long deaths = war.getSideBDeaths();
     * }</pre>
     *
     * @see War
     * @see War#getSideBDeaths()
     * @see War#setSideBDeaths(Long)
     */
    private Long sideBDeaths;
    /**
     * Represents the total number of compatible deaths in a war.
     * <p>
     * This variable is a private instance variable within the War class.
     * It is of type Long and holds the total number of compatible deaths that occurred in the war.
     * Compatible deaths refer to the deaths that occurred between the two fighting sides in the war.
     * This includes deaths of soldiers, civilians, and any other individuals involved directly or indirectly in the war.
     * <p>
     * The value of totalCompatDeaths can be retrieved and modified using the getter and setter methods provided by the War class.
     * It is recommended to use these methods to ensure encapsulation and maintain data integrity.
     * <p>
     * Example usage:
     * <pre>{@code
     * War war = new War();
     * war.setTotalCompatDeaths(10000L);
     * Long compatDeaths = war.getTotalCompatDeaths();
     * }</pre>
     *
     * @see War
     * @see War#getTotalCompatDeaths()
     * @see War#setTotalCompatDeaths(Long)
     */
    private Long totalCompatDeaths;
    /**
     * The numberOfDaysTheWarLasted variable represents the duration of a war in days.
     * <p>
     * This variable is of type Integer and holds the number of days that the war lasted. It is a measure of the duration of the war.
     * <p>
     * It is recommended to use the getter and setter methods provided by the War class to access or modify the value of this variable.
     *
     * @see War#getNumberOfDaysTheWarLasted()
     * @see War#setNumberOfDaysTheWarLasted(Integer)
     */
    private Integer numberOfDaysTheWarLasted;
    /**
     * The numberOfMonthsTheWarLasted variable represents the duration of a war in months.
     * <p>
     * This variable is of type Integer and holds the number of months that the war lasted. It is a measure of the duration of the war.
     * <p>
     * It is recommended to use the getter and setter methods provided by the War class to access or modify the value of this variable.
     *
     * @see War
     * @see War#getNumberOfMonthsTheWarLasted()
     * @see War#setNumberOfMonthsTheWarLasted(Integer)
     */
    private Integer numberOfMonthsTheWarLasted;
    /**
     * Represents the total number of months of war experienced by all participants in a war.
     * <p>
     * This variable is of type Integer and holds the total number of months of war experienced by all participants
     * in a specific war. It is a measure of the collective duration of war for all participants.
     * <p>
     * It is recommended to use the getter and setter methods provided by the class to access or modify the value of this variable.
     *
     * @see War
     * @see War#getTotalNumberOfMonthsWarWasExperiencedByAllParticipants()
     * @see War#setTotalNumberOfMonthsWarWasExperiencedByAllParticipants(Integer)
     */
    private Integer totalNumberOfMonthsWarWasExperiencedByAllParticipants;
    private Boolean intervene;
    private String nonStateDeaths;

    public War(String id, Integer cowId, String name, String type, List<State> sideA, List<State> sideB, List<Date> startDates, List<Date> endDates, Boolean initiator, Integer transTo, Integer transFrom, String outcome, Boolean intervene, String region, String nonStateDeaths, Long battleDeaths) {
        this.id = id;
        this.cowId = cowId;
        this.name = name;
        this.type = type;
        this.sideA = sideA;
        this.sideB = sideB;
        this.startDates = startDates;
        this.endDates = endDates;
        this.initiator = initiator;
        this.transTo = transTo;
        this.transFrom = transFrom;
        this.outcome = outcome;
        this.intervene = intervene;
        this.region = region;
        this.nonStateDeaths = nonStateDeaths;
        this.totalCompatDeaths = battleDeaths;
    }
}
