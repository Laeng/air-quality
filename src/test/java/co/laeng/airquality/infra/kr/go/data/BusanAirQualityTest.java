package co.laeng.airquality.infra.kr.go.data;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@SpringBootConfiguration
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BusanAirQualityTest {
    @Value("${secret.busan-air-quality}")
    private String BUSAN_AIR_QUALITY_KEY;

    @Test
    void 부산_대기질_API_테스트() throws IOException {
        BusanAirQualityInterface airQualityInterface = BusanAirQuality.getClient()
                .create(BusanAirQualityInterface.class);

        Call<BusanAirQualityResult> call =
                airQualityInterface.getAirQualityInfoClassifiedByStation(BUSAN_AIR_QUALITY_KEY);

        System.out.println(call.request().url().toString());

        Response<BusanAirQualityResult> response = call.execute();

        assertThat(response.isSuccessful()).isTrue();
    }
}