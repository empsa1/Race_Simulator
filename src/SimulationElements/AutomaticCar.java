package SimulationElements;

import com.sun.jndi.ldap.Ber;
import distributions.*;
import environment.Board;
import race.Race;

public class AutomaticCar extends Car{
    public AutomaticCar(int id, Board board, Distribution dist) {
        super(id, board, dist);
    }

    private int summonValues() {
        Distribution dist = getDist();
        if (dist instanceof Bernoulli)
            if (Bernoulli.generateRandom(0.5))
            return 1;
        if (dist instanceof Binomial)
            return Binomial.generateRandom(10, 0.5);
        if (dist instanceof Geometric)
            return Geometric.generateRandom(0.5);
        if (dist instanceof HyperGeometric)
            return HyperGeometric.generateRandom(10,20,10);
        if (dist instanceof Poisson)
            return Poisson.generateRandom(5);
        if (dist instanceof Pascal)
            return Pascal.generateRandom(5,0.5);
        return 0;
    }

    @Override
    public void run() {
        doInitialPositioning();
        int i = 0;
        int speed = 0;
        while (this.getNumberOfLaps() < Race.NUMBER_OF_LAPS) {
            speed = summonValues();
            while (speed > 0) {
                if (i < 16) {
                    try {
                        getBoard().getCell(this.getCells().getLast().getPosition().getCellRight()).request(this);
                        cells.add(getBoard().getCell(this.getCells().getLast().getPosition().getCellRight()));
                        this.getCells().getFirst().release();
                        cells.removeFirst();
                        i++;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (i < 34) {
                    try {
                        getBoard().getCell(this.getCells().getLast().getPosition().getCellBelow()).request(this);
                        cells.add(getBoard().getCell(this.getCells().getLast().getPosition().getCellBelow()));
                        this.getCells().getFirst().release();
                        cells.removeFirst();
                        i++;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (i < 53) {
                    try {
                        getBoard().getCell(this.getCells().getLast().getPosition().getCellLeft()).request(this);
                        cells.add(getBoard().getCell(this.getCells().getLast().getPosition().getCellLeft()));
                        this.getCells().getFirst().release();
                        cells.removeFirst();
                        i++;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (i < 71) {
                    try {
                        getBoard().getCell(this.getCells().getLast().getPosition().getCellAbove()).request(this);
                        cells.add(getBoard().getCell(this.getCells().getLast().getPosition().getCellAbove()));
                        this.getCells().getFirst().release();
                        cells.removeFirst();
                        i++;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else if (i < 74) {
                    try {
                        getBoard().getCell(this.getCells().getLast().getPosition().getCellRight()).request(this);
                        cells.add(getBoard().getCell(this.getCells().getLast().getPosition().getCellRight()));
                        this.getCells().getFirst().release();
                        cells.removeFirst();
                        i++;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    this.incrementLap();
                    System.out.println((this.getIdentification() + " has finished another lap and is now on: " + this.getNumberOfLaps()));
                    i = 0;
                }
                speed--;
                getBoard().setChanged();
                try {
                    this.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            try {
                this.sleep(300);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.err.println(this.getIdentification() + " finished the race!" + getBoard().getGoalPosition());
    }
}
