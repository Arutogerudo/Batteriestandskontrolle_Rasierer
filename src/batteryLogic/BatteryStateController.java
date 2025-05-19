package batteryLogic;

import hardwareAbstraction.*;
import persistenceManager.CalibrationData;
import persistenceManager.SettingsStorage;

public class BatteryStateController {
    private final VoltageSensor voltageSensor;
    private final CalibrationData calib;
    private final int lowBatteryThreshold;

    public BatteryStateController(VoltageSimulator simulator){
        voltageSensor = new VoltageSensor(simulator);
        SettingsStorage storage = persistenceManager.SettingsStorage.getInstance();
        calib = storage.readCalibVoltageToSoCFromDisc();
        lowBatteryThreshold = storage.readLowBatteryThresholdFromDisc();
    }

    public int calculateStateOfCharge() {
        double voltage = voltageSensor.readVoltage();

        double[] voltages = calib.getVoltageCalib();
        int[] socValues = calib.getSoCCalib();

        if (voltages == null || socValues == null || voltages.length != socValues.length) {
            throw new IllegalStateException("Calibration data is not initialized.");
        }

        if (voltage >= voltages[0]) return socValues[0];
        if (voltage <= voltages[voltages.length - 1]) return socValues[socValues.length - 1];

        for (int i = 1; i < voltages.length; i++) {
            if (voltage >= voltages[i]) {
                return interpolate(voltage, voltages[i], voltages[i - 1], socValues[i], socValues[i - 1]);
            }
        }

        return 0; // Fallback
    }

    private int interpolate(double x, double x0, double x1, int y0, int y1){
        return (int) (y0 + (y1 - y0) * (x - x0) / (x1 - x0));
    }

    public boolean isLowBattery(){
        return calculateStateOfCharge() <= lowBatteryThreshold;
    }
}
