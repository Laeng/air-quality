package co.laeng.airquality.util;

import co.laeng.airquality.type.PollutantType;
import co.laeng.airquality.type.QualityGradeType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeConverter {
    static final Map<PollutantType, List<Double>> indices = new HashMap<>(){{
        put(PollutantType.PM25, List.of(15D, 35D, 75D, 500D));
        put(PollutantType.PM10, List.of(30D, 80D, 150D, 600D));
        put(PollutantType.O3, List.of(0.03D, 0.09D, 0.15D, 0.6D));
        put(PollutantType.NO2, List.of(0.03D, 0.06D, 0.2D, 2D));
        put(PollutantType.CO, List.of(2D, 9D, 15D, 50D));
        put(PollutantType.SO2, List.of(0.02D, 0.05D, 0.15D, 1D));
    }};

    public static QualityGradeType toQualityGradeType(PollutantType pollutant, Double value) {
        Double index = indices.get(pollutant).stream().filter(standard -> standard > value)
                .findFirst()
                .orElse(-1D);

        return getQualityGradeType(pollutant, index);
    }

    private static QualityGradeType getQualityGradeType(PollutantType pollutant, Double index) {
        if (index == -1) {
            return QualityGradeType.UNKNOWN;
        }

        return QualityGradeType.form(indices.get(pollutant).indexOf(index));
    }
}
