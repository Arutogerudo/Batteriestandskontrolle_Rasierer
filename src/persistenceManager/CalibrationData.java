package persistenceManager;

/**
 * This class encapsulates the calibration data for the battery.
 */
public class CalibrationData {
    private final double[] voltage;
    private final int[] stateOfCharge;

    /**
     * Constructor for CalibrationData.
     * @param voltage Array of calibration voltage values.
     * @param stateOfCharge Array of calibration state of charge values.
     */
    public CalibrationData(double[] voltage, int[] stateOfCharge) {
        this.voltage = voltage;
        this.stateOfCharge = stateOfCharge;
    }

    /**
     * Returns the voltage calibration data.
     * @return Array of calibration voltage values.
     */
    public double[] getVoltageCalib(){
        return voltage;
    }

    /**
     * Returns the state of charge calibration data.
     * @return Array of calibration state of charge values.
     */
    public int[] getSoCCalib(){
        return stateOfCharge;
    }
}

