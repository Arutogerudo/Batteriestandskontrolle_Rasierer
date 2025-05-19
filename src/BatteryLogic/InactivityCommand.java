package BatteryLogic;

public class InactivityCommand implements ButtonCommand {
    @Override
    public void execute(InteractionHandler handler) {
        if (handler.getOperatingState() == OperationStates.OFF) {
            handler.setDisplayState(DisplayStates.OFF);
        }
    }
}
