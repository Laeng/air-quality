package co.laeng.airquality.controller;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@AutoConfigureMockMvc
@AutoConfigureRestDocs
@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AirQualityControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    void 서울시_전역_대기질_가져오기() throws Exception {

        this.mockMvc.perform(get("/api/v1/air-quality/seoul"))
                .andExpect(status().isOk());
    }
}