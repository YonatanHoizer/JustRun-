package Game.Backend;

import Game.Frontend.states.EditorScene;
import Game.Utils.MoseHandler;
import Game.Utils.Vector2f;

import java.awt.*;
import java.awt.event.MouseEvent;

public class DragAndDrop {
    private BoardMap image = null;
    private MoseHandler mouse;
    private Camera camera;
    private EditorScene scene;
    private boolean dragging;
    private final float debounceTime = 2.0f;
    private float debounceLeft = 0.0f;

    public DragAndDrop(BoardMap image, MoseHandler mouse, Camera camera, EditorScene scene) {
        this.image = image;
        this.mouse = mouse;
        this.camera = camera;
        this.scene = scene;
    }

    public void update() {
        if (this.image != null) {
            // Рассчитываем позицию для выравнивания на сетке
            int gridSize = 100;
            float dx = (float) Math.floor((mouse.x + mouse.dx + camera.position.x) / gridSize) * gridSize;
            float dy = (float) Math.floor((mouse.y + mouse.dy + camera.position.y) / gridSize) * gridSize;


            image.position.x = dx;
            image.position.y = dy;



            dragging = false;
            debounceLeft -= 0.1;
            if (mouse.isDragging() && mouse.getButton() == MouseEvent.BUTTON1) {
                dragging = false;
            } else if (!mouse.isDragging() && mouse.pressed && !dragging && debounceLeft < 0) {
                debounceLeft = debounceTime;
                dragging = true;
                BoardMap image2 = image.copy();
                image2.position = new Vector2f(dx, dy);
                scene.addTileToMap(image2);
                this.image = image2;

            }
        }
    }


    public void setImage(BoardMap image) {
        this.image = image;
    }

    public BoardMap getImage() {
        return image;
    }

    public void draw(Graphics g) {
        if (image != null) {
            // Рисуем изображение с учетом смещения камеры
            g.drawImage(image.getTile(), (int) image.position.x, (int) image.position.y, 100, 100, null);
        }
    }
}
