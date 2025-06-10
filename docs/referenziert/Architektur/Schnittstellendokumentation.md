# Schnittstelle batteryLogic -> userInterface

### Methode: updateOperationState()

Beschreibung:
Aktualisiert den Betriebszustand des Systems basierend auf aktuellen Informationen aus der batteryLogic (z. B.
Ladezustand, Spannung).

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Diese Methode triggert eine interne Aktualisierung des Zustands (z. B. für Anzeige oder Betriebslogik), durch Auswertung
der Userinteraktionen mit dem Button.

### Methode: getDisplayState()

Beschreibung:
Liefert den aktuellen Anzeigestatus des Systems (z. B. Ladezustand, Warnhinweise etc.).

Parameter:
Keine.

Rückgabewert:
DisplayStates – Enum, die den darzustellenden Zustand beschreibt.

Verhalten:
Gibt den derzeitigen Zustand zurück, der auf dem Display angezeigt wird (z. B. "Anzeige des Ladezustands", „keine
Anzeige“, etc.).

### Methode: calculateStateOfCharge()

Beschreibung:
Berechnet den aktuellen Ladezustand (State of Charge, SoC) der Batterie basierend auf der Spannung.

Parameter:
Keine.

Rückgabewert:
int – Prozentualer Ladezustand (z. B. 0–100 %).

Verhalten:
Interpoliert auf Basis der kalibrierten Spannungs- und SoC-Werte. Gibt bei fehlender Kalibration eine Ausnahme zurück.

### Methode: isLowBattery()

Beschreibung:
Prüft, ob der Ladezustand der Batterie unterhalb eines definierten Schwellenwerts liegt. Dies dient zur Erkennung eines
niedrigen Batteriestands auf Basis der aktuellen Batteriespannung.

Parameter:
Keine.

Rückgabewert:
boolean – true, wenn der berechnete Ladezustand (State of Charge, SoC) unter dem Schwellenwert für niedrigen
Batteriestand liegt, sonst false.

Verhalten:
Die Methode liest die aktuelle Spannung über den voltageSensor, berechnet daraus den Ladezustand der Batterie mittels
calculateStateOfCharge() und vergleicht diesen mit dem definierten Schwellenwert lowBatteryThreshold. Wird der
Schwellenwert unterschritten, signalisiert die Methode einen niedrigen Batteriestand.

### Methode: setDisplayState()

Beschreibung:
Aktualisiert den aktuellen Anzeigestatus der grafischen Benutzeroberfläche (GUI), indem ein neuer Darstellungszustand
gesetzt wird.

Parameter:
DisplayStates newState – Der neue Anzeigestatus, der in der GUI übernommen werden soll.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Die Methode setzt das Attribut displayState auf den übergebenen neuen Zustand. Dies beeinflusst die Darstellung der
Benutzeroberfläche entsprechend des neuen Zustands, z. B. um zwischen verschiedenen Ansichten oder Betriebsmodi
umzuschalten.

### Methode: calculateRemainingRuntime()

Beschreibung:
Berechnet die verbleibende Laufzeit des Akkus basierend auf der aktuellen Spannung. Die Berechnung erfolgt durch
Interpolation vorhandener Kalibrierdaten und ermöglicht eine genauere Restlaufzeitschätzung.

Parameter:
double voltage – Die aktuelle Spannung des Akkus in Volt, für die die verbleibende Laufzeit geschätzt werden soll.

Rückgabewert:
double – Die berechnete verbleibende Laufzeit in Minuten (oder einer anderen verwendeten Zeiteinheit), basierend auf den
Kalibrierdaten.

Verhalten:
Die Methode greift auf die gespeicherten Kalibrierdaten (calib) zu, welche die Zuordnung zwischen Spannungswerten und
Laufzeitwerten enthalten.
Die Spannungs- und Laufzeitarrays werden zur Interpolation verwendet.
Anschließend wird InterpolationUtils.calculateInterpolatedValue() aufgerufen, um den Laufzeitwert zu berechnen, der dem
übergebenen Spannungswert am nächsten kommt.
Das Ergebnis ist eine geglättete, approximierte Restlaufzeit basierend auf der aktuellen Spannung.

### Methode: updateLowBatteryThreshold()

