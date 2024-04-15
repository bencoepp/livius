package bencoepp.livius.entities.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.ArrayList;
import java.util.List;

/**
 * The Changelog class represents a changelog document.
 * It is used to store information about software updates and changes.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "changelogs")
public class Changelog {
    @Transient
    public static final String SEQUENCE_NAME="changelog_seq";
    @Id
    private String id;
    /**
     * The version variable represents the version number of a software update or change.
     * It is used in the Changelog class to track the version of each update or change.
     * This variable is a String type.
     */
    private String version;
    /**
     * The title variable represents the title of a changelog entry.
     * It is used in the Changelog class to store the title of each update or change.
     * This variable is a String type and is marked as private.
     */
    private String title;
    private String description;
    private List<String> changes;
    /**
     * The contributors variable represents a list of contributors to a changelog entry.
     * It is used in the Changelog class to store the names of individuals who have contributed to the update or change.
     * This variable is of type List<String> and is marked as private, indicating that it can only be accessed within the Changelog class.
     */
    private List<String> contributors = new ArrayList<>();
    /**
     * The acknowledgement variable represents the acknowledgment given for a changelog entry.
     * It is used in the Changelog class to store the acknowledgment related to each update or change.
     * This variable is a String type and is marked as private.
     */
    private String acknowledgement;
    private String references;
    private String created;
    /**
     * The updated variable represents the date and time when the changelog entry was last updated.
     * It is used in the Changelog class to store the updated timestamp of each entry.
     * This variable is of type Instant, which is a representation of a moment in time.
     * It is marked as private, indicating that it can only be accessed within the Changelog class.
     */
    private String updated;
}
