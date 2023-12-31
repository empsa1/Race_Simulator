package Gui;

import environment.Board;
import environment.LocalBoard;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CarGui implements Observer {
    public static final int BOARD_WIDTH = 800;
    public static final int BOARD_HEIGHT = 800;
    public static final int NUM_COLUMNS = 40;
    public static final int NUM_ROWS = 30;
    private JFrame frame;
    private BoardComponent boardGui;
    private Board board;

    public CarGui(Board board, int x, int y) {
        super();
        this.board=board;
        frame= new JFrame("Race Simulator");
        frame.setLocation(x, y);
        buildGui();
    }

    private void buildGui() {
        frame.setLayout(new BorderLayout());
        boardGui = new BoardComponent(board);
        boardGui.setPreferredSize(new Dimension(BOARD_WIDTH, BOARD_HEIGHT));
        frame.add(boardGui,BorderLayout.CENTER);
        JButton resetObstaclesButton=new JButton("Force PitStop");
        resetObstaclesButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO
            }

        });
        frame.add(resetObstaclesButton,BorderLayout.SOUTH);


        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void init() {
        frame.setVisible(true);
        board.addObserver(this);
        board.init();
    }

    @Override
    public void update(Observable o, Object arg) {
        boardGui.repaint();
    }
}

