package Game.Backend;

import Game.Utils.Constans;
import Game.Utils.MoseHandler;

import java.awt.*;

public class Grid {
    private Camera camera;
    public int gridWidth, gridHeight;
    private MoseHandler mouse;

    public Grid(Camera camera, int gridWidth, int gridHeight, MoseHandler mouse) {
        this.camera = camera;
        this.gridWidth = gridWidth;
        this.gridHeight = gridHeight;
        this.mouse = mouse;
    }

    public void render(Graphics g) {
        g.setColor(Color.BLACK);

        float startX = -camera.position.x % gridWidth;
        float startY = -camera.position.y % gridHeight;
        float bottom = Math.min(Constans.GROUND_Y - camera.position.y, Constans.WINDOW_HEIGHT);

        for (int x = (int)startX; x < Constans.WINDOW_WIDTH; x += gridWidth) {
            g.drawLine(x, 0, x, (int)bottom);
        }

        for (int y = (int)startY; y < bottom; y += gridHeight) {
            g.drawLine(0, y, Constans.WINDOW_WIDTH, y);
        }
    }


}
