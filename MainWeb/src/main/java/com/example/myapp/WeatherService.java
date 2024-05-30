package com.example.myapp;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.json.JSONObject;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private final String urlTemplate = "http://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&appid=%s";

    public String getWeather(String city) {
        String url = String.format(urlTemplate, city, apiKey);
        RestTemplate restTemplate = new RestTemplate();
        String response;

        try {
            response = restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            return "Error fetching weather data: " + e.getMessage();
        }

        try {
            JSONObject json = new JSONObject(response);
            double temp = json.getJSONObject("main").getDouble("temp");
            String description = json.getJSONArray("weather").getJSONObject(0).getString("description");
            return String.format("Current temperature in %s is %.1f°C with %s.", city, temp, description);
        } catch (Exception e) {
            return "Error parsing weather data: " + e.getMessage();
        }
    }
}
