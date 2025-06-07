package tests;

import batteryLogic.BatteryStateController;
import hardwareAbstraction.VoltageSensor;
import hardwareAbstraction.VoltageSimulator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BatteryStateControllerTest {

    private BatteryStateController controller;
    private VoltageSensor sensor;
    private VoltageSimulator simulator;

    @BeforeEach
    void setup() {
        this.simulator = new VoltageSimulator();
        this.sensor = new VoltageSensor(simulator);
        controller = new BatteryStateController(simulator);

    }

    /*
     * Test case UT1 for the BatteryStateController class.
     * This test covers the security of the calculateStateOfCharge method.
     */
    @Test
    void testVoltageUnderLowerLimit_shouldClampOrThrow() {
        int soc = controller.calculateStateOfCharge(2.9);
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
        assertEquals(35, soc, 5, "Interpolated SOC should be approximately 35%");
        soc = controller.calculateStateOfCharge(4.2);
        assertEquals(100, soc, 5);
        soc = controller.calculateStateOfCharge(3.75);
        assertEquals(50, soc, 5);
        soc = controller.calculateStateOfCharge(3.0);
        assertEquals(0, soc, 5);
        soc = controller.calculateStateOfCharge(3.9);
        assertEquals(70, soc, 5);
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
}

