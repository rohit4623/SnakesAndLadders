package org.snakesandladders.model.special;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.snakesandladders.model.Player;

public class Crocodile extends SpecialObject {

    public Crocodile(@JsonProperty("position") int position) {
        super(position);
    }

    @Override
    public void applyEffect(Player player) {
        System.out.print(" and encountered a Crocodile at position " + getPosition() + " and moved back 5 steps.");
        System.out.println();
        player.setPosition(player.getPosition() - 5);
    }
}