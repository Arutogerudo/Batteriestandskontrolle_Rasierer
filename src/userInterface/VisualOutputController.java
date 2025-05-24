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
     * @param handler interaction handler to switch display off when charge is starting
     */
    public VisualOutputController(VoltageSimulator simulator, InteractionHandler handler) {
        displayed = new JLabel();
        ledController = new LEDController();
        batteryController = new BatteryStateController(simulator);
        chargingDetector = new ChargingDetection(simulator);
        this.handler = handler;
        updateDisplay(batteryController.calculateStateOfCharge(new VoltageSensor(simulator).readVoltage()), false);
    }

    LEDController getLedController() {
        return ledController;
    }

    JLabel getDisplayed() {
        return displayed;
    }

    void updateDisplay(int percent, boolean showPercentage) {
        if (showPercentage) {
            displayed.setText(percent + "%");
        } else {
            displayed.setText("");
        }

        ChargingStates currentState = chargingDetector.getChargingState();

        if (currentState == ChargingStates.CHARGING && previousChargingState != ChargingStates.CHARGING) {
            handler.setDisplayState(DisplayStates.OFF);
        }

        if (currentState == ChargingStates.CHARGING) {
            if (percent == 100) {
                ledController.controlLED(LEDMode.FULL_CHARGE);
            } else {
                ledController.controlLED(LEDMode.CHARGING);
            }
        } else if (batteryController.isLowBattery()) {
            ledController.controlLED(LEDMode.WARNING);
        } else if (currentState == ChargingStates.OVERLOAD_PROTECTION) {
            ledController.controlLED(LEDMode.FULL_CHARGE);
        } else {
            ledController.controlLED(LEDMode.OFF);
        }

        previousChargingState = currentState;
    }


}
