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
 * The TerritorialChange class represents a territorial change document in the "territorial_changes" collection of a MongoDB database.
 * It contains various properties that describe the characteristics of a territorial change.
 * <p>
 * This class is annotated with the following annotations:
 * - @Getter: Generates getter methods for all non-static fields of the class.
 * - @Setter: Generates setter methods for all non-static fields of the class.
 * - @NoArgsConstructor: Generates a no-argument constructor for the class.
 * - @AllArgsConstructor: Generates a constructor with arguments for all fields of the class.
 * - @Document(collection = "territorial_changes"): Specifies that objects of this class will be stored in the "territorial_changes" collection
 *   in the MongoDB database.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "territorial_changes")
public class TerritorialChange {
    @Transient
    public static final String SEQUENCE_NAME="state_seq";
    @Id
    private String id;
    private Integer territorialChangeNumber;
    private Integer year;
    /**
     * private Integer month;
     * <p>
     * This variable represents the month value.
     * <p>
     * It is of type Integer and holds a value that represents the month of a calendar year.
     * The value ranges from 1 to 12, with 1 representing January and 12 representing December.
     * <p>
     * It is a private variable, meaning it is not directly accessible outside the class it belongs to.
     * To get or set the value of this variable, you can use the provided getter and setter methods.
     * <p>
     * Example usage:
     * ```
     * State state = new State();
     * state.setMonth(6);
     * Integer month = state.getMonth(); // month = 6
     * ```
     *
     * @see State
     * @see State#getMonth()
     * @see State#setMonth(String)
     */
    private String month;
    /**
     * The gainingSide variable represents a list of State objects representing the gaining side in a territorial change.
     * <p>
     * A territorial change refers to the transfer of land or sovereignty from one state to another.
     * The gaining side is the state that acquires territory or control over an area as a result of the territorial change.
     * <p>
     * The gainingSide variable is declared as a private instance member in the {@link TerritorialChange} class,
     * which acts as the containing class for this variable.
     * It is annotated with {@link DocumentReference}, indicating its usage as a reference to a document or section within a document.
     * <p>
     * The type of the elements in the list is {@link State}, which encapsulates the information related to a specific state.
     * Each State object represents a state document in the "states" collection of a MongoDB database,
     * containing various properties that describe the characteristics of a state.
     * <p>
     * Please note that the containing class has several other fields related to the territorial change representation,
     * such as SEQUENCE_NAME, id, territorialChangeNumber, year, month, typeOfChangeForGainingSide, and procedure.
     * The class also has relationships with other classes and their corresponding fields, such as entityExchanged, losingSide, etc.
     * <p>
     * This variable is accessed or modified through the appropriate methods defined in the {@link TerritorialChange} class.
     *
     * @see TerritorialChange
     * @see State
     * @see DocumentReference
     */
    @DocumentReference
    private List<State> gainingSide;
    /**
     * Represents a private variable in the TerritorialChange class that holds the type of change for the gaining side.
     * <p>
     * The typeOfChangeForGainingSide variable is of type String and is used to store the type of change that occurred on the gaining side of a territorial change.
     * It is marked as private, meaning that it can only be accessed and modified within the same class.
     * <p>
     * It is recommended to use the getter and setter methods provided by the TerritorialChange class to access or modify the value of this variable.
     * <p>
     * Example usage:
     * <pre>{@code
     * TerritorialChange territorialChange = new TerritorialChange();
     * territorialChange.setTypeOfChangeForGainingSide("Annexation");
     * String typeOfChange = territorialChange.getTypeOfChangeForGainingSide();
     * }</pre>
     *
     * @see TerritorialChange
     * @see TerritorialChange#getTypeOfChangeForGainingSide()
     * @see TerritorialChange#setTypeOfChangeForGainingSide(String)
     */
    private String typeOfChangeForGainingSide;
    /**
     * Represents a private variable in the containing class.
     * <p>
     * This variable is of type String and holds a value that is not accessible outside the class it belongs to.
     * It is used to store a specific procedure associated with an instance of the containing class.
     * <p>
     * To access or modify the value of this variable, use the getter and setter methods provided by the containing class, as it is marked as private.
     *
     * @see TerritorialChange
     * @see TerritorialChange#getProcedure()
     * @see TerritorialChange#setProcedure(String)
     */
    private String procedure;
    @Transient
    public static final String PROCEDURE_NONE = "none";
    @Transient
    public static final String PROCEDURE_CONQUEST = "conquest";
    @Transient
    public static final String PROCEDURE_ANNEXATION = "annexation";
    @Transient
    public static final String PROCEDURE_CESSION = "cession";
    @Transient
    public static final String PROCEDURE_SECESSION = "secession";
    @Transient
    public static final String PROCEDURE_UNIFICATION = "unification";
    @Transient
    public static final String PROCEDURE_MANDATED_TERRITORY = "mandated_territory";
    /**
     * The entityExchanged variable represents a private String variable in the State class.
     * <p>
     * This variable is used to store the code associated with a specific state object.
     * It is not accessible outside the class it belongs to.
     * <p>
     * It is recommended to use the getter and setter methods provided by the class to access or modify the value of this variable, as it is marked as private.
     * <p>
     * Example usage:
     * <p>
     * State state = new State();
     * state.setEntityExchanged("ABC");
     * String entityExchanged = state.getEntityExchanged();
     *
     * @see State
     * @see State#getEntityExchanged()
     * @see State#setEntityExchanged(String)
     */
    private String entityExchanged;
    /**
     * The contiguityOfUnitExchangedToTheGainingState variable represents the contiguity of a unit that is exchanged to the gaining state.
     * <p>
     * This variable is a private instance member of the {@link TerritorialChange} class, which represents a change in territorial boundaries between states.
     * It is used to store information about the contiguity of a unit that is exchanged to the gaining state during a territorial change.
     * <p>
     * The contiguity of a unit refers to the connectedness or proximity of the unit to other territories or units within the gaining state.
     * It indicates whether the unit is physically adjacent or connected to other parts of the gaining state's territory.
     * <p>
     * The value of the contiguityOfUnitExchangedToTheGainingState variable should be a string representing the contiguity status, such as "contiguous" or "non-contiguous".
     * <p>
     * It is recommended to use the getter and setter methods provided by the {@link TerritorialChange} class to access or modify the value of this variable, as it is marked as private
     * .
     * <p>
     * Example usage:
     * <pre>{@code
     * TerritorialChange territorialChange = new TerritorialChange();
     * territorialChange.setContiguityOfUnitExchangedToTheGainingState("contiguous");
     * String contiguity = territorialChange.getContiguityOfUnitExchangedToTheGainingState();
     * }</pre>
     *
     * @see TerritorialChange
     * @see TerritorialChange#getContiguityOfUnitExchangedToTheGainingState()
     * @see TerritorialChange#setContiguityOfUnitExchangedToTheGainingState(String)
     */
    private String contiguityOfUnitExchangedToTheGainingState;
    /**
     * Represents the area of a territorial unit exchanged in square kilometers.
     *
     * <p>
     * The areaOfUnitExchangedInSquareKilometers variable is a private instance variable declared in the {@link TerritorialChange} class.
     * It represents the area of a territorial unit that is exchanged between two parties in a specific territorial change.
     * The area is measured in square kilometers.
     * </p>
     *
     * <p>
     * This variable can be accessed and modified using the getter and setter methods provided by the {@link TerritorialChange} class.
     * It is recommended to use these methods to ensure encapsulation and maintain data integrity.
     * </p>
     *
     * <p>
     * Example usage:
     * <pre>{@code
     * TerritorialChange territorialChange = new TerritorialChange();
     * territorialChange.setAreaOfUnitExchangedInSquareKilometers(1000.0);
     * Double area = territorialChange.getAreaOfUnitExchangedInSquareKilometers();
     * }</pre>
     * </p>
     *
     * @see TerritorialChange
     * @see TerritorialChange#getAreaOfUnitExchangedInSquareKilometers()
     * @see TerritorialChange#setAreaOfUnitExchangedInSquareKilometers(String)
     */
    private String areaOfUnitExchangedInSquareKilometers;
    /**
     * Represents the population of a unit being exchanged.
     * <p>
     * The populationOfUnitExchanged variable is a private instance variable in the TerritorialChange class. It represents
     * the population of a specific unit being exchanged in a territorial change.
     * <p>
     * The population is an integer value that indicates the number of people living in the unit being exchanged. It is not
     * accessible outside the class in which it is declared.
     * <p>
     * To access or modify the value of the populationOfUnitExchanged variable, it is recommended to use the getter and setter
     * methods provided by the TerritorialChange class.
     *
     * @see TerritorialChange
     * @see TerritorialChange#getPopulationOfUnitExchanged()
     * @see TerritorialChange#setPopulationOfUnitExchanged(String)
     */
    private String populationOfUnitExchanged;
    /**
     * Represents the portion of a unit that is exchanged in a territorial change.
     * <p>
     * The portionOfUnitExchanged variable is an Integer field in the TerritorialChange class.
     * <p>
     * The portion of unit exchanged is a value that represents the amount or size of a unit that is exchanged
     * in a territorial change. It provides information about the extent or scale of the territorial change.
     * The value of the portionOfUnitExchanged field indicates the portion of the unit that is involved in the exchange.
     * <p>
     * The portionOfUnitExchanged field is private, meaning it can only be accessed or modified by methods within the same class.
     * This promotes encapsulation and helps maintain data integrity.
     * <p>
     * It is recommended to use the getter and setter methods provided by the TerritorialChange class to access or modify the value of this field.
     *
     * @see TerritorialChange
     * @see TerritorialChange#getPortionOfUnitExchanged()
     * @see TerritorialChange#setPortionOfUnitExchanged(String)
     * @see State
     */
    private String portionOfUnitExchanged;
    /**
     * The `losingSide` variable is a private instance variable in the `TerritorialChange` class. It represents a list of `State` objects that are part of the losing side in a territorial
     *  change.
     * <p>
     * A territorial change refers to any change in the boundaries or control of territory between states. The losing side in a territorial change is the group of states that lose
     *  territory or control during the change.
     * <p>
     * The type of elements in the list is `State`, which represents a state document in a MongoDB database. It contains various properties that describe the characteristics of a state
     * .
     * <p>
     * The `losingSide` variable is annotated with `@DocumentReference`, indicating its usage as a reference to a document or section within a document.
     * <p>
     * It is recommended to use the appropriate methods defined in the `TerritorialChange` class to access or modify the value of this variable, as it is marked as private.
     *
     * @see TerritorialChange
     * @see State
     * @see DocumentReference
     * @see TerritorialChange#getLosingSide()
     * @see TerritorialChange#setLosingSide(List)
     */
    @DocumentReference
    private List<State> losingSide;
    /**
     * The typeOfChangeForLosingSide variable represents the type of change for the losing side in a territorial change.
     * It is a private String field in the TerritorialChange class.
     * <p>
     * The type of change refers to the nature of the territorial change, such as annexation, cession, secession, unification, or mandated territory.
     * It provides information about how the losing side is affected by the territorial change.
     * <p>
     * Example usage:
     *   TerritorialChange territorialChange = new TerritorialChange();
     *   territorialChange.setTypeOfChangeForLosingSide("cession");
     *   String typeOfChangeForLosingSide = territorialChange.getTypeOfChangeForLosingSide();
     * <p>
     * System.out.println("Type of Change for Losing Side: " + typeOfChangeForLosingSide);
     */
    private String typeOfChangeForLosingSide;
    /**
     * Represents the contiguity of a unit that was exchanged to the losing state in the context of territorial changes.
     * <p>
     * The contiguityOfUnitExchangedToTheLosingState variable is a private instance member of the TerritorialChange class.
     * It holds information about the contiguity of a unit that was exchanged to the losing state during a territorial change.
     * The contiguity refers to the property of being in physical contact or adjacency with other regions or territories.
     * This variable is of type String.
     * <p>
     * It is recommended to access or modify the value of this variable using the getter and setter methods provided by the TerritorialChange class, as it is marked as private.
     * <p>
     * Example usage:
     * ```
     * TerritorialChange territorialChange = new TerritorialChange();
     * territorialChange.setContiguityOfUnitExchangedToTheLosingState("Contiguous");
     * String contiguity = territorialChange.getContiguityOfUnitExchangedToTheLosingState();
     * System.out.println("Contiguity: " + contiguity);
     * ```
     *
     * @see TerritorialChange
     * @see TerritorialChange#getContiguityOfUnitExchangedToTheLosingState()
     * @see TerritorialChange#setContiguityOfUnitExchangedToTheLosingState(String)
     */
    private String contiguityOfUnitExchangedToTheLosingState;
    /**
     * Represents a private Integer variable in the TerritorialChange class.
     * <p>
     * This variable is used to store the system entry value associated with a specific territorial change object.
     * The system entry value represents the entry point of the territorial change in the system.
     * <p>
     * It is recommended to use the getter and setter methods provided by the class to access or modify the value of this variable, as it is marked as private.
     *
     * @see TerritorialChange
     * @see TerritorialChange#getSystemEntry()
     * @see TerritorialChange#setSystemEntry(Integer)
     */
    private Integer systemEntry;
    /**
     * Represents the system exit value.
     * <p>
     * The {@code systemExit} variable is used to store the exit value of a system process.
     * It is of type Integer and holds the status code of the process, indicating the reason for its termination.
     * A value of 0 typically indicates successful execution, while non-zero values indicate errors or abnormal termination.
     * <p>
     * It is recommended to use the getter and setter methods provided by the class to access or modify the value of this variable.
     *
     * @see TerritorialChange
     * @see TerritorialChange#getSystemExit()
     * @see TerritorialChange#setSystemExit(Integer)
     */
    private Integer systemExit;
    /**
     * The independence variable represents the independence associated with a territorial change object.
     * <p>
     * It is a private Boolean field in the TerritorialChange class.
     * <p>
     * This variable stores the independence associated with a territorial change object.
     * The value can be true if the territorial change resulted in independence, or false otherwise.
     * <p>
     * The independence field has private access, meaning it can only be accessed within the TerritorialChange class itself.
     *
     * @see TerritorialChange
     */
    private Boolean independence;
    /**
     * The conflict variable represents the conflict associated with a territorial change object.
     * <p>
     * It is a private Integer field in the TerritorialChange class.
     * <p>
     * This variable stores the conflict associated with a territorial change object.
     * The value can be null if there is no conflict associated with the territorial change.
     * <p>
     * The conflict field has private access, meaning it can only be accessed within the TerritorialChange class itself.
     *
     * @see TerritorialChange
     */
    private Integer conflict;
    @Transient
    public static final String TYPE_DEPENDENT_TERRITORY = "dependent_territory";
    @Transient
    public static final String TYPE_HOMELAND_TERRITORY = "homeland_territory";
    @Transient
    public static final String TYPE_NONE = "none";
}
