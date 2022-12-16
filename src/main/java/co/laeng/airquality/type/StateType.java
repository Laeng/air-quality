package co.laeng.airquality.type;

import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.service.convert.SeoulAirQualityService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

@Getter
public enum StateType {
    SEOUL("seoul") {
        @Override
        public List<CityPollutionDTO> getData() throws IOException {
            return this.getSeoulAirQualityService().toCityPollutionDTOList();
        }
    },
    BUSAN("busan") {
        @Override
        public List<CityPollutionDTO> getData() throws IOException {
            return null;
        }
    };

    private final String state;
    private SeoulAirQualityService seoulAirQualityService;

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
    public static class ServiceInjector {
        @Autowired
        private SeoulAirQualityService seoulAirQualityService;

        @PostConstruct
        public void initialize() {
            Arrays.stream(StateType.values()).forEach(this::injectService);
        }

        private void injectService(StateType state) {
            state.seoulAirQualityService = this.seoulAirQualityService;
        }


    }

    public abstract List<CityPollutionDTO> getData() throws IOException;

}
