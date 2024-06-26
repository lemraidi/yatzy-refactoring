package org.codingdojo.yatzy1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class Yatzy1 {

    private final int[] dice;
    private final Map<Integer, Long> frequencies;

    public Yatzy1(int d1, int d2, int d3, int d4, int d5) {
        dice = new int[5];
        dice[0] = d1;
        dice[1] = d2;
        dice[2] = d3;
        dice[3] = d4;
        dice[4] = d5;
        frequencies = createFrequencyMap();
    }

    public int chance() {
        return Arrays.stream(dice).sum();
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
        return Arrays.stream(dice).distinct().count() == 1 ? 50 : 0;
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

    public int three_of_a_kind() {
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
        int[] tallies;
        boolean _2 = false;
        int i;
        int _2_at = 0;
        boolean _3 = false;
        int _3_at = 0;


        tallies = new int[6];
        tallies[dice[0] - 1] += 1;
        tallies[dice[1] - 1] += 1;
        tallies[dice[2] - 1] += 1;
        tallies[dice[3] - 1] += 1;
        tallies[dice[4] - 1] += 1;

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 2) {
                _2 = true;
                _2_at = i + 1;
            }

        for (i = 0; i != 6; i += 1)
            if (tallies[i] == 3) {
                _3 = true;
                _3_at = i + 1;
            }

        if (_2 && _3)
            return _2_at * 2 + _3_at * 3;
        else
            return 0;
    }

    private int getScoreByCategory(int category) {
        return Arrays.stream(dice).filter(value -> value == category).sum();
    }

    private Map<Integer, Long> createFrequencyMap() {
        return Arrays.stream(dice)
                .boxed()
                .collect(Collectors.groupingBy(d -> d, Collectors.counting()));
    }
}
