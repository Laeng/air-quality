package co.laeng.airquality.factory;

import co.laeng.airquality.repository.StateAirQualityRepository;
import co.laeng.airquality.type.StateType;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StateAirQualityRepositoryFactoryTest {
    final StateAirQualityRepositoryFactory stateAirQualityRepositoryFactory;

    @Autowired
    StateAirQualityRepositoryFactoryTest (
            StateAirQualityRepositoryFactory stateAirQualityRepositoryFactory
    ) {
        this.stateAirQualityRepositoryFactory = stateAirQualityRepositoryFactory;
    }

    @ParameterizedTest(name = "리포지토리 로드 테스트")
    @MethodSource("리포지토리_로드_테스트_데이터")
    void 리포지토리_로드_테스트(StateType state) {
        StateAirQualityRepository repository = this.stateAirQualityRepositoryFactory.getRepository(state);
        StateType repositoryState = repository.getState();

        assertThat(repositoryState).isEqualTo(state);
    }

    static Stream<Arguments> 리포지토리_로드_테스트_데이터() {
        return Stream.of(
                Arguments.of(StateType.SEOUL),
                Arguments.of(StateType.BUSAN)
        );
    }
}