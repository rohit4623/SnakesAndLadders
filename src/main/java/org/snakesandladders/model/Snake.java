package org.snakesandladders.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

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

