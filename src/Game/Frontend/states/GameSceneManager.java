package Game.Frontend.states;

import Game.Utils.MoseHandler;

import java.awt.*;

public class GameSceneManager {
    private Scene gameScene;
    private MoseHandler mouse;

    public GameSceneManager(Scene scene, MoseHandler mouse) {
        this.gameScene = scene;
        this.mouse = mouse;
    }
}
