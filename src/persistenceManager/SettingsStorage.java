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
    private static final double[] INITIAL_VOLTAGE_CALIB = new double[] { 4.2, 4.0, 3.75, 3.5, 3.0 };
    private static final int[] INITIAL_SOC_CALIB = new int[] { 100, 80, 50, 20, 0 };
    private static SettingsStorage instance;

    private final int lowBatteryThreshold;
    private double[] voltage;
    private int[] stateOfCharge;

    private static final Path CALIB_TXT_FILE = Paths.get("src", "resources", "calibVoltageToSoC.txt");
    private static final Path THRESHOLD_TXT_FILE = Paths.get("src", "resources", "threshold.txt");

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
    }

    private void saveSettings() {
        writeCalibVoltageToSoCToDisc();
        writeLowBatteryThresholdToDisc();
    }

    private void writeCalibVoltageToSoCToDisc() {
        validateVoltageRange();
        String csvContent = buildCsvContent();

        try {
            Files.writeString(CALIB_TXT_FILE, csvContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            handleWriteError(e);
        }
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
                    .append("\n");
        }

        return content.toString();
    }

    private void handleWriteError(IOException e) {
        e.printStackTrace();
        throw new UncheckedIOException("Failed to write calibration data to disk.", e);
    }

    private void writeLowBatteryThresholdToDisc() {
        try {
            Files.writeString(THRESHOLD_TXT_FILE, String.valueOf(lowBatteryThreshold), StandardCharsets.UTF_8);
        } catch (IOException e) {
            handleWriteError(e);
        }
    }

    /**
     * Reads the low battery threshold from the disk.
     * @return the low battery threshold
     */
    public int readLowBatteryThresholdFromDisc() {
        try {
            String content = Files.readString(THRESHOLD_TXT_FILE, StandardCharsets.UTF_8);
            return Integer.parseInt(content.trim());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Reads the voltage calibration data from the disk.
     * @return the voltage calibration data
     */
    public CalibrationData readCalibVoltageToSoCFromDisc() {
        try {
            List<String> lines = Files.readAllLines(CALIB_TXT_FILE, StandardCharsets.UTF_8);
            int size = lines.size() - 1;

            double[] voltage = new double[size];
            int[] stateOfCharge = new int[size];

            for (int i = 1; i < lines.size(); i++) {
                String[] parts = lines.get(i).split(",");
                voltage[i - 1] = Double.parseDouble(parts[0].trim());
                stateOfCharge[i - 1] = Integer.parseInt(parts[1].trim());
            }

            return new CalibrationData(voltage, stateOfCharge);

        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return null;
        }
    }
}
