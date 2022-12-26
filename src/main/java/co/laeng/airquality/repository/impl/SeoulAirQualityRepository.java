package co.laeng.airquality.repository.impl;

import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.infra.kr.go.seoul.data.SeoulAirQualityAPI;
import co.laeng.airquality.infra.kr.go.seoul.data.SeoulAirQualityResult;
import co.laeng.airquality.repository.StateAirQualityRepository;
import co.laeng.airquality.type.StateType;
import co.laeng.airquality.util.AirQualityDtoConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SeoulAirQualityRepository implements StateAirQualityRepository {

    @Value("${secret.seoul-air-quality}")
    private String key;

    @Override
    public List<CityPollutionDTO> getCityPollution() throws RuntimeException {
        SeoulAirQualityAPI api = new SeoulAirQualityAPI(this.key);
        SeoulAirQualityResult result = api.getRealTimeAirQuality();

        return result.getRealtimeCityAir().getRow().stream()
                .map(AirQualityDtoConverter::toCityPollutionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StateType getStateType() {
        return StateType.SEOUL;
    }
}
