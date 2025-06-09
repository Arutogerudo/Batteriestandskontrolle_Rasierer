package persistenceManager;

/**
 * This class encapsulates the calibration data for the battery.
 */
public class CalibrationData {
    private final double[] voltage;
    private final int[] stateOfCharge;
    private double[] runtime;

    /**
     * Constructor for CalibrationData.
     * @param voltage Array of calibration voltage values.
     * @param stateOfCharge Array of calibration state of charge values.
     * @param runtime Array of calibrated runtime values.
     */
    public CalibrationData(double[] voltage, int[] stateOfCharge, double[] runtime) {
        if (voltage.length != stateOfCharge.length || voltage.length != runtime.length) {
            throw new IllegalArgumentException("All arrays must have the same length.");
        }
        this.voltage = voltage;
        this.stateOfCharge = stateOfCharge;
        this.runtime = runtime;
    }

    /**
     * Returns the voltage calibration data.
     * @return Array of calibration voltage values.
     */
    public double[] getVoltageCalib(){
        return voltage.clone();
    }

    /**
     * Returns the state of charge calibration data.
     * @return Array of calibration state of charge values.
     */
    public int[] getSoCCalib(){
        return stateOfCharge.clone();
    }

    /**
     * Returns the calibration data of the remaining running time.
     * @return Array of calibration runtime.
     */
    public double[] getRuntime() {
        return runtime.clone();
    }

    void setRuntimeCalib(double[] runtime){
        this.runtime = runtime;
    }
}

