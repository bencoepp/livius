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
import com.netflix.discovery.converters.Auto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    @EventListener(condition = "#event.condition.equals('livius.cow.wrd.csv')")
    public void run(JobEvent event) {
        job = jobRepository.findById(event.getJobId()).get();
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

                    religion.setChristianProtestantNoOfAdherents(Integer.valueOf(data[3]));
                    religion.setChristianRomanCatholicsNoOfAdherents(Integer.valueOf(data[4]));
                    religion.setChristianEasternOrthodoxNoOfAdherents(Integer.valueOf(data[5]));
                    religion.setChristianAnglicanNoOfAdherents(Integer.valueOf(data[6]));
                    religion.setChristianOthersNoOfAdherents(Integer.valueOf(data[7]));
                    religion.setChristianTotalNoOfAdherents(Integer.valueOf(data[8]));

                    religion.setJudaismOrthodoxNoOfAdherents(Integer.valueOf(data[9]));
                    religion.setJudaismConservativesNoOfAdherents(Integer.valueOf(data[10]));
                    religion.setJudaismReformNoOfAdherents(Integer.valueOf(data[11]));
                    religion.setJudaismOthersNoOfAdherents(Integer.valueOf(data[12]));
                    religion.setJudaismTotalNoOfAdherents(Integer.valueOf(data[13]));

                    religion.setIslamSunniNoOfAdherents(Integer.valueOf(data[14]));
                    religion.setIslamShiaNoOfAdherents(Integer.valueOf(data[15]));
                    religion.setIslamIbadhiNoOfAdherents(Integer.valueOf(data[16]));
                    religion.setIslamNationOfIslamNoOfAdherents(Integer.valueOf(data[17]));
                    religion.setIslamAlawiteNoOfAdherents(Integer.valueOf(data[18]));
                    religion.setIslamAhmadiyyaNoOfAdherents(Integer.valueOf(data[19]));
                    religion.setIslamOtherNoOfAdherents(Integer.valueOf(data[20]));
                    religion.setIslamTotalNoOfAdherents(Integer.valueOf(data[21]));

                    religion.setBuddhismMahayanaNoOfAdherents(Integer.valueOf(data[22]));
                    religion.setBuddhismTheravadaNoOfAdherents(Integer.valueOf(data[23]));
                    religion.setBuddhismOtherNoOfAdherents(Integer.valueOf(data[24]));
                    religion.setBuddhismTotalNoOfAdherents(Integer.valueOf(data[25]));


                    religion.setZoroastrianTotalNoOfAdherents(Integer.valueOf(data[26]));
                    religion.setHinduTotalNoOfAdherents(Integer.valueOf(data[27]));
                    religion.setSikhTotalNoOfAdherents(Integer.valueOf(data[28]));
                    religion.setShintoTotalNoOfAdherents(Integer.valueOf(data[29]));
                    religion.setBahaiTotalNoOfAdherents(Integer.valueOf(data[30]));
                    religion.setTaoismTotalNoOfAdherents(Integer.valueOf(data[31]));
                    religion.setConfucianismTotalNoOfAdherents(Integer.valueOf(data[32]));
                    religion.setJainTotalNoOfAdherents(Integer.valueOf(data[33]));
                    religion.setSyncreticReligionsTotalNoOfAdherents(Integer.valueOf(data[34]));
                    religion.setAnimistReligionsTotalNoOfAdherents(Integer.valueOf(data[35]));

                    religion.setNonReligiousTotalNoOfAdherents(Integer.valueOf(data[36]));
                    religion.setOtherTotalNoOfAdherents(Integer.valueOf(data[37]));
                    religion.setSumOfReligionAdherents(Integer.valueOf(data[38]));
                    religion.setTotalPopulation(Integer.valueOf(data[39]));

                    religion.setChristianityProtestantsPctAdherents(Integer.valueOf(data[40]));
                    religion.setChristianityRomanCatholicsPctAdherents(Integer.valueOf(data[41]));
                    religion.setChristianityEasternOrthodoxPctAdherents(Integer.valueOf(data[42]));
                    religion.setChristianityAnglicanPctAdherents(Integer.valueOf(data[43]));
                    religion.setChristianityOthersPctAdherents(Integer.valueOf(data[44]));
                    religion.setChristianityGeneralChrstPctAdherents(Integer.valueOf(data[45]));

                    religion.setJudaismOrthodoxPctAdherents(Integer.valueOf(data[46]));
                    religion.setJudaismConservativesPctAdherents(Integer.valueOf(data[47]));
                    religion.setJudaismReformPctAdherents(Integer.valueOf(data[48]));
                    religion.setJudaismOthersPctAdherents(Integer.valueOf(data[49]));
                    religion.setJudaismGeneralJewishPctAdherents(Integer.valueOf(data[50]));

                    religion.setIslamSunniPctAdherents(Integer.valueOf(data[51]));
                    religion.setIslamShiaPctAdherents(Integer.valueOf(data[52]));
                    religion.setIslamIbadhiPctAdherents(Integer.valueOf(data[53]));
                    religion.setIslamNationofIslamPctAdherents(Integer.valueOf(data[54]));
                    religion.setIslamAlawitePctAdherents(Integer.valueOf(data[55]));
                    religion.setIslamAhmadiyyaPctAdherents(Integer.valueOf(data[56]));
                    religion.setIslamOtherPctAdherents(Integer.valueOf(data[57]));
                    religion.setIslamGeneralMuslimPctAdherents(Integer.valueOf(data[58]));

                    religion.setBuddhismMahayanaPctAdherents(Integer.valueOf(data[59]));
                    religion.setBuddhismTheravadaPctAdherents(Integer.valueOf(data[60]));
                    religion.setBuddhismOtherPctAdherents(Integer.valueOf(data[61]));
                    religion.setBuddhismGenBuddhistPctAdherents(Integer.valueOf(data[62]));

                    religion.setZoroastrianZoroPctAdherents(Integer.valueOf(data[63]));
                    religion.setHinduPctAdherents(Integer.valueOf(data[64]));
                    religion.setSikhPctAdherents(Integer.valueOf(data[65]));
                    religion.setShintoPctAdherents(Integer.valueOf(data[66]));
                    religion.setBahaiPctAdherents(Integer.valueOf(data[67]));
                    religion.setTaoismPctAdherents(Integer.valueOf(data[68]));
                    religion.setJainPctAdherents(Integer.valueOf(data[69]));
                    religion.setConfucianismPctAdherents(Integer.valueOf(data[70]));
                    religion.setSyncreticReligionsPctAdherents(Integer.valueOf(data[71]));
                    religion.setAnimistReligionsPctAdherents(Integer.valueOf(data[72]));
                    religion.setNonReligiousPctAdherents(Integer.valueOf(data[73]));
                    religion.setOtherReligionsPctAdherents(Integer.valueOf(data[74]));
                    religion.setSumPercentReligiousAdherents(Integer.valueOf(data[75]));
                    religion.setTotalPopulationPercantageCheck(Integer.valueOf(data[76]));
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
