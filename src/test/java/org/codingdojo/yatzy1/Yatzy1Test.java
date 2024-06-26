package org.codingdojo.yatzy1;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class Yatzy1Test {

    @Test
    public void accepts_only_dice_side_between_one_and_six() {
        assertThrows(IllegalArgumentException.class, () -> new Yatzy1(new DiceRolls(-1, 2, 3, 6, 1)));
        assertThrows(IllegalArgumentException.class, () -> new Yatzy1(new DiceRolls(8, 2, 3, 6, 1)));
    }

    @Test
    public void chance_scores_sum_of_all_dice() {
        int expected = 15;
        int actual = new Yatzy1(new DiceRolls(2, 3, 4, 5, 1)).chance();
        assertEquals(expected, actual);
        assertEquals(16, new Yatzy1(new DiceRolls(3, 3, 4, 5, 1)).chance());
    }

    @Test
    public void yatzy_scores_50() {
        int expected = 50;
        int actual = new Yatzy1(new DiceRolls(4, 4, 4, 4, 4)).yatzy();
        assertEquals(expected, actual);
        assertEquals(50, new Yatzy1(new DiceRolls(6, 6, 6, 6, 6)).yatzy());
        assertEquals(0, new Yatzy1(new DiceRolls(6, 6, 6, 6, 3)).yatzy());
    }

    @Test
    public void test_1s() {
        assertTrue(new Yatzy1(new DiceRolls(1, 2, 3, 4, 5)).ones() == 1);
        assertEquals(2, new Yatzy1(new DiceRolls(1, 2, 1, 4, 5)).ones());
        assertEquals(0, new Yatzy1(new DiceRolls(6, 2, 2, 4, 5)).ones());
        assertEquals(4, new Yatzy1(new DiceRolls(1, 2, 1, 1, 1)).ones());
    }

    @Test
    public void test_2s() {
        assertEquals(4, new Yatzy1(new DiceRolls(1, 2, 3, 2, 6)).twos());
        assertEquals(10, new Yatzy1(new DiceRolls(2, 2, 2, 2, 2)).twos());
    }

    @Test
    public void test_threes() {
        assertEquals(6, new Yatzy1(new DiceRolls(1, 2, 3, 2, 3)).threes());
        assertEquals(12, new Yatzy1(new DiceRolls(2, 3, 3, 3, 3)).threes());
    }

    @Test
    public void fours_test() {
        assertEquals(12, new Yatzy1(new DiceRolls(4, 4, 4, 5, 5)).fours());
        assertEquals(8, new Yatzy1(new DiceRolls(4, 4, 5, 5, 5)).fours());
        assertEquals(4, new Yatzy1(new DiceRolls(4, 5, 5, 5, 5)).fours());
    }

    @Test
    public void fives() {
        assertEquals(10, new Yatzy1(new DiceRolls(4, 4, 4, 5, 5)).fives());
        assertEquals(15, new Yatzy1(new DiceRolls(4, 4, 5, 5, 5)).fives());
        assertEquals(20, new Yatzy1(new DiceRolls(4, 5, 5, 5, 5)).fives());
    }

    @Test
    public void sixes_test() {
        assertEquals(0, new Yatzy1(new DiceRolls(4, 4, 4, 5, 5)).sixes());
        assertEquals(6, new Yatzy1(new DiceRolls(4, 4, 6, 5, 5)).sixes());
        assertEquals(18, new Yatzy1(new DiceRolls(6, 5, 6, 6, 5)).sixes());
    }

    @Test
    public void one_pair() {
        assertEquals(6, new Yatzy1(new DiceRolls(3, 4, 3, 5, 6)).pair());
        assertEquals(10, new Yatzy1(new DiceRolls(5, 3, 3, 3, 5)).pair());
        assertEquals(12, new Yatzy1(new DiceRolls(5, 3, 6, 6, 5)).pair());
    }

    @Test
    public void two_Pair() {
        assertEquals(16, new Yatzy1(new DiceRolls(3, 3, 5, 4, 5)).twoPairs());
        assertEquals(16, new Yatzy1(new DiceRolls(3, 3, 5, 5, 5)).twoPairs());
        assertEquals(0, new Yatzy1(new DiceRolls(3, 3, 3, 3, 1)).twoPairs());

    }

    @Test
    public void three_of_a_kind() {
        assertEquals(9, new Yatzy1(new DiceRolls(3, 3, 3, 4, 5)).threeOfAKind());
        assertEquals(15, new Yatzy1(new DiceRolls(5, 3, 5, 4, 5)).threeOfAKind());
        assertEquals(9, new Yatzy1(new DiceRolls(3, 3, 3, 3, 5)).threeOfAKind());

    }

    @Test
    public void four_of_a_kind() {
        assertEquals(12, new Yatzy1(new DiceRolls(3, 3, 3, 3, 5)).fourOfAKind());
        assertEquals(20, new Yatzy1(new DiceRolls(5, 5, 5, 4, 5)).fourOfAKind());
        assertEquals(12, new Yatzy1(new DiceRolls(3, 3, 3, 3, 3)).fourOfAKind());
    }

    @Test
    public void smallStraight() {
        assertEquals(15, new Yatzy1(new DiceRolls(1, 2, 3, 4, 5)).smallStraight());
        assertEquals(15, new Yatzy1(new DiceRolls(2, 3, 4, 5, 1)).smallStraight());
        assertEquals(0, new Yatzy1(new DiceRolls(1, 2, 2, 4, 5)).smallStraight());
        assertEquals(0, new Yatzy1(new DiceRolls(2, 3, 4, 5, 6)).smallStraight());
    }

    @Test
    public void largeStraight() {
        assertEquals(20, new Yatzy1(new DiceRolls(6, 2, 3, 4, 5)).largeStraight());
        assertEquals(20, new Yatzy1(new DiceRolls(2, 3, 4, 5, 6)).largeStraight());
        assertEquals(0, new Yatzy1(new DiceRolls(1, 2, 2, 4, 5)).largeStraight());
        assertEquals(0, new Yatzy1(new DiceRolls(1, 2, 3, 4, 5)).largeStraight());

    }

    @Test
    public void fullHouse() {
        assertEquals(18, new Yatzy1(new DiceRolls(6, 2, 2, 2, 6)).fullHouse());
        assertEquals(0, new Yatzy1(new DiceRolls(2, 3, 4, 5, 6)).fullHouse());
    }
}