package batteryLogic;

import persistenceManager.CalibrationData;

class BatteryStateCalculator {

    int calculateStateOfCharge(double voltage, CalibrationData calib) {
        double[] voltages = calib.getVoltageCalib();
        int[] socValues = calib.getSoCCalib();
        Integer[] soc = toIntegerArray(socValues);
        return (int) InterpolationUtils.calculateInterpolatedValue(voltage, voltages, soc);
    }

    double calculateRemainingRuntime(double voltage, CalibrationData calib) {
        double[] voltages = calib.getVoltageCalib();
        double[] runtimes = calib.getRuntime();
        Double[] runtime = toDoubleArray(runtimes);
        return InterpolationUtils.calculateInterpolatedValue(voltage, voltages, runtime);
    }

    private Double[] toDoubleArray(double[] array) {
        Double[] result = new Double[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];  // Autoboxing von double zu Double
        }
        return result;
    }

    private Integer[] toIntegerArray(int[] array) {
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i]; // Autoboxing von int zu Integer
        }
        return result;
    }
}
