package environment;

import SimulationElements.AutomaticCar;
import SimulationElements.Car;
import distributions.Normal;

public class LocalBoard extends Board {

    private static final int NUM_CARS = 2;

    private void createTrack() {

    }
    public LocalBoard() {
        System.out.println("Building local board");
        designTrack();
        System.out.println("Finished loading barriers");
        for (int i = 0; i < NUM_CARS; i++) {
            AutomaticCar car = new AutomaticCar(i, this, new Normal());
            cars.add(car);
        }
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
