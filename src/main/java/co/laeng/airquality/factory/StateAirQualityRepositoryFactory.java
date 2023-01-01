package co.laeng.airquality.factory;

import co.laeng.airquality.repository.StateAirQualityRepository;
import co.laeng.airquality.type.StateType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateAirQualityRepositoryFactory {

    private final List<StateAirQualityRepository> stateAirQualityRepositories;

    public StateAirQualityRepositoryFactory(
            List<StateAirQualityRepository> stateAirQualityRepositories
    ) {
        this.stateAirQualityRepositories = stateAirQualityRepositories;
    }

    public StateAirQualityRepository getRepository(StateType state) {
        String message = String.format("[Repository] %s 에 대한 리포지토리를 찾을 수 없습니다.", state.toString());

        return this.stateAirQualityRepositories.stream()
                .filter(repository -> repository.getState().equals(state))
                .findFirst()
                .orElseThrow(() -> new RuntimeException(message));
    }
}
