package org.mmm.marsroverkata;

import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Plateau {

    private static final HashMap<Integer, Box> boxes = new HashMap<>();
    private final Integer maximumX;
    private final Integer maximumY;


    public Plateau(Integer maximumX, Integer maximumY) {

        this.maximumX = maximumX;
        this.maximumY = maximumY;

        for (int i = 0; i < maximumX; i++) {
            for (int j = 0; j < maximumY; j++) {
                Box box = new Box(new Position(i, j));
                boxes.put(box.getPosition().hashCode(), box);
            }
        }

    }

    public void addRover(Rover rover) {
        Box box = boxes.get(rover.getPosition().hashCode());

        if (box.isOccupied()) throw new IllegalStateException("A rover is already in that position");

        box.setOccupied(true);

    }

    public void updateRoverPosition(Rover rover, Position position) {

        boxes.get(rover.getPosition().hashCode()).setOccupied(false);

        boxes.get(position.hashCode()).setOccupied(true);

        rover.setPosition(position);
    }

    public boolean isPositionOccupied(Position position) {

        return boxes.get(position.hashCode()).isOccupied();
    }


}
