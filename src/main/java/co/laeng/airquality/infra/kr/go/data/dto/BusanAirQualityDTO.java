package co.laeng.airquality.infra.kr.go.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusanAirQualityDTO {
    @JsonProperty("site")
    private String site;

    @JsonProperty("areaIndex")
    private int areaIndex;

    @JsonProperty("controlnumber")
    private String controlNumber;

    @JsonProperty("repItem")
    private String repItem;

    @JsonProperty("repVal")
    private int repVal;

    @JsonProperty("repCai")
    private String repCai;

    @JsonProperty("so2")
    private Double so2;

    @JsonProperty("so2Cai")
    private String so2Cai;

    @JsonProperty("no2")
    private Double no2;

    @JsonProperty("no2Cai")
    private String no2Cai;

    @JsonProperty("o3")
    private Double o3;

    @JsonProperty("o3Cai")
    private String o3Cai;

    @JsonProperty("co")
    private Double co;

    @JsonProperty("coCai")
    private String coCai;

    @JsonProperty("pm25")
    private Double pm25;

    @JsonProperty("pm25Cai")
    private String pm25Cai;

    @JsonProperty("pm10")
    private Double pm10;

    @JsonProperty("pm10Cai")
    private String pm10Cai;
}
