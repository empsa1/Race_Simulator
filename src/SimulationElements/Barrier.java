package SimulationElements;

import java.io.Serializable;
import environment.Board;

@SuppressWarnings("serial")
public class Barrier {
    private Board board;
    public Barrier(Board board) {
        super();
        this.board = board;
    }
}