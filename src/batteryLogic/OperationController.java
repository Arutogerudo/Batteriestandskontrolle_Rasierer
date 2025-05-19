package batteryLogic;

import hardwareAbstraction.ChargingState;
import hardwareAbstraction.VoltageSimulator;

public class OperationController {
    public void updateOperationState(VoltageSimulator simulator, InteractionHandler handler){
        if (handler.getOperatingState() == OperationStates.OFF){
            simulator.setState(ChargingState.DISCHARGING_PASSIVE);
        } else if (handler.getOperatingState() == OperationStates.OPERATING) {
            simulator.setState(ChargingState.DISCHARGING_ACTIVE);
        }
    }
}
