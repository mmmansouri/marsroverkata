package org.mmm.marsroverkata.domain;

import java.util.Objects;

public class Position {
    private Integer x;
    private Integer y;

    public Position(){}

    public Position(Integer x, Integer y){
        this.x=x;
        this.y=y;
    }

    public Position(int x, int y, Direction orientation){
        this.x=new Integer(x);
        this.y=new Integer(y);
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    public void incrementY(){
        this.y+=1;
    }
    public void incrementX(){
        this.x+=1;
    }

    public void decrementY(){
        this.y-=1;

    }
    public void decrementX(){
        this.x-=1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x.equals(position.x) &&
                y.equals(position.y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }


    @Override
    public String toString() {
        return "Position{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }
}
