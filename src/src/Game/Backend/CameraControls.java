package src.Game.Backend;

import com.sun.security.jgss.GSSUtil;
import src.Game.Utils.MoseHandler;

import java.awt.event.MouseEvent;

public class CameraControls {
    private float prevMx, prevMy;
    private MoseHandler mouse;
    private Camera camera;
    public CameraControls(Camera camera, MoseHandler mouse) {
        this.mouse = mouse;
        this.camera = camera;
    }

    public void update() {
        if(mouse.pressed && mouse.button == MouseEvent.BUTTON1 && mouse.isDragging()) {
            float dx = mouse.x + mouse.dx - prevMx;
            float dy = mouse.y + mouse.dy - prevMy;

            camera.position.x -= dx;
            camera.position.y -= dy;
        }
        prevMx = mouse.x + mouse.dx;
        prevMy = mouse.y + mouse.dy;
    }

}
