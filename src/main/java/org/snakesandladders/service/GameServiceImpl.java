package org.snakesandladders.service;

import org.snakesandladders.data.MovementStrategy;
import org.snakesandladders.factory.DiceRollerFactory;
import org.snakesandladders.model.Board;
import org.snakesandladders.model.diceroller.DiceRoller;
import org.snakesandladders.model.Die;
import org.snakesandladders.model.Player;

import java.util.*;

public class GameServiceImpl implements GameService {
    private Board board;
    private List<Player> players;
    private List<Die> dies;
    private int diceCount;
    private MovementStrategy movementStrategy;
    private Random random;

    private Map<Integer, Player> lastPlayerPositionMap;

    public GameServiceImpl(Board board, List<Player> players, int diceCount, MovementStrategy movementStrategy) {
        this.board = board;
        this.players = players;
        this.diceCount = diceCount;
        this.movementStrategy = movementStrategy;
        this.random = new Random();
        this.lastPlayerPositionMap = new HashMap<>();
        this.dies = new ArrayList<>();
        for(int i=0; i < diceCount; i++) {
            dies.add(new Die(6));
        }
    }

    @Override
    public void play() {
        boolean gameWon = false;

        while (!gameWon) {
            for (Player currentPlayer : players) {
                //check if the player has to skip the turn due to hitting mines
                if (currentPlayer.getTurnsToSkip() > 0) {
                    System.out.println(currentPlayer.getName() + " is skipping this turn.");
                    currentPlayer.setTurnsToSkip(currentPlayer.getTurnsToSkip() - 1);
                    continue;
                }

                int roll = rollDice(dies);
                int newPosition = currentPlayer.getPosition() + roll;

                // Ensure the new position does not exceed the board size
                if (newPosition > board.getSize()) {
                    newPosition = board.getSize() - (newPosition - board.getSize());
                }

                //determine the next move according to the snakes, ladders and special objects
                newPosition = board.move(currentPlayer, roll, newPosition);

                //update the players new position
                currentPlayer.setPosition(newPosition);

                //if other player is already present on the new position, kill the player who was placed there last
                killLastPlayerIfExistOnNewPosition(currentPlayer, newPosition);

                //update the last player position in the map to keep track of the player that is placed last at a particular position
                lastPlayerPositionMap.entrySet().removeIf(entry -> entry.getValue().equals(currentPlayer));
                lastPlayerPositionMap.put(newPosition, currentPlayer);

                //check if any winner
                if (newPosition == board.getSize()) {
                    System.out.println(currentPlayer.getName() + " has won the game!");
                    gameWon = true;
                    break;
                }
            }
        }
    }


    private void killLastPlayerIfExistOnNewPosition(Player currentPlayer, int newPosition) {
        if(lastPlayerPositionMap.containsKey(newPosition)) {
            final Player lastPlayerOnCurrentPos = lastPlayerPositionMap.get(newPosition);
            if (lastPlayerOnCurrentPos != currentPlayer && newPosition != 1) {
                System.out.println("Player " + currentPlayer.getName() + " has killed " + lastPlayerOnCurrentPos.getName() + "! " + lastPlayerOnCurrentPos.getName() + " has to start again from position 1!");
                lastPlayerOnCurrentPos.setPosition(1);
                lastPlayerPositionMap.remove(newPosition);
            }
        }
    }

    private int rollDice(List<Die> dies) {
        final DiceRoller diceRoller = DiceRollerFactory.createDiceRoller(movementStrategy, dies);
        return diceRoller.rollDice();
    }
}