package userInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Controller of a simple LED panel that can be used to represent the state of a battery.
 */
class LEDController implements UIConstants {
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
        applyMode(mode);
        currentMode = mode;
    }

    private void applyMode(LEDMode mode) {
        switch (mode) {
            case WARNING -> blink(WARNING_DELAY, Color.RED);
            case CHARGING -> blink(CHARGING_DELAY, Color.YELLOW);
            case FULL_CHARGE -> turnOn(Color.BLUE);
            case UNDERVOLTAGE -> turnOn(Color.RED);
        }
    }

    private void blink(int delay, Color color) {
        blinkTimer.setDelay(delay);
        startBlinking(color);
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
