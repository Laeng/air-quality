package co.laeng.airquality.infra.kr.go.seoul.data;

import co.laeng.airquality.infra.kr.go.seoul.data.dto.RealtimeCityAirDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SeoulAirQualityResult {
    @JsonProperty("RealtimeCityAir")
    private RealtimeCityAirDTO realtimeCityAir;
}
