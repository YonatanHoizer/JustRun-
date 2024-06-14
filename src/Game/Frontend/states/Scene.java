package Game.Frontend.states;

import Game.Backend.BoardMap;
import Game.Backend.Camera;

import javax.swing.*;
import java.awt.*;

public abstract class Scene extends JPanel {
    ;
    private BoardMap ground;
    private GameSceneManager manager;
    private Camera camera;

    protected Scene(Camera camera) {
        this.camera = camera;
        this.setBackground(Color.BLACK);
        //manager = new GameSceneManager(new MoseHandler());


        //JOptionPane.showMessageDialog(null,"message", "", JOptionPane.QUESTION_MESSAGE);
    }




    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       // manager.renderState(g);
        //repaint();
    }

    public abstract void scene_type(int scene);

    public abstract int getScene();

}
