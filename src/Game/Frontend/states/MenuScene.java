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
        ImageIcon icon = new ImageIcon("Logo.jpg");
        logo.setIcon(icon);
        logo.setBounds(Constans.WINDOW_WIDTH / 2 - 400 / 2, 50, 400, 200);
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
        JButton start = new JButton("Start");
        start.setBounds(Constans.WINDOW_WIDTH / 2 - 200 / 2, 300, 200, 50);
        start.setFocusable(false);
        start.setBackground(Color.BLACK);
        start.setForeground(Color.BLUE);
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

    private void startGame() {
        this.scene = 2;
        repaint();
    }

    private JButton createRulesButton() {
        JButton button = new JButton();
        button.setText("?");
        button.setBackground(Color.BLACK);
        button.setForeground(Color.BLUE);
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

    private String rules() {
        return "<html><center>Hi it is game Hide and Seek!<br/>" +
                "rules are very simple:<br/>" + "you have two players<br/>" +
                " player that write above his head <b>Hide</b><br/> need to hide he has" +
                " 10 sec. to hide <br/>another player have to find him to win!<br/><br/>" +
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

    @Override
    public void scene_type(int scene) {
        this.scene = scene;
    }

    private String getScoreText() {
        if(PlayScene.getInfo() == null) return null;

        return PlayScene.getInfo().toString().substring(0, 11);
    }

    private String getLevelText() {
        if(PlayScene.getInfo() == null) return null;

        return PlayScene.getInfo().toString().substring(11);
    }

    public int getScene() {
        return scene;
    }
}
