package bencoepp.livius.weather.controller;

import bencoepp.livius.repositories.weather.WeatherRepository;
import bencoepp.livius.utils.WeatherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/weather/station")
public class StationController {

    @Autowired
    private WeatherRepository weatherRepository;
    @Autowired
    private WeatherUtil weatherUtil;

    @GetMapping("/all")
    public ResponseEntity<Map<String, String>> getAllStations(){
        return ResponseEntity.ok(weatherUtil.findAllStations());
    }
}
