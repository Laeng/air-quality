package co.laeng.airquality.controller;

import co.laeng.airquality.dto.AirQualityDTO;
import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.dto.PollutantDTO;
import co.laeng.airquality.type.PollutantType;
import co.laeng.airquality.type.StateType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/api/air-quality")
public class AirQualityController {

    @GetMapping("/{state}")
    public ResponseEntity<AirQualityDTO> findByRegionCode(
            @PathVariable(name = "state") String state,
            @RequestParam(name = "city", required = false) String city
    ) {
        try {
            List<CityPollutionDTO> pollutions = StateType.from(state).getData();
            AirQualityDTO airQuality = this.createAirQualityDTO(
                    state.toLowerCase(),
                    this.createPM25AverageDTO(pollutions),
                    this.filterByCityName(pollutions, city)
            );

            return new ResponseEntity<>(
                    airQuality,
                    new HttpHeaders(),
                    HttpStatus.OK
            );
        } catch (RuntimeException | IOException exception) {
            return new ResponseEntity<>(
                    this.createAirQualityDTO("", null, null),
                    new HttpHeaders(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
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

    private List<CityPollutionDTO> filterByCityName(List<CityPollutionDTO> cities, String city) {
        if (city != null) {
            return cities.stream()
                    .filter(dto -> dto.getCity().equals(city.toLowerCase()))
                    .collect(Collectors.toList());
        }

        return cities;
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
