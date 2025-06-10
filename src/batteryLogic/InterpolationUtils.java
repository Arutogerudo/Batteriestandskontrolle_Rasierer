package batteryLogic;

class InterpolationUtils {

    /**
     * Generische Interpolationsmethode für Wrapper-Arrays.
     * @param voltage aktueller Spannungswert
     * @param voltages Spannungswerte (absteigend sortiert)
     * @param values zugehörige Werte zum Interpolieren
     * @param <T> Wrapper-Typ Number (Integer, Double, etc.)
     * @return interpolierter Wert als double
     */
    static <T extends Number> double calculateInterpolatedValue(double voltage, double[] voltages, T[] values) {
        validate(voltages, values);

        if (voltage >= voltages[0]) return values[0].doubleValue();
        if (voltage <= voltages[voltages.length - 1]) return values[values.length - 1].doubleValue();

        for (int i = 1; i < voltages.length; i++) {
            if (voltage >= voltages[i]) {
                return linearInterpolate(
                        voltage,
                        voltages[i], voltages[i - 1],
                        values[i].doubleValue(), values[i - 1].doubleValue()
                );
            }
        }

        return 0;
    }

    private static double linearInterpolate(double x, double x0, double x1, double y0, double y1) {
        return y0 + (y1 - y0) * (x - x0) / (x1 - x0);
    }

    private static <T extends Number> void validate(double[] v, T[] val) {
        if (v == null || val == null || v.length != val.length)
            throw new IllegalArgumentException("Calibration arrays must be same length and non-null");
    }
}
