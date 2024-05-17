package org.snakesandladders.util;

import org.snakesandladders.config.GameConfig;
import org.snakesandladders.model.Board;
import org.snakesandladders.model.Ladder;
import org.snakesandladders.model.Snake;
import org.snakesandladders.model.special.Crocodile;
import org.snakesandladders.model.special.Mine;
import org.snakesandladders.model.special.SpecialObject;

import java.util.*;

public class BoardUtil {

    /*
        Rules to generate the board:
        - All snakes and ladders should be placed within the boundaries of the board.
        - Only the end of the ladder is allowed at the last cell of the game (Cell number size*size (1-indexed))
        - Only Snake's tail is allowed at the first cell of the game (Cell number 1)
        - There should be no overlap between the snake and the ladder causing infinite loops
        - There should be no overlaps between any of the snake's head, ladder's start, crocodile, or mine's positions
        - A Snake should always move the player backwards
        - A Ladder should always move the player forward
        - Both the lengths of the snakes and ladders should be non-zero
     */
    public static Board generateRandomBoard(int size, int numSnakes, int numLadders, int numCrocodiles, int numMines) {
        Random random = new Random();
        List<Snake> snakes = new ArrayList<>();
        List<Ladder> ladders = new ArrayList<>();
        List<SpecialObject> specialObjects = new ArrayList<>();
        Set<Integer> occupiedPositions = new HashSet<>();

        while (snakes.size() < numSnakes) {
            int head = random.nextInt(size * size - 2) + 2;
            int tail = random.nextInt(head - 1) + 1;

            // Ensure no overlap and snake should always be moving the player strictly downwards
            if (head > tail && !occupiedPositions.contains(head) && !occupiedPositions.contains(tail)) {
                snakes.add(new Snake(head, tail));
                occupiedPositions.add(head);
                occupiedPositions.add(tail);
            }
        }

        while (ladders.size() < numLadders) {
            int start = random.nextInt(size * size - 1) + 1;
            int end = random.nextInt(size * size - start) + start + 1;

            // Ensure no overlap and ladder should always move a player strictly upwards
            if (start < end && !occupiedPositions.contains(start) && !occupiedPositions.contains(end)) {
                ladders.add(new Ladder(start, end));
                occupiedPositions.add(start);
                occupiedPositions.add(end);
            }
        }

        while (specialObjects.size() < numCrocodiles) {
            //allowed range is 2 to (size*size - 1) inclusive
            int position = random.nextInt(size * size - 2) + 2;

            // Ensure no overlap for crocodiles with mines, snakes and ladders
            if (!occupiedPositions.contains(position)) {
                specialObjects.add(new Crocodile(position));
                occupiedPositions.add(position);
            }
        }

        while (specialObjects.size() < numCrocodiles + numMines) {
            //allowed range is 2 to (size*size - 1) inclusive
            int position = random.nextInt(size * size - 2) + 2;

            // Ensure no overlap of mines with other objects
            if (!occupiedPositions.contains(position)) {
                specialObjects.add(new Mine(position));
                occupiedPositions.add(position);
            }
        }

        return new Board(size, snakes, ladders, specialObjects);
    }

    /*
        This function will combine all the types of special objects like crocodiles
        and mines into a single list of type SpecialObject
     */
    public static List<SpecialObject> buildSpecialObjectsList(GameConfig config) {
        List<SpecialObject> specialObjects = new ArrayList<>();
        if(config.getCrocodiles().size() > 0) {
            specialObjects.addAll(config.getCrocodiles());
        }
        if(config.getMines().size() > 0) {
            specialObjects.addAll(config.getMines());
        }
        return specialObjects;
    }

}
