package org.snakesandladders.util;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.snakesandladders.data.MovementStrategy;
import org.snakesandladders.model.Board;
import org.snakesandladders.model.Player;
import org.snakesandladders.service.GameServiceImpl;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class BoardUtilTest {
    @Test
    public void testRandomBoardGeneration() {
        final Board board = BoardUtil.generateRandomBoard(10, 9, 8, 2, 2);
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2",1 ));

        GameServiceImpl game = new GameServiceImpl(board, players, 1, MovementStrategy.MAX);

        // Mock dice rolls
        game.play();
    }
}
