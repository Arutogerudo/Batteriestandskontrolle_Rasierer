# Schnittstelle userInterface -> batteryLogic

### Methode: updateOperationState()

Beschreibung:
Aktualisiert den Betriebszustand des Systems basierend auf aktuellen Informationen aus der batteryLogic (z. B. Ladezustand, Spannung).

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Diese Methode triggert eine interne Aktualisierung des Zustands (z. B. für Anzeige oder Betriebslogik), durch Auswertung der Userinteraktionen mit dem Button.

### Methode: getDisplayState()

Beschreibung:
Liefert den aktuellen Anzeigestatus des Systems (z. B. Ladezustand, Warnhinweise etc.).

Parameter:
Keine.

Rückgabewert:
DisplayStates – Enum, die den darzustellenden Zustand beschreibt.

Verhalten:
Gibt den derzeitigen Zustand zurück, der auf dem Display angezeigt wird (z. B. "Anzeige des Ladezustands", „keine Anzeige“, etc.).

### Methode: calculateStateOfCharge()

Beschreibung:
Berechnet den aktuellen Ladezustand (State of Charge, SoC) der Batterie basierend auf der Spannung.

Parameter:
Keine.

Rückgabewert:
int – Prozentualer Ladezustand (z. B. 0–100 %).

Verhalten:
Interpoliert auf Basis der kalibrierten Spannungs- und SoC-Werte. Gibt bei fehlender Kalibration eine Ausnahme zurück.


# Schnittstelle userInterface -> hardwareAbstraction

### Methode: ButtonInput()

Beschreibung:
Initialisiert die Tastensteuerung für Benutzerinteraktionen.

Parameter:

JButton button: Die UI-Komponente, auf die gedrückt wird.

InteractionHandler handler: Verarbeitet die Eingaben (z. B. kurze oder lange Tastendrücke).

Rückgabewert:
Konstruktor – kein Rückgabewert.

Verhalten:
Erkennt kurze (<1 s) und lange (≥1 s) Tastendrücke und löst entsprechende Events im Handler aus. Außerdem erkennt er, wenn außerhalb des Betriebs 5s keine Userinteraktion mit dem Knopf stattgefunden hat und löst Event "Inaktivität" aus. Dieses führt zur Deaktivierung der Anzeige.

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
SimulatorState state: Beschreibt z. B. „Laden“, „Entladen“, „Passiv“.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Simuliert unterschiedliche Zustände (aktive/passive Entladung, Aufladung), was sich auf die Spannungsentwicklung auswirkt.


# Schnittstelle batteryLogic -> persistenceManager

  ### Methode: readCalibVoltageToSoCFromDisc()

Beschreibung:
Liest Kalibrierdaten zur Spannungs-SoC-Zuordnung von der Festplatte.

Parameter:
Keine.

Rückgabewert:
CalibrationData – Objekt mit Arrays für Spannungs- und SoC-Werte.

Verhalten:
Parst eine TXT-Datei, die Tabelle mit Spannungs- und Ladezustandswerten von Kalibrierung enthält. Bei Fehlern wird null zurückgegeben.

  ### Methode: readLowBatteryThresholdFromDisc()

Beschreibung:
Liest den Schwellenwert für niedrige Batterie aus einer Datei.

Parameter:
Keine.

Rückgabewert:
int – Prozentualer Schwellenwert oder -1 bei Fehler.

Verhalten:
Liest einen numerischen Wert aus einer Textdatei und interpretiert ihn als unteren SoC-Schwellenwert. Bei Format- oder Leseproblemen erfolgt Fehlerausgabe und Rückgabe von -1.


# Schnittstelle hardwareAbstraction -> persistenceManager

  ### Methode: loadCalibrationData()

Beschreibung:
Lädt Kalibrierdaten für Sensorik und Spannungsmessung.

Parameter:
Keine.

Rückgabewert:
Kein Rückgabewert (void).

Verhalten:
Liest die kalibrierten Daten aus readCalibVoltageToSoCFromDisc() und gibt sie über 2 Getter (getVoltageCalib(), getSoCCalib()) zurück, um die Daten in den Hardware-Abstraktionslayer zu laden.
