import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class zombie {
    int zombieX, zombieY;
    private BufferedImage[] rightImagesZ;
    private BufferedImage[] leftImagesZ;
    private BufferedImage[] upImagesZ;
    private BufferedImage[] downImagesZ;
    private BufferedImage currentImageZ;
    private int index = 0;
    private Direction directionZ = Direction.RIGHT;
    private character c ;
    private zombie arrZ[] = new zombie[10];

    private enum Direction {
        RIGHT, LEFT, UP, DOWN
    }

    public zombie(int zombieX, int zombieY, character c) {
        this.zombieX = zombieX;
        this.zombieY = zombieY;
        this.c = c;

        try {
            rightImagesZ = new BufferedImage[]{
                    ImageIO.read(getClass().getResource("zombie/zombieR1.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieR2.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieR3.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieR2.png")),
            };
            leftImagesZ = new BufferedImage[]{
                    ImageIO.read(getClass().getResource("zombie/zombieL1.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieL2.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieL3.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieL2.png")),
            };
            downImagesZ = new BufferedImage[]{
                    ImageIO.read(getClass().getResource("zombie/zombieD1.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieD2.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieD1.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieD3.png")),
            };
            upImagesZ = new BufferedImage[]{
                    ImageIO.read(getClass().getResource("zombie/zombieU1.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieU2.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieU1.png")),
                    ImageIO.read(getClass().getResource("zombie/zombieU3.png")),
            };

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        currentImageZ = rightImagesZ[0];
    }

    public int getPixels() {
        return currentImageZ.getRGB(0, 0);
    }

    public Direction zombieMoving() {
        int checkCollision = 0;
        int targetX = c.imageX;
        int targetY = c.imageY;
        if (zombieX > targetX) {
            zombieX -= 4;
            directionZ = Direction.LEFT;
        }
        if (zombieX < targetX) {
            zombieX += 4;
            directionZ = Direction.RIGHT;
        }
        else {
            checkCollision += 1;
        }
        if (zombieY > targetY) {
            zombieY -= 4;
            directionZ = Direction.UP;
        }
        if (zombieY < targetY) {
            zombieY += 4;
            directionZ = Direction.DOWN;
        }
        else {
            checkCollision += 1;
        }
        return directionZ;
    }

    public void updateZombieMoving(){

        if (c.isMovingR){
            zombieX -= 10;
        }
        if (c.isMovingL){
            zombieX += 10;
        }
        if (c.isMovingU){
            zombieY += 10;
        }
        if (c.isMovingD){
            zombieY -= 10;
        }
    }


    public void draw(Graphics g) {
        g.drawImage(currentImageZ,zombieX,zombieY,60,60,null);
    }

    public void updateZombie(Direction directionZ) {
        switch (directionZ) {
            case RIGHT:
                currentImageZ = rightImagesZ[index];
                break;
            case LEFT:
                currentImageZ = leftImagesZ[index];
                break;
            case UP:
                currentImageZ = upImagesZ[index];
                break;
            case DOWN:
                currentImageZ = downImagesZ[index];
                break;
        }
        index = (index + 1) % 4;
    }
}


















