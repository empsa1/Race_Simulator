package environment;

import SimulationElements.Car;
import distributions.Normal;

public class LocalBoard extends Board {

    private static final int NUM_CARS = 2;



    public LocalBoard() {

        for (int i = 0; i < NUM_CARS; i++) {
            Car snake = new Car(i, this,new Normal());
            cars.add(snake);
        }
    }

    public void init() {
        for(Car s:cars)
            s.start();
        setChanged();
    }
}
