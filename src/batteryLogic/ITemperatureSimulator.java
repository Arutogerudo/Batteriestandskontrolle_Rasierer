package batteryLogic;

/**
 * Interface for managing the context of commands in a battery management system.
 */
public interface ITemperatureSimulator {
    /**
     * Checks the current temperature of the battery.
     * @return Wether the current temperature of the battery is in a safe range for the battery.
     */
    boolean isTemperatureInSafeRange();
}
