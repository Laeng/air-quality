package co.laeng.airquality.controller;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static co.laeng.airquality.controller.ApiDocumentUtils.*;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;

@AutoConfigureMockMvc
@AutoConfigureRestDocs()
@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AirQualityControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 서울시_대기질_가져오기() throws Exception {
        String state = "seoul";
        String city = "중구";

        this.mockMvc.perform(get("/api/v1/air-quality/{state}?city={city}", state, city))
                .andExpect(status().isOk())
                .andDo(document(
                        "air-quality",
                        getDocumentRequest(),
                        getDocumentResponse(),
                        pathParameters(
                                parameterWithName("state").description("조회할 광역시를 입력. 현재 seoul 만 지원합니다.")
                        ),
                        requestParameters(
                                parameterWithName("city").description("지역 이름을 입력. 한글 이름을 입력하세요.").optional()
                        ),
                        responseFields(
                                fieldWithPath("state").type(JsonFieldType.STRING).description("광역시"),
                                fieldWithPath("stateAvg").type(JsonFieldType.OBJECT).description("광역시 전체 평균"),
                                fieldWithPath("cities").type(JsonFieldType.ARRAY).description("지역 정보")
                        )
                                .andWithPrefix("cities[].", this.fieldCities())
                                .andWithPrefix("stateAvg.", this.fieldPollutant())
                                .andWithPrefix("cities[].pm25.", this.fieldPollutant())
                                .andWithPrefix("cities[].pm10.", this.fieldPollutant())
                                .andWithPrefix("cities[].o3.", this.fieldPollutant())
                                .andWithPrefix("cities[].no2.", this.fieldPollutant())
                                .andWithPrefix("cities[].co.", this.fieldPollutant())
                                .andWithPrefix("cities[].so2.", this.fieldPollutant())
                ));
    }

    private FieldDescriptor[] fieldPollutant() {
        return new FieldDescriptor[] {
                fieldWithPath("name").type(JsonFieldType.STRING).description("이름"),
                fieldWithPath("formula").type(JsonFieldType.STRING).description("성분"),
                fieldWithPath("unit").type(JsonFieldType.STRING).description("단위"),
                fieldWithPath("value").type(JsonFieldType.NUMBER).description("측정 값"),
                fieldWithPath("status").type(JsonFieldType.STRING).description("상태")
        };
    }

    private FieldDescriptor[] fieldCities() {
        return new FieldDescriptor[] {
                fieldWithPath("city").type(JsonFieldType.STRING).description("지역 이름"),
                fieldWithPath("pm25").type(JsonFieldType.OBJECT).description("미세먼지 정보"),
                fieldWithPath("pm10").type(JsonFieldType.OBJECT).description("초 미세먼지 정보"),
                fieldWithPath("o3").type(JsonFieldType.OBJECT).description("오존 정보"),
                fieldWithPath("no2").type(JsonFieldType.OBJECT).description("이산화질소 정보"),
                fieldWithPath("co").type(JsonFieldType.OBJECT).description("일산화탄소 정보"),
                fieldWithPath("so2").type(JsonFieldType.OBJECT).description("아황산가스"),
                fieldWithPath("updateAt").type(JsonFieldType.STRING).description("업데이트 시간")
        };
    }
}