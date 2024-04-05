package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.data.events.JobEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CountryTask extends Task {

    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.country-codes')")
    public void run(JobEvent event) {
        log.info("test test");
        super.run(event);
    }
}
