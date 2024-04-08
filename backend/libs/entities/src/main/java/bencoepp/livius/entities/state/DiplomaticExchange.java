package bencoepp.livius.entities.state;

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
 * The DiplomaticExchange class represents a diplomatic exchange document in the "diplomatic_exchanges" collection of a MongoDB database.
 * It contains various properties that describe a diplomatic exchange between two states.
 *
 * This class is annotated with the following annotations:
 * - @Getter: Generates getter methods for all non-static fields of the class.
 * - @Setter: Generates setter methods for all non-static fields of the class.
 * - @NoArgsConstructor: Generates a no-argument constructor for the class.
 * - @AllArgsConstructor: Generates a constructor with arguments for all fields of the class.
 * - @Document(collection = "diplomatic_exchanges"): Specifies that objects of this class will be stored in the "diplomatic_exchanges" collection
 *   in the MongoDB database.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "diplomatic_exchanges")
public class DiplomaticExchange {
    @Transient
    public static final String SEQUENCE_NAME="diplomatic_exchange_seq";
    @Id
    private String id;
    /**
     * The year variable represents the year associated with a specific State object.
     *
     * It is a private Integer field in the State class.
     *
     * The year is used to indicate the year in which the state was active or valid.
     * It can be used to filter and sort state records based on their associated year.
     *
     * Example usage:
     * State state = new State();
     * state.setYear(2022);
     * Integer year = state.getYear();
     *
     * System.out.println("Year: " + year);
     *
     * @see State
     * @see State#getYear()
     * @see State#setYear(Integer)
     */
    private Integer year;
    /**
     * The sideA variable represents a list of State objects that are part of side A in a diplomatic exchange.
     *
     * <p>
     * This variable is a private instance variable within the DiplomaticExchange class. It is used to store the states
     * that belong to side A of the exchange. The states are represented by objects of the State class.
     *
     * <p>
     * The State class represents a state document in a MongoDB database. It contains various properties that describe the
     * characteristics of a state. Each State object in the sideA list represents a state that is involved in the
     * diplomatic exchange.
     *
     * <p>
     * The sideA variable is marked with the @DocumentReference annotation, which indicates that it refers to a collection
     * of State objects in the MongoDB database. This annotation is part of the Spring Data MongoDB framework.
     *
     * <p>
     * It is recommended to use the getter and setter methods provided by the DiplomaticExchange class to access or modify
     * the value of this variable, as it is marked as private.
     *
     * @see DiplomaticExchange
     * @see DiplomaticExchange#getSideA()
     * @see DiplomaticExchange#setSideA(List)
     * @see State
     * @see StateCollection
     */
    @DocumentReference
    private List<State> sideA;
    /**
     * The sideB variable represents a list of State objects that is part of a DiplomaticExchange object.
     * It is a private instance variable within the DiplomaticExchange class.
     * The sideB variable is annotated with @DocumentReference, indicating that it refers to the "sideB" field in the MongoDB database.
     * The "sideB" field contains a list of State objects representing the states involved in a diplomatic exchange on side B.
     *
     * Example usage:
     *
     * // Create a new DiplomaticExchange object
     * DiplomaticExchange exchange = new DiplomaticExchange();
     *
     * // Create a new State object
     * State state1 = new State();
     * state1.setId("1");
     * state1.setName("State A");
     *
     * // Create a new State object
     * State state2 = new State();
     * state2.setId("2");
     * state2.setName("State B");
     *
     * // Add the State objects to the sideB list
     * exchange.getSideB().add(state1);
     * exchange.getSideB().add(state2);
     *
     * // Get the sideB list from the DiplomaticExchange object
     * List<State> sideB = exchange.getSideB();
     *
     * // Iterate over the sideB list and print the names of the states
     * for (State state : sideB) {
     *     System.out.println(state.getName());
     * }
     *
     * @see DiplomaticExchange
     * @see State
     */
    @DocumentReference
    private List<State> sideB;
    private String diplomaticRepresentationLevelSideA;
    /**
     * Represents the diplomatic representation level of side B in a diplomatic exchange.
     */
    private String diplomaticRepresentationLevelSideB;
    /**
     * The DR_NO_EVIDENCE_OF_DIPLOMATIC_EXCHANGE variable represents a specific code associated with a state object in the DiplomaticExchange class.
     * It is a constant value of type String.
     * <p>
     * This code indicates that there is no evidence of a diplomatic exchange between two parties.
     * It can be used to represent a specific condition or state of a diplomatic relationship.
     *
     * Example usage:
     * <pre>{@code
     * String evidence = DiplomaticExchange.DR_NO_EVIDENCE_OF_DIPLOMATIC_EXCHANGE;
     * }</pre>
     *
     * @see DiplomaticExchange
     */
    public static final String DR_NO_EVIDENCE_OF_DIPLOMATIC_EXCHANGE = "no_evidence_of_diplomatic_exchange";
    /**
     * The DR_CHARGE_DAFFAIRES variable represents the string value "charge_daffaires".
     * <p>
     * This variable is declared as a public static final String in the DiplomaticExchange class.
     * It is used to store the value associated with the charge_daffaires charge in a diplomatic exchange.
     * <p>
     * Example usage:
     * <pre>{@code
     * String charge = DiplomaticExchange.DR_CHARGE_DAFFAIRES;
     * }</pre>
     *
     * @see DiplomaticExchange
     */
    public static final String DR_CHARGE_DAFFAIRES = "charge_daffaires";
    /**
     * Represents a constant variable that holds the value "minister".
     */
    public static final String DR_MINISTER = "minister";
    /**
     * The DR_AMBASSADOR variable represents the code for the ambassador diplomatic representation level.
     * <p>
     * It is a constant variable of type String that is declared in the DiplomaticExchange class.
     * The value of this variable is "ambassador".
     * <p>
     * This variable is used to determine the level of diplomatic representation in a diplomatic exchange between two states.
     * An ambassador represents the highest level of diplomatic representation.
     * <p>
     * Example usage:
     * <pre>{@code
     * String diplomaticRepresentationLevel = DR_AMBASSADOR;
     * }</pre>
     */
    public static final String DR_AMBASSADOR = "ambassador";
    /**
     * Represents the "other" category for diplomatic representation levels.
     *
     * <p>
     * The DR_OTHER variable is a constant of type String that represents the "other" category for diplomatic representation levels.
     * It is one of the possible options for the diplomatic representation levels in the DiplomaticExchange class.
     * <p>
     * Example usage:
     * <pre>{@code
     * String diplomaticRepresentationLevel = DiplomaticExchange.DR_OTHER;
     * }</pre>
     *
     * @see DiplomaticExchange
     */
    public static final String DR_OTHER = "other";
    /**
     * The DR_NONE variable represents a value indicating "none" for a diplomatic representation level.
     * It is a public static final string field in the DiplomaticExchange class.
     * <p>
     * The "none" value signifies that there is no diplomatic representation level for a certain exchange or interaction between two entities (e.g., states).
     * It can be used to indicate the absence of a specific diplomatic rank or level in a given context.
     * <p>
     * Example usage:
     * <pre>{@code
     * String diplomaticRepLevel = DiplomaticExchange.DR_NONE;
     * }</pre>
     *
     * @see DiplomaticExchange
     */
    public static final String DR_NONE = "none";
    /**
     * Represents any diplomatic exchange related to a bilateral relationship between two states.
     */
    private String anyDiplomaticExchange;
    /**
     * DE_NONE is a constant variable of type String.
     * <p>
     * It is used to represent a value of "none".
     * <p>
     * This variable is declared as public, static, and final, meaning it can be accessed from anywhere,
     * and its value cannot be changed once initialized.
     * <p>
     * It is recommended to use this constant instead of hard-coding the value "none" in the code,
     * which improves code readability and maintainability.
     * <p>
     * Example usage:
     * <pre>{@code
     * if (state.getCode().equals(DE_NONE)) {
     *     // Do something
     * }
     * }</pre>
     */
    public static final String DE_NONE = "none";
    /**
     * The DE_NEITHER_SIDE constant is a string value that represents the "neither_side" option for the diplomatic exchange in the DiplomaticExchange class.
     * <p>
     * This constant is a public static final field of type String.
     * It is used to indicate that neither side is involved in the diplomatic exchange.
     * <p>
     * When using this constant, ensure that it is referenced using the fully qualified name: DiplomaticExchange.DE_NEITHER_SIDE.
     * <p>
     * Example usage:
     * <pre>{@code
     * if (diplomaticExchange.equals(DiplomaticExchange.DE_NEITHER_SIDE)) {
     *     // Perform some logic for neither side
     * }
     * }</pre>
     *
     * @see DiplomaticExchange
     * @see DiplomaticExchange#getDiplomaticExchange()
     * @see DiplomaticExchange#setDiplomaticExchange(String)
     */
    public static final String DE_NEITHER_SIDE = "neither_side";
    /**
     * The DE_AT_LEAST_ONE variable represents a string value "at_least_one".
     * <p>
     * This variable is a constant of type String and is marked as public static final, which means its value cannot be changed once assigned and can be accessed from other classes
     * .
     * It is used to represent a condition where at least one item is required.
     * <p>
     * Example usage:
     * <pre>{@code
     * if (condition.equals(DE_AT_LEAST_ONE)) {
     *     // Perform action
     * }
     * }</pre>
     *
     * @see DiplomaticExchange
     * @see DiplomaticExchange#condition
     */
    public static final String DE_AT_LEAST_ONE = "at_least_one";
}
