package org.mmm.marsroverkata.services;

import static java.lang.System.currentTimeMillis;
import static org.mmm.marsroverkata.domain.Direction.E;
import static org.mmm.marsroverkata.domain.Direction.N;
import static org.mmm.marsroverkata.domain.Direction.S;
import static org.mmm.marsroverkata.domain.Direction.W;

import java.util.logging.Logger;
import org.mmm.marsroverkata.domain.Command;
import org.mmm.marsroverkata.domain.Scanner;
import org.mmm.marsroverkata.domain.Plateau;
import org.mmm.marsroverkata.domain.Position;
import org.mmm.marsroverkata.domain.Rover;

public class ScanAreaService {

  private final static Logger LOGGER = Logger.getLogger(ScanAreaService.class.getName());

  public static void scanArea(Plateau plateau, Scanner scanner) {

    Rover rover = scanner.getRover();

    plateau.addRover(rover);

    Position nextRoverPosition = new Position(rover.getPosition().getX(),
        rover.getPosition().getY());

    scanner.getInstructions().forEach(a -> {
      determinateNextPosition(rover, nextRoverPosition, a);

      if (!nextRoverPosition.equals(rover.getPosition())) {
            moveRover(plateau, rover, nextRoverPosition);
          }


        }
    );

  }

  private static void determinateNextPosition(Rover rover, Position nextRoverPosition, Command a) {
    switch (rover.getOrientation()) {
      case N:
        switch (a) {
          case L:
            rover.setOrientation(W);
            break;
          case R:
            rover.setOrientation(E);
            break;
          case M:
            nextRoverPosition.incrementY();
            break;
          default:
            break;
        }
        break;
      case W:
        switch (a) {
          case L:
            rover.setOrientation(S);
            break;
          case R:
            rover.setOrientation(N);
            break;
          case M:
            nextRoverPosition.decrementX();
            break;
          default:
            break;
        }
        break;
      case S:
        switch (a) {
          case L:
            rover.setOrientation(E);
            break;
          case R:
            rover.setOrientation(W);
            break;
          case M:
            nextRoverPosition.decrementY();
            break;
          default:
            break;
        }
        break;
      case E:
        switch (a) {
          case L:
            rover.setOrientation(N);
            break;
          case R:
            rover.setOrientation(S);
            break;
          case M:
            nextRoverPosition.incrementX();
            break;
          default:
            break;
        }
        break;
      default:
        break;
    }
  }

  private static void moveRover(Plateau plateau, Rover rover, Position nextRoverPosition) {

    LOGGER.info(
        Thread.currentThread().getName() + " : Moving rover position from (X:" + rover.getPosition()
            .getX() + ", Y:" + rover.getPosition().getY() + ") to (X:" + nextRoverPosition.getX()
            + ", to Y:" + nextRoverPosition.getY() + ")");
    boolean nextPositionOccupied = plateau.isPositionOccupied(nextRoverPosition);

    //Wait until next position is free to take

    long start = currentTimeMillis();
    long end = start + 3000;

    while (nextPositionOccupied) {

      if (currentTimeMillis() > end) {
        throw new IllegalStateException("A rover is not moving from that position");
      }

      nextPositionOccupied = plateau.isPositionOccupied(nextRoverPosition);

    }

    plateau.updateRoverPosition(rover, nextRoverPosition);
  }

}
