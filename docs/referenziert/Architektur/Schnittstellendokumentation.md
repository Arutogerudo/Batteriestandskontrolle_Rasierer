# Schnittstelle userInterface -> batteryLogic

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

# Schnittstelle userInterface -> hardwareAbstraction

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

# Schnittstelle batteryLogic -> hardwareAbstraction

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
Liest den aktuellen Ladeanschlussstatus aus – ob der Rasierer mit einer Stromquelle verbunden ist. Dient dann direkt als
Basis für den Stromverlauf im Spannungssimulator.

Parameter:
Keine.

Rückgabewert:
bool

Verhalten:
Wird von batteryLogic genutzt, um zu erkennen, ob ein Ladevorgang begonnen werden sollte.

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

# Schnittstelle batteryLogic -> persistenceManager

### Methode: readCalibVoltageToSoCFromDisc()

Beschreibung:
Liest Kalibrierdaten zur Spannungs-SoC-Zuordnung von der Festplatte.

Parameter:
Keine.

Rückgabewert:
CalibrationData – Objekt mit Arrays für Spannungs- und SoC-Werte.

Verhalten:
Parst eine TXT-Datei, die Tabelle mit Spannungs- und Ladezustandswerten von Kalibrierung enthält. Bei Fehlern wird null
zurückgegeben.

### Methode: readLowBatteryThresholdFromDisc()

Beschreibung:
Liest den Schwellenwert für niedrige Batterie aus einer Datei.

Parameter:
Keine.

Rückgabewert:
int – Prozentualer Schwellenwert oder -1 bei Fehler.

Verhalten:
Liest einen numerischen Wert aus einer Textdatei und interpretiert ihn als unteren SoC-Schwellenwert. Bei Format- oder
Leseproblemen erfolgt Fehlerausgabe und Rückgabe von -1.

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

# Schnittstelle hardwareAbstraction -> persistenceManager

### Methode: loadCalibrationData()

Beschreibung:
Lädt Kalibrierdaten für Sensorik und Spannungsmessung.

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Liest die kalibrierten Daten aus readCalibVoltageToSoCFromDisc() und gibt sie über 2 Getter (getVoltageCalib(),
getSoCCalib()) zurück, um die Daten in den Hardware-Abstraktionslayer zu laden.

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
Startet eine interaktive Eingabeschleife zur Steuerung des Ladevorgangs über die Konsole. Reagiert auf Benutzereingaben wie "start" oder "stop" und passt den Ladezustand des Simulators entsprechend an.

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Die Methode öffnet einen Konsolenscanner und wartet dauerhaft auf Benutzereingaben.

Bei Eingabe von "start" wird der Ladevorgang initiiert (ChargingStates.CHARGING).

Bei Eingabe von "stop" wird der Ladevorgang beendet bzw. in den passiven Entladezustand gesetzt (ChargingStates.DISCHARGING_PASSIVE).

Für alle anderen Eingaben erfolgt eine Rückmeldung, dass nur "start" oder "stop" gültig sind.
Die Schleife läuft ununterbrochen, bis das Programm manuell beendet wird.
