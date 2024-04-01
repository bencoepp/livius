package bencoepp.livius.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "countries")
public class Country {
    @Transient
    public static final String SEQUENCE_NAME="weather_seq";
    @Id
    private String id;
    private Boolean cow_data;
    private String state_code;
    private Integer cow_id;
    private String name;
    private Instant created;
    private Instant updated;
}
