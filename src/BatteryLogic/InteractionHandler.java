package BatteryLogic;

public class InteractionHandler {
    private OperationStates operationState = OperationStates.OFF;
    private DisplayStates displayState = DisplayStates.OFF;

    public void handleButtonPress(String event) {
        switch (operationState) {
            case OFF:
                if (event.equals("longPress")) {
                    operationState = OperationStates.OPERATING;
                    displayState = DisplayStates.STATE_OF_CHARGE;
                } else if (event.equals("shortPress")) {
                    displayState = DisplayStates.STATE_OF_CHARGE;
                } else if (event.equals("inactivity")) {
                    displayState = DisplayStates.OFF;
                }
                break;

            case OPERATING:
                if (event.equals("longPress")) {
                    operationState = OperationStates.OFF;
                    displayState = DisplayStates.OFF;
                }
                break;
        }
    }

    public DisplayStates getDisplayState() {
        return displayState;
    }

    public OperationStates getOperatingState() {
        return operationState;
    }
}
