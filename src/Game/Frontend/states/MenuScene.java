package Game.Frontend.states;

import Game.Backend.Camera;
import Game.Utils.Constans;
import Game.Utils.MoseHandler;

import javax.swing.*;
import java.awt.*;

public class MenuScene extends Scene {
    private MoseHandler mouse;
    private int scene;

    private JLabel label;
    private JButton button;
    private JButton buttonStartGame;
    private JLabel logo;
   private Animation animation;
    public MenuScene(Camera camera, MoseHandler mouse) {
        super(camera);
        this.mouse = mouse;

        setUpMenu();
    }

    private void setUpMenu() {
        setLayout(null);
        setPreferredSize(new Dimension(getWidth(), getHeight()));
        button = createRulesButton();
        label = createLabel();
        buttonStartGame = createStartButton();
        logo = createLogo();
        animation = new Animation();
        this.add(label);
        this.add(button);
        this.add(logo);
        this.add(buttonStartGame);
        this.setBackground(Color.BLACK);
        this.setFocusable(true);

        this.scene = 1;
        animation.startAnimation();

    }

    private JLabel createLogo() {
        JLabel logo = new JLabel();
        ImageIcon icon = new ImageIcon("Logo.jpg");
        logo.setIcon(icon);
        logo.setBounds(Constans.WINDOW_WIDTH / 2 - 400/2, 50, 400, 200);

        logo.setVisible(true);
        return logo;
    }

    private JButton createStartButton() {
        JButton start = new JButton("Start");
        start.setBounds(Constans.WINDOW_WIDTH /2 - 200/2,300, 200, 50);
        start.setFocusable(false);
        start.setBackground(Color.BLACK);
        start.setForeground(Color.BLUE);
        start.addActionListener((e) -> this.button.setVisible(!this.button.isVisible()));
        start.addActionListener((e) -> {
            this.buttonStartGame.setVisible(false);
            startGame();
        });
        start.setVisible(true);
        return start;
    }


    private void startGame() {
       this.scene = 2;

        repaint();
    }
    private JButton createRulesButton() {
        JButton button = new JButton();
        button.setText("?");
        button.setBackground(Color.BLACK);
        button.setForeground(Color.BLUE);
        button.setBounds(Constans.WINDOW_WIDTH /2 - 200/2,  400, 200, 50);
        button.setFocusable(false);
        button.addActionListener((e) -> label.setVisible(!label.isVisible()));
        button.addActionListener((e) -> {
            this.buttonStartGame.setVisible(!this.buttonStartGame.isVisible());
            this.logo.setVisible(!this.logo.isVisible());
        });
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
        label.setBounds(Constans.WINDOW_WIDTH/2 - 400/2, 50, 600, 300);
        label.setForeground(Color.CYAN);

        //label.setHorizontalTextPosition(JLabel.CENTER);
        //label.setVerticalAlignment(JLabel.CENTER);
        label.setFont(font);
        label.setVisible(false);
        return label;
    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        animation.draw(g);

        repaint();
    }

    @Override
    public void scene_type(int scene) {
        this.scene = scene;
    }

    public int getScene() {
      //  System.out.println("GetMenuScene " + scene);
        return  scene;
    }
}
