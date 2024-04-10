package bencoepp.livius.utils;

import bencoepp.livius.entities.religion.Religion;
import bencoepp.livius.entities.state.DiplomaticExchange;
import bencoepp.livius.entities.state.State;
import bencoepp.livius.entities.state.TerritorialChange;
import bencoepp.livius.entities.war.War;
import bencoepp.livius.repositories.state.StateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * This class provides utility methods for working with COW (Correlates of War) data.
 */
@Service
@Slf4j
public class COWUtil {

    @Autowired
    private ConfigurableApplicationContext applicationContext;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private SequenceGeneratorService sequenceGeneratorService;
    public static final String DOWNLOAD_DIR = "/cow/data/";
    private Integer csvZipFileCounter = 0;

    /**
     * Retrieves all properties with a given prefix from the environment and returns them as a map.
     *
     * @param prefix the prefix to use for filtering the properties
     * @return a map containing the properties with the given prefix
     */
    public Map<String,String> getAllPropWithPrefix(String prefix) {
        BindResult<Map<String, String>> result = Binder.get(applicationContext.getEnvironment())
                .bind(prefix, Bindable.mapOf(String.class, String.class));
        if (!result.isBound() || result.get()==null) {
            return Collections.emptyMap();
        }
        return result.get().entrySet().stream().collect(Collectors.toMap(x->prefix+"."+x.getKey(), Map.Entry::getValue));
    }

    /**
     * Downloads the source file from the given URL and saves it with the specified filename.
     *
     * @param url      the URL of the source file to download
     * @param filename the filename to save the downloaded file as
     * @throws IOException if an I/O error occurs while downloading or saving the file
     */
    public void downloadSource(String url, String filename) throws IOException {
        try (InputStream in = new URL(url).openStream()) {
            Files.createDirectories(Path.of(System.getProperty("user.dir") + DOWNLOAD_DIR));
            Files.copy(in, Path.of(System.getProperty("user.dir") + DOWNLOAD_DIR + filename), StandardCopyOption.REPLACE_EXISTING);
            log.info("Downloaded file from {}", url);
        }
    }

    /**
     * Parses the given day, month, and year strings and returns a Date object representing the corresponding date.
     *
     * @param day the day of the month as a string (e.g., "01", "02", ..., "31")
     * @param month the month of the year as a string (e.g., "01", "02", ..., "12")
     * @param year the year as a four-digit string (e.g., "2022")
     * @return a Date object representing the parsed date
     */
    public Date getDateFromString(String day, String month, String year){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try{
            return dateFormat.parse(day + "." + month + "." + year);
        }catch (Exception e){
            log.error("An error happened while trying to convert the following date: " + day + "." + month + "." + year);
            try {
                return dateFormat.parse("01.01." + year);
            } catch (ParseException ex) {
                return new Date();
            }
        }
    }

    /**
     * Checks if the given value is not equal to "-9" or "-8".
     *
     * @param value the value to be checked
     * @return {@code true} if the value is not equal to "-9" or "-8", {@code false} otherwise
     */
    public Boolean checkIsNotUnknown(String value){
        if(value.equals("-9")){
            return false;
        }else if(value.equals("-8")){
            return false;
        }

        return true;
    }

    /**
     * Converts a string containing the values "0" or "1" into a Boolean value.
     *
     * @param bit the string to be converted
     * @return true if the input string is "1", false if the input string is "0"
     */
    public Boolean convert0or1ToBoolean(String bit){
        return !bit.equals("0");
    }

    /**
     * Checks if the given value is equal to "A".
     *
     * @param value the string value to be checked
     * @return true if the value is not equal to "A", false otherwise
     */
    public Boolean initiatorIsA(String value){
        return !value.equals("A");
    }

    /**
     * Finds the outcome of a war based on the given code.
     *
     * @param outcome the code representing the war outcome
     * @return the ordinal value of the war outcome
     */
    public String findWarOutcome(Integer outcome){
        return switch (outcome) {
            case 1 -> War.OUTCOME_SIDE_A_WIN;
            case 2 ->War.OUTCOME_SIDE_B_WIN;
            case 3 -> War.OUTCOME_COMPROMISE;
            case 4 -> War.OUTCOME_TRANSFORM_INTO_WAR;
            case 5 -> War.OUTCOME_CONTINUING;
            case 6 -> War.OUTCOME_STALEMATE;
            case 7 -> War.OUTCOME_CONTINUE_BELOW_WAR_LEVEL;
            default -> War.OUTCOME_NONE;
        };
    }

