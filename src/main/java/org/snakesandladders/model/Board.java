package org.snakesandladders.model;

import org.snakesandladders.model.special.SpecialObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Board {
    private int size;
    private Map<Integer, Integer> snakes;
    private Map<Integer, Integer> ladders;
    private Map<Integer, SpecialObject> specialObjects;

    public Board(int size, List<Snake> snakes, List<Ladder> ladders, List<SpecialObject> specialObjects) {
        this.size = size * size;
        this.snakes = new HashMap<>();
        this.ladders = new HashMap<>();
        this.specialObjects = new HashMap<>();

        for (Snake snake : snakes) {
            this.snakes.put(snake.getHead(), snake.getTail());
        }

        for (Ladder ladder : ladders) {
            this.ladders.put(ladder.getStart(), ladder.getEnd());
        }

        for (SpecialObject specialObject : specialObjects) {
            this.specialObjects.put(specialObject.getPosition(), specialObject);
        }
    }

    public int move(Player currentPlayer, int roll, int position) {
        if (snakes.containsKey(position)) {
            System.out.println(currentPlayer.getName() + " rolled a "+ roll + " and got bitten by a snake at " + position + " and moved to " + snakes.get(position));
            return snakes.get(position);
        } else if (ladders.containsKey(position)) {
            System.out.println(currentPlayer.getName() + " rolled a " + roll + " and climbed a ladder at " + position + " and moved to " + ladders.get(position));
            return ladders.get(position);
        } else if (specialObjects.containsKey(position)) {
            System.out.print(currentPlayer.getName() + " rolled a " + roll);
            specialObjects.get(position).applyEffect(currentPlayer);
        } else {
            System.out.println(currentPlayer.getName() + " rolled a " + roll + " and moved to " + position);
        }
        return position;
    }

    public int getSize() {
        return size;
    }

    public int getNumberOfSnakes() {
        return snakes.keySet().size();
    }

    public int getNumberOfLadders() {
        return ladders.keySet().size();
    }
}