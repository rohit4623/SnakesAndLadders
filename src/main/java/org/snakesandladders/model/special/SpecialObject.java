package org.snakesandladders.model.special;

import org.snakesandladders.model.Player;

/**
 * Here we make use of an abstract class since for special object the position attribute is common
 * The only method that is object specific is the applyEffect method. so we make that method as abstract.
 * We reuse the position property of the abstract class and implement the applyEffect method in the crocodile
 * and mine class respectively. It allows to reuse the code and avoid duplication
 */
public abstract class SpecialObject {
    private int position;

    public SpecialObject(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public abstract void applyEffect(Player player);
}
