package org.snakesandladders.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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
