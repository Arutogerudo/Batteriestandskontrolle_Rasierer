package batteryLogic;

import java.util.Random;

/**
 * Simulates Temperature Sensor.
 */
public class TemperatureSimulator extends Thread implements ITemperatureSimulator {
    private static final double LOWER_TEMP_LIMIT = 15.0;
    private static final double UPPER_TEMP_LIMIT = 45.0;
    private static final double NORMAL_TEMP = 25.0;
    private static final int RANDOM_RANGE = 41;
    private static final int UPDATE_TIME = 5000;
    private double temperature;

    /**
     * Simulator for temperature.
     */
    public TemperatureSimulator() {
        this.temperature = NORMAL_TEMP;
    }

    private final Random random = new Random();

    @Override
    public void run() {
        while (true) {
            this.setTemperature(10 + random.nextDouble(RANDOM_RANGE));
            System.out.println("Aktuelle Temperatur: " + this.temperature + "°C");
            try {
                Thread.sleep(UPDATE_TIME);
            } catch (InterruptedException e) {
                System.out.println("Temperatur-Simulator unterbrochen.");
                break;
            }
        }
    }

    private void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * Returns, wether the current temperature is in a safe range or not.
     *
     * @return true, if temperature in safe range; else false
     */
    @Override
    public boolean isTemperatureInSafeRange() {
        return temperature >= LOWER_TEMP_LIMIT && temperature <= UPPER_TEMP_LIMIT;
    }
}

