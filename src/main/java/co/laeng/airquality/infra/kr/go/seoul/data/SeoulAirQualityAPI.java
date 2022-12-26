package co.laeng.airquality.infra.kr.go.seoul.data;

import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class SeoulAirQualityAPI {

    private final String apiKey;

    public SeoulAirQualityAPI(String key) {
        this.apiKey = key;
    }

    public SeoulAirQualityResult getRealTimeAirQuality() throws RuntimeException {
        try {
            Response<SeoulAirQualityResult> response = this.getSeoulAirQualityResponse();

            if (response.body() == null) {
                throw new RuntimeException("[infra] 서울시 대기질 정보 API로 부터 데이터를 응답받을 수 없습니다.");
            }

            if (response.isSuccessful()) {
                return response.body();
            }

            throw new RuntimeException("[infra] 서울시 대기질 정보 API 데이터가 올바르지 않습니다.");

        } catch (IOException exception) {
            throw new RuntimeException("[infra] 서울시 대기질 정보 API 로 부터 데이터를 불러오는데 문제가 발생하였습니다.", exception);
        }
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
