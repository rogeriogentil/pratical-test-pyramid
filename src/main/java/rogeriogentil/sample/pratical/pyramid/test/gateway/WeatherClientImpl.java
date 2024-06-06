package rogeriogentil.sample.pratical.pyramid.test.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.ZoneId;
import java.util.Optional;

@Component
public class WeatherClientImpl implements WeatherClient {

    public static final double LATITUDE = -22.0175;
    public static final double LONGITUDE = -47.8908;
    public static final ZoneId ZONE = ZoneId.of("America/Sao_Paulo");

    @Autowired
    private final RestTemplate restTemplate;
    private final String weatherServiceUrl;

    public WeatherClientImpl(RestTemplate restTemplate,
                             @Value("${weather.url}") String weatherServiceUrl) {
        this.restTemplate = restTemplate;
        this.weatherServiceUrl = weatherServiceUrl;
    }

    @Override
    public Optional<WeatherResponse> fetchWeather() {
        // https://api.open-meteo.com/v1/forecast?latitude=-22.0175&longitude=-47.8908&hourly=temperature_2m&timezone=America%2FSao_Paulo
        var url = new StringBuilder(weatherServiceUrl)
                .append("?latitude=").append(LATITUDE)
                .append("&longitude=").append(LONGITUDE)
                .append("&hourly=temperature_2m")
                .append("&timezone=").append(ZONE);

        try {
            WeatherResponse weatherResponse = restTemplate.getForObject(url.toString(), WeatherResponse.class);
            return Optional.ofNullable(weatherResponse);
        } catch (RestClientException rce) {
            return Optional.empty();
        }
    }
}
