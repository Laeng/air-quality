package co.laeng.airquality.infra.kr.go.seoul.data;

import co.laeng.airquality.infra.kr.go.seoul.data.dto.RealtimeCityAirDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class SeoulAirQualityResult {
    @JsonProperty("RealtimeCityAir")
    private RealtimeCityAirDTO realtimeCityAir;
}
