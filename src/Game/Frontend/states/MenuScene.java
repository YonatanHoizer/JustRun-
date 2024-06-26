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
    private JLabel infoScoreLabel;
    private JLabel infoLevelLabel;

    public MenuScene(Camera camera, MoseHandler mouse) {
        super(camera);
        this.mouse = mouse;
        setUpMenu();
    }

    private void setUpMenu() {
        setLayout(null);
        setPreferredSize(new Dimension(Constans.WINDOW_WIDTH, Constans.WINDOW_HEIGHT));
        button = createRulesButton();
        label = createLabel();
        buttonStartGame = createStartButton();
        logo = createLogo();

        infoScoreLabel = createInfoLabel(getScoreText(), 100, 100);
        infoLevelLabel = createInfoLabel(getLevelText(), Constans.WINDOW_WIDTH/2 + 400, 100);
        animation = new Animation();
        this.add(infoScoreLabel);
        this.add(infoLevelLabel);
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
        ImageIcon icon = new ImageIcon("zombie Logo3.jpg");
        logo.setIcon(icon);
        logo.setBounds(Constans.WINDOW_WIDTH / 2 - 220, 50, 500, 250);
        logo.setVisible(true);
        return logo;
    }

    private JLabel createInfoLabel(String text, int x, int y) {
        if(PlayScene.getInfo() == null) return new JLabel();

        JLabel info = new JLabel();
        Font font = new Font("MV Boli", Font.PLAIN, 20);
        info.setText(text);
        info.setBounds(x ,y, 500, 50);
        info.setForeground(Color.RED);
        info.setFont(font);
        info.setVisible(true);
        return info;
    }

    private JButton createStartButton() {
        GlowingButton start = new GlowingButton("Start game");
        start.setBounds(Constans.WINDOW_WIDTH / 2 - 200 / 2, 300, 200, 50);
        start.setFocusable(false);
        start.setBackground(Color.black);
        start.setForeground(Color.red);
        start.addActionListener((e) -> {
            this.button.setVisible(!this.button.isVisible());
            this.buttonStartGame.setVisible(false);
            this.infoScoreLabel.setVisible(!infoScoreLabel.isVisible());
            this.infoLevelLabel.setVisible(!infoLevelLabel.isVisible());
            startGame();
        });
        start.setVisible(true);
        return start;
    }

    private JButton createRulesButton() {
        GlowingButton button = new GlowingButton("game explanation");
        button.setText("game explanation");
        button.setBackground(Color.black);
        button.setForeground(Color.red);
        button.setBounds(Constans.WINDOW_WIDTH / 2 - 200 / 2, 400, 200, 50);
        button.setFocusable(false);
        button.addActionListener((e) -> label.setVisible(!label.isVisible()));
        button.addActionListener((e) -> {
            this.buttonStartGame.setVisible(!this.buttonStartGame.isVisible());
            this.logo.setVisible(!this.logo.isVisible());
        });
        button.setVisible(true);
        return button;
    }

    private void startGame() {
        this.scene = 2;
        repaint();
    }

    private String rules() {
        return "<html><center>Hi it is game Just-Run!<br/>" +
                "rules are very simple:<br/>" + "you have player and zombie<br/>" +
                " you need to <b>Just-Run</b><br/> and you can collect<br/>" +
                " some coins to win you need<b> 60 </b>coins!<br/><br/>" +
                " Good Luck!</center></html>";
    }

    private JLabel createLabel() {
        JLabel label = new JLabel();
        Font font = new Font("MV Boli", Font.PLAIN, 20);
        label.setText(rules());
        label.setBounds(Constans.WINDOW_WIDTH / 2 - 400 / 2, 50, 600, 300);
        label.setForeground(Color.CYAN);
        label.setFont(font);
        label.setVisible(false);
        return label;
    }

    @Override
    public void update() {
    }

    @Override
    public void render(Graphics g) {
        animation.draw(g);
        paintComponents(g);
    }

    private String getScoreText() {
        if(PlayScene.getInfo() == null) return null;

        return PlayScene.getInfo().toString().substring(0, 10);
    }

    private String getLevelText() {
        if(PlayScene.getInfo() == null) return null;

        return PlayScene.getInfo().toString().substring(10);
    }

    public int getScene() {
        return scene;
    }
}
