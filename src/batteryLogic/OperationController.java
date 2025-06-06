package batteryLogic;

import hardwareAbstraction.ChargingDetection;
import hardwareAbstraction.ChargingStates;
import hardwareAbstraction.VoltageSimulator;

/**
 * This class is responsible for controlling the operation state of the battery.
 */
public class OperationController {
    /**
     * Updates the operation state of the shaver based on the last interactions. This update is directly applied to the simulator.
     * @param simulator The voltage simulator that simulates the battery's behavior.
     * @param tempSim Simulates the temperature of the battery.
     * @param handler The interaction handler that manages the current state of the battery.
     * @param chargingDetector Detects if the charger is plugged in.
     */
    public void updateOperationState(VoltageSimulator simulator, TemperatureSimulator tempSim, InteractionHandler handler, ChargingDetection chargingDetector) {
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

    private void handleUnsafeTemperature(VoltageSimulator simulator, InteractionHandler handler, ChargingStates chargingState, OperationStates opState) {
        if (opState == OperationStates.OPERATING) {
            handler.setOperatingState(OperationStates.OFF);
            simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
        } else if (chargingState == ChargingStates.CHARGING) {
            simulator.setState(ChargingStates.CHARGE_STOP_BC_TEMP);
        }
    }

    private void handleNoChargingOrProtectionState(VoltageSimulator simulator, OperationStates opState) {
        if (opState == OperationStates.OFF) {
            simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
        } else if (opState == OperationStates.OPERATING) {
            simulator.setState(ChargingStates.DISCHARGING_ACTIVE);
        }
    }

    private boolean isChargingOrInProtection(ChargingStates state) {
        return state == ChargingStates.CHARGING ||
               state == ChargingStates.OVERLOAD_PROTECTION ||
               state == ChargingStates.CHARGE_STOP_BC_TEMP;
    }

}
