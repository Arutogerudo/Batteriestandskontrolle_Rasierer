package batteryLogic;

/**
 * Command for no button press in 5 s in the battery management system.
 */
public class InactivityCommand implements ButtonCommand {
    @Override
    public void execute(InteractionHandler handler) {
        if (handler.getOperatingState() == OperationStates.OFF) {
            handler.setDisplayState(DisplayStates.OFF);
        }
    }
}
