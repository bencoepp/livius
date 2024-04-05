package bencoepp.livius.entities.job;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "jobs")
public class Job {
    @Transient
    public static final String SEQUENCE_NAME="war_seq";
    @Id
    private String id;
    private String name;
    private String url;
    private String type;
    @Transient
    public static final String TYPE_COW = "cow";
    private String status;
    @Transient
    public static final String STATUS_SCHEDULED = "scheduled";
    @Transient
    public static final String STATUS_CANCELED = "canceled";
    @Transient
    public static final String STATUS_EXECUTING = "executing";
    @Transient
    public static final String STATUS_FINISHED = "finished";
    @Transient
    public static final String STATUS_ERROR = "error";
    private List<String> dependencies;
}
