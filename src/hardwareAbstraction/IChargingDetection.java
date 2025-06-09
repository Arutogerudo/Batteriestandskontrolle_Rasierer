package hardwareAbstraction;

/**
 * Interface for charging detection logic.
 */
public interface IChargingDetection {
    /**
     * Listens for charging commands to start or stop charging.
     * @return the current charging state of the battery.
     */
    ChargingStates getChargingState();

    /**
     * Updates Charging States in case of protection states.
     */
    void updateBcProtectionStates();
}
