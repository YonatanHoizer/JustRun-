package Game.Frontend.states;

import Game.Utils.Constans;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Animation implements ActionListener {
    private Timer timer;
    private List<BloodDrop> bloodDrops;
    private Random random;

    public Animation() {


        timer = new Timer(100, this);
        bloodDrops = new ArrayList<>();
        random = new Random();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        BloodDrop newDrop = new BloodDrop(random.nextInt(Constans.WINDOW_WIDTH), 0);
        bloodDrops.add(newDrop);

        bloodDrops.removeIf(drop -> drop.getY() > Constans.WINDOW_HEIGHT);

        for (BloodDrop drop : bloodDrops) {
            drop.update();
        }
    }

    public void draw(Graphics g) {
        List<BloodDrop> copy = new ArrayList<>(bloodDrops);
        for(BloodDrop b : copy) {
            b.draw(g);
        }

    }

    private class BloodDrop {
        private final int x;
        private int y;
        private final int speed;

        public BloodDrop(int x, int y) {
            this.x = x;
            this.y = y;
            this.speed = random.nextInt(4,7) ;
        }

        public int getY() {
            return y;
        }

        public void update() {
            y += speed;
        }

        public void draw(Graphics g) {
            g.setColor(Color.RED);
            g.fillOval(x, y, 8, 8);
        }
    }

    public void startAnimation() {
        timer.start();
    }
}
