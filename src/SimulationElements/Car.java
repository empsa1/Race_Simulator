package SimulationElements;

import distributions.*;
import environment.Board;
import environment.BoardPosition;
import environment.Cell;

import java.util.ArrayList;
import java.util.LinkedList;

public class Car extends Thread{
    private int id;
    private Board board;
    private Distribution dist;
    private double moveSpeed;
    private ArrayList<Tire> tires;      //0 --> FRONT LEFT; //1 --> FRONT RIGHT //2 --> BACK LEFT
    private BoardPosition pos;
    private static final int DELTA_SIZE = 10;
    protected LinkedList<Cell> cells = new LinkedList<Cell>();

    public Car(int id, Board board, Distribution dist) {
        this.id = id;
        this.board = board;
        this.dist = dist;
        this.tires = new ArrayList<>();
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

    public int getSize() {
        return 1;
    }

    public int getIdentification() {
        return id;
    }

    public int getLength() {
        return cells.size();
    }

    public LinkedList<Cell> getCells() {
        return cells;
    }

    public LinkedList<BoardPosition> getPath() {
        LinkedList<BoardPosition> coordinates = new LinkedList<BoardPosition>();
        for (Cell cell : cells) {
            coordinates.add(cell.getPosition());
        }
        return coordinates;
    }

    protected void doInitialPositioning() {
        int posX = 0;
        int posY = Board.getFirstPos();
        BoardPosition at = new BoardPosition(posX, posY);
        if (posY >= Board.TRACK_WIDTH)
            return ;
        if (board.getCell(at).isOcupied()) {
            doInitialPositioning();
        }
        else {
            try {
                board.getCell(at).request(this);
                cells.add(board.getCell(at));
            } catch (InterruptedException e) {
                System.out.println("There was an interruption while setting up initial car position!");
                System.exit(-1);
            }
        }
    }

    public Board getBoard() {
        return board;
    }
}
