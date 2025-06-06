package batteryLogic;

public interface CommandContext {
    OperationStates getOperatingState();
    void setOperatingState(OperationStates newState);
    DisplayStates getDisplayState();
    void setDisplayState(DisplayStates newState);
}
