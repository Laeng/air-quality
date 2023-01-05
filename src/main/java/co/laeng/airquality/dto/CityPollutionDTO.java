package co.laeng.airquality.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityPollutionDTO {
    private String city;
    private PollutantDTO pm25;
    private PollutantDTO pm10;
    private PollutantDTO o3;
    private PollutantDTO no2;
    private PollutantDTO co;
    private PollutantDTO so2;

    private String updateAt;

    public CityPollutionDTO() {

    }

    @Builder
    public CityPollutionDTO(
            String city,
            PollutantDTO pm25,
            PollutantDTO pm10,
            PollutantDTO o3,
            PollutantDTO no2,
            PollutantDTO co,
            PollutantDTO so2,
            String updateAt
    ) {
        this.city = city;
        this.pm25 = pm25;
        this.pm10 = pm10;
        this.o3 = o3;
        this.no2 = no2;
        this.co = co;
        this.so2 = so2;
        this.updateAt = updateAt;
    }
}
