package co.laeng.airquality.infra.kr.go.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApiBodyItemsDTO {

    @JsonProperty("item")
    private List<BusanAirQualityDTO> item;
}
