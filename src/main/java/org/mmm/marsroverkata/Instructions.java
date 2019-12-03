package org.mmm.marsroverkata;

import java.util.Collections;
import java.util.List;

public class Instructions {

    private final Rover rover;
    private final List<Command> commandList;

    public Instructions(Rover rover, List<Command> commandList) {
        this.rover = rover;
        this.commandList = commandList;
    }

    public Rover getRover() {
        //TODO : clone
        return rover;
    }

    public List<Command> getCommandList() {
        return Collections.unmodifiableList(commandList);
    }
}
