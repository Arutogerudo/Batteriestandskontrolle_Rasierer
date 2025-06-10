package userInterface;

import javax.swing.*;

import batteryLogic.BatteryStateController;
import batteryLogic.DisplayStates;
import batteryLogic.InteractionHandler;
import hardwareAbstraction.ChargingDetection;
import hardwareAbstraction.ChargingStates;
import hardwareAbstraction.VoltageSensor;
import hardwareAbstraction.VoltageSimulator;

/**
 * This class is responsible for managing the visual output of the battery state.
 */
public class VisualOutputController {
    private final LEDController ledController;
    private final JLabel displayed;
    private final BatteryStateController batteryController;
    private final ChargingDetection chargingDetector;
    private ChargingStates previousChargingState = null;
    private final InteractionHandler handler;

    /**
     * Constructs a VisualOutputController to manage the visual output components,
     * such as display labels and LED indicators, based on battery state and user interactions.
     *
     * @param simulator the voltage simulator used to simulate battery voltage readings
     * @param handler the interaction handler managing user input or external events
     * @param batteryController the controller responsible for calculating battery state of charge and runtime
     */
    VisualOutputController(VoltageSimulator simulator, InteractionHandler handler, BatteryStateController batteryController) {
        displayed = new JLabel();
        ledController = new LEDController();
        this.batteryController = batteryController;
        chargingDetector = new ChargingDetection(simulator);
        this.handler = handler;
        double voltage = new VoltageSensor(simulator).readVoltage();
        updateVisuals(batteryController.calculateStateOfCharge(voltage), false, batteryController.calculateRemainingRuntime(voltage), false);
    }

    LEDController getLedController() {
        return ledController;
    }

    JLabel getDisplayed() {
        return displayed;
    }

    void updateVisuals(int percent, boolean showPercentage, double remainingTime, boolean showRemainingTime) {
        if (showPercentage) {
            updateTextDisplay(percent + "%");
        } else if (showRemainingTime) {
            updateTextDisplay((int)remainingTime + "Min");
        } else {
            updateTextDisplay(" ");
        }
        chargingDetector.updateBcProtectionStates();
        ChargingStates currentState = chargingDetector.getChargingState();

        handleChargingStateTransition(currentState);
        updateLEDState(currentState, percent);

        previousChargingState = currentState;
    }

    private void updateTextDisplay(String value) {
        displayed.setText(value);
    }

    private void handleChargingStateTransition(ChargingStates currentState) {
        if (currentState == ChargingStates.CHARGING && previousChargingState != ChargingStates.CHARGING) {
            handler.setDisplayState(DisplayStates.OFF);
        }
    }

    private void updateLEDState(ChargingStates currentState, int percent) {
        LEDMode mode = switch (currentState) {
            case CHARGING -> (percent == 100) ? LEDMode.FULL_CHARGE : LEDMode.CHARGING;
            case OVERLOAD_PROTECTION -> LEDMode.FULL_CHARGE;
            case UNDERVOLTAGE_PROTECTION -> LEDMode.UNDERVOLTAGE;
            default -> batteryController.isLowBattery() ? LEDMode.WARNING : LEDMode.OFF;
        };

        ledController.controlLED(mode);
    }

}
