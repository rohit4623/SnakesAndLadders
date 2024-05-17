package org.snakesandladders.model.diceroller;


/**
 * DiceRoller interface that defines the behavior of a dice roller
 * Here we utilise the run time polymorphism to determine the object for DiceRoller interface.
 * Then based on the DiceRoller object we can call the rollDice() method based on the strategy
 */
public interface DiceRoller {
    int rollDice();
}
