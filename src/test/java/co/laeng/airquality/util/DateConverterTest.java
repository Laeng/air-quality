package co.laeng.airquality.util;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DateConverterTest {

    @ParameterizedTest(name = "ISO8601 변환 테스트 데이터")
    @MethodSource("ISO_8601_변환_테스트_데이터")
    void ISO_8601_변환_테스트(String datetime, String result) {
        String iso8601 = DateConverter.toISO8601(datetime);
        assertThat(iso8601).isEqualTo(result);
    }

    static Stream<Arguments> ISO_8601_변환_테스트_데이터() {
        return Stream.of(
                Arguments.of("202212161400", "2022-12-16T14:00:00"),
                Arguments.of("2022121614", "2022-12-16T14:00:00")
        );
    }
}