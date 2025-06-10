package batteryLogic;

import hardwareAbstraction.VoltageSensor;
import persistenceManager.*;

class BatteryThresholdManager implements BatteryLogicConstants {

    private final BatteryStateCalculator calculator;
    private final VoltageSensor sensor;

    BatteryThresholdManager(BatteryStateCalculator calculator, VoltageSensor sensor) {
        this.calculator = calculator;
        this.sensor = sensor;
    }

    boolean isLowBattery(CalibrationData calib) {
        return calculator.calculateStateOfCharge(sensor.readVoltage(), calib) <= SettingsStorage.getInstance().readLowBatteryThresholdFromDisc();
    }

    void updateLowBatteryThreshold(int newThreshold) {
        SettingsStorage.getInstance().setLowBatteryThreshold(newThreshold);
    }

    boolean isUndervoltageDetected() {
        return sensor.readVoltage() < UNDERVOLTAGE_LIMIT;
    }
}
