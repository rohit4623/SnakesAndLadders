package org.snakesandladders.model.diceroller;

import org.snakesandladders.model.Die;

import java.util.List;

public class MaxDiceRoller implements DiceRoller {
    private final List<Die> dies;

    public MaxDiceRoller(List<Die> dies) {
        this.dies = dies;
    }

    @Override
    public int rollDice() {
        int max = -1;
        for (Die die : dies) {
            int roll = die.roll();
            if (roll > max) {
                max = roll;
            }
        }
        return max;
    }
}