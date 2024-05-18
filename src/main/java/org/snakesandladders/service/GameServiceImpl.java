package org.snakesandladders.service;

import org.snakesandladders.data.MovementStrategy;
import org.snakesandladders.model.Board;
import org.snakesandladders.model.Player;
import org.snakesandladders.model.diceroller.DiceRoller;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameServiceImpl implements GameService {
    private Board board;
    private List<Player> players;
    private DiceRoller diceRoller;

    private Map<Integer, Player> lastPlayerPositionMap;

    public GameServiceImpl(Board board, List<Player> players, DiceRoller diceRoller) {
        this.board = board;
        this.players = players;
        this.lastPlayerPositionMap = new HashMap<>();
        this.diceRoller = diceRoller;
    }

    @Override
    public void play() {
        play(Collections.emptyMap(), Collections.emptyMap(), null);
    }

    public void play(Map<String, Integer> startingPositions, Map<String, List<List<Integer>>> diceRolls, MovementStrategy movementStrategy) {
        boolean gameWon = false;

        // Initialize player positions based on startingPositions map for manual override
        // otherwise it will take from the json file
        for (Player player : players) {
            if (startingPositions.containsKey(player.getName())) {
                player.setPosition(startingPositions.get(player.getName()));
            }
        }

        int turn = 0;
        while (!gameWon) {
            for (Player currentPlayer : players) {
                //check if the player has to skip the turn due to hitting mines
                if (currentPlayer.getTurnsToSkip() > 0) {
                    System.out.println(currentPlayer.getName() + " is skipping this turn.");
                    currentPlayer.setTurnsToSkip(currentPlayer.getTurnsToSkip() - 1);
                    continue;
                }

                int roll = diceRoller.rollDice();
                //give manual override input for D dies
                if (diceRolls.containsKey(currentPlayer.getName()) && turn < diceRolls.get(currentPlayer.getName()).size()) {
                    List<Integer> rolls = diceRolls.get(currentPlayer.getName()).get(turn);
                    roll = getRollValueForManualOverride(rolls, movementStrategy);
                }

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
                turn++;
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


    private int getRollValueForManualOverride(List<Integer> rolls, MovementStrategy movementStrategy) {
        switch (movementStrategy) {
            case SUM:
                int sum = 0;
                for(int roll: rolls) {
                    sum += roll;
                }
                return sum;
            case MIN:
                int min = Integer.MAX_VALUE;
                for(int roll: rolls) {
                    min = Math.min(min, roll);
                }
                return min;
            case MAX:
                int max = Integer.MIN_VALUE;
                for(int roll: rolls) {
                    max = Math.min(max, roll);
                }
                return max;
            default:
                throw new RuntimeException("Movement Strategy not found");

        }
    }
}