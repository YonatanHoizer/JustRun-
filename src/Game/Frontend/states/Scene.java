package Game.Frontend.states;

import Game.Backend.BoardMap;
import Game.Backend.Camera;

import javax.swing.*;
import java.awt.*;

public abstract class Scene extends JPanel {
    private BoardMap ground;
    private GameSceneManager manager;
    private Camera camera;

    protected Scene(Camera camera) {
        this.camera = camera;
        this.setBackground(Color.BLACK);
    }

    public abstract void update();

    public  void render(Graphics g) {

    }

    public void paintComponent(Graphics g) {}


    public abstract int getScene();

}
