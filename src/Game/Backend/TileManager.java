package Game.Backend;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TileManager {
//    private Tile WALL, FLOOR, FOOD;
//    private Tile M_Road;
    private BufferedImage[] images;
    private ArrayList<Tile> tiles = new ArrayList<>();


    public TileManager() {
        loadImage();
        createTile();
    }

    private void loadImage() {
        images = new BufferedImage[8];
        try {
            // 0 1 6 10, 11 9 7 12

            images[0] = ImageIO.read(Objects.requireNonNull(getClass().getResource("res/An_road.jpg")));
            images[1] = ImageIO.read(Objects.requireNonNull(getClass().getResource("res/F_road.jpg")));
            images[4] = ImageIO.read(Objects.requireNonNull(getClass().getResource("res/BC_road.jpg")));
            images[5] = ImageIO.read(Objects.requireNonNull(getClass().getResource("res/RC_road.jpg")));
            images[6] = ImageIO.read(Objects.requireNonNull(getClass().getResource("res/LC_road.jpg")));
            images[2] = ImageIO.read(Objects.requireNonNull(getClass().getResource("res/RR_road.jpg")));

            images[3] = ImageIO.read(Objects.requireNonNull(getClass().getResource("res/N_road.jpg")));
            images[7] = ImageIO.read(Objects.requireNonNull(getClass().getResource("res/WW_road.jpg")));



        }catch (IOException e) { e.printStackTrace(); }
    }

    public BufferedImage getImage(int id) {
        return tiles.get(id).getSprite();
    }
    private void createTile() {
        for(BufferedImage b : images) {
            tiles.add(new Tile(b));
        }
    }

}
