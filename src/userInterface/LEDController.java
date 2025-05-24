package userInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Controller of a simple LED panel that can be used to represent the state of a battery.
 */
public class LEDController {
    private static final int WARNING_DELAY = 500;
    private static final int FULL_CHARGE_DELAY = 1000;
    private final LEDPanel led;
    private final Timer blinkTimer;
    private boolean blinkState;
    private Color blinkColor;

    LEDController() {
        this.led = new LEDPanel();
        blinkState = false;
        blinkColor = Color.ORANGE;

        blinkTimer = new Timer(WARNING_DELAY, e -> {
            blinkState = !blinkState;
            led.setLEDState(blinkColor, blinkState);
        });
    }

    LEDPanel getLedPanel(){
        return led;
    }

    void controlLED(LEDMode mode) {
        switch (mode) {
            case OFF:
                turnOff();
                stopBlinking();
                break;
            case WARNING:
                blinkTimer.setDelay(WARNING_DELAY);
                startBlinking(Color.RED);
                break;
            case CHARGING:
                turnOn();
            case FULL_CHARGE:
                blinkTimer.setDelay(FULL_CHARGE_DELAY);
                startBlinking(Color.BLUE);
                break;
        }
    }

    private void turnOn() {
        stopBlinking();
        led.setLEDState(Color.YELLOW, true);
    }

    private void turnOff() {
        stopBlinking();
        led.setLEDState(Color.DARK_GRAY, false);
    }

    private void startBlinking(Color color) {
        if (blinkTimer.isRunning()) {
            return; 
        }
        blinkState = false;
        blinkColor = color;
        blinkTimer.start();
    }

    private void stopBlinking() {
        blinkTimer.stop();
        blinkState = false;
    }
}
