package src.Game.ui;

import src.Game.Backend.BoardMap;
import src.Game.Backend.Camera;
import src.Game.Utils.Constans;
import src.Game.Utils.MoseHandler;
import src.Game.Utils.Vector2f;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ImageHolder extends JPanel {

    public BoardMap board;
    private Camera camera;
    public ImageHolder(Camera camera, MoseHandler mouse) {
        setPreferredSize(new Dimension(Constans.WINDOW_WIDTH, Constans.WINDOW_HEIGHT / 4));
        setBackground(Color.PINK);
        setBorder(BorderFactory.createLineBorder(Color.RED));

        board = new BoardMap(new Vector2f(), "res/zombie_streets-tiles.jpg");
        this.camera = camera;

    }

    public void draw(Graphics g) {
        board.draw(g, camera);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(board.getTile(), 50, 50, 50, 50, null);
    }
}
