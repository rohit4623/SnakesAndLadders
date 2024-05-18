package org.snakesandladders.config;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.snakesandladders.data.MovementStrategy;
import org.snakesandladders.model.Ladder;
import org.snakesandladders.model.Player;
import org.snakesandladders.model.Snake;
import org.snakesandladders.model.special.Crocodile;
import org.snakesandladders.model.special.Mine;

import java.util.List;

public class GameConfig {
    private int boardSize;
    private List<Snake> snakes;
    private List<Ladder> ladders;
    private List<Crocodile> crocodiles;
    private List<Mine> mines;
    private List<Player> players;
    private int diceCount;
    private MovementStrategy movementStrategy;
    private boolean generateRandomBoard;
    private int numSnakes;
    private int numLadders;
    private int numCrocodiles;
    private int numMines;

    public GameConfig() {
    }

    public GameConfig(@JsonProperty("boardSize") int boardSize,
                      @JsonProperty("snakes") List<Snake> snakes,
                      @JsonProperty("ladders") List<Ladder> ladders,
                      @JsonProperty("crocodiles") List<Crocodile> crocodiles,
                      @JsonProperty("mines") List<Mine> mines,
                      @JsonProperty("players") List<Player> players,
                      @JsonProperty("diceCount") int diceCount,
                      @JsonProperty("movementStrategy") MovementStrategy movementStrategy,
                      @JsonProperty("generateRandomBoard") boolean generateRandomBoard,
                      @JsonProperty("numSnakes") int numSnakes,
                      @JsonProperty("numLadders") int numLadders,
                      @JsonProperty("numCrocodiles") int numCrocodiles,
                      @JsonProperty("numMines") int numMines) {
        this.boardSize = boardSize;
        this.snakes = snakes;
        this.ladders = ladders;
        this.crocodiles = crocodiles;
        this.mines = mines;
        this.players = players;
        this.diceCount = diceCount;
        this.movementStrategy = movementStrategy;
        this.generateRandomBoard = generateRandomBoard;
        this.numSnakes = numSnakes;
        this.numLadders = numLadders;
        this.numCrocodiles = numCrocodiles;
        this.numMines = numMines;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public List<Snake> getSnakes() {
        return snakes;
    }

    public List<Ladder> getLadders() {
        return ladders;
    }

    public List<Crocodile> getCrocodiles() {
        return crocodiles;
    }

    public List<Mine> getMines() {
        return mines;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public int getDiceCount() {
        return diceCount;
    }

    public MovementStrategy getMovementStrategy() {
        return movementStrategy;
    }

    public boolean isGenerateRandomBoard() {
        return generateRandomBoard;
    }

    public int getNumSnakes() {
        return numSnakes;
    }

    public int getNumLadders() {
        return numLadders;
    }

    public int getNumCrocodiles() {
        return numCrocodiles;
    }

    public int getNumMines() {
        return numMines;
    }

}