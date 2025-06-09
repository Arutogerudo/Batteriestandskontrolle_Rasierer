package batteryLogic;

class InterpolationUtils {
    static int calculateInterpolatedValue(double voltage, double[] voltages, int[] values) {
        validate(voltages, values);
        if (voltage >= voltages[0]) return values[0];
        if (voltage <= voltages[voltages.length - 1]) return values[values.length - 1];

        return interpolate(voltage, voltages, values);
    }

    static double calculateInterpolatedValue(double voltage, double[] voltages, double[] values) {
        validate(voltages, values);
        if (voltage >= voltages[0]) return values[0];
        if (voltage <= voltages[voltages.length - 1]) return values[values.length - 1];

        return interpolate(voltage, voltages, values);
    }

    private static int interpolate(double voltage, double[] voltages, double[] values) {
        for (int i = 1; i < voltages.length; i++) {
            if (voltage >= voltages[i]) {
                return (int) ((int) values[i] + (values[i - 1] - values[i]) *
                                                (voltage - voltages[i]) / (voltages[i - 1] - voltages[i]));
            }
        }
        return 0;
    }

    private static int interpolate(double voltage, double[] voltages, int[] values) {
        for (int i = 1; i < voltages.length; i++) {
            if (voltage >= voltages[i]) {
                return (int) ( values[i] + (values[i - 1] - values[i]) *
                                                (voltage - voltages[i]) / (voltages[i - 1] - voltages[i]));
            }
        }
        return 0;
    }

    private static void validate(double[] v, int[] val) {
        if (v == null || val == null || v.length != val.length)
            throw new IllegalArgumentException("Calibration arrays must be same length and non-null");
    }

    private static void validate(double[] v, double[] val) {
        if (v == null || val == null || v.length != val.length)
            throw new IllegalArgumentException("Calibration arrays must be same length and non-null");
    }
}

