package bencoepp.livius.entities.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.HashMap;

/**
 * Represents a newsletter.
 * <p>
 * This class represents a newsletter that can be sent to multiple recipients.
 * It contains information such as the subject, recipients, data, and template.
 * <p>
 * The recipients can be an array of email addresses.
 * The data is a HashMap containing key-value pairs of additional data.
 * The template is the content of the newsletter.
 * <p>
 * Example usage:
 * <p>
 * Newsletter newsletter = new Newsletter();
 * newsletter.setId("1");
 * newsletter.setSubject("Weekly Newsletter");
 * newsletter.setRecipients(new String[]{"john@example.com", "jane@example.com"});
 * newsletter.setData(new HashMap<>());
 * newsletter.getData().put("date", "2021-07-01");
 * newsletter.setTemplate("Hello {name},\n\nThis is the weekly newsletter.\n\nThank you,\nExample Company");
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "newsletters")
public class Newsletter {
    @Transient
    public static final String SEQUENCE_NAME="newsletter_seq";
    @Id
    private String id;
    private String subject;
    private String[] recipients;
    /**
     * Represents additional data for a newsletter.
     * <p>
     * This class is used to store additional key-value pairs of data for a newsletter.
     * It is implemented using the HashMap collection, where each key corresponds to a specific value.
     * The keys and values are both of type String.
     * <p>
     * Example usage:
     * <pre>{@code
     * Newsletter newsletter = new Newsletter();
     * newsletter.setData(new HashMap<>());
     * newsletter.getData().put("date", "2021-07-01");
     * }</pre>
     *
     * @see Newsletter
     * @since [insert version number]
     */
    private HashMap<String, String> data;
    /**
     * Represents a template for the content of a newsletter.
     * <p>
     * This template is used to define the content of a newsletter, which can be sent to multiple recipients.
     * It is a string that may include placeholders for dynamic values.
     * The placeholders are enclosed in curly braces, e.g. {name}.
     * <p>
     * Example usage:
     * <pre>{@code
     * Newsletter newsletter = new Newsletter();
     * newsletter.setTemplate("Hello {name},\n\nThis is the weekly newsletter.\n\nThank you,\nExample Company");
     * }</pre>
     *
     * @see Newsletter
     */
    private String template;
}
