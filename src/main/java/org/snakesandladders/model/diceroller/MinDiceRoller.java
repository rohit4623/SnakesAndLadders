package org.snakesandladders.model.diceroller;

import org.snakesandladders.model.Die;

import java.util.List;

public class MinDiceRoller implements DiceRoller {
    private final List<Die> dies;

    public MinDiceRoller(List<Die> dies) {
        this.dies = dies;
    }

    @Override
    public int rollDice() {
        int min = Integer.MAX_VALUE;
        for (Die die : dies) {
            int roll = die.roll();
            if (roll < min) {
                min = roll;
            }
        }
        return min;
    }
}