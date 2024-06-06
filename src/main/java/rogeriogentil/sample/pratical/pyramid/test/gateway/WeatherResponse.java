package rogeriogentil.sample.pratical.pyramid.test.gateway;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {

    private Hourly hourly;

    @JsonProperty(value = "hourly_units")
    private HourlyUnits hourlyUnits;

    public Hourly getHourly() {
        return hourly;
    }

    public HourlyUnits getHourlyUnits() {
        return hourlyUnits;
    }

    public String getSummary() {
        StringBuilder forecast = new StringBuilder();
        for (int i = 0; i < this.hourly.getTime().size(); i++) {
            forecast.append(this.hourly.getTime().get(i))
                    .append(": ")
                    .append(this.hourly.getTemperature().get(i))
                    .append(" ")
                    .append(this.hourlyUnits.getTemperatureUnit())
                    .append("\n");
        }
        return forecast.toString();
    }

    public static class HourlyUnits {
        @JsonProperty(value = "time")
        private String timeFormat;

        @JsonProperty(value = "temperature_2m")
        private String temperatureUnit;

        public HourlyUnits(String timeFormat, String temperatureUnit) {
            this.timeFormat = timeFormat;
            this.temperatureUnit = temperatureUnit;
        }

        public String getTimeFormat() {
            return timeFormat;
        }

        public String getTemperatureUnit() {
            return temperatureUnit;
        }
    }

    public static class Hourly {
        private List<LocalDateTime> time;

        @JsonProperty(value = "temperature_2m")
        private List<Double> temperature;

        public Hourly(List<LocalDateTime> time, List<Double> temperature) {
            this.time = time;
            this.temperature = temperature;
        }

        public List<LocalDateTime> getTime() {
            return time;
        }

        public List<Double> getTemperature() {
            return temperature;
        }
    }
}
