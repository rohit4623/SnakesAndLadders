package org.snakesandladders.validator;

import org.snakesandladders.model.Board;
import org.snakesandladders.model.Player;

import java.security.InvalidParameterException;
import java.util.Set;

public class PlayerValidator {

    /**
     * Assumptions: We will have unique player names and at least 2 players are required to play
     * A Player must be placed within the board limits and it doesn't make sense to spawn a player at the last position of the game.
     */
    public static void validate(Board board, Player player, Set<String> uniquePlayers) {

        if(!uniquePlayers.add(player.getName())) {
            throw new InvalidParameterException("Player " + player.getName() + " already exists");
        }

        if(player.getPosition() > board.getSize() || player.getPosition() < 1) {
            throw new InvalidParameterException("Player Starting Position must be in the range [1," + board.getSize() + "]");
        }

        if(player.getPosition() == board.getSize()) {
            throw new InvalidParameterException("Game cannot be started. Player " + player.getName() + " has already reached the end of the game");
        }
    }
}
