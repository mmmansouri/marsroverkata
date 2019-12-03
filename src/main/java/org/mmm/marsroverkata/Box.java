package org.mmm.marsroverkata;

public class Box {
    private final Position position;
    private boolean occupied=false;

    public Box(Position position) {
        this.position = position;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public Position getPosition() {
        return position;
    }
}
