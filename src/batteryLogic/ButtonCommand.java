package batteryLogic;

/**
 * Interface for button commands in the battery management system.
 */
public interface ButtonCommand {
    /**
     * Executes the command associated with a button press.
     * @param context The context in which the command is executed, providing necessary data and services.
     */
    void execute(CommandContext context);
}
