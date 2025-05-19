package batteryLogic;

public class ShortPressCommand implements ButtonCommand {
    @Override
    public void execute(InteractionHandler handler) {
        if (handler.getOperatingState() == OperationStates.OFF) {
            handler.setDisplayState(DisplayStates.STATE_OF_CHARGE);
        }
    }
}
