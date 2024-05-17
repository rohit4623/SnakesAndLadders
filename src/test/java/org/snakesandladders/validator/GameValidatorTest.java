package org.snakesandladders.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.snakesandladders.config.GameConfig;
import org.snakesandladders.model.Ladder;
import org.snakesandladders.model.Player;
import org.snakesandladders.model.Snake;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameValidatorTest {

    @Mock
    GameConfig mockConfig;

    @Test(expected = RuntimeException.class)
    public void testValidateGame_InvalidBoardSize() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));
        
        when(mockConfig.getBoardSize()).thenReturn(-1);
        GameValidator.validateGame(mockConfig, players);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateGame_InvalidDiceCount() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));
        
        when(mockConfig.getBoardSize()).thenReturn(10);
        when(mockConfig.getDiceCount()).thenReturn(0);
        GameValidator.validateGame(mockConfig, players);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateGame_NoSnakes() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));
        
        when(mockConfig.getBoardSize()).thenReturn(10);
        when(mockConfig.getDiceCount()).thenReturn(1);
        when(mockConfig.getSnakes()).thenReturn(Collections.emptyList());
        GameValidator.validateGame(mockConfig, players);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateGame_NoLadders() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));

        when(mockConfig.getBoardSize()).thenReturn(10);
        when(mockConfig.getDiceCount()).thenReturn(1);
        List<Snake> snakes = Collections.singletonList(new Snake(8, 5));
        when(mockConfig.getSnakes()).thenReturn(snakes);
        when(mockConfig.getLadders()).thenReturn(Collections.emptyList());
        GameValidator.validateGame(mockConfig, players);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateGame_SnakePositionOutOfRange() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));
        
        when(mockConfig.getBoardSize()).thenReturn(10);
        when(mockConfig.getDiceCount()).thenReturn(1);
        List<Snake> snakes = Collections.singletonList(new Snake(1, 0));
        when(mockConfig.getSnakes()).thenReturn(snakes);
        GameValidator.validateGame(mockConfig, players);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateGame_SnakeShouldStrictlyMoveBackwards() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));
        
        when(mockConfig.getBoardSize()).thenReturn(10);
        when(mockConfig.getDiceCount()).thenReturn(1);
        List<Snake> snakes = Collections.singletonList(new Snake(8, 10));
        when(mockConfig.getSnakes()).thenReturn(snakes);
        GameValidator.validateGame(mockConfig, players);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateGame_SnakeShouldHavePositiveLength() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));
        
        when(mockConfig.getBoardSize()).thenReturn(10);
        when(mockConfig.getDiceCount()).thenReturn(1);
        List<Snake> snakes = Collections.singletonList(new Snake(8, 8));
        when(mockConfig.getSnakes()).thenReturn(snakes);
        GameValidator.validateGame(mockConfig, players);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateGame_LadderPositionOutOfRange() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));
        
        when(mockConfig.getBoardSize()).thenReturn(10);
        when(mockConfig.getDiceCount()).thenReturn(1);
        List<Snake> snakes = Collections.singletonList(new Snake(8, 5));
        when(mockConfig.getSnakes()).thenReturn(snakes);
        List<Ladder> ladders = Collections.singletonList(new Ladder(0, 1));
        when(mockConfig.getLadders()).thenReturn(ladders);
        GameValidator.validateGame(mockConfig, players);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateGame_LadderShouldStrictlyMoveUpwards() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));
        
        when(mockConfig.getBoardSize()).thenReturn(10);
        when(mockConfig.getDiceCount()).thenReturn(1);
        List<Snake> snakes = Collections.singletonList(new Snake(8, 5));
        when(mockConfig.getSnakes()).thenReturn(snakes);
        List<Ladder> ladders = Collections.singletonList(new Ladder(5, 4));
        when(mockConfig.getLadders()).thenReturn(ladders);
        GameValidator.validateGame(mockConfig, players);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateGame_LadderLengthShouldBeNonZero() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));
        
        when(mockConfig.getBoardSize()).thenReturn(10);
        when(mockConfig.getDiceCount()).thenReturn(1);
        List<Snake> snakes = Collections.singletonList(new Snake(8, 5));
        when(mockConfig.getSnakes()).thenReturn(snakes);
        List<Ladder> ladders = Collections.singletonList(new Ladder(5, 5));
        when(mockConfig.getLadders()).thenReturn(ladders);
        GameValidator.validateGame(mockConfig, players);
    }

    @Test(expected = RuntimeException.class)
    public void testValidateGame_NoOverlapForSnakesOrLadders() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));
        
        when(mockConfig.getBoardSize()).thenReturn(10);
        when(mockConfig.getDiceCount()).thenReturn(1);
        List<Snake> snakes = Collections.singletonList(new Snake(5, 4));
        List<Ladder> ladders = Collections.singletonList(new Ladder(5, 9));
        when(mockConfig.getLadders()).thenReturn(ladders);
        when(mockConfig.getSnakes()).thenReturn(snakes);
        GameValidator.validateGame(mockConfig, players);
    }

    @Test
    public void testValidateGame_ValidGameShouldPassAllValidations() {
        List<Player> players = Arrays.asList(new Player("Player1", 1), new Player("Player2", 1));
        
        when(mockConfig.getBoardSize()).thenReturn(10);
        when(mockConfig.getDiceCount()).thenReturn(1);
        List<Snake> snakes = Collections.singletonList(new Snake(5, 4));
        List<Ladder> ladders = Collections.singletonList(new Ladder(7, 9));
        when(mockConfig.getLadders()).thenReturn(ladders);
        when(mockConfig.getSnakes()).thenReturn(snakes);
        GameValidator.validateGame(mockConfig, players);
    }

}
