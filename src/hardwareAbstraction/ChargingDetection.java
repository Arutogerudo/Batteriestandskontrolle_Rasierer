package hardwareAbstraction;

import batteryLogic.BatteryStateController;

/**
 * Integrates logic to detect charge cable.
 */
public class ChargingDetection implements IChargingDetection {
    private static final double FULL_CHARGE_VOLTAGE = 4.2;
    private final VoltageSimulator simulator;
    private final BatteryStateController batteryController;

    /**
     * Creates an instance to detect the charging state.
     * @param simulator simulates the voltage change
     */
    public ChargingDetection(VoltageSimulator simulator){
        this.simulator = simulator;
        this.batteryController = BatteryStateController.getInstance();
    }

    void setChargingState(ChargingStates state){
        simulator.setState(state);
    }

    /**
     * Returns the current charging state of the battery.
     * @return current charging state
     */
    @Override
    public ChargingStates getChargingState(){
        return simulator.getState();
    }

    public void updateBcProtectionStates(){
        if (simulator.getVoltage() >= FULL_CHARGE_VOLTAGE && simulator.getState() == ChargingStates.CHARGING) {
            simulator.setState(ChargingStates.OVERLOAD_PROTECTION);
        } else if (batteryController.isUndervoltageDetected()) {
            simulator.setState(ChargingStates.UNDERVOLTAGE_PROTECTION);
        }
    }
}