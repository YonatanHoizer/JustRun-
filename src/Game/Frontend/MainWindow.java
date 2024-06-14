package Game.Frontend;

import Game.Backend.Camera;
import Game.Utils.Constans;
import Game.Utils.KeyHandler;
import Game.Utils.MoseHandler;
import Game.Utils.Vector2f;
import Game.Frontend.states.*;

import javax.swing.*;
import java.awt.image.BufferedImage;

public class MainWindow implements Runnable {
    private Scene panel;
    private JFrame frame;
    private final KeyHandler key;
    private final MoseHandler mouse;
    private boolean gameLoopIsRunning;
    private int currentScene;
    private BufferedImage image = new BufferedImage(Constans.WINDOW_WIDTH, Constans.WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);

    public MainWindow() {
        key = new KeyHandler();
        mouse = new MoseHandler();
        gameLoopIsRunning = true;
        // Changing the states
        setUpWindow();
        currentScene = - 1;
        changeState(1);

        frame.setVisible(true);
    }

    private void changeState(int state) {
        if (currentScene != state) {
            this.currentScene = state;
            switch (currentScene) {
                case 0:
                    panel = new EditorScene(new Camera(new Vector2f()), mouse);
                    frame.getContentPane().removeAll();
                    break;
                case 1:
                    panel = new MenuScene(new Camera(new Vector2f()), mouse);
                    frame.getContentPane().removeAll();
                    break;
                case 2:
                    panel = new PlayScene(new Camera(new Vector2f()));
                    frame.getContentPane().removeAll();
                    break;
                default:
                    System.out.println("Unknown state: " + state);
                    return;
            }
            frame.add(panel);
        }
    }

    private void setUpWindow() {
        frame = new JFrame(); // Use the class field
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
        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0;
        double delta = 0;
        int frames = 0;
        int updates = 0;
        while (gameLoopIsRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }
            frames++;

            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                updates = 0;
                frames = 0;
            }

        }
    }

    private void update() {
        changeState(panel.getScene()); // Use the current scene directly
        frame.revalidate();
        frame.repaint();
    }


}
