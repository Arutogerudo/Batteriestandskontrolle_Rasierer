package tests;

import batteryLogic.BatteryStateController;
import hardwareAbstraction.VoltageSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BatteryStateControllerTest {

    private static final double TESTED_LOWER_VOLTAGE = 2.8;
    private static final double TESTED_HIGHER_VOLTAGE = 4.3;
    private static final double VOLTAGE_36 = 3.6;
    private static final double VOLTAGE_42 = 4.2;
    private static final double VOLTAGE_375 = 3.75;
    private static final double VOLTAGE_30 = 3.0;
    private static final double VOLTAGE_39 = 3.9;
    private static final int EXPECTED_SOC_50 = 50;
    private static final int EXPECTED_SOC_65 = 65;
    private static final int EXPECTED_SOC_80 = 80;
    private static final double VOLTAGE_31 = 3.1;
    private static final double VOLTAGE_38 = 3.8;
    private static final double VOLTAGE_33 = 3.3;
    private static final int EXPECTED_RUNTIME_35 = 35;
    private static final int EXPECTED_RUNTIME_25 = 25;
    private BatteryStateController controller;
    private VoltageSimulator simulator;

    @BeforeEach
    void setup() {
        this.simulator = new VoltageSimulator();
        BatteryStateController.initInstance(simulator);
        controller = BatteryStateController.getInstance();
    }

    /*
     * Test case UT1 for the BatteryStateController class.
     * This test covers the security of the calculateStateOfCharge method.
     */
    @Test
    void testVoltageUnderLowerLimit_shouldClampOrThrow() {
        int soc = controller.calculateStateOfCharge(TESTED_LOWER_VOLTAGE);
        assertTrue(soc >= 0, "Should return a valid SOC or clamp to minimum");
    }

    /*
     * Test case UT2 for the BatteryStateController class.
     * This test covers the security of the calculateStateOfCharge method.
     */
    @Test
    void testVoltageAboveUpperLimit_shouldClamp() {
        int soc = controller.calculateStateOfCharge(TESTED_HIGHER_VOLTAGE);
        assertEquals(100, soc, "Should clamp to max SOC");
    }

    /*
     * Test case UT3 for the BatteryStateController class.
     * This test covers the functionality of the calculateStateOfCharge method mit Toleranz von +/- 5%.
     */
    @Test
    void testCalculationForValidVoltage() {
        int soc = controller.calculateStateOfCharge(VOLTAGE_36);
        assertEquals(EXPECTED_SOC_50, soc, 5, "Interpolated SOC should be approximately 50%");
        soc = controller.calculateStateOfCharge(VOLTAGE_42);
        assertEquals(100, soc, 5);
        soc = controller.calculateStateOfCharge(VOLTAGE_375);
        assertEquals(EXPECTED_SOC_65, soc, 5);
        soc = controller.calculateStateOfCharge(VOLTAGE_30);
        assertEquals(0, soc, 5);
        soc = controller.calculateStateOfCharge(VOLTAGE_39);
        assertEquals(EXPECTED_SOC_80, soc, 5);
    }

    /*
     * Test case UT4 for the BatteryStateController class.
     * This test covers the functionality of the isLowBattery method with a voltage sensor.
     */
    @Test
    void testIsLowBattery_whenVoltageLow_shouldReturnTrue() {
        simulator.setVoltage(VOLTAGE_31);

        assertTrue(controller.isLowBattery());
    }

    /*
     * Test case UT5 for the BatteryStateController class.
     * This test covers the functionality of the calculateRemainingRuntime method.
     * It verifies that the runtime estimation is consistent with expected values for given voltages.
     */
    @Test
    void testCalculationForRemainingRuntime() {
        double runtime = controller.calculateRemainingRuntime(VOLTAGE_42);
        assertEquals(EXPECTED_SOC_50, runtime, 5, "Expected approx. 50 minutes at full voltage (4.2V)");

        runtime = controller.calculateRemainingRuntime(VOLTAGE_38);
        assertEquals(EXPECTED_RUNTIME_35, runtime, 5, "Expected approx. 35 minutes at 3.9V");

        runtime = controller.calculateRemainingRuntime(VOLTAGE_36);
        assertEquals(EXPECTED_RUNTIME_25, runtime, 5, "Expected approx. 25 minutes at 3.6V");

        runtime = controller.calculateRemainingRuntime(VOLTAGE_33);
        assertEquals(10, runtime, 5, "Expected approx. 10 minutes at 3.3V");

        runtime = controller.calculateRemainingRuntime(VOLTAGE_30);
        assertEquals(0, runtime, 5, "Expected approx. 0 minutes at low voltage (3.0V)");
    }

}

