package tests;

import batteryLogic.CalibrationManager;
import org.junit.jupiter.api.*;
import persistenceManager.SettingsStorage;

import static org.junit.jupiter.api.Assertions.*;

class CalibrationManagerTest {

    private static final double EXPECTED_RESULT = 45.0;
    private static final int TESTED_NUMBER_OF_CHARGE_CYCLES = 500;
    private CalibrationManager manager;
    private SettingsStorage storage;

    @BeforeEach
    void setUp() {
        manager = CalibrationManager.getInstance();
        storage = SettingsStorage.getInstance();
        storage.setChargeCycleCount(TESTED_NUMBER_OF_CHARGE_CYCLES);
    }

    /*
     * Test case UT6 for the CalibrationManager class.
     * This test covers the functionality of the recalibrateIfNeeded method.
     * It verifies that the recalibration estimation is consistent with expected values for given number of charge cycles.
     */
    @Test
    void testRecalibrationAdjustsRuntimeAsExpected() {
        manager.recalibrateIfNeeded();

        double adjustedRuntime = storage.readRuntimeFullChargeFromDisc();

        // DISCOUNT_PER_CYCLE = 0.0002 → 1 - 0.0002*500 = 0.9
        // Erwartet: 50 * 0.9 = 45
        assertEquals(EXPECTED_RESULT, adjustedRuntime, "Angepasste Restlaufzeit sollte 45.0 Minuten betragen");
    }
}
