package batteryLogic;

import hardwareAbstraction.VoltageSensor;
import persistenceManager.*;

class BatteryThresholdManager implements BatteryLogicConstants {

    private final BatteryStateCalculator calculator;
    private final VoltageSensor sensor;
    private int lowBatteryThreshold;

    BatteryThresholdManager(BatteryStateCalculator calculator, VoltageSensor sensor){
        this.calculator = calculator;
        this.sensor = sensor;
    }

    boolean isLowBattery(CalibrationData calib){
        return calculator.calculateStateOfCharge(sensor.readVoltage(), calib) <= lowBatteryThreshold;
    }

    void updateLowBatteryThreshold(int newThreshold){
        lowBatteryThreshold = newThreshold;
        SettingsStorage storage = SettingsStorage.getInstance();
        storage.setLowBatteryThreshold(newThreshold);
    }

    boolean isUndervoltageDetected(){
        return sensor.readVoltage() < UNDERVOLTAGE_LIMIT;
    }
}
