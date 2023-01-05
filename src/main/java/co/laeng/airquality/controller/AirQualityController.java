package co.laeng.airquality.controller;

import co.laeng.airquality.dto.AirQualityDTO;
import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.service.AirQualityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/air-quality")
public class AirQualityController {

    private final AirQualityService airQualityService;

    @Autowired
    public AirQualityController(
            AirQualityService airQualityService
    ) {
        this.airQualityService = airQualityService;
    }

    @GetMapping("/{state}")
    public AirQualityDTO getAirQuality(
            @PathVariable(name = "state") String state,
            @RequestParam(name = "city", required = false) String city
    ) {
        try {
            AirQualityDTO dto = this.airQualityService.getAirQuality(state.toLowerCase());

            if (dto != null && city != null) {
                CityPollutionDTO cityPollution = dto.getCities().stream()
                        .filter(cityDTO -> cityDTO.getCity().equals(city.toLowerCase()))
                        .findFirst()
                        .orElseThrow(() -> new RuntimeException("[service] 도시를 찾을 수 없습니다."));

                dto = new AirQualityDTO(
                        dto.getState(),
                        dto.getStateAvg(),
                        List.of(cityPollution));
            }

            return dto;

        } catch (RuntimeException exception) {
            return new AirQualityDTO(null, null, null);
        }
    }
}
