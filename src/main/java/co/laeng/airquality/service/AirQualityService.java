package co.laeng.airquality.service;

import co.laeng.airquality.dto.AirQualityDTO;
import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.dto.PollutantDTO;
import co.laeng.airquality.factory.StateAirQualityRepositoryFactory;
import co.laeng.airquality.type.PollutantType;
import co.laeng.airquality.type.StateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AirQualityService {

    private final StateAirQualityRepositoryFactory stateAirQualityRepositoryFactory;

    public AirQualityService(
            StateAirQualityRepositoryFactory stateAirQualityRepositoryFactory
    ) {
        this.stateAirQualityRepositoryFactory = stateAirQualityRepositoryFactory;
    }

    public AirQualityDTO getAirQuality(String state) {
        try {
            List<CityPollutionDTO> pollutions = this.stateAirQualityRepositoryFactory
                    .getAirQualityRepository(StateType.from(state))
                    .getCityPollution();

            return this.createAirQualityDTO(
                    state.toLowerCase(),
                    this.createPM25AverageDTO(pollutions),
                    pollutions
            );

        } catch (RuntimeException exception) {
            return this.createAirQualityDTO(null, null, null);
        }
    }

    private PollutantDTO createPM25AverageDTO(List<CityPollutionDTO> pollutions) {
        return PollutantDTO.builder()
                .pollutant(PollutantType.PM25)
                .value(this.calculatePM25Average(pollutions))
                .build();
    }

    private double calculatePM25Average(List<CityPollutionDTO> pollutions) {
        double sum = pollutions.stream()
                .mapToDouble(dto -> dto.getPm25().getValue())
                .sum();

        return sum / pollutions.size();
    }

    private AirQualityDTO createAirQualityDTO(
            String state,
            PollutantDTO stateAvg,
            List<CityPollutionDTO> cities
    ) {
        return AirQualityDTO.builder()
                .state(state)
                .stateAvg(stateAvg)
                .cities(cities)
                .build();
    }
}
