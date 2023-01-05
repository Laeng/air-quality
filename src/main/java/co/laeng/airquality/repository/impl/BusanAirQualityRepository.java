package co.laeng.airquality.repository.impl;

import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.infra.kr.go.data.BusanAirQualityAPI;
import co.laeng.airquality.infra.kr.go.data.BusanAirQualityResult;
import co.laeng.airquality.repository.StateAirQualityRepository;
import co.laeng.airquality.type.StateType;
import co.laeng.airquality.util.AirQualityDtoConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BusanAirQualityRepository implements StateAirQualityRepository {

    @Value("${secret.busan-air-quality}")
    private String key;

    @Override
    @Cacheable(cacheNames = "getCityPollution")
    public List<CityPollutionDTO> getCityPollution() throws RuntimeException {
        return this.callCityPollutionApi();
    }

    @Override
    @CachePut(cacheNames = "getCityPollution")
    public List<CityPollutionDTO> update() {
        return this.callCityPollutionApi();
    }

    private List<CityPollutionDTO> callCityPollutionApi() {
        if (this.key == null) {
            throw new RuntimeException("[repository] 부산시 대기질 API 인증 키를 찾을 수 없습니다.");
        }

        BusanAirQualityAPI api = new BusanAirQualityAPI(this.key);
        BusanAirQualityResult result = api.getAirQualityInfoClassifiedByStation();

        return result.getGetAirQualityInfoClassifiedByStation().getBody().getItems().getItem().stream()
                .map(AirQualityDtoConverter::toCityPollutionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StateType getState() {
        return StateType.BUSAN;
    }
}
