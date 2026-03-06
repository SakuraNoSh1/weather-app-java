package com.weather.weather_api.service;

import com.weather.weather_api.model.WeatherData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class WeatherServiceTest {

    @Test
    void testWeatherDataObject() {

        WeatherData weather = new WeatherData();
        weather.setCity("London");
        weather.setCountry("GB");
        weather.setTemperature(20);

        assertEquals("London", weather.getCity());
        assertEquals("GB", weather.getCountry());
        assertEquals(20, weather.getTemperature());

    }
}