package batteryLogic;

import persistenceManager.*;

public class CalibrationManager {

    private static final double DISCOUNT_PER_CYCLE = 0.0002;
    private final SettingsStorage storage;
    private final BatteryStateController batteryController;

    private static final int CYCLE_THRESHOLD = 250;

    public CalibrationManager(BatteryStateController batteryController) {
        this.batteryController = batteryController;
        this.storage = SettingsStorage.getInstance();
    }

    public void recalibrateIfNeeded() {
        int cycles = batteryController.chargeCycleCount;

        if (cycles >= CYCLE_THRESHOLD) {
            CalibrationData oldCalib = storage.readCalibVoltageToSoCToRuntimeFromDisc();
            if (oldCalib == null) {
                System.err.println("Kalibrierdaten konnten nicht gelesen werden.");
                return;
            }

            double[] oldRuntime = oldCalib.getRuntime();
            double[] adjustedRuntime = adjustRuntime(oldRuntime, cycles);

            storage.setRuntimeCalib(adjustedRuntime);
            storage.writeCalibVoltageToSoCToRuntimeToDisc();
            System.out.println("Kalibrierung wurde nach " + cycles + " Zyklen angepasst.");
            batteryController.chargeCycleCount = 0;
        }
    }

    private double[] adjustRuntime(double[] oldRuntime, int cycles) {
        double[] newRuntime = new double[oldRuntime.length];
        double degradationFactor = 1 - DISCOUNT_PER_CYCLE * cycles;

        for (int i = 0; i < oldRuntime.length; i++) {
            newRuntime[i] = Math.round(oldRuntime[i] * degradationFactor);
        }

        return newRuntime;
    }
}
