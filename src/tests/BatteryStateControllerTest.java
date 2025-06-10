package tests;

import batteryLogic.BatteryStateController;
import hardwareAbstraction.VoltageSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BatteryStateControllerTest {

    private BatteryStateController controller;
    private VoltageSimulator simulator;

    @BeforeEach
    void setup() {
        this.simulator = new VoltageSimulator();
        controller = BatteryStateController.getInstance();

    }

    /*
     * Test case UT1 for the BatteryStateController class.
     * This test covers the security of the calculateStateOfCharge method.
     */
    @Test
    void testVoltageUnderLowerLimit_shouldClampOrThrow() {
        int soc = controller.calculateStateOfCharge(2.8);
        assertTrue(soc >= 0, "Should return a valid SOC or clamp to minimum");
    }

    /*
     * Test case UT2 for the BatteryStateController class.
     * This test covers the security of the calculateStateOfCharge method.
     */
    @Test
    void testVoltageAboveUpperLimit_shouldClamp() {
        int soc = controller.calculateStateOfCharge(4.3);
        assertEquals(100, soc, "Should clamp to max SOC");
    }

    /*
     * Test case UT3 for the BatteryStateController class.
     * This test covers the functionality of the calculateStateOfCharge method mit Toleranz von +/- 5%.
     */
    @Test
    void testCalculationForValidVoltage() {
        int soc = controller.calculateStateOfCharge(3.6);
        assertEquals(50, soc, 5, "Interpolated SOC should be approximately 50%");
        soc = controller.calculateStateOfCharge(4.2);
        assertEquals(100, soc, 5);
        soc = controller.calculateStateOfCharge(3.75);
        assertEquals(65, soc, 5);
        soc = controller.calculateStateOfCharge(3.0);
        assertEquals(0, soc, 5);
        soc = controller.calculateStateOfCharge(3.9);
        assertEquals(80, soc, 5);
    }

    /*
     * Test case UT4 for the BatteryStateController class.
     * This test covers the functionality of the isLowBattery method with a voltage sensor.
     */
    @Test
    void testIsLowBattery_whenVoltageLow_shouldReturnTrue() {
        simulator.setVoltage(3.2);

        assertTrue(controller.isLowBattery());
    }

    /*
     * Test case UT5 for the BatteryStateController class.
     * This test covers the functionality of the calculateRemainingRuntime method.
     * It verifies that the runtime estimation is consistent with expected values for given voltages.
     */
    @Test
    void testCalculationForRemainingRuntime() {
        double runtime = controller.calculateRemainingRuntime(4.2);
        assertEquals(60, runtime, 5, "Expected approx. 50 minutes at full voltage (4.2V)");

        runtime = controller.calculateRemainingRuntime(3.8);
        assertEquals(35, runtime, 5, "Expected approx. 35 minutes at 3.9V");

        runtime = controller.calculateRemainingRuntime(3.6);
        assertEquals(25, runtime, 5, "Expected approx. 25 minutes at 3.6V");

        runtime = controller.calculateRemainingRuntime(3.3);
        assertEquals(10, runtime, 5, "Expected approx. 10 minutes at 3.3V");

        runtime = controller.calculateRemainingRuntime(3.0);
        assertEquals(0, runtime, 5, "Expected approx. 0 minutes at low voltage (3.0V)");
    }

}

