package userInterface;

import javax.swing.*;

import batteryLogic.BatteryStateController;
import hardwareAbstraction.VoltageSensor;
import hardwareAbstraction.VoltageSimulator;

/**
 * This class is responsible for managing the visual output of the battery state.
 */
public class VisualOutputController {
    private final LEDController ledController;
    private final JLabel displayed;
    private final BatteryStateController batteryController;

    /**
     * Constructor for VisualOutputController.
     * @param simulator The voltage simulator used to read the battery voltage.
     */
    public VisualOutputController(VoltageSimulator simulator) {
        displayed = new JLabel();
        ledController = new LEDController();
        batteryController = new BatteryStateController(simulator);
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
        if (batteryController.isLowBattery()) {
            showLowBatteryWarning();
        }
    }

    private void showLowBatteryWarning() {
        ledController.startBlinking();
    }
}
