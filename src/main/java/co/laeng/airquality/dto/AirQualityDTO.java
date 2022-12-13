package co.laeng.airquality.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AirQualityDTO {
    private final String state;
    private final PollutantDTO stateAvg;
    private final List<CityPollutionDTO> cities;

    @Builder
    public AirQualityDTO(String state, PollutantDTO stateAvg, List<CityPollutionDTO> cities) {
        this.state = state;
        this.stateAvg = stateAvg;
        this.cities = cities;
    }
}
