package org.snakesandladders.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.snakesandladders.model.Board;
import org.snakesandladders.model.Player;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerValidatorTest {

    @Mock
    private Board mockBoard;

    @Test(expected = InvalidParameterException.class)
    public void testValidatePlayerPosition_PlayerPositionGreaterThanBoardSize() {
        Player player = new Player("Player1", 11); // Player position greater than board size
        when(mockBoard.getSize()).thenReturn(10);
        Set<String> uniquePlayers = new HashSet<>();
        uniquePlayers.add("Player1");
        PlayerValidator.validate(mockBoard, player, new HashSet<>());
    }

    @Test(expected = InvalidParameterException.class)
    public void testValidatePlayerPosition_PlayerPositionLessThanOne() {
        Player player = new Player("Player2", 0); // Player position less than 1
        when(mockBoard.getSize()).thenReturn(10);
        Set<String> uniquePlayers = new HashSet<>();
        uniquePlayers.add("Player1");
        PlayerValidator.validate(mockBoard, player, new HashSet<>());
    }

    @Test(expected = InvalidParameterException.class)
    public void testValidatePlayerPosition_PlayerAlreadyAtEnd() {
        Player player = new Player("Player3", 10); // Player already at the end of the board
        when(mockBoard.getSize()).thenReturn(10);
        Set<String> uniquePlayers = new HashSet<>();
        uniquePlayers.add("Player1");
        PlayerValidator.validate(mockBoard, player, new HashSet<>());
    }

    @Test
    public void testValidatePlayerPosition_PlayerValidPosition() {
        Player player = new Player("Player4", 5); // Valid player position
        when(mockBoard.getSize()).thenReturn(10);
        Set<String> uniquePlayers = new HashSet<>();
        uniquePlayers.add("Player1");
        PlayerValidator.validate(mockBoard, player, uniquePlayers);
        // No exception should be thrown
    }

    @Test(expected = InvalidParameterException.class)
    public void testValidatePlayerPosition_PlayerAlreadyExists() {
        Player player = new Player("Player4", 5); // Valid player position
        Set<String> uniquePlayers = new HashSet<>();
        uniquePlayers.add("Player4");
        PlayerValidator.validate(mockBoard, player, uniquePlayers);
        // No exception should be thrown
    }
}
