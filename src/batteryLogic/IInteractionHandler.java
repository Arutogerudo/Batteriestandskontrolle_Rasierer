package batteryLogic;

public interface IInteractionHandler {
    OperationStates getOperatingState();
    void setOperatingState(OperationStates state);
}
