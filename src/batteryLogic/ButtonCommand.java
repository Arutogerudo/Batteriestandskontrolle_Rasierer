package batteryLogic;

/**
 * Interface for button commands in the battery management system.
 */
public interface ButtonCommand {
    /**
     * Executes the command associated with a button press.
     */
    void execute(CommandContext context);
}
