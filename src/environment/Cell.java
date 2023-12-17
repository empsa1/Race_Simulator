package environment;

import SimulationElements.Car;

public class Cell {
    private BoardPosition position;
    private Car ocuppyingCar = null;

    public Cell(BoardPosition position) {
        super();
        this.position = position;
    }

    public BoardPosition getPosition() {
        return position;
    }

    public void request(Car car)
            throws InterruptedException {
        //TODO coordination and mutual exclusion
        ocuppyingCar=car;
    }

    public void release() {
        //TODO
    }

    public boolean isOcupiedByCar() {
        return ocuppyingCar!=null;
    }

    public boolean isOcupied() {
        return isOcupiedByCar();
    }


    public Car getOcuppyingCar() {
        return ocuppyingCar;
    }
}

