package race;

import SimulationElements.Car;

import java.util.LinkedList;
import java.util.Random;

public class Race {

    public static int MIN_TRACK_SIZE = 5000;
    public static int MAX_TRACK_SIZE = 7000;
    private int raceSize;

    private Weather weather;
    private LinkedList<Car> cars;

    public Race() {
        this.cars = new LinkedList<Car>();
        this.raceSize = (int) generateRaceSize();
        this.weather = new Weather();
    }
    private double generateRaceSize() {
        Random random = new Random();
        double raceSize = MIN_TRACK_SIZE + (MAX_TRACK_SIZE - MIN_TRACK_SIZE + 1) * random.nextDouble();
        return raceSize;
    }

}
