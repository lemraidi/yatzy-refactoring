package org.codingdojo.yatzy1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.codingdojo.yatzy1.DiceSide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class Yatzy1Test {


    @ParameterizedTest
    @MethodSource("provideRollsForChance")
    public void chance_scores_sum_of_all_sides(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).chance());
    }

    private static Stream<Arguments> provideRollsForChance() {
        return Stream.of(
                Arguments.of(TWO, THREE, FOUR, FIVE, ONE, 15),
                Arguments.of(THREE, THREE, FOUR, FIVE, ONE, 16)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRollsForYatzy")
    public void yatzy_scores_50_when_all_rolls_are_the_same(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).yatzy());
    }

    private static Stream<Arguments> provideRollsForYatzy() {
        return Stream.of(
                Arguments.of(FOUR, FOUR, FOUR, FOUR, FOUR, 50),
                Arguments.of(SIX, SIX, SIX, SIX, SIX, 50),
                Arguments.of(SIX, SIX, SIX, SIX, THREE, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRollsForOnes")
    public void ones_scores_the_the_sum_of_ones(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).ones());
    }

    private static Stream<Arguments> provideRollsForOnes() {
        return Stream.of(
                Arguments.of(ONE, TWO, THREE, FOUR, FIVE, 1),
                Arguments.of(ONE, TWO, ONE, FOUR, FIVE, 2),
                Arguments.of(SIX, TWO, TWO, FOUR, FIVE, 0),
                Arguments.of(ONE, TWO, ONE, ONE, ONE, 4)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRollsForTwos")
    public void twos_scores_the_sum_of_twos(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).twos());
    }

    private static Stream<Arguments> provideRollsForTwos() {
        return Stream.of(
                Arguments.of(ONE, TWO, THREE, TWO, SIX, 4),
                Arguments.of(TWO, TWO, TWO, TWO, TWO, 10)
        );
    }


    @ParameterizedTest
    @MethodSource("provideRollsForThrees")
    public void threes_scores_the_sum_of_threes(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).threes());
    }

    private static Stream<Arguments> provideRollsForThrees() {
        return Stream.of(
                Arguments.of(ONE, TWO, THREE, TWO, THREE, 6),
                Arguments.of(TWO, THREE, THREE, THREE, THREE, 12)
        );
    }


    @ParameterizedTest
    @MethodSource("provideRollsForFours")
    public void fours_scores_the_sum_of_fours(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).fours());
    }

    private static Stream<Arguments> provideRollsForFours() {
        return Stream.of(
                Arguments.of(FOUR, FOUR, FOUR, FIVE, FIVE, 12),
                Arguments.of(FOUR, FOUR, FIVE, FIVE, FIVE, 8),
                Arguments.of(FOUR, FIVE, FIVE, FIVE, FIVE, 4)
        );
    }


    @ParameterizedTest
    @MethodSource("provideRollsForFives")
    public void fives_scores_the_sum_of_fives(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).fives());
    }

    private static Stream<Arguments> provideRollsForFives() {
        return Stream.of(
                Arguments.of(FOUR, FOUR, FOUR, FIVE, FIVE, 10),
                Arguments.of(FOUR, FOUR, FIVE, FIVE, FIVE, 15),
                Arguments.of(FOUR, FIVE, FIVE, FIVE, FIVE, 20)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRollsForSixes")
    public void sixes_scores_the_sum_of_sixes(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).sixes());
    }

    private static Stream<Arguments> provideRollsForSixes() {
        return Stream.of(
                Arguments.of(FOUR, FOUR, FOUR, FIVE, FIVE, 0),
                Arguments.of(FOUR, FOUR, SIX, FIVE, FIVE, 6),
                Arguments.of(SIX, FIVE, SIX, SIX, FIVE, 18)
        );
    }


    @ParameterizedTest
    @MethodSource("provideRollsForPair")
    public void pair_scores_the_sum_of_the_highest_pair(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).pair());
    }

    private static Stream<Arguments> provideRollsForPair() {
        return Stream.of(
                Arguments.of(THREE, FOUR, THREE, FIVE, SIX, 6),
                Arguments.of(FIVE, THREE, THREE, THREE, FIVE, 10),
                Arguments.of(FIVE, THREE, SIX, SIX, FIVE, 12)
        );
    }


    @ParameterizedTest
    @MethodSource("provideRollsForTwoPairs")
    public void two_pairs_scores_the_sum_of_pairs(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).twoPairs());
    }

    private static Stream<Arguments> provideRollsForTwoPairs() {
        return Stream.of(
                Arguments.of(THREE, THREE, FIVE, FOUR, FIVE, 16),
                Arguments.of(THREE, THREE, FIVE, FIVE, FIVE, 16),
                Arguments.of(THREE, THREE, THREE, THREE, ONE, 0)
        );
    }


    @ParameterizedTest
    @MethodSource("provideRollsForThreeOfAKind")
    public void three_of_a_kind_sums_three_repeated_sides(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).threeOfAKind());
    }

    private static Stream<Arguments> provideRollsForThreeOfAKind() {
        return Stream.of(
                Arguments.of(THREE, THREE, THREE, FOUR, FIVE, 9),
                Arguments.of(FIVE, THREE, FIVE, FOUR, FIVE, 15),
                Arguments.of(THREE, THREE, THREE, THREE, FIVE, 9)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRollsForFourOfAKind")
    public void four_of_a_kind_sums_four_repeated_sides(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).fourOfAKind());
    }

    private static Stream<Arguments> provideRollsForFourOfAKind() {
        return Stream.of(
                Arguments.of(THREE, THREE, THREE, THREE, FIVE, 12),
                Arguments.of(FIVE, FIVE, FIVE, FOUR, FIVE, 20),
                Arguments.of(THREE, THREE, THREE, THREE, THREE, 12)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRollsForSmallStraight")
    public void small_straight_sums_unique_side_up_to_five(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).smallStraight());
    }

    private static Stream<Arguments> provideRollsForSmallStraight() {
        return Stream.of(
                Arguments.of(ONE, TWO, THREE, FOUR, FIVE, 15),
                Arguments.of(TWO, THREE, FOUR, FIVE, ONE, 15),
                Arguments.of(ONE, TWO, TWO, FOUR, FIVE, 0),
                Arguments.of(TWO, THREE, FOUR, FIVE, SIX, 0)
        );
    }

    @ParameterizedTest
    @MethodSource("provideRollsForLargeStraight")
    public void large_straight_sums_unique_sides_from_two_up(DiceSide first, DiceSide second, DiceSide third, DiceSide fourth, DiceSide fifth, int expected) {
        assertEquals(expected, new Yatzy1(DiceRolls
                .first(first)
                .second(second)
                .third(third)
                .fourth(fourth)
                .fifth(fifth)
        ).largeStraight());

    }

    private static Stream<Arguments> provideRollsForLargeStraight() {
        return Stream.of(
                Arguments.of(SIX, TWO, THREE, FOUR, FIVE, 20),
                Arguments.of(TWO, THREE, FOUR, FIVE, SIX, 20),
                Arguments.of(ONE, TWO, TWO, FOUR, FIVE, 0),
                Arguments.of(ONE, TWO, THREE, FOUR, FIVE, 0)
        );
    }

    @Test
    public void full_house_sums_pair_and_triad() {
        assertEquals(18, new Yatzy1(DiceRolls
                .first(SIX)
                .second(TWO)
                .third(TWO)
                .fourth(TWO)
                .fifth(SIX)
        ).fullHouse());
        assertEquals(0, new Yatzy1(DiceRolls
                .first(SIX)
                .second(THREE)
                .third(FOUR)
                .fourth(FIVE)
                .fifth(SIX)
        ).fullHouse());
    }
}