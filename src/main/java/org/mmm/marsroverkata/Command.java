package org.mmm.marsroverkata;

public enum Command {

    TURN_LEFT("L"),
    TURN_RIGHT("R"),
    MOVE_FORWARD("M");

    Command(String commandCode) {

        this.commandCode=commandCode;
    }


    //TODO: why need getter & setter on enum
    private String commandCode;

    public String getCommandCode() {
        return commandCode;
    }

    public void setCommandCode(String commandCode) {
        this.commandCode = commandCode;
    }
}
