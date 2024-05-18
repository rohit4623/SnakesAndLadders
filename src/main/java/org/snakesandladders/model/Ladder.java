package org.snakesandladders.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Ladder will have a start and end.
 * If a player hits ladder at start, it will move upwards
 * we can have a Map<Integer, Integer> to determine which position to go.
 */
public class Ladder {
    private int start;
    private int end;

    @JsonCreator
    public Ladder(@JsonProperty("start") int start, @JsonProperty("end") int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
