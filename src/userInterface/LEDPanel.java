package userInterface;

import javax.swing.*;
import java.awt.*;

/**
 * A simple LED panel that can be used to represent the state of a battery.
 */
class LEDPanel extends JPanel implements UIConstants {
    private Color ledColor = Color.DARK_GRAY;

    LEDPanel() {
        setPreferredSize(new Dimension(SIZE, SIZE));
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
