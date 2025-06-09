package batteryLogic;

import hardwareAbstraction.*;

/**
 * This class is responsible for controlling the operation state of the battery.
 */
public class OperationController {
    private final IVoltageSimulator simulator;
    private final ITemperatureSimulator tempSim;
    private final IInteractionHandler handler;
    private final IChargingDetection chargingDetector;

    /**
     * Constructor for the OperationController.
     * @param simulator The voltage simulator to control the battery's voltage state.
     * @param tempSim The temperature simulator to simulate the battery's temperature.
     * @param handler The interaction handler to manage user interactions and operating states.
     * @param chargingDetector The charging detection interface to recognize charging of the battery.
     */
    public OperationController(IVoltageSimulator simulator, ITemperatureSimulator tempSim, IInteractionHandler handler, IChargingDetection chargingDetector) {
        this.simulator = simulator;
        this.tempSim = tempSim;
        this.handler = handler;
        this.chargingDetector = chargingDetector;
    }

    /**
     * Updates the operation state of the shaver based on the last interactions. This update is directly applied to the simulator.
     */
    public void updateOperationState() {
        chargingDetector.updateBcProtectionStates();
        ChargingStates chargingState = chargingDetector.getChargingState();
        OperationStates opState = handler.getOperatingState();

        if (!tempSim.isTemperatureInSafeRange()) {
            handleUnsafeTemperature(simulator, handler, chargingState, opState);
            return;
        }

        if (!isChargingOrInProtection(chargingState)) {
            handleNoChargingOrProtectionState(simulator, opState);
        } else {
            handler.setOperatingState(OperationStates.OFF);
        }
    }

    private void handleUnsafeTemperature(IVoltageSimulator simulator, IInteractionHandler handler, ChargingStates chargingState, OperationStates opState) {
        if (opState == OperationStates.OPERATING) {
            handler.setOperatingState(OperationStates.OFF);
            simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
        } else if (chargingState == ChargingStates.CHARGING) {
            simulator.setState(ChargingStates.CHARGE_STOP_BC_TEMP);
        }
    }

    private void handleNoChargingOrProtectionState(IVoltageSimulator simulator, OperationStates opState) {
        if (opState == OperationStates.OFF) {
            simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
        } else if (opState == OperationStates.OPERATING) {
            simulator.setState(ChargingStates.DISCHARGING_ACTIVE);
        }
    }

    private boolean isChargingOrInProtection(ChargingStates state) {
        return state == ChargingStates.CHARGING ||
               state == ChargingStates.OVERLOAD_PROTECTION ||
               state == ChargingStates.CHARGE_STOP_BC_TEMP ||
               state == ChargingStates.UNDERVOLTAGE_PROTECTION;
    }

}
