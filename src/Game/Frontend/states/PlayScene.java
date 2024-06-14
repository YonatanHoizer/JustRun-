package Game.Frontend.states;

import Game.Backend.Camera;
import Game.Backend.CameraControls;
import Game.Backend.Players.Player;
import Game.Backend.TileManager;

import java.awt.*;
import java.util.Random;

public class PlayScene extends Scene{
    private Player player;
    private CameraControls cameraControls;
    private final TileManager tileManager;
    private final int [][] ints_map;
    private int scene;
    private Camera camera;
    public PlayScene(Camera camera) {
        super(camera);
        this.camera = camera;


        cameraControls = new CameraControls(camera);
        tileManager = new TileManager();
        ints_map = buildLevel();

       this.scene = 2;
    }

   public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //cameraControls.update();

       for(int y =0; y < ints_map.length; ++y) {
           for(int x =0 ; x < ints_map[y].length; ++x) {
               int id = ints_map[y][x];
               int drawX = (int)(x * 70 - camera.position.x);
               int drawY = (int)(y * 70 - camera.position.y);
               g.drawImage(tileManager.getImage(id), drawX, drawY, 100, 100, null);
           }
       }

   }


    public static int [][] buildLevel() {
        int[][] level = new int[100][100];
        for (int y = 0; y < level.length; ++y) {
            for (int x = 0; x < level[0].length; ++x) {
                if (y == 0 || y == level.length - 1) {
                    level[y][x] = 2;
                } else if (x == 0 || x == level.length - 1) {
                    level[y][x] = 1;
                } else {
                    level[y][x] = getRandomWeightedValue(new Random());
                }
            }
        }
        level[0][0] = 0;
        level[0][level.length - 1] = 5;
        level[level.length - 1][0] = 6;
        level[level.length - 1][level.length - 1] = 4;

        return level;
    }

    private static int getRandomWeightedValue(Random random) {
        int[] weightedValues = {3 ,3,  3, 3, 3, 7};
        return weightedValues[random.nextInt(weightedValues.length)];
    }

    @Override
    public void scene_type(int scene) {
        this.scene = scene;
    }

    public int getScene() {
        return scene;
    }
}
