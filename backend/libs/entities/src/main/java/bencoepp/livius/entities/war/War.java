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
 * <p>
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
    public static final String TYPE_WARS_BETWEEN_NON_STATE_IN_NON_STATE_TERRITORY = "wars_between_non_state_in_non_state_territory";
    public static final String TYPE_WARS_BETWEEN_NSA_ACROSS_BORDER = "wars_between_nsa_across_border";
    public static final String TYPE_CIVIL_WAR_FOR_CENTRAL_CONTROL = "civil_war_for_central_control";
    public static final String TYPE_CIVIL_WAR_OVER_LOCAL_ISSUE = "civil_war_over_local_issue";
    public static final String TYPE_REGIONAL_INTERNAL = "regional_internal";
    public static final String TYPE_INTERCOMMUNAL = "intercommunal";
    private String region;
    public static final String REGION_NORTH_AMERICA = "north_america";
    public static final String REGION_WEST_HEMISPHERE = "west_hemisphere";
    public static final String REGION_SOUTH_AMERICA = "south_america";
    public static final String REGION_EUROPE = "europe";
    public static final String REGION_AFRIKA = "afrika";
    public static final String REGION_ASIA = "asia";
    public static final String REGION_OCEANIA = "oceania";
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
    /**
     * The outcome variable holds a string representing the outcome of a particular operation or event.
     *
     * <p>This variable is private and is accessible only within the class it is declared in. The outcome
     * is represented as a string data type.</p>
     *
     * <p>The meaning and possible values of the outcome string will depend on the context where it is
     * used. It could represent success, failure, an error message, or any other relevant information.</p>
     *
     * <p>Getters or setters may be provided to allow other classes to access or modify the value of the
     * outcome variable.</p>
     */
    private String outcome;
    /**
     * The OUTCOME_NONE variable represents a constant string value indicating that there is no outcome specified.
     * <p>
     * This variable is of type String and is marked as final and static, which means it cannot be modified
     * and is associated with the class rather than an instance of the class.
     * It holds the value "none" to represent the absence of any specific outcome.
     * <p>
     * Example usage:
     * <pre>{@code
     * if (outcome.equals(OUTCOME_NONE)) {
     *     // Handle the case when there is no outcome specified
     * }
     * }</pre>
     *
     * @see War
     * @see War#outcome
     * @see War#setOutcome(String)
     * @see War#getOutcome()
     */
    public static final String OUTCOME_NONE = "none";
    /**
     * The OUTCOME_SIDE_A_WIN variable represents the outcome of a war where side A emerges as the winner.
     * It is a constant String field in the War class.
     * <p>
     * The value of this variable is "side_a_win".
     * <p>
     * Example usage:
     * <pre>{@code
     * if (outcome.equals(OUTCOME_SIDE_A_WIN)) {
     *     System.out.println("Side A is the winner");
     * }
     * }</pre>
     *
     * @see War
     */
    public static final String OUTCOME_SIDE_A_WIN = "side_a_win";
    /**
     * The OUTCOME_SIDE_B_WIN constant represents the outcome of a war where side B wins.
     * <p>
     * This constant is of type String and its value is "side_b_win". It is used to indicate that side B emerged as the winner in a war.
     * <p>
     * This constant is defined in the class War, which is responsible for managing war-related data and operations.
     * <p>
     * Example usage:
     * <pre>{@code
     * if (warOutcome.equals(War.OUTCOME_SIDE_B_WIN)) {
     *     System.out.println("Side B is the winner!");
     * }
     * }</pre>
     *
     * @see War
     */
    public static final String OUTCOME_SIDE_B_WIN = "side_b_win";
    /**
     * The OUTCOME_COMPROMISE variable represents a possible outcome of a war.
     * <p>
     * This variable is of type String and is declared as a constant in the War class.
     * It holds the value "compromise" which indicates that the war ended with a compromise between the parties involved.
     * <p>
     * Example usage:
     * <pre>{@code
     * if (outcome.equals(War.OUTCOME_COMPROMISE)) {
     *     // War ended with a compromise
     * }
     * }</pre>
     */
    public static final String OUTCOME_COMPROMISE = "compromise";
    /**
     * The OUTCOME_TRANSFORM_INTO_WAR variable represents the outcome of a war transformation.
     * <p>
     * This variable is a String constant defined in the class War. It holds the value "transform_into_war".
     * It is used to indicate that the outcome of a war is a transformation into another war.
     * <p>
     * Example usage:
     * <pre>{@code
     * if (outcome.equals(OUTCOME_TRANSFORM_INTO_WAR)) {
     *     System.out.println("The war has transformed into another war.");
     * }
     * }</pre>
     *
     * @see War
     */
    public static final String OUTCOME_TRANSFORM_INTO_WAR = "transform_into_war";
    /**
     * Constant representing the outcome of a continuing war.
     * <p>
     * This variable is a {@code String} and holds the value "continuing".
     * It is used to represent the outcome of a war that is still ongoing and has not been resolved.
     * <p>
     * Example usage:
     * <pre>{@code
     * if (war.getOutcome().equals(OUTCOME_CONTINUING)) {
     *     System.out.println("The war is still ongoing");
     * }
     * }</pre>
     */
    public static final String OUTCOME_CONTINUING = "continuing";
    /**
     * The OUTCOME_STALEMATE variable represents the outcome of a war as a stalemate.
     * It is a constant of type String.
     * <p>
     * The value of the OUTCOME_STALEMATE variable is "stalemate".
     * It is used to indicate that neither side in the war achieved a victory, resulting in a deadlocked situation.
     * <p>
     * Example usage:
     * <pre>{@code
     * String outcome = War.OUTCOME_STALEMATE;
     * }</pre>
     *
     * @see War
     * @see War#outcome
     * @see War#getOutcome()
     */
    public static final String OUTCOME_STALEMATE = "stalemate";
    /**
     * The OUTCOME_CONTINUE_BELOW_WAR_LEVEL variable represents the outcome of a war being continued below the war level.
     * <p>
     * This variable is of type String and holds the value "continue_below_war_level".
     * It is a constant variable, marked as final, and can't be modified once assigned.
     * <p>
     * It is recommended to use this variable to compare the outcome of a war to check if it is continued below the war level.
     * <p>
     * Example usage:
     * <pre>{@code
     * if (war.getOutcome().equals(OUTCOME_CONTINUE_BELOW_WAR_LEVEL)) {
     *     // War is continued below the war level
     * }
     * }</pre>
     *
     * @see War
     * @see War#getOutcome()
     */
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
     * Represents the number of days the war lasted.
     * <p>
     * The numberOfDaysTheWarLasted variable is a private String field in the War class.
     * It holds the value that represents the duration of the war in days.
     * <p>
     * It is recommended to use getter and setter methods provided by the class to access or modify the value of this variable, as it is marked as private.
     * <p>
     * Example usage:
     * <pre>{@code
     * War war = new War();
     * war.setNumberOfDaysTheWarLasted("30");
     * String numberOfDays = war.getNumberOfDaysTheWarLasted();
     * }</pre>
     *
     * @see War
     * @see War#getNumberOfDaysTheWarLasted()
     * @see War#setNumberOfDaysTheWarLasted(String)
     */
    private String numberOfDaysTheWarLasted;
    /**
     * Represents the number of months the war lasted.
     * <p>
     * This variable is of type String and holds the value representing the duration of the war in months.
     * It is a private variable and can only be accessed or modified using the getter and setter methods provided by the class it belongs to.
     * <p>
     * Example usage:
     * <pre>{@code
     * War war = new War();
     * war.setNumberOfMonthsTheWarLasted("12");
     * String duration = war.getNumberOfMonthsTheWarLasted();
     * }</pre>
     *
     * @see War
     * @see War#getNumberOfMonthsTheWarLasted()
     * @see War#setNumberOfMonthsTheWarLasted(String)
     */
    private String numberOfMonthsTheWarLasted;
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
    private Boolean nonStateWar;
    private Boolean intraStateWar;
    private Boolean internationalized;
    /**
     * The sideAPeakTotalMilitaryForces variable represents the peak total military forces of side A in a war.
     * <p>
     * The sideAPeakTotalMilitaryForces variable is a private Integer field within the War class.
     * It is used to store the maximum number of military forces that side A had during the war.
     * <p>
     * Example usage:
     * <pre>{@code
     * War war = new War();
     * war.setSideAPeakTotalMilitaryForces(10000);
     * Integer peakForces = war.getSideAPeakTotalMilitaryForces();
     * System.out.println("Side A Peak Total Military Forces: " + peakForces);
     * }</pre>
     *
     * @see War
     * @see War#getSideAPeakTotalMilitaryForces()
     * @see War#setSideAPeakTotalMilitaryForces(Integer)
     */
    private Integer sideAPeakTotalMilitaryForces;
    /**
     * The sideBPeakTotalMilitaryForces variable represents the peak total military forces of side B in a war.
     * <p>
     * It is a private Integer field in the War class that holds the numerical value of the peak total military forces of side B.
     * The peak total military forces is the highest recorded number of military personnel, including both active duty and reserve forces, mobilized by side B during the course of
     * the war.
     * <p>
     * Example usage:
     * <pre>{@code
     * War war = new War();
     * war.setSideBPeakTotalMilitaryForces(10000);
     * Integer peakTotalForces = war.getSideBPeakTotalMilitaryForces();
     * }</pre>
     *
     * @see War
     * @see War#getSideBPeakTotalMilitaryForces()
     * @see War#setSideBPeakTotalMilitaryForces(Integer)
     */
    private Integer sideBPeakTotalMilitaryForces;
    /**
     * The sideAPeakTheaterForces variable represents the peak number of theater forces of side A in a war.
     * <p>
     * The sideAPeakTheaterForces variable is private and of type Integer. It stores the maximum number of theater forces of side A during the war.
     * Theater forces refer to the military forces deployed specifically for the operations in a particular theater of war.
     * This variable is used to keep track of the maximum number of theater forces deployed by side A during the war.
     * <p>
     * It is recommended to use the getter and setter methods provided by the {@link War} class to access or modify the value of this variable, as it is marked as private.
     * <p>
     * Example usage:
     * <pre>{@code
     * War war = new War();
     * war.setSideAPeakTheaterForces(5000);
     * Integer peakTheaterForces = war.getSideAPeakTheaterForces();
     * }</pre>
     *
     * @see War
     * @see War#getSideAPeakTheaterForces()
     * @see War#setSideAPeakTheaterForces(Integer)
     */
    private Integer sideAPeakTheaterForces;
    /**
     * The sideBPeakTheaterForces variable represents the peak theater forces of a war's side B.
     * <p>
     * This variable is a private Integer field in the War class.
     * It holds the total number of theater forces on side B at the peak of the war.
     * <p>
     * Theater forces refer to the military forces that are specifically designated and organized for deployment in a particular theater of war or operation.
     * The peak theater forces value provides insight into the maximum strength and capability of side B during the war.
     * <p>
     * It is recommended to use the getter and setter methods provided by the class to access or modify the value of this variable, as it is marked as private.
     * <p>
     * Example usage:
     * <pre>{@code
     * War war = new War();
     * war.setSideBPeakTheaterForces(5000);
     * Integer theaterForces = war.getSideBPeakTheaterForces();
     * }</pre>
     *
     * @see War
     * @see War#getSideBPeakTheaterForces()
     * @see War#setSideBPeakTheaterForces(Integer)
     */
    private Integer sideBPeakTheaterForces;
}
