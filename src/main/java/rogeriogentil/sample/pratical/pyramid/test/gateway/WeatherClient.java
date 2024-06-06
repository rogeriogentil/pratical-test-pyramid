package rogeriogentil.sample.pratical.pyramid.test.gateway;

import java.util.Optional;

public interface WeatherClient {

    Optional<WeatherResponse> fetchWeather();
}
