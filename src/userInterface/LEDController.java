package userInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Controller of a simple LED panel that can be used to represent the state of a battery.
 */
public class LEDController {
    private static final int WARNING_DELAY = 500;
    private static final int CHARGING_DELAY = 1000;
    private final LEDPanel led;
    private final Timer blinkTimer;
    private boolean blinkState;
    private Color blinkColor;
    private LEDMode currentMode = LEDMode.OFF;

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
        if (mode == currentMode) return;

        turnOff();

        switch (mode) {
            case OFF:
                break;
            case WARNING:
                blinkTimer.setDelay(WARNING_DELAY);
                startBlinking(Color.RED);
                break;
            case CHARGING:
                blinkTimer.setDelay(CHARGING_DELAY);
                startBlinking(Color.YELLOW);
                break;
            case FULL_CHARGE:
                turnOn(Color.BLUE);
                break;
            case UNDERVOLTAGE:
                turnOn(Color.RED);
        }

        currentMode = mode;
    }

    private void turnOn(Color color) {
        stopBlinking();
        led.setLEDState(color, true);
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
