package com.w.weather_api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "¡API Weather corriendo!";
    }

    @GetMapping("/weather")
    public String weather() {
        return "Hoy hace sol y 25°C"; // ejemplo simple
    }
}
