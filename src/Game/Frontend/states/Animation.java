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


        timer = new Timer(100, this); // Интервал таймера
        bloodDrops = new ArrayList<>();
        random = new Random();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        // Создаем новую каплю крови и добавляем ее в список
        BloodDrop newDrop = new BloodDrop(random.nextInt(Constans.WINDOW_WIDTH), 0);
        bloodDrops.add(newDrop);

        // Удаляем капли крови, которые вышли за пределы экрана
        bloodDrops.removeIf(drop -> drop.getY() > Constans.WINDOW_HEIGHT);

        // Обновляем положение капель крови
        for (BloodDrop drop : bloodDrops) {
            drop.update();
        }
    }

    public void draw(Graphics g) {
        for(BloodDrop b :  bloodDrops) {
            b.draw(g);
        }


    }

    // Класс для представления капли крови
    private class BloodDrop {
        private int x;
        private int y;
        private int speed;

        public BloodDrop(int x, int y) {
            this.x = x;
            this.y = y;
            this.speed = random.nextInt(5) + 1;
        }

        public int getY() {
            return y;
        }

        public void update() {
            y += speed; // Обновляем положение капли
        }

        public void draw(Graphics g) {
            g.setColor(Color.RED); // Цвет крови
            g.fillOval(x, y, 5, 5); // Рисуем каплю крови
        }
    }

    public void startAnimation() {
        timer.start(); // Запускаем таймер
    }
}
