package BatteryLogic;

import java.util.HashMap;
import java.util.Map;

public class InteractionHandler {
    private OperationStates operationState = OperationStates.OFF;
    private DisplayStates displayState = DisplayStates.OFF;

    private final Map<String, ButtonCommand> commandMap = new HashMap<>();

    public InteractionHandler() {
        commandMap.put("shortPress", new ShortPressCommand());
        commandMap.put("longPress", new LongPressCommand());
        commandMap.put("inactivity", new InactivityCommand());
    }

    public void handleButtonPress(String event) {
        ButtonCommand command = commandMap.get(event);
        if (command != null) {
            command.execute(this);
        }
    }

    public DisplayStates getDisplayState() {
        return displayState;
    }

    public OperationStates getOperatingState() {
        return operationState;
    }

    // Diese Setter werden intern von den Commands verwendet
    void setOperatingState(OperationStates newState) {
        this.operationState = newState;
    }

    void setDisplayState(DisplayStates newState) {
        this.displayState = newState;
    }
}
