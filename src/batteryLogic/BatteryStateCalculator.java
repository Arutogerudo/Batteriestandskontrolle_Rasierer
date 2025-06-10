package batteryLogic;

import persistenceManager.CalibrationData;
import persistenceManager.SettingsStorage;

class BatteryStateCalculator {

    int calculateStateOfCharge(double voltage, CalibrationData calib) {
        double[] voltages = calib.getVoltageCalib();
        int[] socValues = calib.getSoCCalib();
        Integer[] soc = toIntegerArray(socValues);
        return (int) InterpolationUtils.calculateInterpolatedValue(voltage, voltages, soc);
    }

    double calculateRemainingRuntime(double voltage, CalibrationData calib) {
        double[] voltageCalib = calib.getVoltageCalib();
        double[] voltages = new double[] {voltageCalib[0], voltageCalib[voltageCalib.length - 1]};
        Double[] runtimes = new Double[] {SettingsStorage.getInstance().readRuntimeFullChargeFromDisc(), 0.0};
        return InterpolationUtils.calculateInterpolatedValue(voltage, voltages, runtimes);
    }

    private Integer[] toIntegerArray(int[] array) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
}
