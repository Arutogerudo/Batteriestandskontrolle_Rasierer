package hardwareAbstraction;

/**
 * Measures the voltage of a battery (here simulated) over time.
 */
public class VoltageSensor {
    private final VoltageSimulator simulator;

    /**
     * Creates a new VoltageSensor instance.
     * @param simulator The simulator to be used for voltage measurement.
     */
    public VoltageSensor(VoltageSimulator simulator){
        this.simulator = simulator;
    }

    /**
     * Reads the current voltage of the battery.
     * @return The current voltage of the battery.
     */
    public double readVoltage(){
        return simulator.getVoltage();
    }
}
