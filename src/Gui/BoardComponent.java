package Gui;

import SimulationElements.Barrier;
import SimulationElements.Car;
import SimulationElements.HumanCar;
import environment.LocalBoard;
import environment.Board;
import environment.BoardPosition;
import environment.Cell;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;

@SuppressWarnings("serial")
public class BoardComponent extends JComponent implements KeyListener {

    private Board board;
    private Image obstacleImage;

    public BoardComponent(Board board) {
        this.board = board;
        try {
            obstacleImage = ImageIO.read(getClass().getResourceAsStream("/resources/obstacle.png"));
        } catch (IOException e) {
            System.err.println("Image file not found: " + e.getMessage());
        }
        setFocusable(true);
        addKeyListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("Inside paintComponent");
        super.paintComponent(g);
        final double CELL_WIDTH = getHeight() / (double) CarGui.NUM_ROWS;

        for (int x = 0; x < LocalBoard.NUM_COLUMNS; x++) {
            for (int y = 0; y < LocalBoard.NUM_ROWS; y++) {
                Cell cell = board.getCell(new BoardPosition(x, y));
                Image image = null;
                if (cell.getBarrier() != null) {
                    Barrier barrier = (Barrier) cell.getBarrier();
                    image = obstacleImage;
                    g.setColor(Color.BLACK);
                    g.drawImage(image, (int) Math.round(cell.getPosition().x * CELL_WIDTH),
                            (int) Math.round(cell.getPosition().y * CELL_WIDTH),
                            (int) Math.round(CELL_WIDTH), (int) Math.round(CELL_WIDTH), null);
                }
                if (cell.isOcupiedByCar()) {
                    if (cell.getOcuppyingCar() instanceof HumanCar)
                        g.setColor(Color.ORANGE);
                    else
                        g.setColor(Color.LIGHT_GRAY);
                    g.fillRect((int) Math.round(cell.getPosition().x * CELL_WIDTH),
                            (int) Math.round(cell.getPosition().y * CELL_WIDTH),
                            (int) Math.round(CELL_WIDTH), (int) Math.round(CELL_WIDTH));
                }
            }
            g.setColor(Color.BLACK);
            g.drawLine((int) Math.round(x * CELL_WIDTH), 0, (int) Math.round(x * CELL_WIDTH),
                    (int) Math.round(LocalBoard.NUM_ROWS * CELL_WIDTH));
        }

        for (int y = 1; y < LocalBoard.NUM_ROWS; y++) {
            g.drawLine(0, (int) Math.round(y * CELL_WIDTH), (int) Math.round(LocalBoard.NUM_COLUMNS * CELL_WIDTH),
                    (int) Math.round(y * CELL_WIDTH));
        }

        for (Car s : board.getCars()) {
            if (s.getLength() > 0) {
                g.setColor(new Color(s.getIdentification() * 1000));
                ((Graphics2D) g).setStroke(new BasicStroke(5));
                BoardPosition prevPos = s.getPath().getFirst();
                for (BoardPosition coordinate : s.getPath()) {
                    if (prevPos != null) {
                        g.drawLine((int) Math.round((prevPos.x + .5) * CELL_WIDTH),
                                (int) Math.round((prevPos.y + .5) * CELL_WIDTH),
                                (int) Math.round((coordinate.x + .5) * CELL_WIDTH),
                                (int) Math.round((coordinate.y + .5) * CELL_WIDTH));
                    }
                    prevPos = coordinate;
                }
                ((Graphics2D) g).setStroke(new BasicStroke(1));
            }
        }
    }

    // Methods keyPressed and keyReleased will react to user pressing and releasing keys on the keyboard.
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT &&
                e.getKeyCode() != KeyEvent.VK_UP && e.getKeyCode() != KeyEvent.VK_DOWN) {
            System.out.println(e.getKeyCode());
            return; // ignore
        }
        board.handleKeyPress(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() != KeyEvent.VK_LEFT && e.getKeyCode() != KeyEvent.VK_RIGHT &&
                e.getKeyCode() != KeyEvent.VK_UP && e.getKeyCode() != KeyEvent.VK_DOWN)
            return; // ignore

        board.handleKeyRelease();
    }

    @Override
    public void keyTyped(KeyEvent arg0) {
        // ignore
    }
}