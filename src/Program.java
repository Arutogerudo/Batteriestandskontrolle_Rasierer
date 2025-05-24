import batteryLogic.TemperatureSimulator;
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
        TemperatureSimulator tempSim = new TemperatureSimulator();
        SimpleGUI gui = new SimpleGUI(simulator, tempSim);
        simulator.setState(ChargingStates.DISCHARGING_PASSIVE);
        ChargingDetection chargingDetection = new ChargingDetection(simulator);

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

        tempSim.start();

        new Thread(() -> {
            while (true) {
                gui.update();
            }
        }).start();

        new Thread(() -> {
            while (true) {
                chargingDetection.listenForChargingCommands();
            }
        }).start();
        
    }
}
