package bencoepp.livius.weather.controller;

import bencoepp.livius.entities.weather.Weather;
import bencoepp.livius.repositories.weather.WeatherRepository;
import bencoepp.livius.utils.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

/**
 * The WeatherController class is a REST controller that handles requests related to weather data.
 */
@RestController
@RequestMapping("/api/v1/weather")
public class WeatherController {

    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private WeatherUtil weatherUtil;
    /**
     * Retrieves all weather data.
     *
     * @param page the page number of the results to retrieve (default: 0)
     * @param size the number of results per page (default: 24)
     * @return a ResponseEntity containing a list of Weather objects
     */
    @GetMapping("/all")
    public ResponseEntity<List<Weather>> allWeatherData(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "24") int size){
        return ResponseEntity.ok(weatherUtil.getWeatherListFromPage(page, size));
    }

    /**
     * Retrieves the total count of weather records.
     *
     * @return a ResponseEntity containing the total count of weather records
     */
    @GetMapping("/count/total")
    public ResponseEntity<Long> countTotal(){
        return ResponseEntity.ok(weatherRepository.count());
    }

    /**
     * Retrieves the weather information for a specific ID.
     *
     * @param id the ID of the weather information to retrieve
     * @return a ResponseEntity containing the weather information if found, or a bad request response if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Weather> getWeather(@PathVariable String id){
        Optional<Weather> optionalWeather = weatherRepository.findById(id);
        return optionalWeather.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /*@GetMapping("/search")
    public ResponseEntity<List<Weather>> searchWeather(){

    }*/
}
