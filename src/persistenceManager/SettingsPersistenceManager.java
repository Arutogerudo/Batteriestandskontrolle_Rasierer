package persistenceManager;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

class SettingsPersistenceManager {
    private int lowBatteryThreshold;
    private static final Path CALIB_TXT_FILE = Paths.get("src", "resources", "calibVoltageToSoC.txt");
    private static final Path THRESHOLD_TXT_FILE = Paths.get("src", "resources", "threshold.txt");
    private static final Path CYCLE_COUNT_FILE = Paths.get("src", "resources", "cyclecount.txt");
    private final CalibrationData calibrationData;

    SettingsPersistenceManager(CalibrationData calibrationData){
        this.calibrationData = calibrationData;
        lowBatteryThreshold = 10;
    }

    void saveSettings() {
        writeCalibVoltageToSoCToRuntimeToDisc();
        writeLowBatteryThresholdToDisc();
        setChargeCycleCount(0);
    }

    void setChargeCycleCount(int count) {
        try {
            Files.writeString(CYCLE_COUNT_FILE, String.valueOf(count), StandardCharsets.UTF_8);
        } catch (IOException e) {
            handleWriteOrReadError(e);
        }
    }

    int readChargeCycleCount() {
        try {
            String content = Files.readString(CYCLE_COUNT_FILE, StandardCharsets.UTF_8);
            return Integer.parseInt(content.trim());
        } catch (IOException e) {
            handleWriteOrReadError(e);
            return -1;
        }
    }

    int readLowBatteryThresholdFromDisc() {
        try {
            String content = Files.readString(THRESHOLD_TXT_FILE, StandardCharsets.UTF_8);
            return Integer.parseInt(content.trim());
        } catch (IOException e) {
            handleWriteOrReadError(e);
            return -1;
        }
    }

    void setLowBatteryThreshold(int threshold) {
        lowBatteryThreshold = threshold;
        writeLowBatteryThresholdToDisc();
    }

    private void writeLowBatteryThresholdToDisc() {
        try {
            Files.writeString(THRESHOLD_TXT_FILE, String.valueOf(lowBatteryThreshold), StandardCharsets.UTF_8);
        } catch (IOException e) {
            handleWriteOrReadError(e);
        }
    }

    CalibrationData readCalibVoltageToSoCToRuntimeFromDisc() {
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

    void writeCalibVoltageToSoCToRuntimeToDisc() {
        String csvContent = buildCsvContent();

        try {
            Files.writeString(CALIB_TXT_FILE, csvContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            handleWriteOrReadError(e);
        }
    }

    private void handleWriteOrReadError(IOException e) {
        e.printStackTrace();
        throw new UncheckedIOException("Failed to write data to disk or read data from disk.", e);
    }

    private String buildCsvContent() {
        StringBuilder content = new StringBuilder("Voltage,SoC\n");
        double[] voltage = calibrationData.getVoltageCalib();
        int[] stateOfCharge = calibrationData.getSoCCalib();
        double[] runtime = calibrationData.getRuntime();
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
}