    /**
     * Finds the religion data type based on the given type.
     *
     * @param type the type of religion data
     * @return the corresponding religion data type
     */
    public String findDataTypeReligion(Integer type){
        return switch (type) {
            case 1 -> Religion.TYPE_SINGLE_SOURCE;
            case 2 -> Religion.TYPE_MULTIPLE_SOURCE;
            case 3 -> Religion.TYPE_INTERPOLATED;
            case 4 -> Religion.TYPE_ADJUSTED;
            case 13 -> Religion.TYPE_SINGLE_SOURCE_INTERPOLATED;
            case 14 -> Religion.TYPE_SINGLE_SOURCE_ADJUSTED;
            case 134 -> Religion.TYPE_SINGLE_SOURCE_INTERPOLATED_ADJUSTED;
            case 23 -> Religion.TYPE_MULTIPLE_SOURCE_INTERPOLATED;
            case 24 -> Religion.TYPE_MULTIPLE_SOURCE_ADJUSTED;
            case 234 -> Religion.TYPE_MULTIPLE_SOURCE_INTERPOLATED_ADJUSTED;
            case 34 -> Religion.TYPE_INTERPOLATED_ADJUSTED;
            default -> Religion.TYPE_NONE;
        };
    }

    /**
     * Finds the reliability of a record based on the given reliability value.
     *
     * @param reliability the reliability value of the record
     * @return the reliability of the record
     */
    public String findReliabilityOfRecord(Integer reliability){
        return switch (reliability) {
            case 1 -> Religion.RELIABILITY_OF_RECORD_VERY_HIGH;
            case 35 -> Religion.RELIABILITY_OF_RECORD_VERY_LOW;
            default -> Religion.RELIABILITY_OF_RECORD_NONE;
        };
    }

    /**
     * Finds the level of reliability of a record based on the given level.
     *
     * @param level The level of reliability (1, 2, 3) representing high, medium, and low respectively.
     *
     * @return The level of reliability of the record as a string.
     */
    public String findLevelOfReliabilityOfRecord(Integer level){
        return switch (level) {
            case 1 -> Religion.LEVEL_OF_RELIABILITY_OF_RECORD_HIGH;
            case 2 -> Religion.LEVEL_OF_RELIABILITY_OF_RECORD_MEDIUM;
            case 3 -> Religion.LEVEL_OF_RELIABILITY_OF_RECORD_LOW;
            default -> Religion.LEVEL_OF_RELIABILITY_OF_RECORD_NONE;
        };
    }

    /**
     * Finds the diplomatic representation level based on the given level.
     *
     * @param level the level of representation
     * @return the corresponding diplomatic representation level
     */
    public String findDiplomaticRepresentationLevel(Integer level){
        return switch (level) {
            case 0 -> DiplomaticExchange.DR_NO_EVIDENCE_OF_DIPLOMATIC_EXCHANGE;
            case 1 -> DiplomaticExchange.DR_CHARGE_DAFFAIRES;
            case 2 -> DiplomaticExchange.DR_MINISTER;
            case 3 -> DiplomaticExchange.DR_AMBASSADOR;
            case 9 -> DiplomaticExchange.DR_OTHER;
            default -> DiplomaticExchange.DR_NONE;
        };
    }

    /**
     * Finds a diplomatic exchange for a given level.
     *
     * @param level the level of the diplomatic exchange
     * @return the diplomatic exchange
     */
    public String findAnyDiplomaticExchange(Integer level){
        return switch (level) {
            case 0 -> DiplomaticExchange.DE_NEITHER_SIDE;
            case 1 -> DiplomaticExchange.DE_AT_LEAST_ONE;
            default -> DiplomaticExchange.DE_NONE;
        };
    }

    /**
     * Finds the territory gain type based on the provided type value.
     *
     * @param type the type value to determine the territory gain type
     * @return the territory gain type as a string
     *
     * @param type the type value to determine the territory gain type
     * @return the territory gain type as a string
     */
    public String findTerritoryGainType(Integer type){
        return switch (type) {
            case 0 -> TerritorialChange.TYPE_DEPENDENT_TERRITORY;
            case 1 -> TerritorialChange.TYPE_HOMELAND_TERRITORY;
            default -> TerritorialChange.TYPE_NONE;
        };
    }

