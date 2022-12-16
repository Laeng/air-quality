package co.laeng.airquality.type;

import co.laeng.airquality.config.ApiSecretConfig;
import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.infra.kr.go.seoul.data.SeoulAirQualityAPI;
import co.laeng.airquality.infra.kr.go.seoul.data.SeoulAirQualityResult;
import co.laeng.airquality.util.AirQualityDtoConverter;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
            SeoulAirQualityResult result = this.getSeoulAirQualityResult();
            return result.getRealtimeCityAir().getRow().stream()
                    .map(AirQualityDtoConverter::toCityPollutionDTO)
                    .collect(Collectors.toList());
        }
    },
    BUSAN("busan") {
        @Override
        public List<CityPollutionDTO> getData() throws IOException {
            return null;
        }
    };

    private final String state;
    private ApiSecretConfig apiSecretConfig;

    StateType(String state) {
        this.state = state;
    }

    public static StateType from(String name) throws NoSuchElementException {
        return Arrays.stream(StateType.values())
                .filter(type -> type.getState().equals(name.toLowerCase()))
                .findFirst()
                .orElseThrow();
    }

    @Component
    public static class ConfigInjector {
        @Autowired
        private ApiSecretConfig apiSecretConfig;
        @PostConstruct
        public void postConstruct() {
            try {
                Arrays.stream(StateType.values()).forEach(state -> state.apiSecretConfig = apiSecretConfig);
            }
            catch(Exception e) {
                throw e;
            }
        }
    }

    public abstract List<CityPollutionDTO> getData() throws IOException;

    public SeoulAirQualityResult getSeoulAirQualityResult() throws IOException {
        this.apiSecretConfig.getSecrets().forEach(nameKey -> System.out.println(nameKey.getKey() + "  " + nameKey.getName()));

        ApiSecretConfig.NameKey secret = this.apiSecretConfig.getSecrets().stream()
                .filter(nameKey -> nameKey.getName().equals("air-quality-seoul"))
                .findFirst()
                .orElseThrow();

        SeoulAirQualityAPI api = new SeoulAirQualityAPI(secret.getKey());

        return api.getRealTimeAirQuality();
    }

}
