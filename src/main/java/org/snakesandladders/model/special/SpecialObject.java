package org.snakesandladders.model.special;

import org.snakesandladders.model.Player;

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
