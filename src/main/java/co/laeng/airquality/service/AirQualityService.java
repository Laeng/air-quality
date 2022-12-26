package co.laeng.airquality.service;

import co.laeng.airquality.dto.AirQualityDTO;
import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.dto.PollutantDTO;
import co.laeng.airquality.repository.StateAirQualityRepository;
import co.laeng.airquality.type.PollutantType;
import co.laeng.airquality.type.StateType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class AirQualityService {

    private final List<StateAirQualityRepository> stateAirQualityRepositories;

    @Autowired
    public AirQualityService(
            List<StateAirQualityRepository> stateAirQualityRepositories
    ) {
        this.stateAirQualityRepositories = stateAirQualityRepositories;
    }

    public AirQualityDTO getAirQuality(String state) {
        try {
            List<CityPollutionDTO> pollutions = this.getStateRepository(StateType.from(state)).getCityPollution();

            return this.createAirQualityDTO(
                    state.toLowerCase(),
                    this.createPM25AverageDTO(pollutions),
                    pollutions
            );

        } catch (RuntimeException exception) {
            System.out.println(exception.getMessage());
            Arrays.stream(exception.getStackTrace())
                    .forEach(e -> System.out.println(e.toString()));

            return this.createAirQualityDTO(null, null, null);
        }
    }

    private StateAirQualityRepository getStateRepository(StateType state) {
        try {
            return this.stateAirQualityRepositories.stream()
                    .filter(repository -> repository.getStateType().equals(state))
                    .findFirst()
                    .orElseThrow();
        } catch (RuntimeException exception) {
            String message = String.format("[service] %s 에 대한 리포지토리를 찾을 수 없습니다.", state.toString());
            throw new RuntimeException(message, exception);
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
