package org.snakesandladders.factory;

import org.snakesandladders.data.MovementStrategy;
import org.snakesandladders.model.*;
import org.snakesandladders.model.diceroller.DiceRoller;
import org.snakesandladders.model.diceroller.MaxDiceRoller;
import org.snakesandladders.model.diceroller.MinDiceRoller;
import org.snakesandladders.model.diceroller.SumDiceRoller;

import java.util.List;


/*
    Factory for creating DiceRoller objects
    Since we can have multiple implementations of DiceRoller we use a Factory Design Pattern
    For extensibility, we can easily add new DiceRoller implementations in the future
 */
public class DiceRollerFactory {
    public static DiceRoller createDiceRoller(MovementStrategy movementStrategy, List<Die> dies) {
        switch (movementStrategy) {
            case SUM:
                return new SumDiceRoller(dies);
            case MAX:
                return new MaxDiceRoller(dies);
            case MIN:
                return new MinDiceRoller(dies);
            default:
                throw new IllegalArgumentException("Unknown movement strategy: " + movementStrategy);
        }
    }
}
