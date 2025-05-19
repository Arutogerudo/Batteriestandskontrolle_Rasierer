package batteryLogic;

public class LongPressCommand implements ButtonCommand {
    @Override
    public void execute(InteractionHandler handler) {
        if (handler.getOperatingState() == OperationStates.OFF) {
            handler.setOperatingState(OperationStates.OPERATING);
            handler.setDisplayState(DisplayStates.STATE_OF_CHARGE);
        } else if (handler.getOperatingState() == OperationStates.OPERATING) {
            handler.setOperatingState(OperationStates.OFF);
            handler.setDisplayState(DisplayStates.OFF);
        }
    }
}
