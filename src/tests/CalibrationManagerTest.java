package tests;

import batteryLogic.CalibrationManager;
import org.junit.jupiter.api.*;
import persistenceManager.SettingsStorage;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CalibrationManagerTest {

    private CalibrationManager manager;
    private SettingsStorage storage;

    @BeforeEach
    void setUp() {
        manager = CalibrationManager.getInstance();
        storage = SettingsStorage.getInstance();
        storage.setChargeCycleCount(500);
    }

    @Test
    void testRecalibrationAdjustsRuntimeAsExpected() {
        manager.recalibrateIfNeeded();

        double adjustedRuntime = storage.readRuntimeFullChargeFromDisc();

        // DISCOUNT_PER_CYCLE = 0.0002 → 1 - 0.0002*500 = 0.9
        // Erwartet: 50 * 0.9 = 45
        assertEquals(45.0, adjustedRuntime, 0.1, "Angepasste Restlaufzeit sollte 45.0 Minuten betragen");
    }
}
