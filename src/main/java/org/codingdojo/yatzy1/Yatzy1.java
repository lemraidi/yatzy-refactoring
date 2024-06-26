package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Yatzy1 {

    private final Map<Integer, Long> frequencies;
    private final DiceRolls roll;

    public Yatzy1(DiceRolls diceRoll) {
        this.roll = diceRoll;
        ;
        frequencies = createFrequencyMap();
    }

    public int chance() {
        return getSumOfAllDice();
    }

    public int ones() {
        return getScoreByCategory(1);
    }

    public int twos() {
        return getScoreByCategory(2);
    }

    public int threes() {
        return getScoreByCategory(3);
    }

    public int fours() {
        return getScoreByCategory(4);
    }

    public int fives() {
        return getScoreByCategory(5);
    }

    public int sixes() {
        return getScoreByCategory(6);
    }

    public int yatzy() {
        return roll.stream().distinct().count() == 1 ? 50 : 0;
    }

    public int pair() {
        return frequencies.entrySet().stream()
                .filter(entry -> entry.getValue() >= 2)
                .max(Comparator.comparingInt(Map.Entry::getKey))
                .map(Map.Entry::getKey)
                .map(value -> value * 2)
                .orElse(0);
    }

    public int twoPairs() {
        int[] pairs = frequencies.entrySet().stream()
                .filter(entry -> entry.getValue() >= 2)
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
        if (frequencies.containsValue(2L) && frequencies.containsValue(3L)) {
            return getSumOfAllDice();
        }
        return 0;
    }

    private int getScoreByCategory(int category) {
        return roll.stream().filter(value -> value == category).sum();
    }

    private Map<Integer, Long> createFrequencyMap() {
        return roll.stream()
                .boxed()
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()));
    }

    private int getSumOfAllDice() {
        return roll.stream().sum();
    }
}
