package src.Game.Frontend;

import src.Game.Backend.Camera;
import src.Game.Utils.Constans;
import src.Game.Utils.KeyHandler;
import src.Game.Utils.MoseHandler;
import src.Game.Utils.Vector2f;
import src.Game.Frontend.states.*;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

public class MainWindow extends JPanel implements Runnable{
    private Scene panel;
    private JFrame frame;
    private KeyHandler key;
    private MoseHandler mouse;
    private boolean gameLoopIsRunning;

    public MainWindow() {
        key = new KeyHandler();
        mouse = new MoseHandler();
        gameLoopIsRunning = true;

        changeState(0);


        setUpWindow();
    }

    private void changeState(int state) {
        switch (state) {
            case 0:
                panel = new EditorScene(new Camera(new Vector2f()),  mouse);
                break;
            case 1:
                panel = new MenuScene(new Camera(new Vector2f()), mouse);
                break;
            case 2:
                panel = new PlayScene(new Camera(new Vector2f()));
                break;
        }
    }

    private void setUpWindow() {
        showWindow();
    }

    private void showWindow() {
        JFrame frame = new JFrame();
        frame.setTitle("Hide and Seek");
        frame.setSize(Constans.WINDOW_WIDTH, Constans.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.addKeyListener(key);
        frame.addMouseListener(mouse);
        frame.addMouseMotionListener(mouse);
        frame.setFocusable(true);

        frame.add(panel);
        frame.setVisible(true);

        this.frame = frame;
    }

    @Override
    public void run() {
        while(gameLoopIsRunning) {

            update();

            render();

        }
    }

    private void update() {

    }

    private void render() {
        BufferStrategy bs = frame.getBufferStrategy();
        if(bs == null) {
            frame.createBufferStrategy(3);
            return;
        }
    }


}
