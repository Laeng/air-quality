package co.laeng.airquality.service;

import co.laeng.airquality.dto.AirQualityDTO;
import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.dto.PollutantDTO;
import co.laeng.airquality.type.PollutantType;
import co.laeng.airquality.type.StateType;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AirQualityService {
    public AirQualityDTO getAirQuality(String state) {
        try {
            List<CityPollutionDTO> pollutions = StateType.from(state).getData();

            return this.createAirQualityDTO(
                    state.toLowerCase(),
                    this.createPM25AverageDTO(pollutions),
                    pollutions
            );

        } catch (RuntimeException | IOException exception) {
            System.out.println(exception.getMessage());
            Arrays.stream(exception.getStackTrace())
                    .forEach(e -> System.out.println(e.toString()));

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
