package hardwareAbstraction;

import batteryLogic.InteractionHandler;

import javax.swing.*;
import java.awt.event.*;

/**
 * This class handles button input for a GUI application.
 */
public class ButtonInput {
    private long pressStartTime;
    private final Timer inactivityTimer;

    /**
     * Creates instance of ButtonInput.
     * @param button the button to be monitored
     * @param handler the handler to process button press events
     */
    public ButtonInput(JButton button, InteractionHandler handler) {
        inactivityTimer = new Timer(5000, e -> handler.handleButtonPress("inactivity"));

        inactivityTimer.setRepeats(false);

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressStartTime = System.currentTimeMillis();
                inactivityTimer.stop();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                long duration = System.currentTimeMillis() - pressStartTime;
                if (duration >= 1000) {
                    handler.handleButtonPress("longPress");
                } else {
                    handler.handleButtonPress("shortPress");
                }
                inactivityTimer.restart();
            }
        });
    }
}
