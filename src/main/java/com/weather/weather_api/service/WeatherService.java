package com.weather.weather_api.service;

import com.weather.weather_api.model.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper objectMapper = new ObjectMapper();

    public WeatherData getWeatherByCity(String city) {
        try {
            String url = String.format("%s?q=%s&appid=%s&units=metric&lang=es",
                    apiUrl, city, apiKey);

            String response = restTemplate.getForObject(url, String.class);
            JsonNode root = objectMapper.readTree(response);

            WeatherData weather = new WeatherData();
            weather.setCity(root.get("name").asText());
            weather.setCountry(root.get("sys").get("country").asText());
            weather.setTemperature(root.get("main").get("temp").asDouble());
            weather.setFeelsLike(root.get("main").get("feels_like").asDouble());
            weather.setHumidity(root.get("main").get("humidity").asInt());
            weather.setDescription(root.get("weather").get(0).get("description").asText());
            weather.setWindSpeed(root.get("wind").get("speed").asDouble());
            weather.setIcon(root.get("weather").get(0).get("icon").asText());

            return weather;
        } catch (Exception e) {
            throw new RuntimeException("Error al obtener datos del clima: " + e.getMessage());
        }
    }
}