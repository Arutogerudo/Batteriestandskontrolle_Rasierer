package hardwareAbstraction;

import java.util.Scanner;

/**
 * Integrates logic to detect charge cable.
 */
public class ChargingDetection implements IChargingDetection {
    private static final double FULL_CHARGE_VOLTAGE = 4.2;
    private final VoltageSimulator simulator;

    /**
     * Creates an instance to detect the charging state.
     * @param simulator simulates the voltage change
     */
    public ChargingDetection(VoltageSimulator simulator){
        this.simulator = simulator;
    }

    /**
     * Listener on commandline to recognize plugin and unplug of charge cable.
     */
    public void listenForChargingCommands() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "start":
                    simulator.setState(ChargingStates.CHARGING);
                    System.out.println("🔌 Laden gestartet");
                    break;
                case "stop":
                    simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
                    System.out.println("🔋 Laden gestoppt");
                    break;
                default:
                    System.out.println("Unbekannter Befehl. Nutze 'start' oder 'stop'.");
            }
        }
    }

    /**
     * Returns the current charging state of the battery.
     * @return current charging state
     */
    @Override
    public ChargingStates getChargingState(){
        if (simulator.getVoltage() >= FULL_CHARGE_VOLTAGE) {
            return ChargingStates.OVERLOAD_PROTECTION;
        }
        return simulator.getState();
    }
}