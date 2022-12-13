package co.laeng.airquality.infra.kr.go.seoul.data;

import org.springframework.beans.factory.annotation.Value;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class SeoulAirQualityAPI {
    private static SeoulAirQualityAPI instance;

    @Value("${api-secret-keys.kr.go.seoul.data.seoul-air-quality}")
    private String SEOUL_AIR_QUALITY_KEY;

    SeoulAirQualityAPI() {

    }
    
    public static SeoulAirQualityAPI getInstance() {
        if (instance == null) {
            instance = new SeoulAirQualityAPI();
        }

        return instance;
    }

    public SeoulAirQualityResult getRealTimeAirQuality() throws IOException {
        Response<SeoulAirQualityResult> response = this.getSeoulAirQualityResponse();

        return new SeoulAirQualityResult();
    }

    private Response<SeoulAirQualityResult> getSeoulAirQualityResponse() throws IOException {
        SeoulAirQualityInterface airQualityInterface = this.getSeoulAirQualityInterface();
        Call<SeoulAirQualityResult> call = airQualityInterface.getRealTimeAirQuality(SEOUL_AIR_QUALITY_KEY);

        return call.execute();
    }

    private SeoulAirQualityInterface getSeoulAirQualityInterface() {
        return SeoulAirQuality.getClient()
                .create(SeoulAirQualityInterface.class);
    }
}
