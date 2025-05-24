package hardwareAbstraction;

import java.util.Scanner;

public class ChargingDetection {
    public void listenForChargingCommands(VoltageSimulator simulator) {
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
                case "exit":
                    System.out.println("Beende Programm...");
                    System.exit(0);
                default:
                    System.out.println("Unbekannter Befehl. Nutze 'start', 'stop' oder 'exit'");
            }
        }
    }
}