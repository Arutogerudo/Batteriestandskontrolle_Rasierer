package persistenceManager;

public class CalibrationData {
    private final double[] voltage;
    private final int[] stateOfCharge;

    public CalibrationData(double[] voltage, int[] stateOfCharge) {
        this.voltage = voltage;
        this.stateOfCharge = stateOfCharge;
    }

    public double[] getVoltageCalib(){
        return voltage;
    }

    public int[] getSoCCalib(){
        return stateOfCharge;
    }
}

