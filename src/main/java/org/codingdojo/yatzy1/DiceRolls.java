package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class DiceRolls {
    private final int[] sides;

    private DiceRolls(int d1, int d2, int d3, int d4, int d5) {
        this.sides = new int[]{d1, d2, d3, d4, d5};
    }

    public IntStream stream() {
        return Arrays.stream(sides);
    }

    public static SecondRoll first(DiceSide side) {
        return new DiceRollsBuilder(side);
    }

    private static class DiceRollsBuilder implements SecondRoll, ThirdRoll, FourthRoll, FifthRoll {

        private final int firstSide;
        private int secondSide;
        private int thirdSide;
        private int fourthSide;

        public DiceRollsBuilder(DiceSide firstSide) {
            this.firstSide = firstSide.getValue();
        }

        @Override
        public ThirdRoll second(DiceSide side) {
            this.secondSide = side.getValue();
            return this;
        }

        @Override
        public FourthRoll third(DiceSide side) {
            this.thirdSide = side.getValue();
            return this;
        }

        @Override
        public FifthRoll fourth(DiceSide side) {
            this.fourthSide = side.getValue();
            return this;
        }

        @Override
        public DiceRolls fifth(DiceSide side) {
            return new DiceRolls(firstSide, secondSide, thirdSide, fourthSide, side.getValue());
        }

    }

    public interface SecondRoll {
        ThirdRoll second(DiceSide side);
    }

    public interface ThirdRoll {
        FourthRoll third(DiceSide side);
    }

    public interface FourthRoll {
        FifthRoll fourth(DiceSide side);
    }

    public interface FifthRoll {
        DiceRolls fifth(DiceSide side);
    }
}