Beschreibung:
Aktualisiert den Schwellenwert, ab dem ein niedriger Batteriestand erkannt wird, und speichert diesen dauerhaft in den
Systemeinstellungen.

Parameter:
int newThreshold – Der neue Schwellenwert für die Erkennung eines niedrigen Batteriestands (in Prozent oder einem
vergleichbaren Maß).

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Die Methode setzt den internen Schwellwert (lowBatteryThreshold) auf den übergebenen Wert und speichert diesen
zusätzlich in einer persistenten Einstellung über das Singleton-Objekt SettingsStorage.
Dies stellt sicher, dass der neue Schwellenwert auch nach einem Neustart des Systems erhalten bleibt.

### Methode: monitorChargeCycle()

Beschreibung:
Überwacht kontinuierlich den Ladezykluszustand des Akkus und aktualisiert die Anzahl der vollständigen Ladezyklen bei
Erkennung eines neuen Zyklus. Dies dient der Nachverfolgung von Akkuverschleiß über die Zeit.

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Die Methode berechnet zunächst den aktuellen Ladezustand (State of Charge, SoC) anhand der Sensorspannung und
Kalibrierdaten.
Wenn ein neuer Entladevorgang erkannt wird (isNewDischargeDetected()), wird ein interner Zustand (dischargedDetected)
gesetzt, um den Beginn eines möglichen neuen Ladezyklus zu markieren.
Wird daraufhin ein vollständiger Ladezyklus erkannt (isNewChargeCycleComplete()), wird der Zähler für Ladezyklen (
chargeCycleCount) erhöht, der Entladezustand zurückgesetzt und der neue Wert persistent in den Einstellungen
gespeichert.
Die Methode ermöglicht so die automatische Erfassung und Speicherung von Akkuladezyklen im Betrieb.

### Methode: recalibrateIfNeeded()

Beschreibung:
Überprüft, ob eine Neukalibrierung der Laufzeitdaten basierend auf der Anzahl der Ladezyklen notwendig ist, und führt
diese gegebenenfalls durch. Dies dient der Genauigkeit der Restlaufzeitabschätzung bei gealterten Akkus.

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Die Methode prüft, ob die Anzahl der Ladezyklen den vordefinierten Schwellwert (CYCLE_THRESHOLD) überschreitet. Falls
nicht, wird die Methode ohne weitere Aktion beendet.
Bei Erreichen oder Überschreiten des Schwellwerts werden die gespeicherten Kalibrierdaten vom Datenträger geladen. Ist
das Laden nicht erfolgreich, wird eine Fehlermeldung ausgegeben und die Methode beendet.
Sind Kalibrierdaten vorhanden, wird die Laufzeitinformation entsprechend der aktuellen Ladezyklenzahl angepasst und im
Speicher aktualisiert. Anschließend wird die neue Kalibrierung auf den Datenträger geschrieben.
Eine Bestätigung der Kalibrierung wird in der Konsole ausgegeben, und der Ladezyklenzähler im batteryController wird
zurückgesetzt.

# Schnittstelle batteryLogic -> hardwareAbstraction

### Methode: isUndervoltageDetected()

Beschreibung:
Erkennt, ob eine Unterspannung (Undervoltage) vorliegt, d. h. ob die aktuelle Spannung unterhalb eines kritischen
Grenzwerts liegt.

Parameter:
Keine.

Rückgabewert:
boolean – true, wenn die aktuell gemessene Spannung unter dem UNDERVOLTAGE_LIMIT liegt; andernfalls false.

Verhalten:
Die Methode liest die aktuelle Spannung des Akkus über den Sensor aus und vergleicht sie mit dem vordefinierten
Grenzwert UNDERVOLTAGE_LIMIT.
Liegt die Spannung darunter, wird true zurückgegeben, was auf eine potenziell kritische Spannungssituation hinweist.
Andernfalls gibt die Methode false zurück.

### Methode: handleButtonPress()

Beschreibung:
Verarbeitet einen Button-Eingabeereignis und führt den zugehörigen Befehl basierend auf dem Ereignistyp aus. Dies
ermöglicht eine flexible und erweiterbare Reaktion auf unterschiedliche Benutzerinteraktionen.

