package org.mmm.marsroverkata;


import java.util.ArrayList;
import java.util.List;

import static java.lang.System.currentTimeMillis;
import static org.mmm.marsroverkata.Command.*;
import static org.mmm.marsroverkata.Direction.*;

public class App {




    public static void main(String[] args) throws InterruptedException {

        Plateau plateau=new Plateau(5,5);

        Position position=new Position(1,2);

        Rover rover=new Rover(position, North);
        List<Command> commands=new ArrayList<>();
        commands.add(TURN_LEFT);
        commands.add(MOVE_FORWARD);

        commands.add(TURN_LEFT);
        commands.add(MOVE_FORWARD);

        commands.add(TURN_LEFT);
        commands.add(MOVE_FORWARD);

        commands.add(TURN_LEFT);
        commands.add(MOVE_FORWARD);
        commands.add(MOVE_FORWARD);

        Instructions instructions1=new Instructions(rover,commands);


        Position position2=new Position(3,3);

        Rover rover2=new Rover(position2, East);

        List<Command> commands2=new ArrayList<>();
        commands2.add(MOVE_FORWARD);
        commands2.add(MOVE_FORWARD);
        commands2.add(TURN_RIGHT);


        commands2.add(MOVE_FORWARD);
        commands2.add(MOVE_FORWARD);
        commands2.add(TURN_RIGHT);


        commands2.add(MOVE_FORWARD);
        commands2.add(TURN_RIGHT);
        commands2.add(TURN_RIGHT);
        commands2.add(MOVE_FORWARD);


        Instructions instructions2=new Instructions(rover2,commands2);


        Runnable runnable1= ()->App.scanArea(plateau, instructions1);
        Runnable runnable2= ()->App.scanArea(plateau, instructions2);

        Thread thread1=new Thread(runnable1);
        thread1.start();

        Thread thread2=new Thread(runnable2);
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("Rover 1 :");
        System.out.println(instructions1.getRover().getOrientation().toString());
        System.out.println(instructions1.getRover().getPosition().getX());
        System.out.println(instructions1.getRover().getPosition().getY());

        System.out.println("Rover 2 :");
        System.out.println(instructions2.getRover().getOrientation().toString());
        System.out.println(instructions2.getRover().getPosition().getX());
        System.out.println(instructions2.getRover().getPosition().getY());
    }


    public static void scanArea(Plateau plateau,Instructions instructions){
        Rover rover=instructions.getRover();

        plateau.addRover(rover);


        Position nextRoverPosition=new Position(rover.getPosition().getX(),rover.getPosition().getY());

        instructions.getCommandList().forEach(a-> {
                    switch (rover.getOrientation()) {
                        case North:
                            switch(a){
                                case TURN_LEFT:rover.setOrientation(West);break;
                                case TURN_RIGHT:rover.setOrientation(East);break;
                                case MOVE_FORWARD:nextRoverPosition.incrementY();break;
                                default:break;
                            }
                            break;
                        case West:
                            switch(a){
                                case TURN_LEFT:rover.setOrientation(South);break;
                                case TURN_RIGHT:rover.setOrientation(North);break;
                                case MOVE_FORWARD:nextRoverPosition.decrementX();break;
                                default:break;
                            }
                            break;
                        case South:
                            switch(a){
                                case TURN_LEFT:rover.setOrientation(East);break;
                                case TURN_RIGHT:rover.setOrientation(West);break;
                                case MOVE_FORWARD:nextRoverPosition.decrementY();break;
                                default:break;
                            }
                            break;
                        case East:
                            switch(a){
                                case TURN_LEFT:rover.setOrientation(North);break;
                                case TURN_RIGHT:rover.setOrientation(South);break;
                                case MOVE_FORWARD:nextRoverPosition.incrementX();break;
                                default:break;
                            }
                            break;
                        default:break;
                    }

                    if(!nextRoverPosition.equals(rover.getPosition())) {
                        updateRoverPosition(plateau, rover, nextRoverPosition);
                    }


                }
        );

    }

    private static void updateRoverPosition(Plateau plateau, Rover rover, Position nextRoverPosition) {


        System.out.println(Thread.currentThread().getName()+" :updating rover position from X:"+rover.getPosition().getX()+" Y:"+rover.getPosition().getY()+" to X:"+nextRoverPosition.getX()+" to Y:"+nextRoverPosition.getY());
        boolean nextPositionOccupied = plateau.isPositionOccupied(nextRoverPosition);

        //Wait until next position is free to take

        long start = currentTimeMillis();
        long end = start + 3000;

        while (nextPositionOccupied ) {

            if(currentTimeMillis()>end) throw new IllegalStateException("A rover is not moving from that position");

            nextPositionOccupied = plateau.isPositionOccupied(nextRoverPosition);

        }

        plateau.updateRoverPosition(rover, nextRoverPosition);
    }
}
