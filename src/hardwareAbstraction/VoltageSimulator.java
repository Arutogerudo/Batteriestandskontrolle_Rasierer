package hardwareAbstraction;

/**
 * Simulates the voltage of a battery over time (per tick -> rates are based on 1 s = 1 tick). With 50 minutes operating time and 60 minutes for a full charge. But increased speed by factor 10.
 */
public class VoltageSimulator {
    private double voltage;
    private ChargingStates state;

    private static final double ACTIVE_DISCHARGE_RATE = 0.0004;
    private static final double PASSIVE_DISCHARGE_RATE = 0.00001;
    private static final double CHARGE_RATE = 0.00033;

    private static final double MIN_VOLTAGE = 3.0;
    private static final double START_VOLTAGE = 4.2;
    private static final double MAX_VOLTAGE = START_VOLTAGE;

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
    public void setState(ChargingStates newState) {
        this.state = newState;
    }

    /**
     * Simulates the passage of time, updating the voltage based on the current state.
     */
    public void tick() {
        switch (state) {
            case DISCHARGING_ACTIVE:
                voltage -= ACTIVE_DISCHARGE_RATE;
                break;
            case DISCHARGING_PASSIVE, OVERLOAD_PROTECTION:
                voltage -= PASSIVE_DISCHARGE_RATE;
                break;
            case CHARGING:
                voltage += CHARGE_RATE;
                break;
        }

        if (voltage > MAX_VOLTAGE) voltage = MAX_VOLTAGE;
        if (voltage < MIN_VOLTAGE) voltage = MIN_VOLTAGE;
    }

    double getVoltage() {
        return voltage;
    }

    /**
     * Returns the current Charging state (Charging, Discharging)
     * @return Current Charging state.
     */
    public ChargingStates getState() {
        return state;
    }
}

