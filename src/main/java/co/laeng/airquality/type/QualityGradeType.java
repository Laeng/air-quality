package co.laeng.airquality.type;

import java.util.Arrays;

public enum QualityGradeType {
    UNKNOWN(-1, "알 수 없음"),
    GOOD(0, "좋음"),
    NORMAL(1, "보통"),
    BAD(2, "나쁨"),
    Danger(3, "매우 나쁨");

    private final Integer id;
    private final String name;

    QualityGradeType(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static QualityGradeType form(int id) {
        return Arrays.stream(values()).filter(status -> status.id == id)
                .findFirst()
                .orElse(QualityGradeType.UNKNOWN);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
