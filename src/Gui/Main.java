package Gui;

import environment.LocalBoard;

public class Main {
    public static void main(String[] args) {
        LocalBoard board=new LocalBoard();
        CarGui game = new CarGui(board,600,0);
        game.init();
    }
}
