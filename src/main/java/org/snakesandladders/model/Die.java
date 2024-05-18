package org.snakesandladders.model;

import java.util.Random;

/**
 * A simple die object configurable to have n sides
 */
public class Die {
    private Random random;
    private int sides;

    public Die(int sides) {
        this.sides = sides;
        this.random = new Random();
    }

    public int roll() {
        return random.nextInt(sides) + 1;
    }
}
