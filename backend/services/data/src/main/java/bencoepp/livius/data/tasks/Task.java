package bencoepp.livius.data.tasks;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.repositories.state.CountryRepository;
import bencoepp.livius.repositories.state.MajorPowerRepository;
import bencoepp.livius.repositories.state.StateRepository;
import bencoepp.livius.repositories.war.WarRepository;
import bencoepp.livius.utils.COWUtil;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Task {
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    @Autowired
    private COWUtil util;
    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;
    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private MajorPowerRepository majorPowerRepository;
    @Autowired
    private WarRepository warRepository;

    public void run(JobEvent jobEvent){
        log.info("Finished Task: {}", getClass().getName());
    }
}
