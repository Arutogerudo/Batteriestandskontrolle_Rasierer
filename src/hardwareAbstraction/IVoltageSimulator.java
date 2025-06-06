package hardwareAbstraction;

/**
 * Interface for voltage simulation logic.
 */
public interface IVoltageSimulator {
    /**
     * Updates the current voltage of the battery.
     * @param state the new state of the voltage simulator
     */
    void setState(ChargingStates state);
}
