package Game.Backend;

import Game.Utils.MoseHandler;

import java.awt.event.MouseEvent;

public class CameraControls {
    private float prevMx, prevMy;
    private MoseHandler mouse;
    private Camera camera;
    public CameraControls(Camera camera, MoseHandler mouse) {
        this.mouse = mouse;
        this.camera = camera;
    }

    public CameraControls(Camera camera) {
        this.camera = camera;
    }

    public void update() {
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
            camera.position.x -= 5;
            camera.position.y -= 5;

            prevMx = 5;
            prevMy = 5;

        }

    }

}
