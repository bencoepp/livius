package bencoepp.livius.entities.state;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

/**
 * The Country class represents a country entity in the system.
 *
 * <p>
 * It is annotated with {@link Document}, indicating that instances of this class
 * are mapped to MongoDB documents.
 * </p>
 * <p>
 * The class also contains various properties that represent the attributes of a country,
 * such as the country ID, name, code, and other related data.
 * </p>
 *
 * <p>
 * The Country class consists of the following properties:
 * <ul>
 *   <li>{@code id} - The unique identifier of the country.</li>
 *   <li>{@code cow_data} - A boolean value indicating whether the country has cow data.</li>
 *   <li>{@code code} - The country code.</li>
 *   <li>{@code cow_id} - The cow ID associated with the country.</li>
 *   <li>{@code name} - The name of the country.</li>
 *   <li>{@code created} - The timestamp when the country was created.</li>
 *   <li>{@code updated} - The timestamp when the country was last updated.</li>
 * </ul>
 * </p>
 *
 * <p>
 * The Country class also defines constants and annotations:
 * <ul>
 *   <li>{@code SEQUENCE_NAME} - A constant representing the name of the sequence used for generating country IDs.</li>
 *   <li>{@link Getter} - An annotation to automatically generate getters for the class properties.</li>
 *   <li>{@link Setter} - An annotation to automatically generate setters for the class properties.</li>
 *   <li>{@link NoArgsConstructor} - An annotation to automatically generate a no-argument constructor.</li>
 *   <li>{@link AllArgsConstructor} - An annotation to automatically generate a constructor with all properties.</li>
 * </ul>
 * </p>
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "countries")
public class Country {
    @Transient
    public static final String SEQUENCE_NAME="weather_seq";
    /**
     * Represents the unique identifier for a Country object.
     *
     * The value of id is annotated with @Id, indicating that it is the primary key for database operations.
     *
     * Note that the actual implementation or type of id is not specified in this documentation.
     *
     * @see Country
     */
    @Id
    private String id;
    /**
     * Represents the data for a cow.
     *
     * This variable stores information about a cow.
     * It can be used to indicate the presence or absence of cow data.
     * The value is of type Boolean, where true represents that cow data is available and false represents that cow data is not available.
     *
     * Example usage:
     *
     * boolean hasCowData = cow_data;
     * if (hasCowData) {
     *     // Process cow data
     * } else {
     *     // Handle case where cow data is not available
     * }
     */
    private Boolean cow_data;
    /**
     * This is a private instance variable called "code" of type String.
     * It is used in the "Country" class.
     *
     * This variable represents the code associated with a country.
     *
     * Note: This documentation does not include example code, author or version tags.
     */
    private String code;
    /**
     * Represents the unique identifier of a cow.
     *
     * This variable is used to store the identification number of a cow.
     * The identification number is an integer value.
     */
    private Integer cow_id;
    /**
     * The name of an entity.
     */
    private String name;
    /**
     * The {@code created} variable represents the timestamp when the country was created.
     *
     * <p>
     * The value of this variable is of type {@link Instant}, which is a point in time represented
     * by the number of seconds and nanoseconds since the epoch. The timestamp is stored in UTC.
     * </p>
     *
     * <p>
     * The {@link Country} class that contains this variable represents a country entity in the system.
     * It is annotated with {@link Document}, indicating that instances of this class are mapped to MongoDB documents.
     * </p>
     * <p>
     * The class also contains various properties that represent the attributes of a country, such as the country ID, name, code,
     * and other related data.
     * </p>
     *
     * <p>
     * For more details about the {@link Country} class, refer to its documentation.
     * </p>
     *
     *
     * @see Country
     * @see Instant
     */
    private Instant created;
    /**
     * The {@code updated} variable represents the timestamp when the country was last updated.
     *
     * <p>
     * The value of this variable is of type {@link Instant}, which is a point in time represented
     * by the number of seconds and nanoseconds since the epoch. The timestamp is stored in UTC.
     * </p>
     *
     * <p>
     * The {@link Country} class that contains this variable represents a country entity in the system.
     * It is annotated with {@link Document}, indicating that instances of this class are mapped to MongoDB documents.
     * </p>
     * <p>
     * The class also contains various properties that represent the attributes of a country, such as the country ID, name, code,
     * and other related data.
     * </p>
     *
     * <p>
     * For more details about the {@link Country} class, refer to its documentation.
     * </p>
     *
     *
     * @see Country
     * @see Instant
     */
    private Instant updated;
}
