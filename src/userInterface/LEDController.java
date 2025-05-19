package userInterface;

import javax.swing.*;
import java.awt.*;

public class LEDController {
    private final LEDPanel led;
    private final Timer blinkTimer;
    private boolean blinkState;
    private Color blinkColor;

    LEDController() {
        this.led = new LEDPanel();
        blinkState = false;
        blinkColor = Color.ORANGE;

        blinkTimer = new Timer(500, e -> {
            blinkState = !blinkState;
            led.setLEDState(blinkColor, blinkState);
        });
    }

    LEDPanel getLedPanel(){
        return led;
    }

    void turnOn(Color color) {
        stopBlinking();
        led.setLEDState(color, true);
    }

    void turnOff() {
        stopBlinking();
        led.setLEDState(Color.DARK_GRAY, false);
    }

    void startBlinking() {
        if (blinkTimer.isRunning()) {
            return; 
        }
        blinkColor = Color.RED;
        blinkState = false;
        blinkTimer.start();
    }

    void stopBlinking() {
        blinkTimer.stop();
        blinkState = false;
    }
}
