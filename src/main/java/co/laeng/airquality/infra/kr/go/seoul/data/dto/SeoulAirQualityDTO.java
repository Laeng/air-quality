package co.laeng.airquality.infra.kr.go.seoul.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SeoulAirQualityDTO {
    @JsonProperty("MSRDT")
    private String updateAt;

    @JsonProperty("MSRRGN_NM")
    private String region;

    @JsonProperty("MSRSTE_NM")
    private String city;

    @JsonProperty("PM10")
    private double pm10;

    @JsonProperty("PM25")
    private double pm25;

    @JsonProperty("O3")
    private double o3;

    @JsonProperty("NO2")
    private double no2;

    @JsonProperty("CO")
    private double co;

    @JsonProperty("SO2")
    private double so2;

    @JsonProperty("IDEX_NM")
    private String benchmarkStatus;

    @JsonProperty("IDEX_MVL")
    private double benchmarkValue;

    @JsonProperty("ARPLT_MAIN")
    private String benchmarkType;

}
