package batteryLogic;

/**
 * Command for short button press in the battery management system.
 */
public class ShortPressCommand implements ButtonCommand {
    @Override
    public void execute(CommandContext context) {
        if (context.getOperatingState() == OperationStates.OFF) {
            context.setDisplayState(DisplayStates.STATE_OF_CHARGE);
        }
    }
}
