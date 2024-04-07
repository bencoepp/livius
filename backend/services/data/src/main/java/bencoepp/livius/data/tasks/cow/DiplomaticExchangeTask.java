package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.utils.SequenceGeneratorService;
import org.springframework.beans.factory.annotation.Autowired;

public class DiplomaticExchangeTask extends Task {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    private Job job;
}
