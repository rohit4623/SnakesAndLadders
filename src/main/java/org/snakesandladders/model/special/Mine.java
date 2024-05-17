package org.snakesandladders.model.special;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.snakesandladders.model.Player;

public class Mine extends SpecialObject {

    public Mine(@JsonProperty("position") int position) {
        super(position);
    }

    @Override
    public void applyEffect(Player player) {
        System.out.print(" and stepped on a Mine at position " + getPosition() + " and will miss 2 turns.");
        System.out.println();
        player.setTurnsToSkip(2);
    }
}
