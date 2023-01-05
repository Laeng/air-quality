package co.laeng.airquality.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
public class AirQualityDTO {
    private String state;
    private PollutantDTO stateAvg;
    private List<CityPollutionDTO> cities;

    public AirQualityDTO() {

    }

    @Builder
    public AirQualityDTO(String state, PollutantDTO stateAvg, List<CityPollutionDTO> cities) {
        this.state = state;
        this.stateAvg = stateAvg;
        this.cities = cities;
    }
}
