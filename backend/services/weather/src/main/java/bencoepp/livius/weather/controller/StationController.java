package bencoepp.livius.weather.controller;

import bencoepp.livius.repositories.weather.WeatherRepository;
import bencoepp.livius.utils.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

/**
 * The StationController class is responsible for handling REST API requests related to weather stations.
 */
@RestController
@RequestMapping("/api/v1/weather/station")
public class StationController {

    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private WeatherUtil weatherUtil;

    /**
     * Retrieves all the stations in the weather repository.
     *
     * @return a ResponseEntity containing a map of station names and identifiers
     */
    @GetMapping("/all")
    public ResponseEntity<Map<String, String>> getAllStations(){
        return ResponseEntity.ok(weatherUtil.findAllStations());
    }

    /**
     * Returns the count of weather records for a specific station ID.
     *
     * @param station the station ID for which to get the weather record count
     * @return a ResponseEntity containing the count of weather records. Returns `ok` status with the count if the station exists, or `bad request` status otherwise
     */
    @GetMapping("/count/{station}")
    public ResponseEntity<Long> countByStation(@PathVariable String station){
        if(weatherRepository.existsByStation(station)){
            return ResponseEntity.ok(weatherRepository.countByStation(station));
        }
        return ResponseEntity.badRequest().build();
    }
}
