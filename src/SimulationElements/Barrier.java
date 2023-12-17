package SimulationElements;

package game;

import java.io.Serializable;
import environment.Board;

@SuppressWarnings("serial")
public class Barrier {
    @SuppressWarnings("unused")
    private Board board;
    public Barrier(Board board) {
        super();
        this.board = board;
    }

}