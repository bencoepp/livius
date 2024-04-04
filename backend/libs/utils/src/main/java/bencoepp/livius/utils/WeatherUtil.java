package bencoepp.livius.utils;

import bencoepp.livius.entities.weather.Weather;
import bencoepp.livius.repositories.weather.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The WeatherUtil class provides utility methods for working with weather data.
 */
@Service
public class WeatherUtil {
    @Autowired
    private WeatherRepository weatherRepository;

    /**
     * Creates a Pageable object using the specified page number and page size.
     *
     * @param page the page number
     * @param size the page size
     * @return a Pageable object
     */
    private Pageable createPageRequestUsing(int page, int size) {
        return PageRequest.of(page, size);
    }

    /**
     * This method retrieves a page of Weather objects from the weather repository, using a specified page number and page size.
     *
     * @param page the page number
     * @param size the page size
     * @return a Page object containing the Weather objects for the specified page
     */
    public Page<Weather> getPageableWeather(int page, int size) {

        Pageable pageRequest = createPageRequestUsing(page, size);

        List<Weather> all = weatherRepository.findAll();
        int start = (int) pageRequest.getOffset();
        int end = Math.min((start + pageRequest.getPageSize()), all.size());

        List<Weather> pageContent = all.subList(start, end);
        return new PageImpl<>(pageContent, pageRequest, all.size());
    }

    /**
     * Retrieves a list of Weather objects from the weather repository, using a specified page number and page size.
     *
     * @param page the page number
     * @param size the page size
     * @return a List of Weather objects
     */
    public List<Weather> getWeatherListFromPage(int page, int size) {
        Pageable pageRequest = createPageRequestUsing(page, size);
        Page<Weather> allCustomers = weatherRepository.findAll(pageRequest);

        return allCustomers.hasContent() ? allCustomers.getContent() : Collections.emptyList();
    }

    public Map<String, String> findAllStations(){
        Map<String, String> output = new HashMap<>();

        //TODO needs to be implemented

        return output;
    }
}
