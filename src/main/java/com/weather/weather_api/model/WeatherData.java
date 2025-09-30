package com.weather.weather_api.model;

public class WeatherData {
    private String city;
    private String country;
    private double temperature;
    private String description;
    private double feelsLike;
    private int humidity;
    private double windSpeed;
    private String icon;

    public WeatherData() {}

    public WeatherData(String city, String country, double temperature,
                       String description, double feelsLike, int humidity,
                       double windSpeed, String icon) {
        this.city = city;
        this.country = country;
        this.temperature = temperature;
        this.description = description;
        this.feelsLike = feelsLike;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.icon = icon;
    }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public double getTemperature() { return temperature; }
    public void setTemperature(double temperature) { this.temperature = temperature; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public double getFeelsLike() { return feelsLike; }
    public void setFeelsLike(double feelsLike) { this.feelsLike = feelsLike; }

    public int getHumidity() { return humidity; }
    public void setHumidity(int humidity) { this.humidity = humidity; }

    public double getWindSpeed() { return windSpeed; }
    public void setWindSpeed(double windSpeed) { this.windSpeed = windSpeed; }

    public String getIcon() { return icon; }
    public void setIcon(String icon) { this.icon = icon; }
}