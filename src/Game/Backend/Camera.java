package Game.Backend;

import Game.Backend.Players.Character;
import Game.Utils.Constans;
import Game.Utils.Vector2f;

public class Camera {
    private Character character;
    public Vector2f position;

    public Camera(Vector2f position, Character character) {
        this.position = position;
        this.character = character;
    }

    public Camera(Vector2f position) {
        this.position = position;
    }

    public void update(int charX, int charY) {
        position.x = charX - (float)Constans.WINDOW_WIDTH / 2;
        position.y = charY - (float)Constans.WINDOW_HEIGHT / 2;
    }

}
