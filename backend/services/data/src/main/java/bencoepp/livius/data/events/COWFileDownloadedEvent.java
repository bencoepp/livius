package bencoepp.livius.data.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class COWFileDownloadedEvent extends ApplicationEvent {
    private String file;

    public COWFileDownloadedEvent(Object source, String file) {
        super(source);
        this.file = file;
    }
}
