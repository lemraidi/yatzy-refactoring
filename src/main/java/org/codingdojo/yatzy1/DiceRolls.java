package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DiceRolls {
    private final int[] sides;

    public DiceRolls(int d1, int d2, int d3, int d4, int d5) {
        this.sides = new int[]{d1, d2, d3, d4, d5};
        if (stream().anyMatch(value -> value > 6 || value < 1)) {
            throw new IllegalArgumentException("a dice side must be between 1 and 6");
        }
    }

    public IntStream stream() {
        return Arrays.stream(sides);
    }
}
