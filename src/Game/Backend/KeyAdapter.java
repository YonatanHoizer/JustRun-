import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyAdapter implements KeyListener {
    private character c;

    public KeyAdapter(character c) {
        this.c = c;

    }
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_LEFT:
                c.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                c.moveRight();
                break;
            case KeyEvent.VK_UP:
                c.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                c.moveDown();
                break;

        }
        c.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // לא נדרש כאן
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // לא נדרש כאן
    }
}
