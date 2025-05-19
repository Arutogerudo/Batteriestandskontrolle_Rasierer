import java.awt.*;
import javax.swing.*;

import BatteryLogic.BatteryStateController;
import HardwareAbstraction.VoltageSimulator;

public class VisualOutputController {
    //private boolean showRemainingTime;
    private LEDController ledController;
    private JLabel displayed;
    private BatteryStateController batteryController;

    public VisualOutputController(VoltageSimulator simulator){
        //this.showRemainingTime = showRemainingTime;
        displayed = new JLabel();
        ledController = new LEDController();
        batteryController = new BatteryStateController(simulator);
        updateDisplay(batteryController.calculateStateOfCharge(), false);
    }

    LEDController getLedController(){
        return ledController;
    }

    JLabel getDisplayed(){
        return displayed;
    }

    public void updateDisplay(int percent, boolean showPercentage){
        if(showPercentage){
            displayed.setText(percent + "%");
        //} else if(showRemainingTime){
        //    displayed = new JLabel(minutes + "Min.");
        } else {
            displayed.setText("");;
        }
        if(batteryController.isLowBattery()){
            showLowBatteryWarning();
        }
    }

    private void showLowBatteryWarning(){
        ledController.startBlinking(Color.RED);
    }
}
