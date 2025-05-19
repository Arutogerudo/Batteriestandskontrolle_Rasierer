package hardwareAbstraction;

public class VoltageSimulator {
    private double voltage;  
    private ChargingState state;

    private static final double ACTIVE_DISCHARGE_RATE = 0.0004; // (4.2-3) / 50 Min / 60 sProMin -> 1 volle Ladung reicht für 50 Min. Betrieb
    private static final double PASSIVE_DISCHARGE_RATE = 0.00001;
    private static final double CHARGE_RATE = 0.00033; // 1 volle Ladung dauert 60 Min

    private static final double MIN_VOLTAGE = 3.0;
    private static final double MAX_VOLTAGE = 4.2;

    public VoltageSimulator() {
        this.voltage = 4.2;
        this.state = ChargingState.DISCHARGING_PASSIVE;
    }

    public void setState(ChargingState newState) {
        this.state = newState;
    }

    public void tick() {
        switch (state) {
            case DISCHARGING_ACTIVE:
                voltage -= ACTIVE_DISCHARGE_RATE;
                break;
            case DISCHARGING_PASSIVE:
                voltage -= PASSIVE_DISCHARGE_RATE;
                break;
            case CHARGING:
                voltage += CHARGE_RATE;
                break;
        }

        if (voltage > MAX_VOLTAGE) voltage = MAX_VOLTAGE;
        if (voltage < MIN_VOLTAGE) voltage = MIN_VOLTAGE;
    }

    double getVoltage() {
        return voltage;
    }
}

