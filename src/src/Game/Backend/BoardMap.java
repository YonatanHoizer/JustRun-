package src.Game.Backend;

import src.Game.Utils.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BoardMap {
    private BufferedImage tile;
    private int tileWidth;
    private int tileHeight;
    private Color backgroundColor = Color.DARK_GRAY;
    private String file;
    public Vector2f position;

    private void removeTrash() {
        int trash = 200;
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                Color color = new Color(tile.getRGB(x, y));
                if (color.getRed() > trash && color.getGreen() > trash && color.getBlue() > trash) {
                    tile.setRGB(x, y, backgroundColor.getRGB());
                }
            }
        }
    }

    public BoardMap(Vector2f position, String file) {
        this.position = position;
        this.file = file;
        try {
            tile = ImageIO.read(getClass().getResource(file));
            // TODO: don't using this wired
            tileWidth = tile.getWidth();
            tileHeight = tile.getHeight();

            tile = subTile(0, 0, 110, 110);
            removeTrash();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public BoardMap(Vector2f position) {
        this.position = position;
    }

    public void draw(Graphics g, Camera camera) {
        int drawX = (int) (position.x - camera.position.x);
        int drawY = (int) (position.y - camera.position.y);
        g.setColor(Color.BLACK);
        g.drawImage(tile, drawX, drawY, 100, 100, null);
    }

    private BufferedImage subTile(int x, int y, int w, int h) {
        return tile.getSubimage(5 * 70 + 175, 4 * 70 + 7, w, h);
    }

    public BoardMap copy() {
        return new BoardMap(position.copy(), file);
    }

    public BufferedImage getTile() {
        return tile;
    }

    @Override
    public String toString() {
        return position.toString();
    }
}
