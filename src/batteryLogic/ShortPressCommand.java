package batteryLogic;

/**
 * Command for short button press in the battery management system.
 */
public class ShortPressCommand implements ButtonCommand {
    @Override
    public void execute(CommandContext context) {
        if (context.getDisplayState() == DisplayStates.OFF || context.getDisplayState() == DisplayStates.REMAINING_TIME) {
            context.setDisplayState(DisplayStates.STATE_OF_CHARGE);
        } else if (context.getDisplayState() == DisplayStates.STATE_OF_CHARGE) {
            context.setDisplayState(DisplayStates.REMAINING_TIME);
        }
    }
}
