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
     * @param handler The interaction handler that manages the current state of the battery.
     */
    public void updateOperationState(VoltageSimulator simulator, TemperatureSimulator tempSim, InteractionHandler handler, ChargingDetection chargingDetecter) {
        if (!tempSim.isTemperatureInSafeRange()) {
            if (handler.getOperatingState() == OperationStates.OPERATING) {
                handler.setOperatingState(OperationStates.OFF);
                simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
            } else if (simulator.getState() == ChargingStates.CHARGING) {
                simulator.setState(ChargingStates.CHARGE_STOP_BC_TEMP);
            }
            return;
        }

        if (chargingDetecter.getChargingState() != ChargingStates.CHARGING && chargingDetecter.getChargingState() != ChargingStates.OVERLOAD_PROTECTION && chargingDetecter.getChargingState() != ChargingStates.CHARGE_STOP_BC_TEMP) {
            if (handler.getOperatingState() == OperationStates.OFF) {
                simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
            } else if (handler.getOperatingState() == OperationStates.OPERATING) {
                simulator.setState(ChargingStates.DISCHARGING_ACTIVE);
            }
        } else {
            if (chargingDetecter.getChargingState() != ChargingStates.OVERLOAD_PROTECTION && chargingDetecter.getChargingState() != ChargingStates.CHARGE_STOP_BC_TEMP) {
                simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
            }
            handler.setOperatingState(OperationStates.OFF);
        }
    }
}
