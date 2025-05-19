package BatteryLogic;

import HardwareAbstraction.*;
import PersistenceManager.CalibrationData;
import PersistenceManager.SettingsStorage;

public class BatteryStateController {
    private final VoltageSensor voltageSensor;
    private final CalibrationData calib;
    private final int lowBatteryThreshold;

    public BatteryStateController(VoltageSimulator simulator){
        voltageSensor = new VoltageSensor(simulator);
        SettingsStorage storage = PersistenceManager.SettingsStorage.getInstance();
        calib = storage.readCalibVoltageToSoCFromDisc();
        lowBatteryThreshold = storage.readLowBatteryThresholdFromDisc();
    }

    public int calculateStateOfCharge(){
        double voltage = voltageSensor.readVoltage();
        if (calib == null || calib.getVoltageCalib().length != calib.getSoCCalib().length) {
            throw new IllegalStateException("Calibration data is not initialized.");
        }

        if (voltage >= calib.getVoltageCalib()[0]) {
            return calib.getSoCCalib()[0];
        }
        if (voltage <= calib.getVoltageCalib()[calib.getVoltageCalib().length - 1]) {
            return calib.getSoCCalib()[calib.getSoCCalib().length - 1];
        }

        if (voltage >= calib.getVoltageCalib()[0]) return calib.getSoCCalib()[0];
        else if (voltage >= calib.getVoltageCalib()[1]) return interpolate(voltage, calib.getVoltageCalib()[1], calib.getVoltageCalib()[0], calib.getSoCCalib()[1], calib.getSoCCalib()[0]);
        else if (voltage >= calib.getVoltageCalib()[2]) return interpolate(voltage, calib.getVoltageCalib()[2], calib.getVoltageCalib()[1], calib.getSoCCalib()[2], calib.getSoCCalib()[1]);
        else if (voltage >= calib.getVoltageCalib()[3]) return interpolate(voltage, calib.getVoltageCalib()[3], calib.getVoltageCalib()[2], calib.getSoCCalib()[3], calib.getSoCCalib()[2]);
        else if (voltage >= calib.getVoltageCalib()[4]) return interpolate(voltage, calib.getVoltageCalib()[4], calib.getVoltageCalib()[3], calib.getSoCCalib()[4], calib.getSoCCalib()[3]);
        else return 0;
    }

    private int interpolate(double x, double x0, double x1, int y0, int y1){
        return (int) (y0 + (y1 - y0) * (x - x0) / (x1 - x0));
    }

    public boolean isLowBattery(){
        return calculateStateOfCharge() <= lowBatteryThreshold;
    }
}
