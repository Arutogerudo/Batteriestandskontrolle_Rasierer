package UserInterface;

import javax.swing.*;
import java.awt.*;

public class LEDPanel extends JPanel {
    private Color ledColor = Color.DARK_GRAY;

    LEDPanel() {
        setPreferredSize(new Dimension(16, 16));
        setOpaque(false);
    }

    void setLEDState(Color color, boolean on) {
        this.ledColor = on ? color : Color.DARK_GRAY;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(ledColor);
        g.fillOval(0, 0, getWidth(), getHeight());
        g.setColor(Color.BLACK);
        g.drawOval(0, 0, getWidth() - 1, getHeight() - 1);
    }
}
