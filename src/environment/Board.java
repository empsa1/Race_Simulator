package environment;

import SimulationElements.Barrier;
import SimulationElements.Car;
import race.Weather;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

public abstract class Board extends Observable {
    protected Cell[][] cells;
    private BoardPosition goalPosition;
    public static final int NUM_COLUMNS = 30;
    public static final int NUM_ROWS = 30;
    protected LinkedList<Car> cars = new LinkedList<Car>();
    public LinkedList<Barrier> barriers= new LinkedList<Barrier>();
    public static final int FIRST_EVER = 5;
    public static int CURRENT_POS = 5;
    public static int TRACK_WIDTH = 3; //number - 1

    private Weather weather;

    public static int getFirstPos() {
        CURRENT_POS++;
      return (CURRENT_POS - 1);
    }
    public Board() {
        this.weather = new Weather();
        cells = new Cell[NUM_COLUMNS][NUM_ROWS];
        for (int x = 0; x < NUM_COLUMNS; x++) {
            for (int y = 0; y < NUM_ROWS; y++) {
                cells[x][y] = new Cell(new BoardPosition(x, y));
            }
        }

    }

    public Weather getWeather() {
        return weather;
    }

    public Cell getCell(BoardPosition cellCoord) {
        return cells[cellCoord.x][cellCoord.y];
    }

    public BoardPosition getGoalPosition() {
        return goalPosition;
    }

    public void designTrack() {
        int i = Board.TRACK_WIDTH;
        while (i < Board.NUM_COLUMNS - Board.TRACK_WIDTH) {
            BoardPosition bp = new BoardPosition(i, Board.FIRST_EVER);
            Barrier barrier = new Barrier(this);
            getBarriers().add(barrier);
            this.getCell(bp).setGameElement(barrier);
            i++;
        }
        int temp = Board.FIRST_EVER;
        i--;
        while (temp < Board.NUM_COLUMNS - Board.TRACK_WIDTH) {
            BoardPosition bp = new BoardPosition(i, temp);
            Barrier barrier = new Barrier(this);
            getBarriers().add(barrier);
            this.getCell(bp).setGameElement(barrier);
            temp++;
        }
        temp = Board.FIRST_EVER;
        while (temp < Board.NUM_COLUMNS - Board.TRACK_WIDTH) {
            BoardPosition bp = new BoardPosition(Board.TRACK_WIDTH, temp);
            Barrier barrier = new Barrier(this);
            getBarriers().add(barrier);
            this.getCell(bp).setGameElement(barrier);
            temp++;
        }
        i = Board.TRACK_WIDTH;
        while (i < Board.NUM_COLUMNS - Board.TRACK_WIDTH) {
            BoardPosition bp = new BoardPosition(i, temp);
            Barrier barrier = new Barrier(this);
            getBarriers().add(barrier);
            this.getCell(bp).setGameElement(barrier);
            i++;
        }


        i = Board.TRACK_WIDTH * 2 + 1;
        while (i < Board.NUM_COLUMNS - Board.TRACK_WIDTH * 2 - 1) {
            BoardPosition bp = new BoardPosition(i, Board.FIRST_EVER + Board.TRACK_WIDTH + 1);
            Barrier barrier = new Barrier(this);
            getBarriers().add(barrier);
            this.getCell(bp).setGameElement(barrier);
            i++;
        }

        i--;
        temp = Board.FIRST_EVER + Board.TRACK_WIDTH + 1;
        while (temp < Board.NUM_COLUMNS - Board.TRACK_WIDTH * 2) {
            BoardPosition bp = new BoardPosition(i, temp);
            Barrier barrier = new Barrier(this);
            getBarriers().add(barrier);
            this.getCell(bp).setGameElement(barrier);
            temp++;
        }
        temp--;
        i = Board.TRACK_WIDTH * 2 + 1;
        while (i < Board.NUM_COLUMNS - Board.TRACK_WIDTH * 2 - 1) {
            BoardPosition bp = new BoardPosition(i, temp);
            Barrier barrier = new Barrier(this);
            getBarriers().add(barrier);
            this.getCell(bp).setGameElement(barrier);
            i++;
        }

        temp = Board.FIRST_EVER + Board.TRACK_WIDTH + 1;
        while (temp < Board.NUM_COLUMNS - Board.TRACK_WIDTH * 2) {
            BoardPosition bp = new BoardPosition(Board.TRACK_WIDTH * 2 + 1, temp);
            Barrier barrier = new Barrier(this);
            getBarriers().add(barrier);
            this.getCell(bp).setGameElement(barrier);
            temp++;
        }
    }

    private LinkedList<Barrier> getBarriers() {
        return barriers;
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

    public abstract void handleKeyPress(int keyCode);

    public abstract void handleKeyRelease();

    public void addCar(Car car) {
        cars.add(car);
    }
}