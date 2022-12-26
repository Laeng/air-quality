package co.laeng.airquality.infra.kr.go.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AirQualityInfoClassifiedByStationDTO {
    @JsonProperty("header")
    private ApiHeaderDTO header;

    @JsonProperty("body")
    private ApiBodyDTO body;

}
