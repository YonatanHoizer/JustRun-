package Game.Backend;

import Game.Utils.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Coin {
    private final Vector2f position;
    private Image coinImage;

    public Coin(Vector2f position) {
        this.position = position;
        //loadImage();
    }

   // private void loadImage() {
     //   try {
       //     coinImage = ImageIO.read(new File("gameCoin (2).jpg"));
       // } catch (IOException e) {
         //   e.printStackTrace();
       // }
   // }

    public void draw(Graphics g, Camera camera) {
        int drawX = (int)position.x - (int) camera.position.x;
        int drawY = (int)position.y - (int) camera.position.y;
        if (coinImage != null) {
            g.drawImage(coinImage, drawX, drawY, null);
        } else {
            g.setColor(Color.orange);
            g.fillOval(drawX, drawY, 20, 20);
        }
    }

    public Vector2f getPosition() {
        return position;
    }
}
