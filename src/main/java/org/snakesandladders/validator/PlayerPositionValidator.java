package org.snakesandladders.validator;

import org.snakesandladders.model.Board;
import org.snakesandladders.model.Player;

import java.security.InvalidParameterException;

public class PlayerPositionValidator {
    public static void validatePlayerPosition(Board board, Player player) {
        if(player.getPosition() > board.getSize() || player.getPosition() < 1) {
            throw new InvalidParameterException("Player Starting Position must be in the range [1," + board.getSize() + "]");
        }

        if(player.getPosition() == board.getSize()) {
            throw new InvalidParameterException("Game cannot be started. Player " + player.getName() + " has already reached the end of the game");
        }
    }
}
