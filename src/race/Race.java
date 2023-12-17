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

    public Weather getWeather() {
        return weather;
    }

    private double generateRaceSize() {
        Random random = new Random();
        double raceSize = MIN_TRACK_SIZE + (MAX_TRACK_SIZE - MIN_TRACK_SIZE + 1) * random.nextDouble();
        return raceSize;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public LinkedList<Car> getCars() {
        return cars;
    }

    private boolean carFinished() {
        int i = -1;
        while (++i < cars.size()) {
            if (cars.get(i).getPos() >= raceSize) {
                System.out.println(cars.get(i).getId() + " just won the race!");
                return true;
            }
        }
        return false;
    }

    public void startRace() {
        int i = 0;
        int temp = 0;
        while (!carFinished()) {
            temp = cars.get(i).getPos();
            cars.get(i).move();
            if ((cars.get(i).getPos() - temp) > 0)
                System.out.println("CarParts.Car with id: " + cars.get(i).getId() + " just moved " + (cars.get(i).getPos() - temp));
            i++;
            if (i >= cars.size())
                i = 0;
        }
    }
}
