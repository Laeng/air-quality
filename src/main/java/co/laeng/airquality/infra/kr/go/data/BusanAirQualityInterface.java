package co.laeng.airquality.infra.kr.go.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BusanAirQualityInterface {

    @GET("/6260000/AirQualityInfoService/getAirQualityInfoClassifiedByStation?pageNo=1&numOfRows=33&resultType=json")
    Call<BusanAirQualityResult> getAirQualityInfoClassifiedByStation(@Query("serviceKey") String key);
}
