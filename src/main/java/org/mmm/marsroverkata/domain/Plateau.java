package org.mmm.marsroverkata.domain;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class Plateau {

  private final static Logger LOGGER = Logger.getLogger(Plateau.class.getName());

  private static final HashMap<Integer, Cell> cells = new HashMap<>();
  private final Integer maximumX;
  private final Integer maximumY;


  public Plateau(Integer maximumX, Integer maximumY) {

    this.maximumX = maximumX;
    this.maximumY = maximumY;

    for (int i = 0; i <= maximumX; i++) {
      for (int j = 0; j <= maximumY; j++) {
        Cell cell = new Cell(new Position(i, j));
        cells.put(cell.getPosition().hashCode(), cell);
      }
    }

  }

  public void addRover(Rover rover) {
    Cell cell = cells.get(rover.getPosition().hashCode());

    if (cell.isOccupied()) {
      throw new IllegalStateException("A rover is already in that position");
    }

    cell.setOccupied(true);

  }

  public void updateRoverPosition(Rover rover, Position newPosition) {

    LOGGER.info(Thread.currentThread().getName() + ": Updating rover position from ( X: " + rover
        .getPosition().getX() + ", Y:" + rover
        .getPosition().getX() + " to (X:" + newPosition.getX() + ", Y:" + newPosition.getY() + ")");
    cells.get(rover.getPosition().hashCode()).setOccupied(false);

    cells.get(newPosition.hashCode()).setOccupied(true);

    rover.setPosition(newPosition);
  }

  public boolean isPositionOccupied(Position position) {

    return cells.get(position.hashCode()).isOccupied();
  }


  public List<Cell> getOccupiedCells() {

    return cells.entrySet().stream().filter(a -> a.getValue().isOccupied()).map(a -> a.getValue())
        .collect(Collectors.toList());
  }
}
