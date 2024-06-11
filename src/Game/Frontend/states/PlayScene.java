package src.Game.Frontend.states;

import src.Game.Backend.Camera;
import src.Game.Backend.Players.Player;

import java.awt.*;

public class PlayScene extends Scene{
    private Player player;
    public PlayScene(Camera camera) {
        super(camera);

        System.out.println("Inside Play");

    }

   public void paintComponent(Graphics g) {
        super.paintComponent(g);


   }
}
