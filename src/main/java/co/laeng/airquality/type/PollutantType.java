package co.laeng.airquality.type;

public enum PollutantType {
    PM25("미세먼지", "PM-2.5", "µg/m³"),
    PM10("초미세먼지", "PM-10", "µg/m³"),
    SO2("아황산가스", "SO₂", "ppm"),
    NO2("이산화질소", "NO₂", "ppm"),
    O3("오존", "O₃", "ppm"),
    CO("이산화질소","CO", "ppm");

    private final String name;
    private final String formula;
    private final String unit;

    PollutantType(String name, String formula, String unit) {
        this.name = name;
        this.formula = formula;
        this.unit = unit;
    }

    public String getName() {
        return name;
    }

    public String getFormula() {
        return formula;
    }

    public String getUnit() {
        return unit;
    }
}