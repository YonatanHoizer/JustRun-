import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.event.*;
import java.util.Random;

public class character extends JPanel {
    public int x = 0, y = 0;
    public int imageX = 320;
    public int imageY = 250;
    private camera camera;
    public boolean isMovingR, isMovingU, isMovingL, isMovingD = false;
    private BufferedImage[] rightImages;
    private BufferedImage[] leftImages;
    private BufferedImage[] upImages;
    private BufferedImage[] downImages;
    private BufferedImage currentImage;
    private int index = 0;
    public Direction direction = Direction.RIGHT;
    private Timer timer;
    private  zombie zombie;
    private zombie[] zombies;
    private Random r = new Random();

    private enum Direction {
        RIGHT, LEFT, UP, DOWN
    }

    public character(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        zombie = new zombie(0, 0, this);
        zombies = new zombie[10];
        for(int i =0; i < zombies.length; ++i) {
            zombies[i] = new zombie(r.nextInt(-180,820), r.nextInt(-250,750), this);
        }
        addKeyListener(new KeyAdapter(this) {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveRight();
                        break;
                    case KeyEvent.VK_UP:
                        moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        moveDown();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        setMovingL(false);
                        break;
                    case KeyEvent.VK_RIGHT:
                        setMovingR(false);
                        break;
                    case KeyEvent.VK_UP:
                        setMovingU(false);
                        break;
                    case KeyEvent.VK_DOWN:
                        setMovingD(false);
                        break;
                }
            }
        });

        camera = new camera(this);

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

        } catch (IOException e) {
            System.out.println("Error: " + e);
        }

        currentImage = rightImages[0];

        setBackground(new Color(getPixels()));

        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateImage();
                zombie.updateZombie(zombie.zombieMoving());
                zombie.updateZombieMoving();
                camera.update();
                repaint();
            }
        });
        timer.start();

        setFocusable(true);
        requestFocusInWindow();
    }

    private void updateImage() {
        switch (direction) {
            case RIGHT:
                currentImage = rightImages[index];
                break;
            case LEFT:
                currentImage = leftImages[index];
                break;
            case UP:
                currentImage = upImages[index];
                break;
            case DOWN:
                currentImage = downImages[index];
                break;
        }
        index = (index + 1) % 4;
    }

    public void moveLeft() {
        isMovingL = true;
        direction = Direction.LEFT;
    }

    public void moveRight() {
        isMovingR = true;
        direction = Direction.RIGHT;
    }

    public void moveUp() {
        isMovingU = true;
        direction = Direction.UP;
    }

    public void moveDown() {
        isMovingD = true;
        direction = Direction.DOWN;
    }

    public int getPixels() {
        return currentImage.getRGB(0, 0);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int rectX = camera.getCameraX(0);
        int rectY = camera.getCameraY(0);
        g.fillRect(rectX, rectY, 50, 50);

        for(zombie z : zombies) {
            z.draw(g);
            z.updateZombie(z.zombieMoving());
            z.updateZombieMoving();
        }

        g.drawImage(currentImage, imageX, imageY, 40, 40, null);
    }

    public void setMovingR(boolean movingR) {
        isMovingR = movingR;
    }

    public void setMovingU(boolean movingU) {
        isMovingU = movingU;
    }

    public void setMovingL(boolean movingL) {
        isMovingL = movingL;
    }

    public void setMovingD(boolean movingD) {
        isMovingD = movingD;
    }

    public int getImageX() {
        return imageX;
    }

    public int getImageY() {
        return imageY;
    }
}
