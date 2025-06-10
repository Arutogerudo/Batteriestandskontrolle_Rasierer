package batteryLogic;

import hardwareAbstraction.VoltageSensor;
import persistenceManager.CalibrationData;
import persistenceManager.SettingsStorage;

class BatteryChargeCycleMonitor implements BatteryLogicConstants {
    private boolean dischargedDetected = false;
    private final SettingsStorage storage;

    BatteryChargeCycleMonitor(){
        storage = SettingsStorage.getInstance();
    }

    void monitorChargeCycle(VoltageSensor sensor, CalibrationData calib, BatteryStateCalculator calculator) {
        int soc = calculator.calculateStateOfCharge(sensor.readVoltage(), calib);
        int chargeCycleCount = storage.readChargeCycleCount();

        if (isNewDischargeDetected(soc)) {
            dischargedDetected = true;
            return;
        }

        if (isNewChargeCycleComplete(soc)) {
            storage.setChargeCycleCount(++chargeCycleCount);
            dischargedDetected = false;
        }
    }

    private boolean isNewDischargeDetected(int soc) {
        return !dischargedDetected && soc <= DISCHARGED_THRESHOLD;
    }

    private boolean isNewChargeCycleComplete(int soc) {
        return dischargedDetected && soc >= FULLY_CHARGED_THRESHOLD;
    }
}
