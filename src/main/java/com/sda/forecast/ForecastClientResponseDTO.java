package com.sda.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastClientResponseDTO {
    private List<SingleForecastDTO> daily;

    @Data
    public static class SingleForecastDTO {
        @JsonProperty("dt")
        private Long timestamp;
        @JsonProperty("temp")
        private TemperatureDTO temperature;
        private Integer pressure;
        private Integer humidity;
        @JsonProperty("wind_speed")
        private Double windSpeed;
        @JsonProperty("wind_deg")
        private Integer windDeg;

        @Data
        public static class TemperatureDTO {
            private Double day;
        }
    }
}
