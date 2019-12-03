package org.mmm.marsroverkata;


import static org.mmm.marsroverkata.services.ScanAreaService.scanArea;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;
import org.mmm.marsroverkata.domain.Command;
import org.mmm.marsroverkata.domain.Direction;
import org.mmm.marsroverkata.domain.Scanner;
import org.mmm.marsroverkata.domain.Plateau;
import org.mmm.marsroverkata.domain.Position;
import org.mmm.marsroverkata.domain.Rover;

public class App {

  private final static Logger LOGGER = Logger.getLogger(App.class.getName());

  public static void main(String[] args) throws IOException {

    List<String> stringList = Files.lines(Paths.get(args[0])).collect(Collectors.toList());

    if (StringUtils.isEmpty(stringList.get(0))) {
      throw new IllegalStateException("Plateau size not found");
    }

    String[] size = stringList.get(0).split(" ");

    Plateau plateau = new Plateau(Integer.valueOf(size[0]), Integer.valueOf(size[1]));

    stringList.remove(0);

    List<Scanner> scannerList = new ArrayList<>();

    for (int i = 0; i < stringList.size(); i++) {
      String[] roverPosition = stringList.get(i).split(" ");
      String[] instructions = stringList.get(i + 1).split("");
      Rover rover = new Rover(
          new Position(Integer.valueOf(roverPosition[0]), Integer.valueOf(roverPosition[1])),
          Direction.valueOf(roverPosition[2]));
      List<Command> commandList = Arrays.stream(instructions).map(Command::valueOf).collect(
          Collectors.toList());
      scannerList.add(new Scanner(rover, commandList));
      i++;
    }

    scannerList.forEach(a -> {
      Runnable runnable = () -> scanArea(plateau, a);
      Thread thread = new Thread(runnable);
      thread.start();
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });

    LOGGER.info("Occupied Cells : " + plateau.getOccupiedCells().toString());

    scannerList.forEach(a -> LOGGER.info("Rover position " + a.getRover().toString()));


  }


}
