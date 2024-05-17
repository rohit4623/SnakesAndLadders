package org.snakesandladders.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.snakesandladders.data.MovementStrategy;
import org.snakesandladders.model.Board;
import org.snakesandladders.model.Ladder;
import org.snakesandladders.model.Player;
import org.snakesandladders.model.Snake;
import org.snakesandladders.model.special.Crocodile;
import org.snakesandladders.model.special.Mine;

import java.util.Arrays;
import java.util.List;

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

        GameServiceImpl game = new GameServiceImpl(board, players, 1, MovementStrategy.SUM);

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
}