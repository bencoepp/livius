package bencoepp.livius.data.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class JobEvent extends ApplicationEvent {
    private String condition;
    private String jobId;
    public JobEvent(Object source, String condition, String jobId) {
        super(source);
        this.condition = condition;
        this.jobId = jobId;
    }
}
