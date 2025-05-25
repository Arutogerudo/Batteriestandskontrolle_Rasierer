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
            if (opState == OperationStates.OPERATING) {
                handler.setOperatingState(OperationStates.OFF);
                simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
            } else if (chargingState == ChargingStates.CHARGING) {
                simulator.setState(ChargingStates.CHARGE_STOP_BC_TEMP);
            }
            return;
        }

        boolean isChargingOrProtection = chargingState == ChargingStates.CHARGING
                || chargingState == ChargingStates.OVERLOAD_PROTECTION
                || chargingState == ChargingStates.CHARGE_STOP_BC_TEMP;

        if (!isChargingOrProtection) {
            if (opState == OperationStates.OFF) {
                simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
            } else if (opState == OperationStates.OPERATING) {
                simulator.setState(ChargingStates.DISCHARGING_ACTIVE);
            }
        } else {
            if (chargingState != ChargingStates.OVERLOAD_PROTECTION && chargingState != ChargingStates.CHARGE_STOP_BC_TEMP) {
                simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
            }
            handler.setOperatingState(OperationStates.OFF);
        }
    }

}
