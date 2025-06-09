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

    private static final double MIN_VOLTAGE = 3;
    private static final double MAX_VOLTAGE = 4.2;
    private static final double[] INITIAL_VOLTAGE_CALIB = new double[] { 4.2, 3.9, 3.6, 3.3, 3.0 };
    private static final int[] INITIAL_SOC_CALIB = new int[] { 100, 80, 50, 20, 0 };
    private static final double[] INITIAL_RUNTIME_CALIB = new double[] { 50, 40, 25, 10, 0 };

    private static SettingsStorage instance;

    private int lowBatteryThreshold;
    private double[] voltage;
    private int[] stateOfCharge;
    private double[] runtime;

    private static final Path CALIB_TXT_FILE = Paths.get("src", "resources", "calibVoltageToSoC.txt");
    private static final Path THRESHOLD_TXT_FILE = Paths.get("src", "resources", "threshold.txt");
    private static final Path CYCLE_COUNT_FILE = Paths.get("src", "resources", "cyclecount.txt");

    private SettingsStorage() {
        lowBatteryThreshold = 10;
        initialCalibration();
        saveSettings();
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

    private void initialCalibration() {
        voltage = INITIAL_VOLTAGE_CALIB;
        stateOfCharge = INITIAL_SOC_CALIB;
        runtime = INITIAL_RUNTIME_CALIB;
    }

    private void saveSettings() {
        writeCalibVoltageToSoCToRuntimeToDisc();
        writeLowBatteryThresholdToDisc();
        setChargeCycleCount(0);
    }

    /**
     * Writes the voltage, state of charge, and runtime calibration data to disk.
     */
    public void writeCalibVoltageToSoCToRuntimeToDisc() {
        validateVoltageRange();
        String csvContent = buildCsvContent();

        try {
            Files.writeString(CALIB_TXT_FILE, csvContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            handleWriteOrReadError(e);
        }
    }

    /**
     * Sets the runtime calibration values.
     *
     * @param runtime array of runtime calibration values
     */
    public void setRuntimeCalib(double[] runtime){
        this.runtime = runtime;
    }

    private void validateVoltageRange() {
        for (double v : voltage) {
            if (v < MIN_VOLTAGE || v > MAX_VOLTAGE) {
                throw new IllegalArgumentException("Voltage must be between 3 and 4.2.");
            }
        }
    }

    private String buildCsvContent() {
        StringBuilder content = new StringBuilder("Voltage,SoC\n");

        for (int i = 0; i < voltage.length; i++) {
            content
                    .append(voltage[i])
                    .append(",")
                    .append(stateOfCharge[i])
                    .append(",")
                    .append(runtime[i])
                    .append("\n");
        }

        return content.toString();
    }

    private void handleWriteOrReadError(IOException e) {
        e.printStackTrace();
        throw new UncheckedIOException("Failed to write data to disk or read data from disk.", e);
    }

    private void writeLowBatteryThresholdToDisc() {
        try {
            Files.writeString(THRESHOLD_TXT_FILE, String.valueOf(lowBatteryThreshold), StandardCharsets.UTF_8);
        } catch (IOException e) {
            handleWriteOrReadError(e);
        }
    }

    /**
     * Sets a new low battery threshold value and persists it to disk.
     *
     * @param threshold the threshold value in percentage (0-100)
     */
    public void setLowBatteryThreshold(int threshold) {
        lowBatteryThreshold = threshold;
        writeLowBatteryThresholdToDisc();
    }


    /**
     * Reads the low battery threshold from the disk.
     * @return the low battery threshold
     */
    public int readLowBatteryThresholdFromDisc() {
        try {
            String content = Files.readString(THRESHOLD_TXT_FILE, StandardCharsets.UTF_8);
            return Integer.parseInt(content.trim());
        } catch (IOException e) {
            handleWriteOrReadError(e);
            return -1;
        }
    }

    /**
     * Reads the voltage calibration data from the disk.
     * @return the voltage calibration data
     */
    public CalibrationData readCalibVoltageToSoCToRuntimeFromDisc() {
        try {
            List<String> lines = Files.readAllLines(CALIB_TXT_FILE, StandardCharsets.UTF_8).subList(1, Files.readAllLines(CALIB_TXT_FILE).size());

            double[] voltage = new double[lines.size()];
            int[] stateOfCharge = new int[lines.size()];
            double[] runtime = new double[lines.size()];

            for (int i = 0; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                voltage[i] = Double.parseDouble(parts[0].trim());
                stateOfCharge[i] = Integer.parseInt(parts[1].trim());
                runtime[i] = Double.parseDouble(parts[2].trim());
            }

            return new CalibrationData(voltage, stateOfCharge, runtime);
        } catch (IOException e) {
            handleWriteOrReadError(e);
            return null;
        }
    }

    /**
     * Sets and saves the current battery charge cycle count.
     *
     * @param count the number of charge cycles
     */
    public void setChargeCycleCount(int count) {
        try {
            Files.writeString(CYCLE_COUNT_FILE, String.valueOf(count), StandardCharsets.UTF_8);
        } catch (IOException e) {
            handleWriteOrReadError(e);
        }
    }

    /**
     * Reads the stored charge cycle count from disk.
     *
     * @return the number of charge cycles, or -1 on failure
     */
    public int readChargeCycleCount() {
        try {
            String content = Files.readString(CYCLE_COUNT_FILE, StandardCharsets.UTF_8);
            return Integer.parseInt(content.trim());
        } catch (IOException e) {
            handleWriteOrReadError(e);
            return -1;
        }
    }

}
