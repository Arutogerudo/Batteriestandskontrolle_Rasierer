package batteryLogic;

/**
 * Interface for managing the context of commands in a battery management system.
 */
public interface CommandContext {
    /**
     * Retrieves the current operating state of the battery management system.
     * @return the current operating state of the battery management system.
     */
    OperationStates getOperatingState();

    /**
     * Sets a new operating state for the battery management system.
     * @param newState the new operating state to set for the battery management system.
     */
    void setOperatingState(OperationStates newState);

    /**
     * Retrieves the current display state of the battery management system.
     * @return the current display state of the battery management system.
     */
    DisplayStates getDisplayState();

    /**
     * Sets a new display state for the battery management system.
     * @param newState the new display state to set for the battery management system.
     */
    void setDisplayState(DisplayStates newState);
}
