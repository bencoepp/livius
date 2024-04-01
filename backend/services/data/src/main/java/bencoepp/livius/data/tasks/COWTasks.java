package bencoepp.livius.data.tasks;

import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class COWTasks {

    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;

}
