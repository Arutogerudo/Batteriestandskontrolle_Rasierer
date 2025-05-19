package HardwareAbstraction;

public class VoltageSensor {
    private VoltageSimulator simulator;

    public VoltageSensor(VoltageSimulator simulator){
        this.simulator = simulator;
    }

    public double readVoltage(){
        return simulator.getVoltage();
    }
}
