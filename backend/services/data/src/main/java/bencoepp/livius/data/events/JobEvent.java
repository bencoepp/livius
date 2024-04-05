package bencoepp.livius.data.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
@Getter
public class JobEvent extends ApplicationEvent {
    private String condition;
    public JobEvent(Object source, String condition) {
        super(source);
        this.condition = condition;
    }
}
