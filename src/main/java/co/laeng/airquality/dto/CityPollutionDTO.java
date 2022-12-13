package co.laeng.airquality.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CityPollutionDTO {
    private final String city;
    private final PollutantDTO pm25;
    private final PollutantDTO pm10;
    private final PollutantDTO o3;
    private final PollutantDTO no2;
    private final PollutantDTO co;
    private final PollutantDTO so2;

    private final String updateAt;

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
