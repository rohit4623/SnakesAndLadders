package org.snakesandladders.factory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.snakesandladders.data.MovementStrategy;
import org.snakesandladders.model.*;
import org.snakesandladders.model.diceroller.DiceRoller;
import org.snakesandladders.model.diceroller.MaxDiceRoller;
import org.snakesandladders.model.diceroller.MinDiceRoller;
import org.snakesandladders.model.diceroller.SumDiceRoller;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class DiceRollerFactoryTest {

    @Test
    public void testCreateDiceRoller() {
        Die die1 = mock(Die.class);
        Die die2 = mock(Die.class);
        List<Die> dice = Arrays.asList(die1, die2);


        DiceRoller sumDiceRoller = DiceRollerFactory.createDiceRoller(MovementStrategy.SUM, dice);
        assertTrue(sumDiceRoller instanceof SumDiceRoller);


        DiceRoller maxDiceRoller = DiceRollerFactory.createDiceRoller(MovementStrategy.MAX, dice);
        assertTrue(maxDiceRoller instanceof MaxDiceRoller);


        DiceRoller minDiceRoller = DiceRollerFactory.createDiceRoller(MovementStrategy.MIN, dice);
        assertTrue(minDiceRoller instanceof MinDiceRoller);

    }

    @Test(expected = RuntimeException.class)
    public void testCreateDiceRoller_InvalidStrategy() {
        Die die1 = mock(Die.class);
        Die die2 = mock(Die.class);
        List<Die> dice = Arrays.asList(die1, die2);

        DiceRoller sumDiceRoller = DiceRollerFactory.createDiceRoller(null, dice);

    }
}
