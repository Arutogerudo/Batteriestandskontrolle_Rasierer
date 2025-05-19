import HardwareAbstraction.*;
import UserInterface.*;

public class Program {
    public static void main(String[] args) {
        VoltageSimulator simulator = new VoltageSimulator();
        SimpleGUI gui = new SimpleGUI(simulator);
        simulator.setState(ChargingState.DISCHARGING_ACTIVE);

        new Thread(() -> {
            while (true) {
                simulator.tick();
                try {
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
        
    }
}
