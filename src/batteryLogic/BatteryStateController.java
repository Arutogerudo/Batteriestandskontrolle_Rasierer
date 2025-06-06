package batteryLogic;

import hardwareAbstraction.*;
import persistenceManager.CalibrationData;
import persistenceManager.SettingsStorage;

/**
 * This class is responsible for managing the battery state and calculating the state of charge (SoC)
 */
public class BatteryStateController {
    private final VoltageSensor voltageSensor;
    private final CalibrationData calib;
    private final int lowBatteryThreshold;

    /**
     *  Creates BatteryStateController.
     * @param simulator The voltage simulator used to read the battery voltage
     */
    public BatteryStateController(VoltageSimulator simulator){
        voltageSensor = new VoltageSensor(simulator);
        SettingsStorage storage = persistenceManager.SettingsStorage.getInstance();
        calib = storage.readCalibVoltageToSoCFromDisc();
        lowBatteryThreshold = storage.readLowBatteryThresholdFromDisc();
    }

    /**
     * Calculates the state of charge (SoC) based on the given voltage.
     * @param voltage The voltage to be converted to SoC
     * @return The state of charge (SoC) as a percentage
     */
    public int calculateStateOfCharge(double voltage) {
        double[] voltages = calib.getVoltageCalib();
        int[] socValues = calib.getSoCCalib();

        validateCalibrationData(voltages, socValues);

        if (voltage >= voltages[0]) {
            return socValues[0];
        }

        if (voltage <= voltages[voltages.length - 1]) {
            return socValues[socValues.length - 1];
        }

        return interpolateBetweenPoints(voltage, voltages, socValues);
    }

    private void validateCalibrationData(double[] voltages, int[] socValues) {
        if (voltages == null || socValues == null || voltages.length != socValues.length) {
            throw new IllegalStateException("Calibration data is not initialized.");
        }
    }

    private int interpolateBetweenPoints(double voltage, double[] voltages, int[] socValues) {
        for (int i = 1; i < voltages.length; i++) {
            if (voltage >= voltages[i]) {
                return interpolate(voltage, voltages[i], voltages[i - 1], socValues[i], socValues[i - 1]);
            }
        }
        return 0;
    }

    private int interpolate(double x, double x0, double x1, int y0, int y1){
        return (int) (y0 + (y1 - y0) * (x - x0) / (x1 - x0));
    }

    /**
     * Checks if the battery is low based on the current state of charge (SoC).
     * @return If the current state of charge (SoC) is below the low battery threshold
     */
    public boolean isLowBattery(){
        return calculateStateOfCharge(voltageSensor.readVoltage()) <= lowBatteryThreshold;
    }
}
