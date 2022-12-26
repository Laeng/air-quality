package co.laeng.airquality.infra.kr.go.data;

import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

public class BusanAirQualityAPI {

    private final String apiKey;

    public BusanAirQualityAPI(String key) {
        this.apiKey = key;
    }

    public BusanAirQualityResult getAirQualityInfoClassifiedByStation() throws RuntimeException {
        try {
            Response<BusanAirQualityResult> response = this.getBusanAirQualityResponse();

            if (response.body() == null) {
                throw new RuntimeException("[infra] 부산시 대기질 정보 API로 부터 데이터를 응답받을 수 없습니다.");
            }

            if (response.isSuccessful()) {
                return response.body();
            }

            throw new RuntimeException("[infra] 부산시 대기질 정보 API 데이터가 올바르지 않습니다.");

        } catch (IOException exception) {
            throw new RuntimeException("[infra] 부산시 대기질 정보 API 로 부터 데이터를 불러오는데 문제가 발생하였습니다.", exception);
        }
    }

    private Response<BusanAirQualityResult> getBusanAirQualityResponse() throws IOException {
        BusanAirQualityInterface airQualityInterface = this.getBusanAirQualityInterface();
        Call<BusanAirQualityResult> call = airQualityInterface.getAirQualityInfoClassifiedByStation(this.apiKey);
        return call.execute();
    }

    private BusanAirQualityInterface getBusanAirQualityInterface() {
        return BusanAirQuality.getClient()
                .create(BusanAirQualityInterface.class);
    }
}
