package org.snakesandladders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.snakesandladders.config.GameConfig;
import org.snakesandladders.data.MovementStrategy;
import org.snakesandladders.factory.DiceRollerFactory;
import org.snakesandladders.model.Board;
import org.snakesandladders.model.Die;
import org.snakesandladders.model.Player;
import org.snakesandladders.model.diceroller.DiceRoller;
import org.snakesandladders.model.special.SpecialObject;
import org.snakesandladders.service.GameServiceImpl;
import org.snakesandladders.util.BoardUtil;
import org.snakesandladders.validator.GameValidator;
import org.snakesandladders.validator.PlayerValidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.snakesandladders.util.BoardUtil.buildSpecialObjectsList;

public class Main {
    public static void main(String[] args) throws IOException {
        // Load configuration from JSON file
        final ObjectMapper mapper = new ObjectMapper();
        final GameConfig config = mapper.readValue(new File("src/main/resources/config.json"), GameConfig.class);

        // Initialize board
        final Board board;
        if (config.isGenerateRandomBoard()) {
            board = BoardUtil.generateRandomBoard(config.getBoardSize(), config.getNumSnakes(), config.getNumLadders(), config.getNumCrocodiles(), config.getNumMines());
        } else {
            List<SpecialObject> specialObjects = buildSpecialObjectsList(config);
            board = new Board(config.getBoardSize(), config.getSnakes(), config.getLadders(), specialObjects);
        }

        // Initialize players
        final List<Player> players = new ArrayList<>();
        final Set<String> uniquePlayers = new HashSet<>();
        for (final Player player: config.getPlayers()) {
            PlayerValidator.validate(board, player, uniquePlayers);
            players.add(player);
        }

        GameValidator.validateGame(config, players);

        MovementStrategy movementStrategy = config.getMovementStrategy();
        int diceCount = config.getDiceCount();

        final DiceRoller diceRoller = initializeDiceRoller(movementStrategy, diceCount);

        // Initialize and start game
        final GameServiceImpl game = new GameServiceImpl(board, players, diceRoller);
        game.play();
    }

    private static DiceRoller initializeDiceRoller(MovementStrategy movementStrategy, int diceCount) {
        List<Die> dies = new ArrayList<>();
        for(int i = 0; i < diceCount; i++) {
            dies.add(new Die(6));
        }
        final DiceRoller diceRoller = DiceRollerFactory.createDiceRoller(movementStrategy, dies);
        return diceRoller;
    }
}