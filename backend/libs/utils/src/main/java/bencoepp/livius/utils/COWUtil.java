package bencoepp.livius.utils;

import bencoepp.livius.entities.war.War;
import bencoepp.livius.entities.war.WarOutcome;
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
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
public class COWUtil {

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    public static final String DOWNLOAD_DIR = "/cow/data/";

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
     * @throws ParseException if the input strings cannot be parsed into a valid date
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
     * Finds the outcome of a war based on the given code.
     *
     * @param outcome the code representing the war outcome
     * @return the ordinal value of the war outcome
     */
    public Integer findWarOutcome(Integer outcome){
        return switch (outcome) {
            case 1 -> WarOutcome.SIDE_A_WIN.ordinal();
            case 2 -> WarOutcome.SIDE_B_WIN.ordinal();
            case 3 -> WarOutcome.COMPROMISE.ordinal();
            case 4 -> WarOutcome.TRANSFORM_INTO_WAR.ordinal();
            case 5 -> WarOutcome.CONTINUING.ordinal();
            case 6 -> WarOutcome.STALEMATE.ordinal();
            case 7 -> WarOutcome.CONTINUE_BELOW_WAR_LEVEL.ordinal();
            default -> WarOutcome.NONE.ordinal();
        };
    }
}
