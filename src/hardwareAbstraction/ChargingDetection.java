package hardwareAbstraction;

import userInterface.VisualOutputController;

import java.util.Scanner;

public class ChargingDetection {
    public static final double FULL_CHARGE_VOLTAGE = 4.2;
    private final VoltageSimulator simulator;

    public ChargingDetection(VoltageSimulator simulator){
        this.simulator = simulator;
    }

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

    public ChargingStates getChargingState(){
        if (simulator.getVoltage() >= FULL_CHARGE_VOLTAGE) {
            return ChargingStates.OVERLOAD_PROTECTION;
        }
        return simulator.getState();
    }
}