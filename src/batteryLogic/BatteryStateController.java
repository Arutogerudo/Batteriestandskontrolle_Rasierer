package batteryLogic;

import hardwareAbstraction.*;
import persistenceManager.CalibrationData;
import persistenceManager.SettingsStorage;

/**
 * Singleton-Klasse zur Verwaltung des Batteriezustands und zur Berechnung des Ladezustands (SoC)
 */
public class BatteryStateController {
    private static final double UNDERVOLTAGE_LIMIT = 2.8;
    private static BatteryStateController instance;

    private final VoltageSensor voltageSensor;
    private final CalibrationData calib;
    private final SettingsStorage storage;
    private int lowBatteryThreshold;

    /**
     * Privater Konstruktor, um Singleton zu erzwingen.
     * @param simulator Der Spannungssimulator zum Auslesen der Batteriespannung
     */
    private BatteryStateController(VoltageSimulator simulator) {
        voltageSensor = new VoltageSensor(simulator);
        storage = SettingsStorage.getInstance();
        calib = storage.readCalibVoltageToSoCFromDisc();
        lowBatteryThreshold = storage.readLowBatteryThresholdFromDisc();
    }

    /**
     * Gibt die Singleton-Instanz zurück oder erstellt sie bei Bedarf.
     * @param simulator Der Simulator, der für die Initialisierung benötigt wird (nur beim ersten Aufruf erforderlich)
     * @return Die Singleton-Instanz von BatteryStateController
     */
    public static synchronized BatteryStateController getInstance(VoltageSimulator simulator) {
        if (instance == null) {
            instance = new BatteryStateController(simulator);
        }
        return instance;
    }

    /**
     * Berechnet den Ladezustand (SoC) basierend auf der Spannung.
     * @param voltage Spannung in Volt
     * @return Ladezustand als Prozentwert
     */
    public int calculateStateOfCharge(double voltage) {
        double[] voltages = calib.getVoltageCalib();
        int[] socValues = calib.getSoCCalib();
        return calculateInterpolatedValue(voltage, voltages, socValues);
    }

    /**
     * Berechnet die geschätzte Restlaufzeit (in Minuten) basierend auf der Spannung.
     * @param voltage aktuelle Batteriespannung
     * @return geschätzte Restlaufzeit in Minuten
     */
    public int calculateRemainingRuntime(double voltage) {
        double[] voltages = calib.getVoltageCalib();
        int[] runtimes = calib.getRuntime();
        return calculateInterpolatedValue(voltage, voltages, runtimes);
    }

    private int calculateInterpolatedValue(double voltage, double[] voltages, int[] values) {
        validateCalibrationData(voltages, values);

        if (voltage >= voltages[0]) {
            return values[0];
        }

        if (voltage <= voltages[voltages.length - 1]) {
            return values[values.length - 1];
        }

        return interpolateBetweenPoints(voltage, voltages, values);
    }



    private void validateCalibrationData(double[] voltages, int[] socValues_or_runtimes) {
        if (voltages == null || socValues_or_runtimes == null || voltages.length != socValues_or_runtimes.length) {
            throw new IllegalStateException("Calibration data is not initialized.");
        }
    }

    private int interpolateBetweenPoints(double voltage, double[] voltages, int[] ys) {
        for (int i = 1; i < voltages.length; i++) {
            if (voltage >= voltages[i]) {
                return interpolate(voltage, voltages[i], voltages[i - 1], ys[i], ys[i - 1]);
            }
        }
        return 0;
    }

    private int interpolate(double x, double x0, double x1, int y0, int y1){
        return (int) (y0 + (y1 - y0) * (x - x0) / (x1 - x0));
    }

    public boolean isLowBattery(){
        return calculateStateOfCharge(voltageSensor.readVoltage()) <= lowBatteryThreshold;
    }

    public void updateLowBatteryThreshold(int newThreshold){
        lowBatteryThreshold = newThreshold;
        storage.setLowBatteryThreshold(newThreshold);
    }

    public boolean isUndervoltageDetected(){
        return voltageSensor.readVoltage() < UNDERVOLTAGE_LIMIT;
    }
}
