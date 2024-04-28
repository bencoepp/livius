package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.state.TerritorialChange;
import bencoepp.livius.entities.trade.Trade;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.state.StateRepository;
import bencoepp.livius.repositories.trade.TradeRepository;
import bencoepp.livius.utils.COWUtil;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.Instant;
import java.util.List;

import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

@Component
@Slf4j
public class TradeTask extends Task {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    private Job job;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private TradeRepository tradeRepository;
    @Autowired
    private COWUtil util;

    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.it.zip')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();

        boolean skip = false;

        for (String dependency : job.getDependencies()) {
            Job depJob = jobRepository.findByName(dependency);
            if (!depJob.getStatus().equals(Job.STATUS_FINISHED)) {
                skip = true;
            }
        }

        if (skip) {
            log.info("Waiting for dependency to finish executing");
        } else {
            job.setStatus(Job.STATUS_EXECUTING);
            job.setStarted(Instant.now());
            jobRepository.save(job);

            log.info("Unpacking .zip File {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());

            try {
                List<File> files = util.unzipZipFile(job.getName());

                File file = files.stream().filter(data -> data.getName().contains("data-0.csv")).findAny().get();
                log.info("Processing CSV file {}", file.getName());

                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                    bufferedReader.lines().forEach(line -> {
                        if(!line.contains(",year,")){
                            String[] data = line.split(",");

                            Trade trade = new Trade();
                            trade.setImporterA(stateRepository.findByCowId(Integer.parseInt(data[0])));
                            trade.setImporterB(stateRepository.findByCowId(Integer.parseInt(data[1])));

                            trade.setYear(Integer.parseInt(data[2]));

                            trade.setImporterAFlow(Double.parseDouble(data[5]));
                            trade.setImporterBFlow(Double.parseDouble(data[6]));
                            trade.setSmoothedAFlow(Double.parseDouble(data[7]));
                            trade.setSmoothedBFlow(Double.parseDouble(data[8]));
                            trade.setSmoothedTrade(data[8]);
                            trade.setSpikeA(data[9]);
                            trade.setSpikeB(data[10]);
                            trade.setDipA(data[11]);
                            trade.setDipB(data[12]);
                            trade.setTradeSpike(data[13]);
                            trade.setTradeDip(data[14]);
                            trade.setSourceImporterAFlow(util.findTradeSource(data[15]));
                            trade.setSourceImporterBFlow(util.findTradeSource(data[16]));
                            trade.setOriginalPRCTradeValuesImporterA(data[17]);
                            trade.setOriginalPRCTradeValuesImporterB(data[18]);
                            trade.setOriginalLUXTradeValuesImporterA(data[19]);
                            trade.setOriginalLUXTradeValuesImporterB(data[20]);

                            tradeRepository.save(trade);
                        }
                    });
                }catch (Exception e){
                    log.error("Error occurred while streaming CSV file content", e);
                }
            }catch (IOException e){
                log.error("Not able to unpack zip:", e);
            }
            super.run(event);
        }
    }
}