Parameter:
String event – Das aufgetretene Ereignis, z. B. "shortPress", "longPress" oder "inactivity".

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Die Methode sucht in einer internen Befehlszuordnung (commandMap) nach einem ButtonCommand, das dem übergebenen Ereignis
entspricht. Wird ein passender Befehl gefunden, wird dessen execute()-Methode aufgerufen und erhält das aktuelle Objekt
als Kontext. Bei unbekannten Ereignissen geschieht nichts.

# Schnittstelle hardwareAbstraction -> batteryLogic

### Methode: readVoltage()

Beschreibung:
Liest die aktuelle Batteriespannung aus dem Simulator.

Parameter:
Keine.

Rückgabewert:
double – Aktuelle Spannung in Volt.

Verhalten:
Holt den aktuellen Spannungswert aus der simulierten Hardwareumgebung zur weiteren Auswertung durch die Logik.

### Methode: setState()

Beschreibung:
Setzt den Lade-/Entladezustand im Simulator, um das Verhalten der Batterie zu beeinflussen.

Parameter:
SimulatorState state: Beschreibt z. B. „Laden“, „Entladen“, „Passiv“.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Simuliert unterschiedliche Zustände (aktive/passive Entladung, Aufladung), was sich auf die Spannungsentwicklung
auswirkt.

### Methode: getChargingState()

Beschreibung:
Gibt den aktuellen Ladezustand des Akkusimulators zurück.

Parameter:
Keine.

Rückgabewert:
ChargingStates – Der aktuelle Ladezustand, z. B. CHARGING, IDLE, OVERLOAD_PROTECTION usw.

Verhalten:
Ruft den aktuellen Zustand vom Akkusimulator (simulator) ab und gibt diesen zurück.

### Methode updateBcProtectionStates()

Beschreibung:
Aktualisiert den Ladezustand des Akkusimulators unter Berücksichtigung von Schutzmechanismen wie Überladung und
Unterspannung.

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Wenn die Spannung des Simulators den Grenzwert für volle Ladung überschreitet und der Zustand CHARGING ist, wird der
Zustand auf OVERLOAD_PROTECTION gesetzt.
Wenn der Batteriecontroller Unterspannung erkennt, wird der Zustand auf UNDERVOLTAGE_PROTECTION gesetzt.

# Schnittstelle persistenceManager → userInterface

### Methode: readLowBatteryThresholdFromDisc()

Beschreibung:
Liest den Schwellenwert für niedrigen Akkustand von der Datei auf dem Datenträger.

Parameter:
Keine.

Rückgabewert:
int – Der gespeicherte Schwellenwert. Bei Fehlern wird -1 zurückgegeben.

Verhalten:
Liest den Schwellenwert als Textdatei (THRESHOLD_TXT_FILE), konvertiert ihn zu einem Integer und gibt diesen zurück.
Fehler beim Lesen werden über handleWriteOrReadError behandelt.

# Schnittstelle persistenceManager -> batteryLogic

### Methode: readCalibVoltageToSoCToRuntimeFromDisc()

Beschreibung:
Lädt Kalibrierdaten (Spannung, Ladezustand, Laufzeit) aus einer TXT-Datei.

Parameter:
Keine.

Rückgabewert:
CalibrationData – Ein Objekt mit den geladenen Kalibrierdaten. Gibt null zurück, falls ein Fehler beim Lesen auftritt.

Verhalten:
Liest die CSV-Datei CALIB_TXT_FILE, überspringt die Kopfzeile und parst alle Zeilen in Arrays für Spannung, Ladezustand
und Laufzeit.
Erzeugt daraus ein CalibrationData-Objekt.
Fehler beim Lesen oder Parsen werden über handleWriteOrReadError behandelt.

### Methode: getVoltageCalib()

Beschreibung:
Gibt die gespeicherten Kalibrierungsspannungswerte zurück, die zur Zuordnung von Spannung zu Ladezustand oder Laufzeit
verwendet werden.

Parameter:
Keine.

Rückgabewert:
double[] – Ein Array mit den Kalibrierungsspannungswerten. Das Array ist eine Kopie der internen Daten, um unerwünschte
Änderungen zu vermeiden.

Verhalten:
Die Methode liefert eine geklonte Kopie des internen Spannungsarrays zurück.

### Methode: getSoCCalib()

