package Game.Backend.Players;

import Game.Backend.Camera;
import Game.Backend.Coin;
import Game.Utils.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Character {
    private int x;
    private int y;

    private BufferedImage currentImage;
    private BufferedImage[] rightImages;
    private BufferedImage[] leftImages;
    private BufferedImage[] upImages;
    private BufferedImage[] downImages;
    private Rectangle bounds;
    private int imageIndex = 0;
    private Direction direction = Direction.RIGHT;
    private Timer timer;
    private  List<Zombie> zombies;
    private List<Coin> coins;
    private Random random = new Random();
    private final int[][] levelMap;
    private static final int SPEED = 5;
    private int zSpeed = 3;
    private static final int CHARACTER_SIZE = 40;
    private static final int COINS_CAP = 100;
    private static final int ZOMBIE_CAP = 70;
    private int score = 0;
    private boolean isMovingFaster = false;

    private enum Direction {
        RIGHT, LEFT, UP, DOWN
    }

    public Character(int x, int y, int[][] levelMap) {
        this.x = x;
        this.y = y;
        this.levelMap = levelMap;
        this.bounds = new Rectangle(x, y, 40, 40);
        setupZombies();
        setupTimer();
        setUpCoins();

        try {
            rightImages = new BufferedImage[]{
                    ImageIO.read(getClass().getResource("res/imege R1.png")),
                    ImageIO.read(getClass().getResource("res/imege R2.png")),
                    ImageIO.read(getClass().getResource("res/imege R1.png")),
                    ImageIO.read(getClass().getResource("res/imege R3.png")),
            };
            leftImages = new BufferedImage[]{
                    ImageIO.read(getClass().getResource("res/imaga L1.png")),
                    ImageIO.read(getClass().getResource("res/imaga L2.png")),
                    ImageIO.read(getClass().getResource("res/imaga L1.png")),
                    ImageIO.read(getClass().getResource("res/imaga L3.png")),
            };
            downImages = new BufferedImage[]{
                    ImageIO.read(getClass().getResource("res/imaga D1.png")),
                    ImageIO.read(getClass().getResource("res/imaga D2.png")),
                    ImageIO.read(getClass().getResource("res/imaga D1.png")),
                    ImageIO.read(getClass().getResource("res/imaga D3.png")),
            };
            upImages = new BufferedImage[]{
                    ImageIO.read(getClass().getResource("res/image U1.png")),
                    ImageIO.read(getClass().getResource("res/image U2.png")),
                    ImageIO.read(getClass().getResource("res/image U1.png")),
                    ImageIO.read(getClass().getResource("res/image U3.png")),
            };

            currentImage = rightImages[0];

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    private void setupZombies() {
        zombies = new ArrayList<>();
        for (int i = 0; i < ZOMBIE_CAP; ++i) {
            zombies.add(new Zombie(random.nextInt(0, levelMap[0].length * 70), random.nextInt(0, levelMap.length * 70), this));
        }
    }

    private void setUpCoins() {
        coins = new ArrayList<>();
        int mapWidthInPixels = levelMap[0].length * 70;
        int mapHeightInPixels = levelMap.length * 70;
        for (int i = 0; i < COINS_CAP; ++i) {
            int x, y;
            do {
                x = random.nextInt(mapWidthInPixels);
                y = random.nextInt(mapHeightInPixels);
            } while (isCollidingWithWalls(x, y, levelMap));

            coins.add(new Coin(new Vector2f(x, y)));
        }
    }


    public Rectangle getBounds() {
        return bounds;
    }

    private void setupTimer() {
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateZombies();
                updateImage();

            }
        });
        timer.start();
    }

    private void updateZombies() {
        for (Zombie zombie : zombies) {
            zombie.update();
        }
    }

    public void updateImage() {
        switch (direction) {
            case RIGHT -> currentImage = rightImages[imageIndex];
            case LEFT -> currentImage = leftImages[imageIndex];
            case UP -> currentImage = upImages[imageIndex];
            case DOWN -> currentImage = downImages[imageIndex];
        }
        imageIndex = (imageIndex + 1) % 4;
    }

    public void moveLeft(int[][] levelMap) {

        if (!isCollidingWithWalls(x - SPEED, y, levelMap)) {
            x -= SPEED;
            direction = Direction.LEFT;
        }

    }

    public void moveRight(int[][] levelMap) {

        if (!isCollidingWithWalls(x + SPEED, y, levelMap)) {
            x += SPEED;
            direction = Direction.RIGHT;
        }
    }

    public void moveUp(int[][] levelMap) {
        if (!isCollidingWithWalls(x, y - SPEED, levelMap)) {
            y -= SPEED;
            direction = Direction.UP;
        }
    }

    public void moveDown(int[][] levelMap) {
        if (!isCollidingWithWalls(x, y + SPEED, levelMap)) {
            y += SPEED;
            direction = Direction.DOWN;
        }
    }

    private boolean isCollidingWithWalls(int newX, int newY, int[][] levelMap) {
        int tileX = newX / 70;
        int tileY = newY / 70;


        if (tileX < 0 || tileY < 0 || tileX >= levelMap[0].length || tileY >= levelMap.length) {
            return true;
        }


        return levelMap[tileY][tileX] == 1 || levelMap[tileY][tileX] == 2;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Graphics g, Camera camera) {
    int collectRadius = 40;
    int collectRadiusSquared = collectRadius * collectRadius;


    Iterator<Coin> iterator = coins.iterator();
    while(iterator.hasNext()) {
        Coin coin = iterator.next();
        int coinX = (int) coin.getPosition().x;
        int coinY = (int) coin.getPosition().y;


        double distanceSquared = Math.pow(x - coinX, 2) + Math.pow(y - coinY, 2);




        if (distanceSquared <= collectRadiusSquared) {
            iterator.remove();
            score++;
        } else {

            coin.draw(g, camera);
        }
    }

        int drawX = x - (int) camera.position.x;
        int drawY = y - (int) camera.position.y;
        g.drawImage(currentImage, drawX, drawY, CHARACTER_SIZE, CHARACTER_SIZE, null);

        for (Zombie zombie : zombies) {
            zombie.draw(g, camera);
            if(isMovingFaster) {
                isMovingFaster = false;
                zombie.setSpeed(zSpeed);
                zombies.set(random.nextInt(zombies.size()), zombie);
               // setNewZombie();
            }

        }
    }

    public void setNewZombie() {
        zombies.add(new Zombie(random.nextInt(0, levelMap[0].length * 70), random.nextInt(0, levelMap.length * 70), this));
    }

    public boolean AABB(int x, int y) {
        for (Zombie zombie : zombies) {
            if (zombie.catch_p(x, y, CHARACTER_SIZE)) {
                return true;
            }
        }
        return false;
    }
    public void setSpeed () {
        isMovingFaster = true;
        zSpeed = 2;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public  int getScore() {return score;}
}

