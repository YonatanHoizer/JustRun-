package Game.Frontend.states;

import javax.swing.*;
import java.awt.*;

public class GlowingButton extends JButton {
    private Color glowColor = Color.WHITE;

    public GlowingButton(String text) {
        super(text);
        setContentAreaFilled(false);
        setFocusPainted(false);
        setFont(new Font("Arial", Font.BOLD, 20));
        setHorizontalAlignment(SwingConstants.CENTER);
        setVerticalAlignment(SwingConstants.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.setColor(getBackground());
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw glowing effect
        FontMetrics fm = g2d.getFontMetrics();
        int x = (getWidth() - fm.stringWidth(getText())) / 2;
        int y = (getHeight() + fm.getAscent()) / 2 - fm.getDescent();

        g2d.setColor(glowColor);
        g2d.drawString(getText(), x + 1, y + 1);
        g2d.drawString(getText(), x - 1, y - 1);
        g2d.drawString(getText(), x + 1, y - 1);
        g2d.drawString(getText(), x - 1, y + 1);

        g2d.setColor(getForeground());
        g2d.drawString(getText(), x, y);

        g2d.dispose();
    }
}