    /**
     * Finds the territorial exchange procedure based on the given procedure number.
     *
     * @param procedure the procedure number
     * @return the territorial exchange procedure associated with the given number
     */
    public String findTerritoryExchangeProcedure(Integer procedure){
        return switch (procedure) {
            case 1 -> TerritorialChange.PROCEDURE_CONQUEST;
            case 2 -> TerritorialChange.PROCEDURE_ANNEXATION;
            case 3 -> TerritorialChange.PROCEDURE_CESSION;
            case 4 -> TerritorialChange.PROCEDURE_SECESSION;
            case 5 -> TerritorialChange.PROCEDURE_UNIFICATION;
            case 6 -> TerritorialChange.PROCEDURE_MANDATED_TERRITORY;
            default -> TerritorialChange.PROCEDURE_NONE;
        };
    }

    /**
     * Creates a non-state entity with the given name.
     *
     * @param name the name of the entity to be created
     * @return the created entity if it already exists in the state repository, otherwise a new state entity is created and saved to the repository
     */
    public List<State> createNonStateEntity(String name){
        if(stateRepository.existsByName(name)){
            return stateRepository.findByName(name);
        }else{
            State state = new State();
            state.setId(sequenceGeneratorService.getSequenceNumber(State.SEQUENCE_NAME));
            state.setName(name);
            return Collections.singletonList(stateRepository.save(state));
        }
    }

    /**
     * Checks if the given string is numeric.
     *
     * @param strNum the string to be checked
     * @return true if the string is numeric, false otherwise
     */
    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    /**
     * Unzips a given zip file and returns a list of the extracted files.
     *
     * @param file The name of the zip file to be extracted.
     * @return A list of File objects representing the extracted files.
     * @throws IOException If an I/O error occurs during the extraction process.
     */
    public List<File> unzipZipFile(String file) throws IOException {
        List<File> output = new ArrayList<>();
        String zipFile = System.getProperty("user.dir") + DOWNLOAD_DIR + file;
        File destDir = new File(System.getProperty("user.dir") + DOWNLOAD_DIR + file.substring(0, file.lastIndexOf(".")));

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));
        ZipEntry zipEntry = zis.getNextEntry();

        while (zipEntry != null) {
            File newFile = newFile(destDir, zipEntry);
            if (zipEntry.isDirectory()) {
                if (!newFile.isDirectory() && !newFile.mkdirs()) {
                    csvZipFileCounter = 0;
                    throw new IOException("Failed to create directory " + newFile);
                }
            } else {
                // fix for Windows-created archives
                File parent = newFile.getParentFile();
                if (!parent.isDirectory() && !parent.mkdirs()) {
                    csvZipFileCounter = 0;
                    throw new IOException("Failed to create directory " + parent);
                }

                // write file content
                FileOutputStream fos = new FileOutputStream(newFile);
                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
                fos.close();
            }
            zipEntry = zis.getNextEntry();
            output.add(newFile);
        }

        zis.closeEntry();
        zis.close();
        csvZipFileCounter = 0;
        return output;
    }

    /**
     * Creates a new file in the specified destination directory based on the given ZipEntry.
     *
     * @param destinationDir the destination directory where the file will be created
     * @param zipEntry the ZipEntry representing the file
     * @return the newly created File object
     * @throws IOException if an I/O error occurs while creating the file or determining its path
     * @throws IllegalArgumentException if the file name doesn't end with ".csv"
     * @throws IOException if the entry is located outside of the target directory
     */
    private File newFile(File destinationDir, ZipEntry zipEntry) throws IOException {
        String fileName = zipEntry.getName();
        if(fileName.contains(".csv")){
            fileName = "data-" + csvZipFileCounter + ".csv";
            csvZipFileCounter++;
        }
        File destFile = new File(destinationDir, fileName);

        String destDirPath = destinationDir.getCanonicalPath();
        String destFilePath = destFile.getCanonicalPath();

        if (!destFilePath.startsWith(destDirPath + File.separator)) {
            csvZipFileCounter = 0;
            throw new IOException("Entry is outside of the target dir: " + fileName);
        }

        return destFile;
    }

    /**
     * Converts a string value to an integer.
     *
     * @param value the string value to be converted
     * @return the converted integer value, or 0 if the conversion fails
     */
    public Integer convertStringToInteger(String value){
        try{
            return Integer.valueOf(value);
        }catch(NumberFormatException e){
            return 0;
        }
    }
}
