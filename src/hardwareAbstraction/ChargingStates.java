package hardwareAbstraction;

/**
 * Basis for simulation of the voltage curve of a battery over time. States define the gradient.
 */
public enum ChargingStates {
    /**
     * Shaver is operating.
     */
    DISCHARGING_ACTIVE,
    /**
     * Shaver is not operating.
     */
    DISCHARGING_PASSIVE,
    /**
     * Shaver is charging.
     */
    CHARGING,
    OVERLOAD_PROTECTION
}

