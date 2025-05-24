import hardwareAbstraction.*;
import userInterface.*;

/**
 * Class to start programm.
 */
public class Program {
    /**
     * Start of Programm.
     * @param args arguments
     */
    public static void main(String[] args) {
        VoltageSimulator simulator = new VoltageSimulator();
        SimpleGUI gui = new SimpleGUI(simulator);
        simulator.setState(ChargingStates.DISCHARGING_ACTIVE);
        ChargingDetection chargingDetection = new ChargingDetection();

        new Thread(() -> {
            while (true) {
                simulator.tick();
                try {
                    // intentional sleep for simulation purposes
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while (true) {
                gui.update();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                chargingDetection.listenForChargingCommands(simulator);
            }
        }).start();
        
    }
}
