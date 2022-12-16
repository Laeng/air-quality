package co.laeng.airquality.infra.kr.go.seoul.data;

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
class SeoulAirQualityTest {

    @Value("${secret.seoul-air-quality}")
    private String SEOUL_AIR_QUALITY_KEY;

    @Test
    void 서울_대기질_API_테스트() throws IOException {
        SeoulAirQualityInterface airQualityInterface = SeoulAirQuality.getClient()
                .create(SeoulAirQualityInterface.class);

        Call<SeoulAirQualityResult> call = airQualityInterface.getRealTimeAirQuality(SEOUL_AIR_QUALITY_KEY);
        Response<SeoulAirQualityResult> response = call.execute();

        assertThat(response.isSuccessful()).isTrue();
    }
}