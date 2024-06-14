package Game.ui;

import Game.Backend.BoardMap;
import Game.Backend.Camera;
import Game.Utils.Constans;
import Game.Utils.MoseHandler;
import Game.Utils.Vector2f;

import javax.swing.*;
import java.awt.*;

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
