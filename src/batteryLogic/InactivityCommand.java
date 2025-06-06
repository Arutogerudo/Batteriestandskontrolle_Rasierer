package batteryLogic;

/**
 * Command for no button press in 5 s in the battery management system.
 */
public class InactivityCommand implements ButtonCommand {
    @Override
    public void execute(CommandContext context) {
        if (context.getOperatingState() == OperationStates.OFF) {
            context.setDisplayState(DisplayStates.OFF);
        }
    }
}
