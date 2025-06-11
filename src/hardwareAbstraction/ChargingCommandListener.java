package hardwareAbstraction;

import java.util.Scanner;

/**
 * Listens for charging commands via the command line interface.
 */
public class ChargingCommandListener {
    private final ChargingDetection chargingDetection;

    /**
     * Constructs a ChargingCommandListener with the given ChargingDetection instance.
     *
     * @param chargingDetection the ChargingDetection instance to update charging states on
     */
    public ChargingCommandListener(ChargingDetection chargingDetection) {
        this.chargingDetection = chargingDetection;
    }

    /**
     * Listener on commandline to recognize plugin and unplug of charge cable.
     */
    public void listenForChargingCommands() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().trim().toLowerCase();
        switch (input) {
            case "start":
                chargingDetection.setChargingState(ChargingStates.CHARGING);
                System.out.println("🔌 Laden gestartet");
                break;
            case "stop":
                chargingDetection.setChargingState(ChargingStates.DISCHARGING_PASSIVE);
                System.out.println("🔋 Laden gestoppt");
                break;
            default:
                System.out.println("Unbekannter Befehl. Nutze 'start' oder 'stop'.");
        }
    }
}
