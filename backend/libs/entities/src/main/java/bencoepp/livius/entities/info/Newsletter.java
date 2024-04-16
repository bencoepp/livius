package bencoepp.livius.entities.info;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

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
}
