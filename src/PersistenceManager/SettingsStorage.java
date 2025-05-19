package PersistenceManager;

import java.util.List;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

public class SettingsStorage {

    private static SettingsStorage instance;

    private int lowBatteryThreshold;
    private double[] voltage;
    private int[] stateOfCharge;

    private static final Path CALIB_TXT_FILE = Path.of("C:\\Users\\laraa\\OneDrive - Technische Hochschule Deggendorf\\Studium\\Semester 4\\Software Engineering\\Projekt - Batteriestandkontrolle Rasierer\\Batteriestandskontrolle_Rasierer\\src\\resources\\calibVoltageToSoC.txt");
    private static final Path THRESHOLD_TXT_FILE = Path.of("C:\\Users\\laraa\\OneDrive - Technische Hochschule Deggendorf\\Studium\\Semester 4\\Software Engineering\\Projekt - Batteriestandkontrolle Rasierer\\Batteriestandskontrolle_Rasierer\\src\\resources\\threshold.txt");

    private SettingsStorage() {
        lowBatteryThreshold = 10;
        initialCalibration();
        saveSettings();
    }

    public static synchronized SettingsStorage getInstance() {
        if (instance == null) {
            instance = new SettingsStorage();
        }
        return instance;
    }

    private void initialCalibration() {
        voltage = new double[] { 4.2, 4.0, 3.75, 3.5, 3.0 };
        stateOfCharge = new int[] { 100, 80, 50, 20, 0 };
    }

    private void saveSettings() {
        writeCalibVoltageToSoCToDisc();
        writeLowBatteryThresholdToDisc();
    }

    private void writeCalibVoltageToSoCToDisc() {
        try {
            StringBuilder content = new StringBuilder();
            content.append("Voltage,SoC\n");

            for (int i = 0; i < voltage.length; i++) {
                if (voltage[i] < 2.5 || voltage[i] > 4.5) {
                    throw new IllegalArgumentException("Voltage must be between 2.5 and 4.5.");
                }

                content
                    .append(voltage[i])
                    .append(",")
                    .append(stateOfCharge[i])
                    .append("\n");
            }

            Files.writeString(CALIB_TXT_FILE, content.toString(), StandardCharsets.UTF_8);

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void writeLowBatteryThresholdToDisc() {
        try {
            Files.writeString(THRESHOLD_TXT_FILE, String.valueOf(lowBatteryThreshold), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public int readLowBatteryThresholdFromDisc() {
        try {
            String content = Files.readString(THRESHOLD_TXT_FILE, StandardCharsets.UTF_8);
            return Integer.parseInt(content.trim());
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
            return -1;
        }
    }

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
