package environment;

import SimulationElements.AutomaticCar;
import SimulationElements.Car;
import distributions.*;

public class LocalBoard extends Board {

    private static final int NUM_CARS = 2;

    public LocalBoard() {
        designTrack();
        AutomaticCar car0 = new AutomaticCar(0, this, new Poisson());
        cars.add(car0);
        AutomaticCar car1 = new AutomaticCar(1, this, new Binomial());
        cars.add(car1);
        AutomaticCar car2 = new AutomaticCar(2, this, new Pascal());
        cars.add(car2);
    }

    public void init() {
        for(Car s:cars)
            s.start();
        setChanged();
    }

    @Override
    public void handleKeyPress(int keyCode) {

    }

    @Override
    public void handleKeyRelease() {

    }
}
