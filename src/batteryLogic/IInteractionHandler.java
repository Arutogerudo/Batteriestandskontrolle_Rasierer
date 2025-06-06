package batteryLogic;

/**
 * Interface for managing the context of commands in a battery management system.
 */
public interface IInteractionHandler {
    /**
     * Sets the display state of the battery system.
     * @return the current display state of the battery system.
     */
    OperationStates getOperatingState();

    /**
     * Sets the operating state of the battery system.
     * @param state the new operating state to set for the battery system.
     */
    void setOperatingState(OperationStates state);
}
