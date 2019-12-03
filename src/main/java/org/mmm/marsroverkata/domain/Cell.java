package org.mmm.marsroverkata.domain;

public class Cell {
    private final Position position;
    private boolean occupied=false;

    public Cell(Position position) {
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

    @Override
    public String toString() {
        return "Cell{" +
            "position=" + position +
            ", occupied=" + occupied +
            '}';
    }
}
