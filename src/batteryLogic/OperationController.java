package batteryLogic;

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
    public void updateOperationState(VoltageSimulator simulator, InteractionHandler handler) {
        if (handler.getOperatingState() == OperationStates.OFF && simulator.getState() != ChargingStates.CHARGING) {
            simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
        } else if (handler.getOperatingState() == OperationStates.OPERATING && simulator.getState() != ChargingStates.CHARGING) {
            simulator.setState(ChargingStates.DISCHARGING_ACTIVE);
        }
    }
}
