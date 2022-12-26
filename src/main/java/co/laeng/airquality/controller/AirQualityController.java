package co.laeng.airquality.controller;

import co.laeng.airquality.dto.AirQualityDTO;
import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.service.AirQualityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<AirQualityDTO> getAirQuality(
            @PathVariable(name = "state") String state,
            @RequestParam(name = "city", required = false) String city
    ) {
        try {
            AirQualityDTO dto = this.airQualityService.getAirQuality(state);

            if (dto != null && city != null) {
                CityPollutionDTO cityPollution = dto.getCities().stream()
                        .filter(cityDTO -> cityDTO.getCity().equals(city))
                        .findFirst()
                        .orElseThrow();

                dto = new AirQualityDTO(
                        dto.getState(),
                        dto.getStateAvg(),
                        List.of(cityPollution));
            }

            return new ResponseEntity<>(dto, new HttpHeaders(), HttpStatus.OK);

        } catch (RuntimeException exception) {
            return new ResponseEntity<>(null, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
