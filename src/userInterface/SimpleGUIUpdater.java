package userInterface;

import batteryLogic.*;
import hardwareAbstraction.ChargingDetection;
import hardwareAbstraction.VoltageSensor;
import hardwareAbstraction.VoltageSimulator;

class SimpleGUIUpdater {
    private final OperationController operationController;
    private final VoltageSensor sensor;
    private final CalibrationManager calibrationManager;
    private final BatteryStateController batteryController;
    private final VisualOutputController visualOutputController;
    private final InteractionHandler handler;
    private final SimpleGUIPanelBuilder panelBuilder;


    SimpleGUIUpdater(VoltageSimulator simulator, TemperatureSimulator tempSim, SimpleGUIPanelBuilder panelBuilder, InteractionHandler handler, VisualOutputController visualOutputController){
        this.sensor = new VoltageSensor(simulator);
        batteryController = BatteryStateController.getInstance();
        ChargingDetection chargingDetector = new ChargingDetection(simulator);
        this.handler = handler;
        operationController = new OperationController(simulator, tempSim, handler, chargingDetector);
        calibrationManager = CalibrationManager.getInstance();
        this.visualOutputController = visualOutputController;
        this.panelBuilder = panelBuilder;
    }

    /**
     * Updates the GUI based on the current state of the battery and user interactions.
     */
    void update(){
        boolean showPercentage = handler.getDisplayState() == DisplayStates.STATE_OF_CHARGE;
        boolean showRemainingRuntime = handler.getDisplayState() == DisplayStates.REMAINING_TIME;
        operationController.updateOperationState();
        visualOutputController.updateVisuals(batteryController.calculateStateOfCharge(sensor.readVoltage()), showPercentage, batteryController.calculateRemainingRuntime(sensor.readVoltage()), showRemainingRuntime);
        batteryController.monitorChargeCycle();
        calibrationManager.recalibrateIfNeeded();
        updateShaveReadyIcon();
    }

    private void updateShaveReadyIcon() {
        panelBuilder.getShaveReadyIcon().setVisible(batteryController.calculateRemainingRuntime(sensor.readVoltage()) >= 5);
    }
}
