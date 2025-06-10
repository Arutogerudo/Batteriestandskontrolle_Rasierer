package hardwareAbstraction;

/**
 * Basis for simulation of the voltage curve of a battery over time. States define the gradient.
 */
public enum ChargingStates implements VoltageChangeProvider {
    /**
     * Shaver is operating.
     */
    DISCHARGING_ACTIVE {
        @Override
        public double getVoltageChange() {
            return -ACTIVE_DISCHARGE_RATE;
        }
    },
    /**
     * Shaver is not operating.
     */
    DISCHARGING_PASSIVE {
        @Override
        public double getVoltageChange() {
            return -PASSIVE_DISCHARGE_RATE;
        }
    },
    /**
     * Shaver is charging.
     */
    CHARGING {
        @Override
        public double getVoltageChange() {
            return CHARGE_RATE;
        }
    },
    /**
     * Shaver is fully loaded but charging cable is still plugged in.
     */
    OVERLOAD_PROTECTION {
        @Override
        public double getVoltageChange() {
            return -PASSIVE_DISCHARGE_RATE;
        }
    },
    /**
     * Battery´s temperature is not in ok range, so the charge is paused.
     */
    CHARGE_STOP_BC_TEMP {
        @Override
        public double getVoltageChange() {
            return -PASSIVE_DISCHARGE_RATE;
        }
    },
    /**
     * Battery`s state of charge is below 2.8 V, so it switches into power saving mode.
     */
    UNDERVOLTAGE_PROTECTION {
        @Override
        public double getVoltageChange() {
            return -POWER_SAVING_MODE;
        }
    }
}

