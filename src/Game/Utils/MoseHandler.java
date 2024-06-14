package Game.Utils;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MoseHandler implements MouseListener, MouseMotionListener {
    public int x, y;
    public int dx, dy;
    public int button;
    public boolean pressed;
    private boolean dragging;

    public MoseHandler() {
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressed = true;
        this.button = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.dx = 0;
        this.dy = 0;
        this.dragging = false;
        this.pressed = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        dx = e.getX() - this.x;
        dy = e.getY() - this.y;
        this.dragging = true;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.x = e.getX();
        this.y = e.getY();
    }

    public boolean isDragging() {
        return dragging;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public int getButton() {
        return button;
    }

    @Override
    public String toString() {
        return "{ original: x= " + x + ", y= " + y + " }" + "\n" +
                "{ destination: dx= " + dx + ", dy=" + dy + " }";
    }
}

