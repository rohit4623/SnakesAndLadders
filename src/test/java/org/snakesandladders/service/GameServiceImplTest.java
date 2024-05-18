package org.snakesandladders.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.snakesandladders.data.MovementStrategy;
import org.snakesandladders.factory.DiceRollerFactory;
import org.snakesandladders.model.*;
import org.snakesandladders.model.diceroller.DiceRoller;
import org.snakesandladders.model.special.Crocodile;
import org.snakesandladders.model.special.Mine;
import org.snakesandladders.model.special.SpecialObject;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class GameServiceImplTest {

    @Test
    public void testGameWithFixedBoard() {
        final Board board = new Board(10,
                Arrays.asList(new Snake(16, 6)),
                Arrays.asList(new Ladder(2, 14)),
                Arrays.asList(new Crocodile(5), new Mine(10)));

        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));

        List<Die> dies = new ArrayList<>();
        int diceCount = 1;
        for(int i = 0; i < diceCount; i++) {
            dies.add(new Die(6));
        }
        DiceRoller diceRoller = DiceRollerFactory.createDiceRoller(MovementStrategy.SUM, dies);

        GameServiceImpl game = new GameServiceImpl(board, players, diceRoller);

        // Mock dice rolls
        game.play();
    }

    @Test
    public void testSpecialObjects() {
        Player player = new Player("Player1", 10);
        Crocodile crocodile = new Crocodile(10);
        Mine mine = new Mine(20);

        crocodile.applyEffect(player);
        assertEquals(player.getPosition(), 5);

        mine.applyEffect(player);
        assertEquals(player.getTurnsToSkip(), 2);
    }

    @Test
    public void testGameServiceImpl_PlayGameWithManualOverride() {
        // Initialize the game with board, players, snakes, ladders, etc.
        List<Player> players = Arrays.asList(new Player("Gaurav", 1), new Player("Sagar", 1));
        List<Snake> snakes = Arrays.asList(new Snake(62, 5), new Snake(33, 6), new Snake(49, 9));
        List<Ladder> ladders = Arrays.asList(new Ladder(2, 37), new Ladder(27, 46), new Ladder(10, 32));
        List<SpecialObject> specialObjects = Arrays.asList(new Crocodile(50), new Mine(55));
        Board board = new Board(10, snakes, ladders, specialObjects);


        List<Die> dies = new ArrayList<>();
        int diceCount = 2;
        for(int i = 0; i < diceCount; i++) {
            dies.add(new Die(6));
        }
        DiceRoller diceRoller = DiceRollerFactory.createDiceRoller(MovementStrategy.SUM, dies);

        GameServiceImpl game = new GameServiceImpl(board, players, diceRoller);

        // Define starting positions for players
        Map<String, Integer> startingPositions = new HashMap<>();
        startingPositions.put("Gaurav", 1);
        startingPositions.put("Sagar", 1);

        // Define D dice rolls for each player for every turn
        Map<String, List<List<Integer>>> diceRolls = new HashMap<>();
        diceRolls.put("Gaurav", Arrays.asList(Arrays.asList(3, 4), Arrays.asList(2, 6), Arrays.asList(1, 1)));
        diceRolls.put("Sagar", Arrays.asList(Arrays.asList(4, 2), Arrays.asList(3, 3), Arrays.asList(5, 1)));

        // Play the game with manual inputs
        game.play(startingPositions, diceRolls, MovementStrategy.SUM);
    }
}