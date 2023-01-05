package co.laeng.airquality.repository;

import co.laeng.airquality.dto.CityPollutionDTO;
import co.laeng.airquality.type.StateType;

import java.io.IOException;
import java.util.List;

public interface StateAirQualityRepository {
    List<CityPollutionDTO> getCityPollution();
    StateType getState();
    List<CityPollutionDTO> update();
}
