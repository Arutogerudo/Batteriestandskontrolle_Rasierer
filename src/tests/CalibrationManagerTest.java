package tests;

import batteryLogic.BatteryStateController;
import batteryLogic.CalibrationManager;
import hardwareAbstraction.VoltageSimulator;
import org.junit.jupiter.api.*;
import persistenceManager.CalibrationData;

import java.io.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class CalibrationManagerTest {

    private CalibrationManager manager;

    @BeforeEach
    void setUp() {
        BatteryStateController.initInstance(new VoltageSimulator());
        manager = CalibrationManager.getInstance();
    }

//    @Test
//    void testRecalibrateIfNeeded() throws IOException {
//        manager.recalibrateIfNeeded();
//
//        CalibrationData calib = manager.getCalibrationValues();
//
//        // Beispielhafte Annahmen – passe an deine Werte/Logik an
//        assertTrue(calib.containsKey("voltageOffset"), "voltageOffset sollte gesetzt sein");
//        assertTrue(calib.containsKey("referenceLevel"), "referenceLevel sollte gesetzt sein");
//
//        // Konkrete Werte je nach erwarteter Kalibrierung prüfen
//        double offset = Double.parseDouble(values.get("voltageOffset"));
//        assertTrue(offset >= -0.2 && offset <= 0.2, "Offset sollte in realistischem Bereich liegen");
//
//        double ref = Double.parseDouble(values.get("referenceLevel"));
//        assertTrue(ref >= 3.5 && ref <= 4.2, "Referenzlevel sollte plausibel sein");
//    }
}

