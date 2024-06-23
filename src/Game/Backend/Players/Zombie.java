package Game.Backend.Players;

import Game.Backend.Camera;
import Game.Utils.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

public class Zombie {
    private int x, y;
    private int SPEED = 2;
    private BufferedImage currentImage;
    private int imageIndex = 0;
    private BufferedImage[] rightImagesZ, leftImagesZ, downImagesZ, upImagesZ;
    private Direction direction = Direction.RIGHT;
    private Character character;
    private Random random = new Random();
    private static final int ZOMBIE_SIZE = 60;

    private enum Direction {
        RIGHT, LEFT, UP, DOWN
    }

    public Zombie(int zombieX, int zombieY, Character c) {
        this.x = zombieX;
        this.y = zombieY;
        this.character = c;

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

            currentImage = rightImagesZ[0];
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public void update() {
        updatePosition();
        updateImage();
    }

    private void updatePosition() {
        int targetX = character.getX();
        int targetY = character.getY();
        if (x > targetX) {
            x -= SPEED;
            direction = Direction.LEFT;
        }
        if (x < targetX) {
            x += SPEED;
            direction = Direction.RIGHT;
        }

        if (y > targetY) {
            y -= SPEED;
            direction = Direction.UP;
        }
        if (y < targetY) {
            y += SPEED;
            direction = Direction.DOWN;
        }
    }

    public void setSpeed(int speed) {
        this.SPEED = speed;
    }

    public int getSPEED() {return SPEED;}

    private void updateImage() {
        switch (direction) {
            case RIGHT -> currentImage = rightImagesZ[imageIndex];
            case LEFT -> currentImage = leftImagesZ[imageIndex];
            case UP -> currentImage = upImagesZ[imageIndex];
            case DOWN -> currentImage = downImagesZ[imageIndex];
        }
        imageIndex = (imageIndex + 1) % 4;
    }

    public void draw(Graphics g, Camera camera) {
        int drawX = x - (int) camera.position.x;
        int drawY = y - (int) camera.position.y;
        g.drawImage(currentImage, drawX, drawY, ZOMBIE_SIZE, ZOMBIE_SIZE, null);
    }

    public boolean catch_p(int charX, int charY, int charSize) {
        return charX < x + ZOMBIE_SIZE-25 &&
                charX + charSize - 25 > x &&
                charY < y + ZOMBIE_SIZE-25 &&
                charY + charSize - 25 > y;
    }

    @Override
    public String toString() {
        return "speed "+ SPEED;
    }
}
