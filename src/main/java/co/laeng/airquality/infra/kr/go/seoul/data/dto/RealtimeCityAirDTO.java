package co.laeng.airquality.infra.kr.go.seoul.data.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class RealtimeCityAirDTO {
    @JsonProperty("list_total_count")
    private int listTotalCount;

    @JsonProperty("row")
    private List<SeoulAirQualityDTO> row;
}
