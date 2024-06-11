package src.Game.Backend.Players;

import java.awt.*;
import java.awt.image.BufferedImage;
import src.Game.Backend.Sprite;
import src.Game.Utils.Vector2f;

public class Player {
    private Sprite spriteSheet;
    private BufferedImage[][] playerMoves;
    private Vector2f position;

    public Player(Vector2f position, Sprite sprite) {
        this.position = position;
        this.spriteSheet = sprite;

        for(int y = 0; y < 4; ++y) {
            for(int x = 0; x< 5; ++x) {
                playerMoves[y][x] = spriteSheet.loadSprite(x * 100, y * 100, 100, 100);
            }
        }
    }

    public void drawPlayer(Graphics g) {
        
    }
}