Beschreibung:
Gibt die Kalibrierdaten zum Ladezustand (State of Charge) zurück.

Parameter:
Keine.

Rückgabewert:
int[] – Ein Array mit den Kalibrierungsladezustandswerten. Das Array ist eine Kopie der internen Daten.

Verhalten:
Die Methode liefert eine geklonte Kopie des internen Ladezustandsarrays zurück.

### Methode: getRuntime()

Beschreibung:
Gibt die Kalibrierdaten zur verbleibenden Laufzeit zurück.

Parameter:
Keine.

Rückgabewert:
double[] – Ein Array mit den Kalibrierungswerten der Laufzeit. Das Array ist eine Kopie der internen Daten.

Verhalten:
Die Methode liefert eine geklonte Kopie des internen Laufzeitarrays zurück.

### Methode: setLowBatteryThreshold()

Beschreibung:
Setzt den internen Schwellenwert für niedrigen Akkustand und speichert diesen auf dem Datenträger.

Parameter:
int threshold – Neuer Schwellenwert für niedrigen Akkustand.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Aktualisiert die interne Variable lowBatteryThreshold.
Ruft die Methode writeLowBatteryThresholdToDisc() auf, um den Wert persistent zu speichern.

### Methode: writeCalibVoltageToSoCToRuntimeToDisc()

Beschreibung:
Speichert die aktuellen Kalibrierdaten als CSV-Datei auf dem Datenträger.

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Erstellt CSV-String mit Kalibrierdaten über buildCsvContent() und schreibt diesen in die Datei CALIB_TXT_FILE.
Fehler beim Schreiben werden durch handleWriteOrReadError behandelt.

### Methode: setChargeCycleCount()

Beschreibung:
Speichert die aktuelle Anzahl der Ladezyklen in eine Datei auf dem Datenträger.

Parameter:
int count – Die Anzahl der Ladezyklen, die gespeichert werden soll.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Schreibt die übergebene Zyklenzahl als Textdatei (CYCLE_COUNT_FILE).
Bei einem Fehler während des Schreibvorgangs wird die Methode handleWriteOrReadError aufgerufen.

### Methode: readChargeCycleCount()

Beschreibung:
Liest die Anzahl der bisher gezählten Ladezyklen von der Datei auf dem Datenträger.

Parameter:
Keine.

Rückgabewert:
int – Die gespeicherte Anzahl der Ladezyklen. Bei einem Lesefehler wird -1 zurückgegeben.

Verhalten:
Liest den Inhalt der Datei (CYCLE_COUNT_FILE), wandelt diesen in einen Integer um und gibt ihn zurück.
Bei Fehlern wird handleWriteOrReadError aufgerufen und -1 zurückgegeben.

### Methode: setRuntimeCalib()

Beschreibung:
Setzt neue Laufzeit-Kalibrierungswerte nach einer durchgeführten Neukalibrierung. Diese Werte werden intern gespeichert
und können für weitere Berechnungen oder Justierungen verwendet werden.

Parameter:
double[] runtime – Ein Array mit den kalibrierten Laufzeitwerten, die nach einer Re-Kalibrierung ermittelt wurden.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Die Methode überschreibt die aktuell gespeicherten Laufzeit-Kalibrierungswerte mit den neu übergebenen Werten. Es findet
keine Prüfung der Werte statt; es wird davon ausgegangen, dass die Daten gültig sind.

# Schnittstelle von SW-Logik zu Programm (Main-Methode)

### Methode: tick()

Beschreibung:
Simuliert den Zeitverlauf im System, indem die Batteriespannung basierend auf dem aktuellen Systemzustand aktualisiert
wird.

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Diese Methode wird zyklisch aufgerufen, um die Spannung entsprechend dem momentanen Zustand des Systems anzupassen.
Dabei erfolgt zunächst eine Aktualisierung der Spannung durch updateVoltageBasedOnState(). Anschließend wird
sichergestellt, dass die Spannung innerhalb definierter Grenzen bleibt (clampVoltage()).

### Methode: update()

