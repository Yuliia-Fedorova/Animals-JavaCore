package com.domain.util;

public class Move {
    private Direction direction;
    private int moveLength;

    public Move(Direction direction, int moveLength) {
        this.direction = direction;
        this.moveLength = moveLength;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getMoveLength() {
        return moveLength;
    }
}
