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
    /**
     * Shaver is fully loaded but charging cable is still plugged in.
     */
    OVERLOAD_PROTECTION,
    /**
     * Battery´s temperature is not in ok range, so the charge is paused.
     */
    CHARGE_STOP_BC_TEMP
}

