package batteryLogic;

import persistenceManager.*;

/**
 * Verantwortlich für die Überprüfung und Durchführung einer Neukalibrierung der geschätzten Restlaufzeit
 * basierend auf der Anzahl von Ladezyklen.
 */
public class CalibrationManager implements BatteryLogicConstants {
    private final SettingsStorage storage;
    private int number_recalibrations;
    private static CalibrationManager instance;

    private static final int CYCLE_THRESHOLD = 250;

    /**
     * Returns the singleton instance of CalibrationManager.
     * @return the singleton instance of CalibrationManager
     */
    public static synchronized CalibrationManager getInstance() {
        if (instance == null) {
            instance = new CalibrationManager();
        }
        return instance;
    }

    /**
     * To initialize the calibration manager.
     */
    private CalibrationManager() {
        this.storage = SettingsStorage.getInstance();
        number_recalibrations = 1;
    }

    /**
     * Performs a recalibration when the number of charging cycles has reached the threshold value.
     * The saved remaining runtime is adjusted depending on usage.
     * The updated calibration data is then saved persistently.
     */
    public void recalibrateIfNeeded() {
        if (storage.readChargeCycleCount() / CYCLE_THRESHOLD < number_recalibrations){
            return;
        }

        storage.setRuntimeFullCharge(adjustRuntime(storage.readRuntimeFullChargeFromDisc(), storage.readChargeCycleCount() / number_recalibrations));
        System.out.println("Kalibrierung wurde nach " + storage.readChargeCycleCount() + " Zyklen angepasst.");
        number_recalibrations++;
    }


    private double adjustRuntime(double oldRuntime, int cycles) {
        double degradationFactor = 1 - DISCOUNT_PER_CYCLE * cycles;

        return Math.round(oldRuntime * degradationFactor);
    }
}
