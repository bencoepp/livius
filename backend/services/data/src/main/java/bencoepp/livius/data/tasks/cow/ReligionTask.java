package bencoepp.livius.data.tasks.cow;

import bencoepp.livius.data.events.JobEvent;
import bencoepp.livius.data.tasks.Task;
import bencoepp.livius.entities.job.Job;
import bencoepp.livius.entities.religion.Religion;
import bencoepp.livius.repositories.job.JobRepository;
import bencoepp.livius.repositories.religion.ReligionRepository;
import bencoepp.livius.repositories.state.StateRepository;
import bencoepp.livius.utils.COWUtil;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

@Component
@Slf4j
public class ReligionTask extends Task {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    private Job job;
    @Autowired
    private ReligionRepository religionRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private COWUtil util;
    @Value("${livius.cow.load-wrd}")
    private Boolean startJob;

    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.wrd.csv')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();

        boolean skip = false;

        for (String dependency : job.getDependencies()) {
            Job depJob = jobRepository.findByName(dependency);
            if (!depJob.getStatus().equals(Job.STATUS_FINISHED)) {
                skip = true;
            }
        }

        if(!startJob){
            log.info("Job will be skipped");
        } else if(skip){
            log.info("Waiting for dependency to finish executing");
        } else {
            job.setStatus(Job.STATUS_EXECUTING);
            job.setStarted(Instant.now());
            jobRepository.save(job);

            log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());

            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName()))) {
                bufferedReader.lines().forEach(line -> {
                    if(!line.contains("year,state,name,chrstprot,chrstcat")) {
                        String[] data = line.split(",");
                        Religion religion = new Religion();
                        religion.setYear(Integer.parseInt(data[0]));
                        religion.setCowId(Integer.parseInt(data[1]));
                        religion.setState(stateRepository.findByCode(data[2]));

                        religion.setChristianProtestantNoOfAdherents(util.convertStringToInteger(data[3]));
                        religion.setChristianRomanCatholicsNoOfAdherents(util.convertStringToInteger(data[4]));
                        religion.setChristianEasternOrthodoxNoOfAdherents(util.convertStringToInteger(data[5]));
                        religion.setChristianAnglicanNoOfAdherents(util.convertStringToInteger(data[6]));
                        religion.setChristianOthersNoOfAdherents(util.convertStringToInteger(data[7]));
                        religion.setChristianTotalNoOfAdherents(util.convertStringToInteger(data[8]));

                        religion.setJudaismOrthodoxNoOfAdherents(util.convertStringToInteger(data[9]));
                        religion.setJudaismConservativesNoOfAdherents(util.convertStringToInteger(data[10]));
                        religion.setJudaismReformNoOfAdherents(util.convertStringToInteger(data[11]));
                        religion.setJudaismOthersNoOfAdherents(util.convertStringToInteger(data[12]));
                        religion.setJudaismTotalNoOfAdherents(util.convertStringToInteger(data[13]));

                        religion.setIslamSunniNoOfAdherents(util.convertStringToInteger(data[14]));
                        religion.setIslamShiaNoOfAdherents(util.convertStringToInteger(data[15]));
                        religion.setIslamIbadhiNoOfAdherents(util.convertStringToInteger(data[16]));
                        religion.setIslamNationOfIslamNoOfAdherents(util.convertStringToInteger(data[17]));
                        religion.setIslamAlawiteNoOfAdherents(util.convertStringToInteger(data[18]));
                        religion.setIslamAhmadiyyaNoOfAdherents(util.convertStringToInteger(data[19]));
                        religion.setIslamOtherNoOfAdherents(util.convertStringToInteger(data[20]));
                        religion.setIslamTotalNoOfAdherents(util.convertStringToInteger(data[21]));

                        religion.setBuddhismMahayanaNoOfAdherents(util.convertStringToInteger(data[22]));
                        religion.setBuddhismTheravadaNoOfAdherents(util.convertStringToInteger(data[23]));
                        religion.setBuddhismOtherNoOfAdherents(util.convertStringToInteger(data[24]));
                        religion.setBuddhismTotalNoOfAdherents(util.convertStringToInteger(data[25]));


                        religion.setZoroastrianTotalNoOfAdherents(util.convertStringToInteger(data[26]));
                        religion.setHinduTotalNoOfAdherents(util.convertStringToInteger(data[27]));
                        religion.setSikhTotalNoOfAdherents(util.convertStringToInteger(data[28]));
                        religion.setShintoTotalNoOfAdherents(util.convertStringToInteger(data[29]));
                        religion.setBahaiTotalNoOfAdherents(util.convertStringToInteger(data[30]));
                        religion.setTaoismTotalNoOfAdherents(util.convertStringToInteger(data[31]));
                        religion.setConfucianismTotalNoOfAdherents(util.convertStringToInteger(data[32]));
                        religion.setJainTotalNoOfAdherents(util.convertStringToInteger(data[33]));
                        religion.setSyncreticReligionsTotalNoOfAdherents(util.convertStringToInteger(data[34]));
                        religion.setAnimistReligionsTotalNoOfAdherents(util.convertStringToInteger(data[35]));

                        religion.setNonReligiousTotalNoOfAdherents(util.convertStringToInteger(data[36]));
                        religion.setOtherTotalNoOfAdherents(util.convertStringToInteger(data[37]));
                        religion.setSumOfReligionAdherents(util.convertStringToInteger(data[38]));
                        religion.setTotalPopulation(util.convertStringToInteger(data[39]));

                        religion.setChristianityProtestantsPctAdherents(Double.valueOf(data[40]));
                        religion.setChristianityRomanCatholicsPctAdherents(Double.valueOf(data[41]));
                        religion.setChristianityEasternOrthodoxPctAdherents(Double.valueOf(data[42]));
                        religion.setChristianityAnglicanPctAdherents(Double.valueOf(data[43]));
                        religion.setChristianityOthersPctAdherents(Double.valueOf(data[44]));
                        religion.setChristianityGeneralChrstPctAdherents(Double.valueOf(data[45]));

                        religion.setJudaismOrthodoxPctAdherents(Double.valueOf(data[46]));
                        religion.setJudaismConservativesPctAdherents(Double.valueOf(data[47]));
                        religion.setJudaismReformPctAdherents(Double.valueOf(data[48]));
                        religion.setJudaismOthersPctAdherents(Double.valueOf(data[49]));
                        religion.setJudaismGeneralJewishPctAdherents(Double.valueOf(data[50]));

                        religion.setIslamSunniPctAdherents(Double.valueOf(data[51]));
                        religion.setIslamShiaPctAdherents(Double.valueOf(data[52]));
                        religion.setIslamIbadhiPctAdherents(Double.valueOf(data[53]));
                        religion.setIslamNationofIslamPctAdherents(Double.valueOf(data[54]));
                        religion.setIslamAlawitePctAdherents(Double.valueOf(data[55]));
                        religion.setIslamAhmadiyyaPctAdherents(Double.valueOf(data[56]));
                        religion.setIslamOtherPctAdherents(Double.valueOf(data[57]));
                        religion.setIslamGeneralMuslimPctAdherents(Double.valueOf(data[58]));

                        religion.setBuddhismMahayanaPctAdherents(Double.valueOf(data[59]));
                        religion.setBuddhismTheravadaPctAdherents(Double.valueOf(data[60]));
                        religion.setBuddhismOtherPctAdherents(Double.valueOf(data[61]));
                        religion.setBuddhismGenBuddhistPctAdherents(Double.valueOf(data[62]));

                        religion.setZoroastrianZoroPctAdherents(Double.valueOf(data[63]));
                        religion.setHinduPctAdherents(Double.valueOf(data[64]));
                        religion.setSikhPctAdherents(Double.valueOf(data[65]));
                        religion.setShintoPctAdherents(Double.valueOf(data[66]));
                        religion.setBahaiPctAdherents(Double.valueOf(data[67]));
                        religion.setTaoismPctAdherents(Double.valueOf(data[68]));
                        religion.setJainPctAdherents(Double.valueOf(data[69]));
                        religion.setConfucianismPctAdherents(Double.valueOf(data[70]));
                        religion.setSyncreticReligionsPctAdherents(Double.valueOf(data[71]));
                        religion.setAnimistReligionsPctAdherents(Double.valueOf(data[72]));
                        religion.setNonReligiousPctAdherents(Double.valueOf(data[73]));
                        religion.setOtherReligionsPctAdherents(Double.valueOf(data[74]));
                        religion.setSumPercentReligiousAdherents(Double.valueOf(data[75]));
                        religion.setTotalPopulationPercantageCheck(Double.valueOf(data[76]));
                        religion.setDualReligion(util.convert0or1ToBoolean(data[77]));

                        religion.setDataType(util.findDataTypeReligion(Integer.valueOf(data[78])));
                        religion.setReliabilityOfRecord(util.findReliabilityOfRecord(Integer.valueOf(data[79])));
                        religion.setLevelOfReliabilityOfRecord(util.findLevelOfReliabilityOfRecord(Integer.valueOf(data[80])));
                        religion.setSourceCode(data[81]);
                        religionRepository.save(religion);
                    }
                });
            } catch (Exception e) {
                log.error("Error occurred while streaming CSV file content", e);
            }

            log.info("Finished processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + job.getName());
            super.run(event);
        }
    }
}
