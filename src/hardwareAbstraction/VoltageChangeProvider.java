package hardwareAbstraction;

interface VoltageChangeProvider {
    double CHARGE_RATE = 0.0033;
    double ACTIVE_DISCHARGE_RATE = 0.004;
    double PASSIVE_DISCHARGE_RATE = 0.0001;
    double POWER_SAVING_MODE = 0.00001;

    double getVoltageChange();
}

