package org.snakesandladders.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A Snake will have a head and tail.
 * If a player hits snake at head, it will go to tail
 * we can have a Map<Integer, Integer> to determine which position to go.
 */
public class Snake {
    private int head;
    private int tail;

    @JsonCreator
    public Snake(@JsonProperty("head") int head, @JsonProperty("tail") int tail) {
        this.head = head;
        this.tail = tail;
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }
}

