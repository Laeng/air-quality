package co.laeng.airquality.type;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum StateType {
    SEOUL("seoul"),
    BUSAN("busan");

    private final String state;

    StateType(String state) {
        this.state = state;
    }

    public static StateType from(String name) throws NoSuchElementException {
        return Arrays.stream(StateType.values())
                .filter(type -> type.toString().equals(name.toLowerCase()))
                .findFirst()
                .orElseThrow();
    }

    @Override
    public String toString() {
        return this.state;
    }

}
