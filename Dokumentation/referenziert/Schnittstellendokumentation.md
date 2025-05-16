# Schnittstelle UserInterface -> BatteryLogic

### Methode: getStateOfCharge()

Beschreibung:
  Liefert den aktuellen Ladezustand in Prozent, basierend auf gemessenen Spannung des Akkus und der Lookup Tabelle zur Zuordnung zwischen Spannung und Ladezustand.

Parameter:
  — keine —

Rückgabewert:
  Ladezustand in Prozent (z.B. 4,2 V = 100)

Fehlerfälle:
  - Wenn keine Spannung messbar → Rückgabe "Fehler"

Vorbedingung:
  Hardwareabstraktion muss bereit sein (System initialisiert), Spannung muss gemessen worden sein

Nachbedingung:
  Rückgabe ist ≥ 0

Beispiel:
  getStateOfCharge() → 75%

### Methode: getRemainingRuntime()

Beschreibung:
  Liefert die verbleibende Betriebszeit in Minuten, basierend auf aktuellem SoC
  und der durch Kalibrierung ermittelten Alterung der Batterie.

Parameter:
  — keine —

Rückgabewert:
  Duration — Geschätzte Restlaufzeit (z. B. 100% = 60 Minuten)

Fehlerfälle:
  - Wenn keine Spannung messbar → Rückgabe "Fehler"

Vorbedingung:
  Hardwareabstraktion muss bereit sein (System initialisiert), Ladezustand muss bestimmt worden sein

Nachbedingung:
  Rückgabe ist ≥ 0

Beispiel:
  getRemainingRuntime() → 30 Minuten


# Schnittstelle BatteryLogic -> HardwareAbstraction

### Methode: getBatteryVoltage()

Beschreibung:
  Liefert die gemessene Spannung des Akkus in Volt.

Parameter:
  — keine —

Rückgabewert:
  Spannung des Akkus - in Volt

Fehlerfälle:
  - Wenn keine Spannung messbar → Rückgabe "Fehler"

Vorbedingung:
  Hardwareabstraktion muss bereit sein (System initialisiert)

Nachbedingung:
  Rückgabe ist >= 2,5 und <= 4.5

Beispiel:
  getBatteryVoltage() → 3,75 V

### Methode: getButtonPress()

Beschreibung:
  Liefert eine Rückgabe, falls Button gedrückt wurde. Das Drücken des Buttons wird in der Hardware über Sensoren wahrgenommen. Es gibt verschiedene Arten von Rückgaben, je nachdem ob einmal kurz, zweimal kurz oder einmal lang gedrückt wurde.

Parameter:
  — keine —

Rückgabewert:
  Art des Knopfdrucks (1- 1 x kurz, 2 - 2 x kurz, 3 - 1 x lang)

Fehlerfälle:
  -- keine --

Vorbedingung:
  Hardwareabstraktion muss bereit sein (System initialisiert)

Nachbedingung:
  -- keine --

Beispiel:
  getButtonPress() → 3

  # Schnittstelle HardwareAbstraction -> PersistenceManager

  ### Methode: loadCalibrationData()

Beschreibung:
  Lädt die Kalibrierungsdaten für die korrekte Umrechnung der Spannung in den Ladezustand aus dem internen Speicher.

Parameter:
  — keine —

Rückgabewert:
  Lookup Tabelle Spannung zu Ladezustand

Fehlerfälle:
  - Wenn Lookup Tabelle nicht gefunden: gebe "Fehler" zurück und fordere Einschicken zu Hersteller

Vorbedingung:
  Lookup Tabelle muss persistiert sein

Nachbedingung:
  -- keine -- 

Beispiel:
  loadLookupTabelle() → dict
