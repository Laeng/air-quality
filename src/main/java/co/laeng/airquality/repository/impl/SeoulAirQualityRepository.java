package co.laeng.airquality.repository.impl;

import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.infra.kr.go.seoul.data.SeoulAirQualityAPI;
import co.laeng.airquality.infra.kr.go.seoul.data.SeoulAirQualityResult;
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
public class SeoulAirQualityRepository implements StateAirQualityRepository {

    @Value("${secret.seoul-air-quality}")
    private String key;

    @Override
    @Cacheable(cacheNames = "getCityPollution")
    public List<CityPollutionDTO> getCityPollution() {
        return this.callCityPollutionApi();
    }

    @Override
    @CachePut(cacheNames = "getCityPollution")
    public List<CityPollutionDTO> update() {
        return this.callCityPollutionApi();
    }

    private List<CityPollutionDTO> callCityPollutionApi() {
        if (this.key == null) {
            throw new RuntimeException("[repository] 서울시 대기질 API 인증 키를 찾을 수 없습니다.");
        }

        SeoulAirQualityAPI api = new SeoulAirQualityAPI(this.key);
        SeoulAirQualityResult result = api.getRealTimeAirQuality();

        return result.getRealtimeCityAir().getRow().stream()
                .map(AirQualityDtoConverter::toCityPollutionDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StateType getState() {
        return StateType.SEOUL;
    }
}
