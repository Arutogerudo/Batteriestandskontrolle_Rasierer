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
     * Constructor for VisualOutputController.
     *
     * @param simulator The voltage simulator used to read the battery voltage.
     * @param handler   interaction handler to switch display off when charge is starting
     */
    public VisualOutputController(VoltageSimulator simulator, InteractionHandler handler, BatteryStateController batteryController) {
        displayed = new JLabel();
        ledController = new LEDController();
        this.batteryController = batteryController;
        chargingDetector = new ChargingDetection(simulator);
        this.handler = handler;
        updateVisuals(batteryController.calculateStateOfCharge(new VoltageSensor(simulator).readVoltage()), false);
    }

    LEDController getLedController() {
        return ledController;
    }

    JLabel getDisplayed() {
        return displayed;
    }

    void updateVisuals(int percent, boolean showPercentage) {
        updateTextDisplay(percent, showPercentage);
        ChargingStates currentState = chargingDetector.getChargingState();

        handleChargingStateTransition(currentState);
        updateLEDState(currentState, percent);

        previousChargingState = currentState;
    }

    private void updateTextDisplay(int percent, boolean showPercentage) {
        displayed.setText(showPercentage ? percent + "%" : " ");
    }

    private void handleChargingStateTransition(ChargingStates currentState) {
        if (currentState == ChargingStates.CHARGING && previousChargingState != ChargingStates.CHARGING) {
            handler.setDisplayState(DisplayStates.OFF);
        }
    }

    private void updateLEDState(ChargingStates currentState, int percent) {
        LEDMode mode;

        if (currentState == ChargingStates.CHARGING) {
            mode = (percent == 100) ? LEDMode.FULL_CHARGE : LEDMode.CHARGING;
        } else if (currentState == ChargingStates.OVERLOAD_PROTECTION) {
            mode = LEDMode.FULL_CHARGE;
        } else if (currentState == ChargingStates.UNDERVOLTAGE_PROTECTION) {
            mode = LEDMode.UNDERVOLTAGE;
        } else if (batteryController.isLowBattery()) {
            mode = LEDMode.WARNING;
        } else {
            mode = LEDMode.OFF;
        }

        ledController.controlLED(mode);
    }
}
