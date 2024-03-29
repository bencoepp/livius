package bencoepp.livius.weather.controller;

import bencoepp.livius.repositories.weather.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * The CountController class is a REST controller that handles count-related API requests for weather data.
 */
@RestController
@RequestMapping("/api/v1/weather/count")
public class CountController {
    @Autowired
    private WeatherRepository weatherRepository;

    /**
     * Returns the total count of weather records.
     *
     * @return the total count of weather records
     */
    @GetMapping("/count/total")
    public ResponseEntity<Long> countTotal(){
        return ResponseEntity.ok(weatherRepository.count());
    }

    /**
     * Returns the count of weather records for a specific station ID.
     *
     * @param id the station ID for which to get the weather record count
     * @return a ResponseEntity containing the count of weather records. Returns `ok` status with the count if the station exists, or `bad request` status otherwise
     */
    @GetMapping("/count/station/{station}")
    public ResponseEntity<Long> countByStation(@PathVariable String station){
        if(weatherRepository.existsByStation(station)){
            return ResponseEntity.ok(weatherRepository.countByStation(station));
        }
        return ResponseEntity.badRequest().build();
    }
}
