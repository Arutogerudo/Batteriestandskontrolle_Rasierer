package hardwareAbstraction;

public class VoltageSensor {
    private final VoltageSimulator simulator;

    public VoltageSensor(VoltageSimulator simulator){
        this.simulator = simulator;
    }

    public double readVoltage(){
        return simulator.getVoltage();
    }
}
