package Game.Frontend.states;

import Game.Backend.Camera;
import Game.Backend.CameraControls;
import Game.Backend.PInfo;
import Game.Backend.TileManager;
import Game.Backend.Players.Character;
import Game.Utils.Constans;
import Game.Utils.KeyHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PlayScene extends Scene {
    private Character character;
    private Camera camera;
    private int scene;
    private final int[][] levelMap;
    private Random random = new Random();
    private static final int TILE_SIZE = 70;
    private TileManager tileManager;
    private CameraControls cameraControls;
    private KeyHandler key;
    private int level = 1;
    private static PInfo info;

    private Timer timer;
    private int remainingSeconds;

    public PlayScene(Camera camera, KeyHandler key) {
        super(camera);
        this.key = key;

        levelMap = buildLevel();
        character = new Character(random.nextInt(75, levelMap[0].length*70), random.nextInt(75, levelMap.length*70), levelMap);
        this.camera = camera;
        cameraControls = new CameraControls(camera, character);
        info = new PInfo(character.getScore(), level);
        this.setFocusable(true);
        this.requestFocus();
        tileManager = new TileManager();
        this.scene = 2;

        remainingSeconds = 10;
        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (remainingSeconds > 0) {
                    remainingSeconds--;
                } else {
                    level+= 1;
                    remainingSeconds = 10;
                    character.setSpeed();
                }
            }
        });
        timer.start();

        processKey();
    }

    private void processKey() {
        if (key.isKeyPressed(KeyEvent.VK_UP)) {
            character.moveUp(levelMap);
        } else if (key.isKeyPressed(KeyEvent.VK_DOWN)) {
            character.moveDown(levelMap);
        } else if (key.isKeyPressed(KeyEvent.VK_LEFT)) {
            character.moveLeft(levelMap);
        } else if (key.isKeyPressed(KeyEvent.VK_RIGHT)) {
            character.moveRight(levelMap);
        }
    }

    public void update() {
        info.setScore(character.getScore());
        info.setLevel(level);
        cameraControls.update(levelMap[0].length, levelMap.length);

        if (character.AABB(character.getX(), character.getY())) {
            showCustomDialog("You Lose :/", Color.RED);
            this.scene = 1;
        } else if (character.getScore() == 60) {
            showCustomDialog("You Win :)", Color.GREEN);
            this.scene = 1;
        }

        processKey();
    }
    private void showCustomDialog(String message, Color backgroundColor) {
        JDialog dialog = new JDialog((Frame) null, message, true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawGlowingText((Graphics2D) g, message, getWidth() / 2, getHeight() / 2);
            }
        };
        panel.setBackground(backgroundColor);
        panel.setLayout(new BorderLayout());

        dialog.add(panel);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    private void drawGlowingText(Graphics2D g2d, String text, int x, int y) {
        Font font = new Font("Arial", Font.BOLD, 36);
        g2d.setFont(font);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        for (int i = 5; i > 0; i--) {
            g2d.setColor(new Color(255, 255, 255, 20 * i));
            g2d.drawString(text, x - g2d.getFontMetrics().stringWidth(text) / 2, y - g2d.getFontMetrics().getAscent() / 2 + i + 30);
        }

        g2d.setColor(Color.black);
        g2d.drawString(text, x - g2d.getFontMetrics().stringWidth(text) / 2 , y - g2d.getFontMetrics().getAscent() / 2 + 30);
    }


    @Override
    public void render(Graphics g) {
        int screenWidth = Constans.WINDOW_WIDTH;
        int screenHeight = Constans.WINDOW_HEIGHT;
        int tilesX = screenWidth / TILE_SIZE + 2;
        int tilesY = screenHeight / TILE_SIZE + 2;

        int startX = (int) (camera.position.x / TILE_SIZE);
        int startY = (int) (camera.position.y / TILE_SIZE);

        for (int y = startY; y < startY + tilesY; y++) {
            for (int x = startX; x < startX + tilesX; x++) {
                if (x >= 0 && x < levelMap[0].length && y >= 0 && y < levelMap.length) {
                    int id = levelMap[y][x];
                    int drawX = x * TILE_SIZE - (int) camera.position.x;
                    int drawY = y * TILE_SIZE - (int) camera.position.y;
                    g.drawImage(tileManager.getImage(id), drawX, drawY, TILE_SIZE, TILE_SIZE, null);
                }
            }
        }

        character.draw(g, camera);
        // timer
        g.setColor(Color.RED);
        g.setFont(new Font("MV Boli", Font.PLAIN, 40));
        g.drawString("Level-Up: " + remainingSeconds, Constans.WINDOW_WIDTH/2 - 100/2, 70);
    }
    @Override
    public int getScene() {
        return scene;
    }

    public static int[][] buildLevel() {
        int[][] level = new int[50][50];
        for (int y = 0; y < level.length; ++y) {
            for (int x = 0; x < level[0].length; ++x) {
                if (y == 0 || y == level.length - 1) {
                    level[y][x] = 2;
                } else if (x == 0 || x == level.length - 1) {
                    level[y][x] = 1;
                } else {
                    level[y][x] = getRandomWeightedValue();
                }
            }
        }
        level[0][0] = 0;
        level[0][level.length - 1] = 5;
        level[level.length - 1][0] = 6;
        level[level.length - 1][level.length - 1] = 4;

        return level;
    }

    private static int getRandomWeightedValue() {
        int[] weightedValues = {3, 3, 3, 3, 3, 7};
        return weightedValues[new Random().nextInt(weightedValues.length)];
    }

    public static PInfo getInfo() {
        return info;
    }
}
