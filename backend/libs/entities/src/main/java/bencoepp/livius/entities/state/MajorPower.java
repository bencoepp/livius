package bencoepp.livius.entities.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;

/**
 * The MajorPower class represents a major power entity with the following properties:
 * <p>
 *  - id: the unique identifier of the major power
 *  - state: the state of the major power
 *  - start_date: the start date of the major power
 *  - end_date: the end date of the major power
 *  - cow_version: the version of the Correlates of War (COW) dataset
 * <p>
 * It also provides the getters and setters for these properties.
 * <p>
 * The class is annotated with @Document from the Spring Data MongoDB to specify the collection name "major_powers"
 * where the instances of MajorPower class will be stored in the MongoDB database.
 * <p>
 * The class also defines a static final field SEQUENCE_NAME to represent the name of the sequence used for generating
 * unique identifiers for major powers.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "major_powers")
public class MajorPower {
    @Transient
    public static final String SEQUENCE_NAME="major_power_seq";
    @Id
    private String id;
    /**
     * Represents the state of an object.
     * <p>
     * This variable is a private String that holds the state of the object. It is an instance variable and can be accessed
     * and modified within the class it belongs to.
     * <p>
     * The state variable can be used to keep track of the current state or status of an object. The value of the state
     * variable will depend on the logic and requirements of the application.
     * <p>
     * It is important to note that the state variable is private, meaning it can only be accessed and modified by methods
     * within the same class. This is a common practice to ensure encapsulation and data integrity.
     *
     * @see MajorPower
     * @see MajorPower#state
     * @see MajorPower#getState()
     * @see MajorPower#setState(String)
     */
    private String state;
    private Integer cowId;
    /**
     * The start_date field represents the start date of a MajorPower instance.
     * <p>
     * This field is part of the MajorPower class, which represents a major power entity in a system.
     * It is used to store the start date of a major power in the major_powers collection in a MongoDB database.
     * <p>
     * This field is a private instance variable within the MajorPower class and should not be accessed directly.
     * Instead, use the getter and setter methods provided by the class to access and update the value of this field.
     *
     * @see MajorPower#getStartDate()
     * @see MajorPower#setStartDate(Date)
     */
    private Date startDate;
    /**
     * The end_date field represents the end date of a MajorPower instance.
     * <p>
     * This field is part of the MajorPower class, which represents a major power entity in a system.
     * It is used to store the end date of a major power in the major_powers collection in a MongoDB database.
     * <p>
     * This field is a private instance variable within the MajorPower class and should not be accessed directly.
     * Instead, use the getter and setter methods provided by the class to access and update the value of this field.
     *
     * @see MajorPower#getEndDate()
     * @see MajorPower#setEndDate(Date)
     */
    private Date endDate;
    private Instant created;
    private Instant updated;

    public MajorPower(String csvLine) throws ParseException {
        String[] data = csvLine.split(",");
        this.state = data[0];
        this.cowId = Integer.valueOf(data[1]);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        this.startDate = dateFormat.parse(data[4] + "." + data[3] + "." + data[2]);
        this.endDate = dateFormat.parse(data[7] + "." + data[6] + "." + data[5]);
    }
}
