package Game.Backend.Players;

import Game.Backend.Sprite;
import Game.Utils.Vector2f;

import java.util.ArrayList;

public class Enemy extends Player{
    private ArrayList<Sprite> enemies;
    public Enemy(Vector2f position, Sprite sprite) {
        super(position, sprite);
    }
}
