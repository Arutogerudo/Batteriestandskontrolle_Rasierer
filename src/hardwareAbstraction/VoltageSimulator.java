package hardwareAbstraction;

/**
 * Simulates the voltage of a battery over time (per tick -> rates are based on 1 s = 1 tick). With 50 minutes operating time and 60 minutes for a full charge.
 */
public class VoltageSimulator implements IVoltageSimulator {
    private double voltage;
    private ChargingStates state;
    private static final double START_VOLTAGE = 4.2;

    /**
     * Creates a VoltageSimulator.
     */
    public VoltageSimulator() {
        this.voltage = START_VOLTAGE;
        this.state = ChargingStates.DISCHARGING_PASSIVE;
    }

    /**
     * Sets the state of the battery, e.g. loading, actively discharging or passively discharging.
     *
     * @param newState the new state of the battery
     */
    @Override
    public void setState(ChargingStates newState) {
        this.state = newState;
    }

    /**
     * Simulates the passage of time, updating the voltage based on the current state.
     */
    public void tick() {
        updateVoltageBasedOnState();
    }

    private void updateVoltageBasedOnState() {
        voltage += state.getVoltageChange();
    }

    double getVoltage() {
        return voltage;
    }

    /**
     * This method is used for testing purposes to set the voltage of the simulator.
     *
     * @param voltage the voltage to set for the simulator.
     */
    public void setVoltage(double voltage) {
        this.voltage = voltage;
    }

    /**
     * Returns the current Charging state (Charging, Discharging)
     *
     * @return Current Charging state.
     */
    public ChargingStates getState() {
        return state;
    }
}

