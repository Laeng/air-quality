package co.laeng.airquality.type;

import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.dto.PollutantDTO;
import co.laeng.airquality.infra.kr.go.seoul.data.SeoulAirQualityAPI;
import co.laeng.airquality.infra.kr.go.seoul.data.SeoulAirQualityResult;
import co.laeng.airquality.infra.kr.go.seoul.data.dto.SeoulAirQualityDTO;
import co.laeng.airquality.util.DateConverter;
import lombok.Getter;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Getter
public enum StateType {
    SEOUL("seoul") {
        @Override
        public List<CityPollutionDTO> getData() throws IOException {
            SeoulAirQualityResult result = SeoulAirQualityAPI.getInstance().getRealTimeAirQuality();
            return result.getRealtimeCityAir().getRow().stream()
                    .map(StateType::createSeoulAirQualityDTO)
                    .collect(Collectors.toList());
        }
    },
    BUSAN("busan") {
        @Override
        public List<CityPollutionDTO> getData() throws IOException {
            return null;
        }
    };

    private final String name;

    StateType(String name) {
        this.name = name;
    }

    public static StateType from(String name) throws NoSuchElementException {
        return Arrays.stream(StateType.values())
                .filter(type -> type.getName().equals(name.toLowerCase()))
                .findFirst()
                .orElseThrow();
    }

    private static CityPollutionDTO createSeoulAirQualityDTO(SeoulAirQualityDTO dto) {
        return CityPollutionDTO.builder()
                .city(dto.getCity())
                .updateAt(DateConverter.toISO8601(dto.getUpdateAt()))
                .pm25(createPollutantDTO(PollutantType.PM25, dto.getPm25()))
                .pm10(createPollutantDTO(PollutantType.PM10, dto.getPm10()))
                .o3(createPollutantDTO(PollutantType.O3, dto.getO3()))
                .so2(createPollutantDTO(PollutantType.SO2, dto.getSo2()))
                .no2(createPollutantDTO(PollutantType.NO2, dto.getNo2()))
                .co(createPollutantDTO(PollutantType.CO, dto.getCo()))
                .build();
    }

    private static PollutantDTO createPollutantDTO(PollutantType pollutant, double value) {
        return PollutantDTO.builder()
                .pollutant(pollutant)
                .value(value)
                .build();
    }

    public abstract List<CityPollutionDTO> getData() throws IOException;


}
