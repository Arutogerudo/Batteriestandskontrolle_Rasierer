package batteryLogic;

import persistenceManager.*;

/**
 * Verantwortlich für die Überprüfung und Durchführung einer Neukalibrierung der geschätzten Restlaufzeit
 * basierend auf der Anzahl von Ladezyklen.
 */
public class CalibrationManager implements BatteryLogicConstants {
    private final SettingsStorage storage;
    private final BatteryStateController batteryController;

    private static final int CYCLE_THRESHOLD = 250;

    /**
     * To initialize the calibration manager.
     *
     * @param batteryController The responsible battery controller.
     */
    public CalibrationManager(BatteryStateController batteryController) {
        this.batteryController = batteryController;
        this.storage = SettingsStorage.getInstance();
    }

    /**
     * Performs a recalibration when the number of charging cycles has reached the threshold value.
     * The saved remaining runtime is adjusted depending on usage.
     * The updated calibration data is then saved persistently.
     */
    public void recalibrateIfNeeded() {
        if (batteryController.chargeCycleCount < CYCLE_THRESHOLD) return;

        CalibrationData oldCalib = storage.readCalibVoltageToSoCToRuntimeFromDisc();
        if (oldCalib == null) {
            System.err.println("Kalibrierdaten konnten nicht gelesen werden.");
            return;
        }

        storage.setRuntimeCalib(adjustRuntime(oldCalib.getRuntime(), batteryController.chargeCycleCount));
        storage.writeCalibVoltageToSoCToRuntimeToDisc();
        System.out.println("Kalibrierung wurde nach " + batteryController.chargeCycleCount + " Zyklen angepasst.");
        batteryController.chargeCycleCount = 0;
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
