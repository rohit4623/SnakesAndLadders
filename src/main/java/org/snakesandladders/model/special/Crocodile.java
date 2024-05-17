package org.snakesandladders.model.special;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.snakesandladders.model.Player;

public class Crocodile extends SpecialObject {

    public Crocodile(@JsonProperty("position") int position) {
        super(position);
    }

    @Override
    public void applyEffect(Player player) {
        System.out.print(" and encountered a Crocodile at position " + getPosition() + " and moved back 5 steps to position " + Math.max(getPosition() - 5, 1));
        System.out.println();
        player.setPosition(Math.max(getPosition() - 5, 1));
    }
}