package org.snakesandladders.service;

import org.snakesandladders.data.MovementStrategy;
import org.snakesandladders.model.Board;
import org.snakesandladders.model.Player;

import java.util.*;

public class GameServiceImpl implements GameService {
    private Board board;
    private List<Player> players;
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
    }

    @Override
    public void play() {
        boolean gameWon = false;

        while (!gameWon) {
            for (Player currentPlayer : players) {
                if (currentPlayer.getTurnsToSkip() > 0) {
                    System.out.println(currentPlayer.getName() + " is skipping this turn.");
                    currentPlayer.setTurnsToSkip(currentPlayer.getTurnsToSkip() - 1);
                    continue;
                }

                int roll = rollDice();
                int newPosition = currentPlayer.getPosition() + roll;
                if (newPosition > board.getSize()) {
                    newPosition = board.getSize() - (newPosition - board.getSize());
                }
                newPosition = board.move(currentPlayer, roll, newPosition);

                currentPlayer.setPosition(newPosition);
                killLastPlayerIfExistOnNewPosition(currentPlayer, newPosition);

                lastPlayerPositionMap.entrySet().removeIf(entry -> entry.getValue().equals(currentPlayer));
                lastPlayerPositionMap.put(newPosition, currentPlayer);

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
            if (lastPlayerOnCurrentPos != currentPlayer) {
                System.out.println("Player " + currentPlayer.getName() + " has killed " + lastPlayerOnCurrentPos.getName() + "! " + lastPlayerOnCurrentPos.getName() + " has to start again from position 1!");
                lastPlayerOnCurrentPos.setPosition(1);
                lastPlayerPositionMap.remove(newPosition);
            }
        }
    }

    private int rollDice() {
        int[] rolls = new int[diceCount];
        for (int i = 0; i < diceCount; i++) {
            rolls[i] = random.nextInt(6) + 1;
        }

        switch (movementStrategy) {
            case SUM:
                int sum = 0;
                for (int roll : rolls) {
                    sum += roll;
                }
                return sum;
            case MAX:
                int max = rolls[0];
                for (int roll : rolls) {
                    if (roll > max) {
                        max = roll;
                    }
                }
                return max;
            case MIN:
                int min = rolls[0];
                for (int roll : rolls) {
                    if (roll < min) {
                        min = roll;
                    }
                }
                return min;
            default:
                throw new IllegalArgumentException("Unknown movement strategy: " + movementStrategy);
        }
    }
}