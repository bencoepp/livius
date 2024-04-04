package bencoepp.livius.data.tasks;

import bencoepp.livius.data.events.COWFileDownloadedEvent;
import bencoepp.livius.entities.state.Country;
import bencoepp.livius.entities.state.MajorPower;
import bencoepp.livius.entities.state.State;
import bencoepp.livius.entities.war.War;
import bencoepp.livius.entities.war.WarType;
import bencoepp.livius.repositories.state.CountryRepository;
import bencoepp.livius.repositories.state.MajorPowerRepository;
import bencoepp.livius.repositories.state.StateRepository;
import bencoepp.livius.repositories.war.WarRepository;
import bencoepp.livius.utils.COWUtil;
import bencoepp.livius.utils.SequenceGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.Map;

import static bencoepp.livius.utils.COWUtil.DOWNLOAD_DIR;

@Component
@Slf4j
public class COWTasks {

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

    /**
     * This method is triggered when the application is ready to start importing data.
     * It downloads the source files from correlatesofwar.org concurrently and saves them with specified filenames.
     */
    @EventListener(ApplicationReadyEvent.class)
    public void run(){
        log.info("Downloading sources from correlatesofwar.org");

        Map<String, String> sources = util.getAllPropWithPrefix("livius.cow");
        log.info("Gathered urls of cow sources");

        // Use CompletableFuture to make downloadSource method be invoked concurrently.
        sources.forEach((key, value) -> CompletableFuture.runAsync(() -> {
            try {
                String filename = value.substring(value.lastIndexOf("/") + 1);
                // Check if the file was already downloaded
                if(!Files.exists(Path.of(System.getProperty("user.dir") + DOWNLOAD_DIR + filename))){
                    String file = key + filename.substring(filename.lastIndexOf("."));
                    util.downloadSource(value, file);
                    COWFileDownloadedEvent event = new COWFileDownloadedEvent(this, file);
                    applicationEventPublisher.publishEvent(event);
                }
            } catch (IOException e) {
                log.error("Error occurred while downloading file from {}", value, e);
            }
        }));
    }

