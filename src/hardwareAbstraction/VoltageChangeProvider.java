package hardwareAbstraction;

interface VoltageChangeProvider {
    double CHARGE_RATE = 0.00033;
    double ACTIVE_DISCHARGE_RATE = 0.0004;
    double PASSIVE_DISCHARGE_RATE = 0.00001;
    double POWER_SAVING_MODE = 0.000001;

    double getVoltageChange();
}

