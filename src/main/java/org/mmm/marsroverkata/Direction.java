package org.mmm.marsroverkata;

public enum Direction {
    North("N"),
    South("S"),
    East("E"),
    West("W");

    Direction(String directionCode) {
        this.directionCode=directionCode;
    }

    private String directionCode;

    public String getDirection(){
        return directionCode;
    }

    public void setDirection(String directionCode){
        this.directionCode=directionCode;
    }
}