    /**
     * Imports country codes from a CSV file into the system.
     *
     * <p>
     * This method is triggered when a {@link COWFileDownloadedEvent} is fired with a file name indicating that
     * the file to be processed is the "livius.cow.country-codes.csv" file.
     * </p>
     *
     * <p>
     * The method processes the CSV file line by line, skipping lines that contain the string ",CCode,".
     * For each line, a {@link Country} object is created using the line data, and it is checked whether a country
     * with the same code or cowId already exists in the system. If not, the country is saved to the database
     * with a generated ID, and the created and updated timestamps set to the current instant.
     * </p>
     *
     * <p>
     * Note: If an exception occurs while streaming the CSV file content, an error message will be logged.
     * </p>
     *
     * @param event the {@link COWFileDownloadedEvent} that triggered the method
     *
     * @see COWFileDownloadedEvent
     * @see Country
     * @see CountryRepository#existsByCodeOrCowId(String, Integer)
     * @see SequenceGeneratorService#getSequenceNumber(String)
     */
    @EventListener(condition = "#event.file.equals('livius.cow.country-codes.csv')")
    public void importCountryCodes(COWFileDownloadedEvent event){
        log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile()))) {
            bufferedReader.lines().forEach(line -> {
                if(!line.contains(",CCode,")){
                    Country country = new Country(line);
                    if(!countryRepository.existsByCodeOrCowId(country.getCode(), country.getCowId())){
                        country.setId(sequenceGeneratorService.getSequenceNumber(Country.SEQUENCE_NAME));
                        country.setCreated(Instant.now());
                        country.setUpdated(Instant.now());

                        country.setAccessTime(Instant.now());
                        country.setUrl("https://correlatesofwar.org/data-sets/cow-country-codes-2/");
                        countryRepository.save(country);
                    }
                }
            });
        } catch (Exception e) {
            log.error("Error occurred while streaming CSV file content", e);
        }

        log.info("Finished processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());
    }

    /**
     * Imports the states from a CSV file into the database.
     *
     * @param event the COWFileDownloadedEvent that contains the information about the downloaded file
     */
    @EventListener(condition = "#event.file.equals('livius.cow.states.csv')")
    public void importStates(COWFileDownloadedEvent event){
        log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());
        stateRepository.deleteAll();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile()))) {
            bufferedReader.lines().forEach(line -> {
                if(!line.contains(",ccode,statenme,")){
                    try {
                        State state = new State(line);
                        state.setId(sequenceGeneratorService.getSequenceNumber(State.SEQUENCE_NAME));
                        state.setCreated(Instant.now());
                        state.setUpdated(Instant.now());

                        state.setAccessTime(Instant.now());
                        state.setCitation("Correlates of War Project. 2017. “State System Membership List, v2016.” Online, http://correlatesofwar.org");
                        state.setAuthors(new String[]{
                                "Volker Krause, Eastern Michigan University",
                                "Phil Schafer, University of Michigan",
                                "Karen Ruth Adams, University of Montan"
                        });
                        state.setFaqEmail("vkrause@emich.edu");
                        stateRepository.save(state);
                    } catch (ParseException e) {
                        log.error("Error occurred while creating a state instance", e);
                    }
                }
            });
        } catch (Exception e) {
            log.error("Error occurred while streaming CSV file content", e);
        }

        log.info("Finished processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());
        //for effekt we are going to execute the major power import a second time
        importMajorPowers(new COWFileDownloadedEvent(this, "livius.cow.majors.csv"));
    }

    /**
     * Imports major powers from a CSV file.
     *
     * This method is called when a COWFileDownloadedEvent is triggered and the file name is equal to 'livius.cow.majors.csv'.
     * It processes the CSV file and saves the MajorPower objects to the database.
     *
     * @param event the COWFileDownloadedEvent object representing the event triggered
     */
    @EventListener(condition = "#event.file.equals('livius.cow.majors.csv')")
    public void importMajorPowers(COWFileDownloadedEvent event){
        log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());
        majorPowerRepository.deleteAll();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile()))) {
            bufferedReader.lines().forEach(line -> {
                if(!line.contains("stateabb,ccode,styear,stmonth,stday,endyear,endmonth,endday,version")){
                    try {
                        MajorPower power = new MajorPower(line);
                        power.setId(sequenceGeneratorService.getSequenceNumber(State.SEQUENCE_NAME));
                        power.setCreated(Instant.now());
                        power.setUpdated(Instant.now());

                        power.setAccessTime(Instant.now());
                        power.setCitation("Correlates of War Project. 2017. “State System Membership List, v2016.” Online, http://correlatesofwar.org");
                        power.setAuthors(new String[]{
                                "Volker Krause, Eastern Michigan University",
                                "Phil Schafer, University of Michigan",
                                "Karen Ruth Adams, University of Montan"
                        });
                        power.setFaqEmail("vkrause@emich.edu");
                        majorPowerRepository.save(power);

                        List<State> states = stateRepository.findByCowIdAndCode(power.getCowId(), power.getState());
                        for (State state : states){
                            state.getWasMajorPower().add(power);
                            stateRepository.save(state);
                        }
                    } catch (ParseException e) {
                        log.error("Error occurred while creating a state instance", e);
                    }
                }
            });
        } catch (Exception e) {
            log.error("Error occurred while streaming CSV file content", e);
        }

        log.info("Finished processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());
    }

    @EventListener(condition = "#event.file.equals('livius.cow.extra-state-war.csv')")
    public void importExtraStateWars(COWFileDownloadedEvent event) throws InterruptedException {
        log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());
        wait(50000);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile()))) {
            bufferedReader.lines().forEach(line -> {
                if(!line.contains("WarNum,WarName,WarType,ccode1,SideA,ccode2,SideB,StartMonth1,StartDay1,StartYear1,EndMonth1,EndDay1,EndYear1,StartMonth2,StartDay2,StartYear2,EndMonth2,EndDay2 ,EndYear2,Initiator,Interven,TransFrom,Outcome,TransTo,WhereFought,BatDeath,NonStateDeaths,Version")){
                    String[] data = line.split(",");

                    int warType = WarType.NONE.ordinal();

                    if(data[2].equals("2")){
                        warType = WarType.COLONIAL_WAR.ordinal();
                    }else if(data[2].equals("3")){
                        warType = WarType.IMPERIAL_WAR.ordinal();
                    }

                    List<State> sideA = new ArrayList<>();

                    if(util.checkIsNotUnknown(data[3])){
                        State state = stateRepository.findByCowId(Integer.valueOf(data[3]));
                        if(state.getName().equals(data[4])){
                            sideA.add(state);
                        }else{
                            //TODO if state name does not match create the state
                        }
                    }

                    List<State> sideB = new ArrayList<>();

                    if(util.checkIsNotUnknown(data[5])){
                        State state = stateRepository.findByCowId(Integer.valueOf(data[3]));
                        if(state.getName().equals(data[6])){
                            sideA.add(state);
                        }else{
                            //TODO if state name does not match create the state
                        }
                    }

                    List<Date> start = new ArrayList<>();

                    start.add(util.getDateFromString(data[8], data[7], data[9]));
                    if(util.checkIsNotUnknown(data[15])){
                        start.add(util.getDateFromString(data[14], data[13], data[15]));
                    }

                    List<Date> end = new ArrayList<>();

                    end.add(util.getDateFromString(data[11], data[10], data[12]));
                    if(util.checkIsNotUnknown(data[18])){
                        end.add(util.getDateFromString(data[17], data[16], data[18]));
                    }

                    War war = new War(
                            sequenceGeneratorService.getSequenceNumber(War.SEQUENCE_NAME),
                            Integer.valueOf(data[0]),
                            data[1],
                            warType,
                            sideA,
                            sideB,
                            start,
                            end,
                            util.convert0or1ToBoolean(data[19]),
                            Integer.valueOf(data[23]),
                            Integer.valueOf(data[21]),
                            util.findWarOutcome(Integer.valueOf(data[22])),
                            util.convert0or1ToBoolean(data[20]),
                            data[24],
                            data[26],
                            Long.valueOf(data[25])
                    );

                    warRepository.save(war);
                }
            });
        } catch (Exception e) {
            log.error("Error occurred while streaming CSV file content", e);
        }

        log.info("Finished processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());
    }

    @EventListener(condition = "#event.file.equals('livius.cow.intra-state-war.zip')")
    public void importIntraStateWars(COWFileDownloadedEvent event) {
        log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());

        log.info("Finished processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());
    }

    @EventListener(condition = "#event.file.equals('livius.cow.non-state-war')")
    public void importNonStateWars(COWFileDownloadedEvent event) {
        log.info("Processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());

        log.info("Finished processing CSV file {}", System.getProperty("user.dir") + DOWNLOAD_DIR + event.getFile());
    }
}