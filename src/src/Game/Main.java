package src.Game;

import src.Game.Frontend.MainWindow;

public class Main {
    public static void main(String[] args) {
        MainWindow window = new MainWindow();
        Thread t = new Thread(window);
        t.start();

    }


}

