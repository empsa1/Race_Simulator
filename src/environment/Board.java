package environment;

import SimulationElements.Car;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public abstract class Board extends Observable {
    protected Cell[][] cells;
    private BoardPosition goalPosition;
    public static final long PLAYER_PLAY_INTERVAL = 100;
    public static final long REMOTE_REFRESH_INTERVAL = 200;
    public static final int NUM_COLUMNS = 30;
    public static final int NUM_ROWS = 30;
    protected LinkedList<Car> cars = new LinkedList<Car>();
    protected boolean isFinished;

    public Board() {
        cells = new Cell[NUM_COLUMNS][NUM_ROWS];
        for (int x = 0; x < NUM_COLUMNS; x++) {
            for (int y = 0; y < NUM_ROWS; y++) {
                cells[x][y] = new Cell(new BoardPosition(x, y));
            }
        }

    }
    public Cell getCell(BoardPosition cellCoord) {
        return cells[cellCoord.x][cellCoord.y];
    }
    protected BoardPosition getRandomPosition() {
        return new BoardPosition((int) (Math.random() *NUM_ROWS),(int) (Math.random() * NUM_ROWS));
    }
    public BoardPosition getGoalPosition() {
        return goalPosition;
    }
    public void setGoalPosition(BoardPosition goalPosition) {
        this.goalPosition = goalPosition;
    }
    public List<BoardPosition> getNeighboringPositions(Cell cell) {
        ArrayList<BoardPosition> possibleCells=new ArrayList<BoardPosition>();
        BoardPosition pos=cell.getPosition();
        if(pos.x>0)
            possibleCells.add(pos.getCellLeft());
        if(pos.x<NUM_COLUMNS-1)
            possibleCells.add(pos.getCellRight());
        if(pos.y>0)
            possibleCells.add(pos.getCellAbove());
        if(pos.y<NUM_ROWS-1)
            possibleCells.add(pos.getCellBelow());
        return possibleCells;

    }
    public LinkedList<Car> getCars() {
        return cars;
    }
    @Override
    public void setChanged() {
        super.setChanged();
        notifyObservers();
    }
    public abstract void init();

    public void addSnake(Car snake) {
        cars.add(snake);
    }
}