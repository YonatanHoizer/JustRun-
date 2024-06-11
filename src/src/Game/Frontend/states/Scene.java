package src.Game.Frontend.states;

import src.Game.Backend.BoardMap;
import src.Game.Backend.Camera;
import src.Game.Utils.MoseHandler;

import javax.swing.*;
import java.awt.*;

public abstract class Scene extends JPanel {
    ;
    private BoardMap ground;
    private GameSceneManager manager;
    private Camera camera;

    protected Scene(Camera camera) {
        this.camera = camera;
        this.setBackground(Color.WHITE);
        //manager = new GameSceneManager(new MoseHandler());


        //JOptionPane.showMessageDialog(null,"message", "", JOptionPane.QUESTION_MESSAGE);
    }




    public void paintComponent(Graphics g) {
        super.paintComponent(g);
       // manager.renderState(g);
        //repaint();
    }

}
