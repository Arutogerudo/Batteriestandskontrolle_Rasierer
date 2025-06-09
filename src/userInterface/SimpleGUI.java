package userInterface;

import javax.swing.*;

import batteryLogic.*;
import hardwareAbstraction.ButtonInput;
import hardwareAbstraction.VoltageSimulator;

/**
 * This class represents a simple GUI for the battery status check of a shaver.
 */
public class SimpleGUI implements UIConstants {
    private final SimpleGUIPanelBuilder panelBuilder;
    private final SimpleGUIUpdater guiUpdater;


    /**
     * Constructor for the SimpleGUI class.
     * @param simulator The voltage simulator used to simulate the battery voltage.
     * @param tempSim Temperature simulator of battery
     */
    public SimpleGUI(VoltageSimulator simulator, TemperatureSimulator tempSim){
        InteractionHandler handler = new InteractionHandler();
        panelBuilder = new SimpleGUIPanelBuilder();
        BatteryStateController batteryController = BatteryStateController.getInstance();
        VisualOutputController visualOutputController = new VisualOutputController(simulator, handler, batteryController);
        guiUpdater = new SimpleGUIUpdater(simulator, tempSim, panelBuilder, handler, visualOutputController);
        new ButtonInput(panelBuilder.getButton(), handler);
        setupPanel(visualOutputController.getLedController().getLedPanel(), visualOutputController.getDisplayed());
    }

    /**
     * Updates the GUI based on the current state of the battery and user interactions.
     */
    public void update(){
        guiUpdater.update();
    }

    private void setupPanel(LEDPanel led, JLabel statusLabel){
        panelBuilder.setupPanel(led, statusLabel);
    }
}
