package co.laeng.airquality.dto;

import co.laeng.airquality.type.PollutantType;
import lombok.Getter;

@Getter
public class PollutantDTO {
    private final String name;
    private final String formula;
    private final String unit;
    private final double value;

    public PollutantDTO(PollutantType pollutant, double value) {
        this.name = pollutant.getName();
        this.formula = pollutant.getFormula();
        this.unit = pollutant.getUnit();
        this.value = value;
    }
}
