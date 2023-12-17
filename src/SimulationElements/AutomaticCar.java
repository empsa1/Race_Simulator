package SimulationElements;

import distributions.Distribution;
import environment.Board;

public class AutomaticCar extends Car{
    public AutomaticCar(int id, Board board, Distribution dist) {
        super(id, board, dist);
    }

    @Override
    public void run() {
        doInitialPositioning();
        System.err.println(this.getIdentification() + " has died!" + getBoard().getGoalPosition());
    }
}
