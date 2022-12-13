package co.laeng.airquality.infra.kr.go.seoul.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SeoulAirQualityInterface {
    @GET("/{key}/json/RealtimeCityAir/1/25")
    Call<SeoulAirQualityResult> getRealTimeAirQuality(@Path("key") String key);
}
