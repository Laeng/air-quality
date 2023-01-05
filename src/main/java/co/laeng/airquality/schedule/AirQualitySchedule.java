package co.laeng.airquality.schedule;

import co.laeng.airquality.factory.StateAirQualityRepositoryFactory;
import co.laeng.airquality.type.StateType;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Arrays;

public class AirQualitySchedule {

    private final StateAirQualityRepositoryFactory stateAirQualityRepositoryFactory;

    public AirQualitySchedule(
            StateAirQualityRepositoryFactory stateAirQualityRepositoryFactory
    ) {
        this.stateAirQualityRepositoryFactory = stateAirQualityRepositoryFactory;
    }

    @Scheduled(cron = "1 * * * *")
    public void updateAirQuality() {
        Arrays.stream(StateType.values()).forEach(state -> this.stateAirQualityRepositoryFactory.getRepository(state).update());
    }
}
