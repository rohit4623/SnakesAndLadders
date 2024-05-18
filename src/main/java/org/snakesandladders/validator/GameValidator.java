package org.snakesandladders.validator;

import org.snakesandladders.config.GameConfig;
import org.snakesandladders.model.Ladder;
import org.snakesandladders.model.Player;
import org.snakesandladders.model.Snake;

import java.util.List;

public class GameValidator {
    public static void validateGame(GameConfig config, List<Player> players) {
        int size = config.getBoardSize()*config.getBoardSize();

        if(config.getBoardSize() < 0) {
            throw new RuntimeException("Invalid board size : " + config.getBoardSize());
        }

        if(config.getMovementStrategy() == null) {
            throw new RuntimeException("Movement strategy can't be empty");
        }

        if(config.getDiceCount() < 1) {
            throw new RuntimeException("Invalid dice count : " + config.getDiceCount());
        }

        if(config.getSnakes().size() < 1) {
            throw new RuntimeException("The game must have at least one snake");
        }

        validateSnakePositions(config.getSnakes(), size);

        if(config.getLadders().size() < 1) {
            throw new RuntimeException("The game must have at least one ladder");
        }

        validateLadderPositions(config.getLadders(), size);

        validateSnakeLadderOverlap(config.getSnakes(), config.getLadders());


        if(players.size() < 2) {
            throw new RuntimeException(" Two or more players are required to play this game.");
        }
    }

    /**
     * Assumptions: A snake should be defined within the board limits.
     * It does not make sense to put a snake's head at the last or first position of the game
     * It only makes sense to have a snake's tail to end at first position in the game
     * a snake should move a player strictly downwards, so it's length cannot be zero
     */
    private static void validateSnakePositions(List<Snake> snakes, int size) {
        for(Snake snake: snakes) {

            if(snake.getHead() <= 1 || snake.getHead() >= size) {
                throw new RuntimeException("Snake's head position " + snake.getHead() +  " out of range: [" + "2," + (size-1) + "]");
            }

            if(snake.getTail() < 1 || snake.getTail() >= size) {
                throw new RuntimeException("Snake's tail position " + snake.getTail() +  " out of range: [" + "1," + (size-1) + "]");
            }

            if(snake.getHead() == snake.getTail()) {
                throw new RuntimeException("Snake's length can't be zero");
            }

            if(snake.getHead() < snake.getTail()) {
                throw new RuntimeException("Invalid Snake Position. It must move a player backwards");
            }
        }
    }

    /**
     * similar assumptions for ladders as above
     */
    private static void validateLadderPositions(List<Ladder> ladders, int size) {
        for(Ladder ladder: ladders) {
            if(ladder.getStart() <= 1 || ladder.getStart() >= size) {
                throw new RuntimeException("Ladder's start position " + ladder.getStart() +  " out of range: [" + "2," + (size-1) + "]");
            }

            if(ladder.getEnd() <= 1 || ladder.getEnd() > size) {
                throw new RuntimeException("Ladder's end position " + ladder.getEnd() +  " out of range: [" + "2," + (size) + "]");
            }

            if(ladder.getStart() == ladder.getEnd()) {
                throw new RuntimeException("Ladder's length can't be zero");
            }

            if(ladder.getStart() > ladder.getEnd()) {
                throw new RuntimeException("Invalid Ladder Position. It must move a player forward");
            }
        }
    }

    /**
     * If snake's head coincides with ladder end, and snake's tail coincides with ladder start
     * The piece will remain in an infinite loop forever.
     * Secondly, if snake's head and ladder's start coincide, we will not be able make a decision
     */
    private static void validateSnakeLadderOverlap(List<Snake> snakes, List<Ladder> ladders) {
        for(final Snake snake: snakes) {
            for(final Ladder ladder: ladders) {
                if(snake.getHead() == ladder.getEnd() && snake.getTail() == ladder.getStart()) {
                    throw new RuntimeException("The snake and ladder are overlapping in an infinite loop");
                }
                if(snake.getHead() == ladder.getStart()) {
                    throw new RuntimeException("The snake head and ladder start cannot coincide");
                }
            }
        }
    }
}
