package co.laeng.airquality.infra.kr.go.seoul.data;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class SeoulAirQualityAPI {

    private final String apiKey;

    public SeoulAirQualityAPI(String key) {
        this.apiKey = key;
    }

    public SeoulAirQualityResult getRealTimeAirQuality() throws IOException {
        Response<SeoulAirQualityResult> response = this.getSeoulAirQualityResponse();
        return response.body();
    }

    private Response<SeoulAirQualityResult> getSeoulAirQualityResponse() throws IOException {
        SeoulAirQualityInterface airQualityInterface = this.getSeoulAirQualityInterface();
        Call<SeoulAirQualityResult> call = airQualityInterface.getRealTimeAirQuality(this.apiKey);
        return call.execute();
    }

    private SeoulAirQualityInterface getSeoulAirQualityInterface() {
        return SeoulAirQuality.getClient()
                .create(SeoulAirQualityInterface.class);
    }
}
