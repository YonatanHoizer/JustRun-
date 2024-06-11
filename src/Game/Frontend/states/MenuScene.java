package src.Game.Frontend.states;

import src.Game.Backend.Camera;
import src.Game.Utils.Constans;
import src.Game.Utils.MoseHandler;

import javax.swing.*;
import java.awt.*;

public class MenuScene extends Scene {
    private MoseHandler mouse;

    private JLabel label;
    private JButton button;
    private JButton buttonStartGame;
    public MenuScene(Camera camera, MoseHandler mouse) {
        super(camera);
        this.mouse = mouse;
        System.out.println("Inside Menu");
        setUpMenu();
    }

    private void setUpMenu() {
        setLayout(null);
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        button = createRulesButton();
        label = createLabel();
        buttonStartGame = createStartButton();
        this.add(label);
        this.add(button);
        this.add(buttonStartGame);
        this.setBackground(Color.ORANGE);
        this.setFocusable(true);

    }

    private JButton createStartButton() {
        JButton start = new JButton("Start");
        start.setBounds(Constans.WINDOW_WIDTH /2 - 50/2,Constans.WINDOW_HEIGHT - 2*50, 100, 50);
        start.setFocusable(false);
        start.addActionListener((e) -> this.button.setVisible(!this.button.isVisible()));
        start.addActionListener((e) -> {
            this.buttonStartGame.setVisible(false);
            startGame();
        });
        start.setVisible(true);
        return start;
    }


    private void startGame() {
        // TODO: remove this
        //ground = new BoardMap("res/zombie_streets-tiles.jpg");

        repaint();
    }
    private JButton createRulesButton() {
        JButton button = new JButton();
        button.setText("?");
        button.setBounds(30, Constans.WINDOW_HEIGHT - 2*50, 70, 50);
        button.setFocusable(false);
        button.addActionListener((e) -> label.setVisible(!label.isVisible()));
        button.addActionListener((e) -> this.buttonStartGame.setVisible(!this.buttonStartGame.isVisible()));
        button.setVisible(true);
        return button;
    }

    // printing rules
    private String rules() {
        return "<html><center>Hi it is game Hide and Seek!<br/>" +
                "rules are very simple:<br/>" + "you have two players<br/>" +
                " player that write above his had <b>Hide</b><br/> need to hide he has" +
                " 10 sec. to hide <br/>another player have to find him to win!<br/><br/>" +
                " Good Luck!</center></html>";
    }
    private JLabel createLabel() {
        JLabel label = new JLabel();
        Font font = new Font("MV Boli", Font.PLAIN, 20);
        label.setText(rules());
        label.setBounds(150, 50, 600, 300);
        label.setAlignmentX(100);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(font);
        label.setVisible(false);
        return label;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

    }
}
