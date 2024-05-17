package org.snakesandladders;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.snakesandladders.config.GameConfig;
import org.snakesandladders.model.Board;
import org.snakesandladders.model.Player;
import org.snakesandladders.model.special.SpecialObject;
import org.snakesandladders.service.GameServiceImpl;
import org.snakesandladders.util.BoardUtil;
import org.snakesandladders.validator.GameValidator;
import org.snakesandladders.validator.PlayerPositionValidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        for (final Player player: config.getPlayers()) {
            PlayerPositionValidator.validatePlayerPosition(board, player);
            players.add(player);
        }

        GameValidator.validateGame(config, players);

        // Initialize and start game
        final GameServiceImpl game = new GameServiceImpl(board, players, config.getDiceCount(), config.getMovementStrategy());
        game.play();
    }

    private static List<SpecialObject> buildSpecialObjectsList(GameConfig config) {
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