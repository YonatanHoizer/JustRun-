package Game.Frontend;

import Game.Backend.Camera;
import Game.Utils.Constans;
import Game.Utils.KeyHandler;
import Game.Utils.MoseHandler;
import Game.Utils.Vector2f;
import Game.Frontend.states.*;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;

public class MainWindow implements Runnable {
    private Scene panel;
    private JFrame frame;
    private final MoseHandler mouse;
    private boolean gameLoopIsRunning;
    private int currentScene;
    private KeyHandler key;

    public MainWindow() {
        mouse = new MoseHandler();
        key = new KeyHandler();
        gameLoopIsRunning = true;
        setUpWindow();
        currentScene = -1;
        changeState(1);

        frame.setVisible(true);
    }

    private void changeState(int state) {
        if (currentScene != state) {
            this.currentScene = state;
            frame.getContentPane().removeAll();
            switch (currentScene) {
                case 0 -> panel = new EditorScene(new Camera(new Vector2f()), mouse);
                case 1 -> panel = new MenuScene(new Camera(new Vector2f()), mouse);
                case 2 -> panel = new PlayScene(new Camera(new Vector2f()), key);
                default -> {
                    System.out.println("Unknown state: " + state);
                    return;
                }
            }
            frame.getContentPane().add(panel);
            frame.getContentPane().revalidate();
            frame.getContentPane().repaint();
        }
    }

    private void setUpWindow() {
        frame = new JFrame();
        frame.setTitle("Just-Run-");
        frame.setSize(Constans.WINDOW_WIDTH, Constans.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        frame.addKeyListener(key);
        frame.addMouseListener(mouse);
        frame.addMouseMotionListener(mouse);
        frame.setFocusable(true);


    }

    @Override
    public void run() {
        while (gameLoopIsRunning) {
            update();
            render();
            try {
                Thread.sleep(1000 / 60);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Release resources
        frame.dispose();
    }

    private void update() {
        changeState(panel.getScene());
        panel.update();
    }

    private void render() {
        BufferStrategy bs = frame.getBufferStrategy();
        if (bs == null) {
            frame.createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Constans.WINDOW_WIDTH, Constans.WINDOW_HEIGHT);
        panel.render(g);
        g.dispose();
        bs.show();
    }
}