Beschreibung:
Aktualisiert die grafische Benutzeroberfläche (GUI) basierend auf dem aktuellen Zustand der Batterie und den
Benutzerinteraktionen. Dabei wird entschieden, ob der Ladezustand in Prozent angezeigt werden soll, und die Anzeige
entsprechend aktualisiert.

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Die Methode prüft zunächst anhand des aktuellen Anzeigezustands (DisplayStates), ob die Batteriekapazität prozentual
angezeigt werden soll. Anschließend wird der Betriebszustand durch den operationController aktualisiert. Zuletzt werden
die visuellen Ausgaben durch den visualOutputController mit dem berechneten Ladezustand und der Anzeigepräferenz
aktualisiert.

### Methode: listenForChargingCommands()

Beschreibung:
Startet eine interaktive Eingabeschleife zur Steuerung des Ladevorgangs über die Konsole. Reagiert auf Benutzereingaben
wie "start" oder "stop" und passt den Ladezustand des Simulators entsprechend an.

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Die Methode öffnet einen Konsolenscanner und wartet dauerhaft auf Benutzereingaben.

Bei Eingabe von "start" wird der Ladevorgang initiiert (ChargingStates.CHARGING).

Bei Eingabe von "stop" wird der Ladevorgang beendet bzw. in den passiven Entladezustand gesetzt (
ChargingStates.DISCHARGING_PASSIVE).

Für alle anderen Eingaben erfolgt eine Rückmeldung, dass nur "start" oder "stop" gültig sind.
Die Schleife läuft ununterbrochen, bis das Programm manuell beendet wird.

## Andere

### Methode: getChargingState()

Beschreibung:
Liefert den aktuellen Ladezustand des Systems, basierend auf der internen Analyse der batteryLogic.

Parameter:
Keine.

Rückgabewert:
ChargingState (Enum)

Verhalten:
Diese Methode dient der Abfrage des aktuellen Ladevorgangsstatus. Sie wird z. B. vom UserInterface verwendet, um die
LED- oder Displayanzeige entsprechend zu aktualisieren.

### Methode: readVoltage()

Beschreibung:
Liest die aktuelle Spannung vom Simulator aus und gibt diesen Wert zurück. Diese Methode wird verwendet, um den
aktuellen elektrischen Spannungswert aus dem simulierten System zu erhalten.

Parameter:
Keine.

Rückgabewert:
`double` – Der aktuelle Spannungswert in Volt, wie vom Simulator geliefert.

Verhalten:
Die Methode ruft den Spannungswert direkt vom `simulator`-Objekt ab (über `getVoltage()`) und gibt ihn zurück. Sie
verändert keinen internen Zustand und hat keine Seiteneffekte.

### Methode: ButtonInput()

Beschreibung:
Initialisiert die Tastensteuerung für Benutzerinteraktionen.

Parameter:

JButton button: Die UI-Komponente, auf die gedrückt wird.

InteractionHandler handler: Verarbeitet die Eingaben (z. B. kurze oder lange Tastendrücke).

Rückgabewert:
Konstruktor – kein Rückgabewert.

Verhalten:
Erkennt kurze (<1 s) und lange (≥1 s) Tastendrücke und löst entsprechende Events im Handler aus. Außerdem erkennt er,
wenn außerhalb des Betriebs 5s keine Userinteraktion mit dem Knopf stattgefunden hat und löst Event "Inaktivität" aus.
Dieses führt zur Deaktivierung der Anzeige.

### Methode: readTemperature()

Beschreibung:
Liest die aktuelle Gerätetemperatur während des Betriebs oder Ladevorgangs aus einem Temperaturfühler.

Parameter:
Keine.

Rückgabewert:
float (z. B. in °C)

Verhalten:
Die Temperatur wird von batteryLogic verwendet, um einen sicheren Ladebetrieb zu gewährleisten (z. B.
Überhitzungsschutz).

### Methode: readTempThresholdFromDisc()

Beschreibung:
Lädt die konfigurierten Temperaturgrenzwerte (min/max) zur sicheren Ladeüberwachung aus persistentem Speicher.

Parameter:
Keine.

Rückgabewert:
TemperatureThresholds (Objekt mit z. B. minTemp: float, maxTemp: float)

Verhalten:
Wird beim Systemstart oder Kalibrierung von batteryLogic verwendet, um Grenzwerte für das Ladeverhalten korrekt zu
interpretieren und Sicherheitsprüfungen durchzuführen.