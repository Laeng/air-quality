package co.laeng.airquality.util;

import co.laeng.airquality.type.PollutantType;
import co.laeng.airquality.type.QualityGradeType;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GradeConverterTest {
    @ParameterizedTest(name = "오염 등급 변환 테스트")
    @MethodSource("오염_등급_변환_테스트_데이터")
    void 오염_등급_변환_테스트(PollutantType pollutant, Double value, QualityGradeType result) {
        assertThat(GradeConverter.toQualityGradeType(pollutant, value)).isEqualTo(result);
    }

    static Stream<Arguments> 오염_등급_변환_테스트_데이터() {
        return Stream.of(
                Arguments.of(PollutantType.CO, 2D, QualityGradeType.GOOD),
                Arguments.of(PollutantType.NO2, 0.05D, QualityGradeType.NORMAL),
                Arguments.of(PollutantType.SO2, 1.1D, QualityGradeType.Danger),
                Arguments.of(PollutantType.PM25, 520D, QualityGradeType.Danger),
                Arguments.of(PollutantType.PM10, 0D, QualityGradeType.GOOD)
        );
    }
}