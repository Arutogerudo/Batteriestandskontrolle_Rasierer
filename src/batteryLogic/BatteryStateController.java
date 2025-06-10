package batteryLogic;

import hardwareAbstraction.*;
import persistenceManager.CalibrationData;
import persistenceManager.SettingsStorage;

/**
 * Singleton-Klasse zur Verwaltung des Batteriezustands und zur Berechnung des Ladezustands (SoC)
 */
public class BatteryStateController implements BatteryLogicConstants {
    private static BatteryStateController instance;

    private final BatteryStateCalculator calculator;
    private final BatteryChargeCycleMonitor cycleMonitor;
    private final VoltageSensor voltageSensor;
    private final CalibrationData calib;
    private final BatteryThresholdManager thresholdManager;

    /**
     * Privater Konstruktor, um Singleton zu erzwingen.
     * @param simulator Der Spannungssimulator zum Auslesen der Batteriespannung
     */
    private BatteryStateController(VoltageSimulator simulator) {
        calculator = new BatteryStateCalculator();
        cycleMonitor = new BatteryChargeCycleMonitor();
        voltageSensor = new VoltageSensor(simulator);
        SettingsStorage storage = SettingsStorage.getInstance();
        calib = storage.readCalibVoltageToSoCToRuntimeFromDisc();
        thresholdManager = new BatteryThresholdManager(calculator, voltageSensor);
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
        return calculator.calculateStateOfCharge(voltage, calib);
    }

    /**
     * Berechnet die geschätzte Restlaufzeit (in Minuten) basierend auf der Spannung.
     * @param voltage aktuelle Batteriespannung
     * @return geschätzte Restlaufzeit in Minuten
     */
    public double calculateRemainingRuntime(double voltage) {
        return calculator.calculateRemainingRuntime(voltage, calib);
    }

    /**
     * Checks if the current battery state of charge (SoC) is below the defined low battery threshold.
     *
     * @return {@code true} if SoC is below the threshold, otherwise {@code false}.
     */
    public boolean isLowBattery(){
        return thresholdManager.isLowBattery(calib);
    }

    /**
     * Updates the low battery threshold and persists the new value to storage.
     *
     * @param newThreshold The new threshold value for low battery detection.
     */
    public void updateLowBatteryThreshold(int newThreshold){
        thresholdManager.updateLowBatteryThreshold(newThreshold);
    }

    /**
     * Checks if the current battery voltage has fallen below the critical undervoltage limit.
     *
     * @return {@code true} if the current voltage is below the undervoltage limit, otherwise {@code false}.
     */
    public boolean isUndervoltageDetected(){
        return thresholdManager.isUndervoltageDetected();
    }

    /**
     * Monitors the charge cycle by tracking when a full discharge and subsequent full charge occurs.
     * Increments the charge cycle count and stores it persistently.
     */
    public void monitorChargeCycle() {
        cycleMonitor.monitorChargeCycle(voltageSensor, calib, calculator);
    }
}