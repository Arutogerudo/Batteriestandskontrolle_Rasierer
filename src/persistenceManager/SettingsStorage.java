package persistenceManager;

import java.io.UncheckedIOException;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Singleton class to manage settings related to battery calibration and low battery threshold.
 */
public class SettingsStorage {

    private static final double[] INITIAL_VOLTAGE_CALIB = new double[] { 4.2, 3.9, 3.6, 3.3, 3.0 };
    private static final int[] INITIAL_SOC_CALIB = new int[] { 100, 80, 50, 20, 0 };
    private static final double[] INITIAL_RUNTIME_CALIB = new double[] { 50, 40, 25, 10, 0 };

    private static SettingsStorage instance;

    private final CalibrationData calibrationData;
    private final SettingsPersistenceManager persistenceManager;

    private SettingsStorage() {
        calibrationData = new CalibrationData(INITIAL_VOLTAGE_CALIB, INITIAL_SOC_CALIB, INITIAL_RUNTIME_CALIB);
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
     * Writes the voltage, state of charge, and runtime calibration data to disk.
     */
    public void writeCalibVoltageToSoCToRuntimeToDisc() {
        persistenceManager.writeCalibVoltageToSoCToRuntimeToDisc();
    }

    /**
     * Sets a new low battery threshold value and persists it to disk.
     *
     * @param threshold the threshold value in percentage (0-100)
     */
    public void setLowBatteryThreshold(int threshold) {
        persistenceManager.setLowBatteryThreshold(threshold);
    }


    /**
     * Reads the low battery threshold from the disk.
     * @return the low battery threshold
     */
    public int readLowBatteryThresholdFromDisc() {
        return persistenceManager.readLowBatteryThresholdFromDisc();
    }

    /**
     * Reads the voltage calibration data from the disk.
     * @return the voltage calibration data
     */
    public CalibrationData readCalibVoltageToSoCToRuntimeFromDisc() {
        return persistenceManager.readCalibVoltageToSoCToRuntimeFromDisc();
    }

    /**
     * Sets and saves the current battery charge cycle count.
     *
     * @param count the number of charge cycles
     */
    public void setChargeCycleCount(int count) {
        persistenceManager.setChargeCycleCount(count);
    }

    /**
     * Reads the stored charge cycle count from disk.
     *
     * @return the number of charge cycles, or -1 on failure
     */
    public int readChargeCycleCount() {
        return persistenceManager.readChargeCycleCount();
    }

    /**
     * Sets the runtime calibration values.
     *
     * @param runtime array of runtime calibration values
     */
    public void setRuntimeCalib(double[] runtime) {
        calibrationData.setRuntimeCalib(runtime);
    }
}
