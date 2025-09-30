package com.weather.weather_api.controller;

import com.weather.weather_api.model.WeatherData;
import com.weather.weather_api.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/weather")
@CrossOrigin(origins = "*")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/city/{cityName}")
    public ResponseEntity<WeatherData> getWeatherByCity(@PathVariable String cityName) {
        try {
            WeatherData weather = weatherService.getWeatherByCity(cityName);
            return ResponseEntity.ok(weather);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("¡API funcionando correctamente!");
    }
}