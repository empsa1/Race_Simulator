package environment;

import SimulationElements.Barrier;
import SimulationElements.Car;

import java.io.Serializable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/** Main class for game representation.
 *
 * @author luismota
 *
 */
@SuppressWarnings("serial")
public class Cell implements Serializable{
    private BoardPosition position;
    private Car ocuppyingCar = null;
    private Barrier barrier=null;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public Barrier getBarrier() {
        return (barrier);
    }

    public Cell(BoardPosition position) {
        super();
        this.position = position;
    }

    public BoardPosition getPosition() {
        return position;
    }

    public boolean request(Car car) throws InterruptedException {
        lock.lock();
        try {
            if (ocuppyingCar == null && barrier == null) {
                ocuppyingCar = car;
                return true;
            }
        } finally {
            lock.unlock();
        }
        return false;
    }

    public void release() {
        lock.lock();
        try {
            if (ocuppyingCar != null ) {
                ocuppyingCar = null;
                condition.signalAll();
            }
        } finally {
            lock.unlock();
        }
    }

    public boolean isOcupiedByCar() {
        return ocuppyingCar!=null;
    }

    public  boolean setGameElement(Barrier barrier) {
        lock.lock();
        try {
            this.barrier = barrier;
        } finally {
            lock.unlock();
        }
        return true;
    }

    public boolean isOcupied() {
        return isOcupiedByCar() || (barrier!=null && barrier instanceof Barrier);
    }

    public Car getOcuppyingCar() {
        return ocuppyingCar;
    }
    public void removeObstacle() {
        lock.lock();
        try {
            if (barrier != null)
                barrier = null;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}

