package org.snakesandladders.model.diceroller;

import org.snakesandladders.model.Die;

import java.util.List;

public class SumDiceRoller implements DiceRoller {
    private final List<Die> dies;

    public SumDiceRoller(List<Die> dies) {
        this.dies = dies;
    }

    @Override
    public int rollDice() {
        int sum = 0;
        for (Die die : dies) {
            sum += die.roll();
        }
        return sum;
    }
}