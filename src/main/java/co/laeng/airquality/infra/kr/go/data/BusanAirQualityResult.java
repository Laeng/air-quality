package co.laeng.airquality.infra.kr.go.data;

import co.laeng.airquality.infra.kr.go.data.dto.AirQualityInfoClassifiedByStationDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class BusanAirQualityResult {
    @JsonProperty("getAirQualityInfoClassifiedByStation")
    private AirQualityInfoClassifiedByStationDTO getAirQualityInfoClassifiedByStation;
}
