package batteryLogic;

import java.util.HashMap;
import java.util.Map;

/**
 * Handles user interactions with the device over button input.
 */
public class InteractionHandler {
    private OperationStates operationState = OperationStates.OFF;
    private DisplayStates displayState = DisplayStates.OFF;

    private final Map<String, ButtonCommand> commandMap = new HashMap<>();

    /**
     * Constructor for InteractionHandler. Maps fitting strings to their corresponding commands for easier access.
     */
    public InteractionHandler() {
        commandMap.put("shortPress", new ShortPressCommand());
        commandMap.put("longPress", new LongPressCommand());
        commandMap.put("inactivity", new InactivityCommand());
    }

    /**
     * Handles button press events by executing the corresponding command.
     * @param event The event that occurred, e.g., "shortPress", "longPress", or "inactivity".
     */
    public void handleButtonPress(String event) {
        ButtonCommand command = commandMap.get(event);
        if (command != null) {
            command.execute(this);
        }
    }

    /**
     * Returns the display state of the GUI.
     * @return The current display state of the gui.
     */
    public DisplayStates getDisplayState() {
        return displayState;
    }

    OperationStates getOperatingState() {
        return operationState;
    }

    void setOperatingState(OperationStates newState) {
        this.operationState = newState;
    }
    void setDisplayState(DisplayStates newState) {
        this.displayState = newState;
    }
}
