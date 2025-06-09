package batteryLogic;

import hardwareAbstraction.*;
import persistenceManager.CalibrationData;
import persistenceManager.SettingsStorage;

/**
 * Singleton-Klasse zur Verwaltung des Batteriezustands und zur Berechnung des Ladezustands (SoC)
 */
public class BatteryStateController implements BatteryLogicConstants {
    private static BatteryStateController instance;

    private final VoltageSensor voltageSensor;
    private final CalibrationData calib;
    private final SettingsStorage storage;
    private int lowBatteryThreshold;

    int chargeCycleCount;
    private boolean dischargedDetected = false;

    /**
     * Privater Konstruktor, um Singleton zu erzwingen.
     * @param simulator Der Spannungssimulator zum Auslesen der Batteriespannung
     */
    private BatteryStateController(VoltageSimulator simulator) {
        voltageSensor = new VoltageSensor(simulator);
        storage = SettingsStorage.getInstance();
        calib = storage.readCalibVoltageToSoCToRuntimeFromDisc();
        lowBatteryThreshold = storage.readLowBatteryThresholdFromDisc();

        chargeCycleCount = storage.readChargeCycleCount();
    }

    /**
     * Erstellt die Singleton-Instanz.
     * @param simulator Der Simulator, der für die Initialisierung benötigt wird (nur beim ersten Aufruf erforderlich)
     */
    public static synchronized void initInstance(VoltageSimulator simulator) {
        if (instance == null) {
            instance = new BatteryStateController(simulator);
        }
    }

    /**
     * Gibt die Singleton-Instanz zurück.
     * @return Die Singleton-Instanz.
     */
    public static BatteryStateController getInstance() {
        if (instance == null) throw new IllegalStateException("Instance not initialized");
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
        return InterpolationUtils.calculateInterpolatedValue(voltage, voltages, socValues);
    }

    /**
     * Berechnet die geschätzte Restlaufzeit (in Minuten) basierend auf der Spannung.
     * @param voltage aktuelle Batteriespannung
     * @return geschätzte Restlaufzeit in Minuten
     */
    public double calculateRemainingRuntime(double voltage) {
        double[] voltages = calib.getVoltageCalib();
        double[] runtimes = calib.getRuntime();
        return InterpolationUtils.calculateInterpolatedValue(voltage, voltages, runtimes);
    }

    /**
     * Checks if the current battery state of charge (SoC) is below the defined low battery threshold.
     *
     * @return {@code true} if SoC is below the threshold, otherwise {@code false}.
     */
    public boolean isLowBattery(){
        return calculateStateOfCharge(voltageSensor.readVoltage()) <= lowBatteryThreshold;
    }

    /**
     * Updates the low battery threshold and persists the new value to storage.
     *
     * @param newThreshold The new threshold value for low battery detection.
     */
    public void updateLowBatteryThreshold(int newThreshold){
        lowBatteryThreshold = newThreshold;
        storage.setLowBatteryThreshold(newThreshold);
    }

    /**
     * Checks if the current battery voltage has fallen below the critical undervoltage limit.
     *
     * @return {@code true} if the current voltage is below the undervoltage limit, otherwise {@code false}.
     */
    public boolean isUndervoltageDetected(){
        return voltageSensor.readVoltage() < UNDERVOLTAGE_LIMIT;
    }

    /**
     * Monitors the charge cycle by tracking when a full discharge and subsequent full charge occurs.
     * Increments the charge cycle count and stores it persistently.
     */
    public void monitorChargeCycle() {
        int soc = calculateStateOfCharge(voltageSensor.readVoltage());

        if (isNewDischargeDetected(soc)) {
            dischargedDetected = true;
            return;
        }

        if (isNewChargeCycleComplete(soc)) {
            chargeCycleCount++;
            dischargedDetected = false;
            storage.setChargeCycleCount(chargeCycleCount);
        }
    }

    private boolean isNewDischargeDetected(int soc) {
        return !dischargedDetected && soc <= DISCHARGED_THRESHOLD;
    }

    private boolean isNewChargeCycleComplete(int soc) {
        return dischargedDetected && soc >= FULLY_CHARGED_THRESHOLD;
    }
}