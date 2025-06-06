package batteryLogic;

/**
 * Command for long button press in the battery management system.
 */
public class LongPressCommand implements ButtonCommand {
    @Override
    public void execute(CommandContext context) {
        if (context.getOperatingState() == OperationStates.OFF) {
            context.setOperatingState(OperationStates.OPERATING);
            context.setDisplayState(DisplayStates.STATE_OF_CHARGE);
        } else if (context.getOperatingState() == OperationStates.OPERATING) {
            context.setOperatingState(OperationStates.OFF);
            context.setDisplayState(DisplayStates.OFF);
        }
    }
}
