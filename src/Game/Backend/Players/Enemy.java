package src.Game.Backend.Players;

import src.Game.Backend.Sprite;
import src.Game.Utils.Vector2f;

import java.util.ArrayList;

public class Enemy extends Player{
    private ArrayList<Sprite> enemies;
    public Enemy(Vector2f position, Sprite sprite) {
        super(position, sprite);
    }
}
