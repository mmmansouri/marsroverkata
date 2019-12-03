package org.mmm.marsroverkata.domain;

import java.util.Collections;
import java.util.List;

public class Scanner {

    private final Rover rover;
    private final List<Command> instructions;

    public Scanner(Rover rover, List<Command> instructions) {
        this.rover = rover;
        this.instructions = instructions;
    }

    public Rover getRover() {
        return rover;
    }

    public List<Command> getInstructions() {
        return Collections.unmodifiableList(instructions);
    }
}
