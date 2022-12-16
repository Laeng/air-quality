package co.laeng.airquality.service.convert;

import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.infra.kr.go.seoul.data.SeoulAirQualityAPI;
import co.laeng.airquality.infra.kr.go.seoul.data.SeoulAirQualityResult;
import co.laeng.airquality.util.AirQualityDtoConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SeoulAirQualityService {
    @Value("${secret.seoul-air-quality}")
    private String key;

    public List<CityPollutionDTO> toCityPollutionDTOList() throws IOException {
        SeoulAirQualityAPI api = new SeoulAirQualityAPI(this.key);
        SeoulAirQualityResult result = api.getRealTimeAirQuality();

        return result.getRealtimeCityAir().getRow().stream()
                .map(AirQualityDtoConverter::toCityPollutionDTO)
                .collect(Collectors.toList());
    }
}
