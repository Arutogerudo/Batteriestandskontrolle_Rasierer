package persistenceManager;

/**
 * Singleton class to manage settings related to battery calibration and low battery threshold.
 */
public class SettingsStorage {

    private static final double[] INITIAL_VOLTAGE_CALIB = new double[] { 4.2, 3.9, 3.6, 3.3, 3.0 };
    private static final int[] INITIAL_SOC_CALIB = new int[] { 100, 80, 50, 20, 0 };

    private static SettingsStorage instance;

    private final SettingsPersistenceManager persistenceManager;

    private SettingsStorage() {
        CalibrationData calibrationData = new CalibrationData(INITIAL_VOLTAGE_CALIB, INITIAL_SOC_CALIB);
        persistenceManager = new SettingsPersistenceManager(calibrationData);
        persistenceManager.saveSettings();
    }

    /**
     * Returns the singleton instance of SettingsStorage.
     * @return the singleton instance of SettingsStorage
     */
    public static synchronized SettingsStorage getInstance() {
        if (instance == null) {
            instance = new SettingsStorage();
        }
        return instance;
    }

    /**
     * Sets a new runtime value and persists it to disk.
     *
     * @param newRuntimeFullCharge the recalibrated operation runtime if the battery is fully charged
     */
    public synchronized void setRuntimeFullCharge(double newRuntimeFullCharge) {
        persistenceManager.setRuntimeFullCharge(newRuntimeFullCharge);
    }


    /**
     * Reads the runtime, which is available if the battery is fully charged, from the disk.
     * @return the runtime if battery is fully charged
     */
    public synchronized double readRuntimeFullChargeFromDisc() {
        return persistenceManager.readRuntimeFullChargeFromDisc();
    }

    /**
     * Sets a new low battery threshold value and persists it to disk.
     *
     * @param threshold the threshold value in percentage (0-100)
     */
    public synchronized void setLowBatteryThreshold(int threshold) {
        persistenceManager.setLowBatteryThreshold(threshold);
    }


    /**
     * Reads the low battery threshold from the disk.
     * @return the low battery threshold
     */
    public synchronized int readLowBatteryThresholdFromDisc() {
        return persistenceManager.readLowBatteryThresholdFromDisc();
    }

    /**
     * Reads the voltage calibration data from the disk.
     * @return the voltage calibration data
     */
    public synchronized CalibrationData readCalibVoltageToSoCToRuntimeFromDisc() {
        return persistenceManager.readCalibVoltageToSoCFromDisc();
    }

    /**
     * Sets and saves the current battery charge cycle count.
     *
     * @param count the number of charge cycles
     */
    public synchronized void setChargeCycleCount(int count) {
        persistenceManager.setChargeCycleCount(count);
    }

    /**
     * Reads the stored charge cycle count from disk.
     *
     * @return the number of charge cycles, or -1 on failure
     */
    public synchronized int readChargeCycleCount() {
        return persistenceManager.readChargeCycleCount();
    }
}
