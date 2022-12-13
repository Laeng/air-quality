package co.laeng.airquality.dto;

import co.laeng.airquality.type.PollutantType;
import co.laeng.airquality.util.GradeConverter;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollutantDTO {
    private String name;
    private String formula;
    private String unit;
    private double value;
    private String status;

    @Builder
    public PollutantDTO(PollutantType pollutant, double value) {
        this.name = pollutant.getName();
        this.formula = pollutant.getFormula();
        this.unit = pollutant.getUnit();
        this.value = value;
        this.status = GradeConverter.toQualityGradeType(pollutant, value).toString();
    }
}
