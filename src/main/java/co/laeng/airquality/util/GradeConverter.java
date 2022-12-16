package co.laeng.airquality.util;

import co.laeng.airquality.type.PollutantType;
import co.laeng.airquality.type.QualityGradeType;

import java.util.*;

public class GradeConverter {
    static final Map<PollutantType, List<Double>> indices = new HashMap<>(){{
        put(PollutantType.PM25, List.of(0D, 51D, 101D, 251D));
        put(PollutantType.PM10, List.of(0D, 31D, 81D, 151D));
        put(PollutantType.O3, List.of(0D, 0.031D, 0.091D, 0.151D));
        put(PollutantType.NO2, List.of(0D, 0.031D, 0.061D, 0.201D));
        put(PollutantType.CO, List.of(0D, 2.01D, 9.01D, 15.01D));
        put(PollutantType.SO2, List.of(0D, 0.021D, 0.051D, 0.151D));
    }};

    public static QualityGradeType toQualityGradeType(PollutantType pollutant, Double value) {
        List<Double> standard = new ArrayList<>(indices.get(pollutant));
        Collections.reverse(standard);

        Double index = standard.stream().filter(element -> element <= value)
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
