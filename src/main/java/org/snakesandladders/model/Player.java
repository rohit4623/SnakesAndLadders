package org.snakesandladders.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {
    private String name;
    private int position;
    private int turnsToSkip;

    public Player(@JsonProperty("name") String name, @JsonProperty("startPos") int startPos) {
        this.name = name;
        this.position = startPos;
        this.turnsToSkip = 0;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getTurnsToSkip() {
        return turnsToSkip;
    }

    public void setTurnsToSkip(int turnsToSkip) {
        this.turnsToSkip = turnsToSkip;
    }
}