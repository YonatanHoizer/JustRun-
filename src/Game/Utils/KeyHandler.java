package src.Game.Utils;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {


    private boolean keyCodes[] = new boolean[128];

    public KeyHandler() {}

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        keyCodes[e.getKeyCode()] = true;
    }

  //  public void setState(boolean state) {
  //      this.inState = state;
    //}

    @Override
    public void keyReleased(KeyEvent e) {
       keyCodes[e.getKeyCode()] = false;
    }

    public boolean isKeyPressed(int key) {
        return keyCodes[key];
    }

}
