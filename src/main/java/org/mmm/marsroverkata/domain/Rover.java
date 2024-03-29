package org.mmm.marsroverkata.domain;

import java.util.Objects;

public class Rover {

    private Position position;

    private Direction orientation;

    public Rover(){}

    public Rover(Position position,Direction orientation ){
        this.orientation=orientation;
        this.position=position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction orientation) {
        this.orientation = orientation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rover rover = (Rover) o;
        return position.equals(rover.position) &&
                orientation == rover.orientation;
    }

    @Override
    public int hashCode() {
        return Objects.hash(position, orientation);
    }

    @Override
    public String toString() {
        return "Rover{" +
            "position=" + position +
            ", orientation=" + orientation +
            '}';
    }
}
