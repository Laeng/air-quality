package co.laeng.airquality.util;

import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.dto.PollutantDTO;
import co.laeng.airquality.infra.kr.go.seoul.data.dto.SeoulAirQualityDTO;
import co.laeng.airquality.type.PollutantType;

public class AirQualityDtoConverter {
    public static CityPollutionDTO toCityPollutionDTO(SeoulAirQualityDTO dto) {
        return CityPollutionDTO.builder()
                .city(dto.getCity())
                .updateAt(DateConverter.toISO8601(dto.getUpdateAt()))
                .pm25(toPollutantDTO(PollutantType.PM25, dto.getPm25()))
                .pm10(toPollutantDTO(PollutantType.PM10, dto.getPm10()))
                .o3(toPollutantDTO(PollutantType.O3, dto.getO3()))
                .so2(toPollutantDTO(PollutantType.SO2, dto.getSo2()))
                .no2(toPollutantDTO(PollutantType.NO2, dto.getNo2()))
                .co(toPollutantDTO(PollutantType.CO, dto.getCo()))
                .build();
    }

    public static PollutantDTO toPollutantDTO(PollutantType pollutant, double value) {
        return PollutantDTO.builder()
                .pollutant(pollutant)
                .value(value)
                .build();
    }
}
