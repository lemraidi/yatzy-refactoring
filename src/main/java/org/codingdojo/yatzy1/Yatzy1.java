package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Yatzy1 {

    private static final long PAIR = 2L;
    private static final long TRIAD = 3L;
    private final Map<Integer, Long> frequencies;
    private final DiceRolls rolls;

    public Yatzy1(DiceRolls rolls) {
        this.rolls = rolls;
        this.frequencies = createFrequencyMap();
    }

    public int chance() {
        return getSumOfAllSides();
    }

    public int ones() {
        return getScoreBySide(DiceSide.ONE);
    }

    public int twos() {
        return getScoreBySide(DiceSide.TWO);
    }

    public int threes() {
        return getScoreBySide(DiceSide.THREE);
    }

    public int fours() {
        return getScoreBySide(DiceSide.FOUR);
    }

    public int fives() {
        return getScoreBySide(DiceSide.FIVE);
    }

    public int sixes() {
        return getScoreBySide(DiceSide.SIX);
    }

    public int yatzy() {
        return rolls.stream().distinct().count() == 1 ? 50 : 0;
    }

    public int pair() {
        return frequencies.entrySet().stream()
                .filter(entry -> entry.getValue() >= PAIR)
                .max(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getKey)
                .map(value -> value * 2)
                .orElse(0);
    }

    public int twoPairs() {
        int[] pairs = frequencies.entrySet().stream()
                .filter(entry -> entry.getValue() >= PAIR)
                .mapToInt(Map.Entry::getKey)
                .toArray();
        if (pairs.length == 2) {
            return Arrays.stream(pairs).sum() * 2;
        }
        return 0;
    }

    public int fourOfAKind() {
        return getScoreByOccurrence(4);
    }

    public int threeOfAKind() {
        return getScoreByOccurrence(3);
    }

    private Integer getScoreByOccurrence(int occurrence) {
        return frequencies.entrySet().stream()
                .filter(entry -> entry.getValue() >= occurrence)
                .map(Map.Entry::getKey)
                .findFirst()
                .map(value -> value * occurrence)
                .orElse(0);
    }

    public int smallStraight() {
        if (frequencies.size() != 5 || frequencies.containsKey(6)) {
            return 0;
        }
        return 15;
    }

    public int largeStraight() {
        if (frequencies.size() != 5 || frequencies.containsKey(1)) {
            return 0;
        }
        return 20;
    }

    public int fullHouse() {
        if (frequencies.containsValue(PAIR) && frequencies.containsValue(TRIAD)) {
            return getSumOfAllSides();
        }
        return 0;
    }

    private int getScoreBySide(DiceSide side) {
        return rolls.stream().filter(value -> value == side.getValue()).sum();
    }

    private Map<Integer, Long> createFrequencyMap() {
        return rolls.stream()
                .boxed()
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()));
    }

    private int getSumOfAllSides() {
        return rolls.stream().sum();
    }
}
