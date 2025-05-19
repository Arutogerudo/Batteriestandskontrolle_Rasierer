package UserInterface;

import javax.swing.*;

import BatteryLogic.BatteryStateController;
import HardwareAbstraction.VoltageSimulator;

public class VisualOutputController {
    private final LEDController ledController;
    private final JLabel displayed;
    private final BatteryStateController batteryController;

    public VisualOutputController(VoltageSimulator simulator) {
        displayed = new JLabel();
        ledController = new LEDController();
        batteryController = new BatteryStateController(simulator);
        updateDisplay(batteryController.calculateStateOfCharge(), false);
    }

    LEDController getLedController() {
        return ledController;
    }

    JLabel getDisplayed() {
        return displayed;
    }

    public void updateDisplay(int percent, boolean showPercentage) {
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
