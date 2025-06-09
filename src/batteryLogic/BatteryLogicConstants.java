package batteryLogic;

interface BatteryLogicConstants {
    double LOWER_TEMP_LIMIT = 15.0;
    double UPPER_TEMP_LIMIT = 45.0;
    double NORMAL_TEMP = 25.0;
    int RANDOM_RANGE = 41;
    int UPDATE_TIME = 5000;
    double DISCOUNT_PER_CYCLE = 0.0002;
    double UNDERVOLTAGE_LIMIT = 2.8;
    int DISCHARGED_THRESHOLD = 20;
    int FULLY_CHARGED_THRESHOLD = 80;
}
