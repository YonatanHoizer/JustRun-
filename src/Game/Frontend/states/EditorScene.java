package Game.Frontend.states;

import Game.Backend.*;
import Game.Backend.DragAndDrop;
import Game.Utils.Constans;
import Game.Utils.MoseHandler;
import Game.ui.ImageHolder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class EditorScene extends Scene {
    private final int GRID_WIDTH = 100;
    private final int GRID_HEIGHT = 100;
    private CameraControls cameraControls;
    private Grid grid;
    private DragAndDrop drop;
    private MoseHandler mouse;
    private Camera camera;
    private ArrayList<BoardMap> maps;
    private ImageHolder holder;
    private final TileManager tileManager;
    private final int [][] ints_map;
    private JButton play_scene;
    private int scene;

    public EditorScene(Camera camera, MoseHandler mouse) {
        super(camera);
        setLayout(null);
        this.mouse = mouse;
        this.camera = camera;

//        play_scene = new JButton();
//        play_scene.setBounds(20, 20, 70, 30);
//        play_scene.setText(" Start ");
//        play_scene.addActionListener((e) -> System.out.println("Hello"));
//        play_scene.setForeground(Color.BLUE);

     //   add(play_scene);
        // maps = new ArrayList<>();
     //  holder = new ImageHolder(camera, mouse);
       // setLayout(new BorderLayout());
        //add(holder, BorderLayout.SOUTH);

        cameraControls = new CameraControls(camera, mouse);

//        grid = new Grid(camera, GRID_WIDTH, GRID_HEIGHT, mouse);
//        drop = new DragAndDrop(holder.board, mouse, camera, this);

        tileManager = new TileManager();
        ints_map = buildLevel();

        this.scene = 0;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        g.drawRect(0,0, Constans.WINDOW_WIDTH, Constans.WINDOW_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
          cameraControls.update(0, 0);
//        grid.render(g);



        // NOTE: this is second prototype of editor is simpler
        for(int y =0; y < ints_map.length; ++y) {
            for(int x =0 ; x < ints_map[y].length; ++x) {
                int id = ints_map[y][x];
                int drawX = (int)(x * 70 - camera.position.x);
                int drawY = (int)(y * 70 - camera.position.y);
                g.drawImage(tileManager.getImage(id), drawX, drawY, 100, 100, null);
            }
        }





// second option
//        holder.draw(g);
//
//        drop.draw(g);
//
//        drop.update();
//
//        for(BoardMap map : maps) {
//            map.draw(g, camera);
//        }


        try {
            Thread.sleep(10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        repaint();
    }



    // second option to build editor scene
    public static int [][] buildLevel() {
        int[][] level = new int[50][50];
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

    // need for the first option of editor scene
    public  ArrayList<BoardMap> getMap() {
        return maps;
    }

    public int getScene() {return  scene;}
    public void addTileToMap(BoardMap map) {
        this.maps.add(map);
    }
}
