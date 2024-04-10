package bencoepp.livius.entities.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * The State class represents a state document in the "states" collection of a MongoDB database.
 * It contains various properties that describe the characteristics of a state.
 * <p>
 * This class is annotated with the following annotations:
 * - @Getter: Generates getter methods for all non-static fields of the class.
 * - @Setter: Generates setter methods for all non-static fields of the class.
 * - @NoArgsConstructor: Generates a no-argument constructor for the class.
 * - @AllArgsConstructor: Generates a constructor with arguments for all fields of the class.
 * - @Document(collection = "states"): Specifies that objects of this class will be stored in the "states" collection
 *   in the MongoDB database.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "states")
public class State {
    @Transient
    public static final String SEQUENCE_NAME="state_seq";
    @Id
    private String id;
    /**
     * Represents a private variable in the State class.
     * <p>
     * This variable is of type String and holds a value that is not accessible outside the class it belongs to.
     * It is used to store code associated with a specific state object.
     * <p>
     * It is recommended to use the getter and setter methods provided by the class to access or modify the value of this variable, as it is marked as private.
     * <p>
     * Example usage:
     * <pre>{@code
     * State state = new State();
     * state.setCode("123");
     * String code = state.getCode();
     * }</pre>
     *
     * @see State
     * @see State#getCode()
     * @see State#setCode(String)
     */
    private String code;
    private Integer cowId;
    private String name;
    private Date startDate;
    /**
     * The end_date variable represents the end date of a state record.
     * It is a private Date field in the State class.
     * <p>
     * The end date is the date when a state record ends or becomes inactive.
     * It can be used to determine the duration of a state's existence or the validity of certain data associated with the state.
     * <p>
     * Example usage:
     * State state = new State();
     * Date endDate = state.getEndDate();
     * <p>
     * System.out.println("End Date: " + endDate);
     */
    private Date endDate;
    /**
     * Represents a list of MajorPower objects indicating whether a state was a major power.
     * <p>
     * The wasMajorPower variable is declared in the State class and is annotated with @DocumentReference.
     * It refers to the "wasMajorPower" field in the "states" collection in the MongoDB database.
     * <p>
     * A MajorPower object represents a major power in the "major_powers" collection in the MongoDB database.
     * It contains various properties like the state, start date, end date, and cow version.
     * <p>
     * Example usage:
     * State state = new State();
     * List<MajorPower> wasMajorPower = state.getWasMajorPower();
     * <p>
     * for (MajorPower majorPower : wasMajorPower) {
     *     System.out.println(majorPower.getState());
     *     System.out.println(majorPower.getStartDate());
     *     System.out.println(majorPower.getEndDate());
     *     System.out.println(majorPower.getCowVersion());
     * }
     */
    @DocumentReference
    private List<MajorPower> wasMajorPower;
    /**
     * This variable represents a list of diplomatic exchanges in the context of a state.
     *
     * A diplomatic exchange refers to any form of communication or negotiation
     * between two or more states, aimed at resolving conflicts, reaching agreements
     * or maintaining diplomatic relations.
     * <p>
     * The type of the elements in the list is {@link DiplomaticExchange}, which
     * encapsulates the information related to a specific diplomatic exchange.
     * <p>
     * This variable is declared as a private instance member in the {@link State} class,
     * which acts as the containing class of this variable.
     * <p>
     * Please note that the containing class has several other fields and methods related
     * to the state representation, such as id, code, cowId, name, startDate, endDate,
     * wasMajorPower, created, updated, citation, accessTime, authors, and faqEmail.
     * <p>
     * This variable is annotated with {@link DocumentReference}, indicating its usage
     * as a reference to a document or section within a document.
     * <p>
     * This variable is accessed or modified through the appropriate methods defined in
     * the {@link State} class.
     *
     * @see DiplomaticExchange
     * @see State
     * @see DocumentReference
     */
    @DocumentReference
    private List<DiplomaticExchange> diplomaticExchanges;
    /**
     * The created field represents the creation timestamp of an object.
     * <p>
     * This field is a private instance variable within the State class, which represents a state entity in a system.
     * It is used to store the timestamp when the state object was created.
     * <p>
     * The created field is of type Instant. Instant is a class in the Java 8 Date-Time API that represents a point in time.
     * <p>
     * The value of the created field is determined when the State object is instantiated and should not be modified afterwards.
     * <p>
     * It is important to note that the created field is private, meaning it can only be accessed and modified by methods within the same class.
     * This promotes encapsulation and maintains data integrity.
     *
     * @see State
     * @see State#created
     */
    private Instant created;
    /**
     * The updated variable represents the timestamp when an object or entity was last updated.
     * <p>
     * This variable is an instance of the Instant class from the Java 8 Date-Time API.
     * It holds the timestamp information in UTC (Coordinated Universal Time) format.
     * The value of the updated variable can be retrieved and modified using the getter and setter methods.
     * <p>
     * The updated variable is private, meaning it can only be accessed and modified within the same class.
     * This is done to ensure encapsulation and data integrity.
     *
     * @see State
     * @see State#updated
     * @see State#getUpdated()
     * @see State#setUpdated(Instant)
     */
    private Instant updated;
    private String citation;
    private Instant accessTime;
    private String[] authors;
    private String faqEmail;

    public State(String csvLine) throws ParseException {
        String[] data = csvLine.split(",");
        this.code = data[0];
        this.cowId = Integer.valueOf(data[1]);
        this.name = data[2];
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.startDate = dateFormat.parse(data[5] + "." + data[4] + "." + data[3]);
        this.endDate = dateFormat.parse(data[8] + "." + data[7] + "." + data[6]);
        this.wasMajorPower = new ArrayList<>();
    }
}
