package org.snakesandladders.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.snakesandladders.model.diceroller.MaxDiceRoller;
import org.snakesandladders.model.diceroller.MinDiceRoller;
import org.snakesandladders.model.diceroller.SumDiceRoller;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DiceRollerTest {

    @Mock
    private Die mockDie1;

    @Mock
    private Die mockDie2;

    @Test
    public void testSumDiceRoller() {
        when(mockDie1.roll()).thenReturn(3);
        when(mockDie2.roll()).thenReturn(4);

        List<Die> dice = Arrays.asList(mockDie1, mockDie2);
        SumDiceRoller sumDiceRoller = new SumDiceRoller(dice);

        assertEquals(7, sumDiceRoller.rollDice());
    }

    @Test
    public void testMinDiceRoller() {
        when(mockDie1.roll()).thenReturn(3);
        when(mockDie2.roll()).thenReturn(4);

        List<Die> dice = Arrays.asList(mockDie1, mockDie2);
        MinDiceRoller minDiceRoller = new MinDiceRoller(dice);

        assertEquals(3, minDiceRoller.rollDice());
    }

    @Test
    public void testMaxDiceRoller() {
        when(mockDie1.roll()).thenReturn(3);
        when(mockDie2.roll()).thenReturn(4);

        List<Die> dice = Arrays.asList(mockDie1, mockDie2);
        MaxDiceRoller maxDiceRoller = new MaxDiceRoller(dice);

        assertEquals(4, maxDiceRoller.rollDice());
    }
}
