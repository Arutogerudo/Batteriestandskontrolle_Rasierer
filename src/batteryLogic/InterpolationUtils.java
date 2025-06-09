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
                double v1 = voltages[i - 1];
                double v2 = voltages[i];
                double val1 = values[i - 1].doubleValue();
                double val2 = values[i].doubleValue();

                return val2 + (val1 - val2) * (voltage - v2) / (v1 - v2);
            }
        }
        return 0;
    }

    private static <T> void validate(double[] v, T[] val) {
        if (v == null || val == null || v.length != val.length)
            throw new IllegalArgumentException("Calibration arrays must be same length and non-null");
    }
}
