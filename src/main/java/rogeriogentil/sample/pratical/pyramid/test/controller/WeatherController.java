package rogeriogentil.sample.pratical.pyramid.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rogeriogentil.sample.pratical.pyramid.test.gateway.WeatherClient;
import rogeriogentil.sample.pratical.pyramid.test.gateway.WeatherResponse;

@RestController
public class WeatherController {

    private final WeatherClient weatherClient;

    @Autowired
    public WeatherController(WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @GetMapping("/weather")
    public String weather() {
        return weatherClient.fetchWeather()
                .map(WeatherResponse::getSummary)
                .orElse("Sorry! It wasn't possible to fetch the weather.");
    }
}
