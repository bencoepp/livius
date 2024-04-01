package bencoepp.livius.entities.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;
import java.sql.Date;
import java.util.List;

/**
 * The State class represents a state document in the "states" collection of a MongoDB database.
 * It contains various properties that describe the characteristics of a state.
 *
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
     * The cow_data variable represents the data associated with a cow.
     *
     * This variable is a private instance variable within the State class. It is a Boolean type variable,
     * which can only hold true or false values. The cow_data variable can be used to store information
     * about whether or not a cow is available or present in the system.
     *
     * The value of the cow_data variable will depend on the logic and requirements of the application.
     * It can be accessed and modified within the State class using getter and setter methods.
     *
     * It is important to note that this variable is defined in the State class, and its purpose and usage
     * may vary depending on the context and functionality of the State class.
     *
     * @see State
     * @see State#cow_data
     * @see State#getCow_data()
     * @see State#setCow_data(Boolean)
     */
    private Boolean cow_data;
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
    private String cow_id;
    private String name;
    private Date start_date;
    /**
     * The end_date variable represents the end date of a state record.
     * It is a private Date field in the State class.
     * <p>
     * The end date is the date when a state record ends or becomes inactive.
     * It can be used to determine the duration of a state's existence or the validity of certain data associated with the state.
     * <p>
     * Example usage:
     * State state = new State();
     * Date endDate = state.getEnd_date();
     * <p>
     * System.out.println("End Date: " + endDate);
     */
    private Date end_date;
    /**
     * The cow_version variable represents the version of the COW (Correlates of War) dataset associated with a state.
     * It is a private String field in the State class.
     *
     * The cow_version indicates the specific version of the dataset that was used to collect and analyze data related to the state.
     * This information is useful for tracking and managing changes in the dataset over time.
     *
     * Example usage:
     * State state = new State();
     * String cow_version = state.getCow_version();
     *
     * System.out.println("COW Version: " + cow_version);
     */
    private String cow_version;
    /**
     * Represents a list of MajorPower objects indicating whether a state was a major power.
     *
     * The wasMajorPower variable is declared in the State class and is annotated with @DocumentReference.
     * It refers to the "wasMajorPower" field in the "states" collection in the MongoDB database.
     *
     * A MajorPower object represents a major power in the "major_powers" collection in the MongoDB database.
     * It contains various properties like the state, start date, end date, and cow version.
     *
     * Example usage:
     * State state = new State();
     * List<MajorPower> wasMajorPower = state.getWasMajorPower();
     *
     * for (MajorPower majorPower : wasMajorPower) {
     *     System.out.println(majorPower.getState());
     *     System.out.println(majorPower.getStartDate());
     *     System.out.println(majorPower.getEndDate());
     *     System.out.println(majorPower.getCowVersion());
     * }
     */
    @DocumentReference
    List<MajorPower> wasMajorPower;
}
