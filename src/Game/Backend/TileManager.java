package src.Game.Backend;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

public class TileManager {
    private Tile WALL, FLOOR, FOOD;
    private BufferedImage image;
    private ArrayList<Tile> tiles = new ArrayList<>();


    public TileManager() {
        loadImage();
        createTile();
    }

    private void loadImage() {
        try {
            image = ImageIO.read(getClass().getResource("res/zombie_streets-tiles.jpg"));
            int imageWidth = image.getWidth();
            System.out.println("Width: " + imageWidth/6);
            int imageHeight = image.getHeight();
            System.out.println("Height: " + imageHeight/4);
        }catch (IOException e) { e.printStackTrace(); }
    }

    public BufferedImage getImage(int id) {
        return tiles.get(id).getSprite();
    }
    private void createTile() {
        WALL = new Tile(getSubImage(2, 1));
        FLOOR = new Tile(getSubImage(1, 3));
        FOOD = new Tile(getSubImage(0,0) );
        tiles.add(WALL);
        tiles.add(FLOOR);
        tiles.add(FOOD);
    }

    private BufferedImage getSubImage(int x, int y) {
        return this.image.getSubimage(x * 130, y * 143, 120, 143);
    }

}
