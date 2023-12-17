package SimulationElements;

import distributions.*;
import environment.Board;
import environment.BoardPosition;

import java.util.ArrayList;

public class Car extends Thread{
    private int id;
    private Board board;
    private Distribution dist;
    private double moveSpeed;
    private ArrayList<Tire> tires;      //0 --> FRONT LEFT; //1 --> FRONT RIGHT //2 --> BACK LEFT
    private BoardPosition pos;

    public Car(int id, Board board, Distribution dist) {
        this.id = id;
        this.board = board;
        this.dist = dist;
        this.pos = new BoardPosition();
        for (int i = 0; i < 3; i++)
            this.tires.add(new Tire(Tire.TireType.SOFT));
    }

    public Distribution getDist() {
        return dist;
    }

    public long getId() {
        return id;
    }

    public void setMoveSpeed(double newMoveSpeed) {
        moveSpeed = newMoveSpeed;
    }

    public BoardPosition getPos() {
        return pos;
    }
}
