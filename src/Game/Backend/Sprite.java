package Game.Backend;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Sprite {
    private BufferedImage image;
    private int imageWidth;
    private int imageHeight;
    public Sprite(String file_path){
        try {
            image = ImageIO.read(getClass().getResource(file_path));
            imageWidth = image.getWidth();
            System.out.println("Width: " + imageWidth);
            imageHeight = image.getHeight();
            System.out.println("Height: " + imageWidth);
        }catch (IOException e) { e.printStackTrace(); }
    }



    public BufferedImage loadSprite(int x, int y, int w , int h) {
        return image.getSubimage(x, y, w, h);
    }
}
