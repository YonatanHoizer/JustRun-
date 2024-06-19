package Game.Backend;

import Game.Utils.Constans;
import Game.Utils.MoseHandler;
import Game.Backend.Players.Character;

import java.awt.event.MouseEvent;

public class CameraControls {
    private float prevMx, prevMy;
    private MoseHandler mouse;
    private Character character;
    private Camera camera;
    public CameraControls(Camera camera, MoseHandler mouse) {
        this.mouse = mouse;
        this.camera = camera;
    }

    public CameraControls(Camera camera, Character character) {
        this.camera = camera;
        this.character = character;
    }

    public void update(int mapWidth, int mapHeight) {
        if(mouse != null) {
            if (mouse.pressed && mouse.button == MouseEvent.BUTTON1 && mouse.isDragging()) {
                float dx = mouse.x + mouse.dx - prevMx;
                float dy = mouse.y + mouse.dy - prevMy;

                camera.position.x -= dx;
                camera.position.y -= dy;
            }
            prevMx = mouse.x + mouse.dx;
            prevMy = mouse.y + mouse.dy;
        }
        else {
            camera.update(character.getX(), character.getY());


            int mapWidthInPixels = mapWidth *  70;
            int mapHeightInPixels = mapHeight * 70;

            int maxCameraX = mapWidthInPixels - Constans.WINDOW_WIDTH;
            int maxCameraY = mapHeightInPixels - Constans.WINDOW_HEIGHT;

            if (camera.position.x < 0) {
                camera.position.x = 0;
            }else if (camera.position.x > maxCameraX) {
                camera.position.x = maxCameraX;
            }

            if (camera.position.y < 0) {
                camera.position.y = 0;
            } else if (camera.position.y > maxCameraY) {
                camera.position.y = maxCameraY;
            }
        }
    }

}
