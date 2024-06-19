package Game.Backend;

import Game.Utils.Vector2f;

import java.awt.*;
import java.util.Random;

public class Coin {
    private final Vector2f position;

    public Coin(Vector2f position) {
        this.position = position;
    }

    public void draw(Graphics g, Camera camera) {
        g.setColor(Color.orange);
        int drawX = (int)position.x - (int) camera.position.x;
        int drawY = (int)position.y - (int) camera.position.y;
        g.fillOval(drawX, drawY, 20, 20);
    }

    public Vector2f getPosition() {
        return position;
    }
}
