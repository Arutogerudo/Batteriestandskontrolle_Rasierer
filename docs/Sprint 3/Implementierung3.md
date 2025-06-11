# Implementierung

## Traceability-Matrix

| Requirement-ID | Jira-Issue | Komponente                         | Klasse(n)                                          | Schnittstelle(n)                                            | Testfall(e)    |
|----------------|------------|------------------------------------|----------------------------------------------------|-------------------------------------------------------------|----------------|
| 1.1            | BAT-7      | batteryLogic, hardwareAbstraction  | `VoltageSensor`                                    | readVoltage()                                               | BB1            |
| 1.2            | BAT-8      | batteryLogic                       | `BatteryStateController`                           | calculateStateOfCharge()                                    | UT3            |
| 1.3            | BAT-9      | batteryLogic                       | `BatteryStateController`                           | getDisplayState()                                           | UX1            |
| 2.1            | BAT-10     | userInterface                      | `SimpleGUI`                                        | getDisplayState()                                           | BB2            |
| 2.2            | BAT-11     | userInterface, hardwareAbstraction | `ButtonInput`, `InteractionHandler`                | ButtonInput()                                               | BB4            |
| 2.3            | BAT-12     | userInterface, batteryLogic        | `SimpleGUIUpdater`, `BatteryStateController`       | calculateRemainingRuntime()                                 | UT5, UX8, BB12 |
| 2.4            | BAT-13     | userInterface                      | `SimpleGUI`                                        |                                                             |                |
| 2.5            | BAT-14     | userInterface, hardwareAbstraction | `InteractionHandler`                               | setState()                                                  | BB3            |
| 2.6            | BAT-15     | userInterface                      | `SimpleGUI`                                        |                                                             | UX3            |
| 2.7            | BAT-16     | userInterface                      | `SimpleGUI`                                        |                                                             | UX2            |
| 2.8            | BAT-17     | userInterface                      | `SimpleGUI`                                        | updateLEDState()                                            | UX5            |
| 2.9            | BAT-18     | userInterface                      | `SimpleGUI`                                        |                                                             | UX4            |
| 2.10           | BAT-19     | userInterface                      | `SimpleGUI`                                        | getDisplayState()                                           | BB5            |
| 3.1            | BAT-20     | persistenceManager                 | `SettingsStorage`, `VoltageSimulator`              | calculateStateOfCharge()                                    | UT1, UT2       |
| 3.2            | BAT-21     | persistenceManager                 | `SettingsStorage`                                  | readCalibVoltageToSoCFromDisc()                             |                |
| 3.3            | BAT-22     | batteryLogic                       | `CalibrationManager`                               | racalibrateIfNeeded()                                       | BB13           |
| 3.4            | BAT-23     | batteryLogic                       | `BatteryStateController`                           | getDisplayState()                                           |                |
| 3.5            | BAT-24     | batteryLogic                       | `BatteryStateController`                           | recalibrateIfNeeded()                                       | UT6            |
| 3.6            | BAT-25     | batteryLogic                       | `CalibrationManager`                               |                                                             |                |
| 4.1            | BAT-26     | batteryLogic, userInterface        | `BatteryStateController`, `VisualOutputController` | isLowBattery()                                              | UT4, BB6       |
| 4.2            | BAT-27     | userInterface                      | `SimpleGUIPanelBuilder`, `SettingsStorage`         | setLowBatteryThreshold(), readLowBatteryThresholdFromDisc() | BB14           |
| 4.3            | BAT-28     | userInterface                      | `LEDController`, `VisualOutputController`          | startBlinking(), setLEDState()                              | UX6            |
| 5.1            | BAT-29     | userInterface                      | `VisualOutputController`, `LEDController`          | updateOperationState()                                      | BB7            |
| 5.2            | BAT-30     | userInterface                      | `VisualOutputController`, `LEDController`          | updateOperationState()                                      | BB8            |
| 5.3            | BAT-31     | userInterface, batteryLogic        | `VisualOutputController`, `OperationController`    | setLEDState(), updateOperationState()                       | BB9            |
| 5.4            | BAT-32     | hardwareAbstraction                | `ChargingDetection`                                | getChargingState(), listenForChargingCommands()             | UX7            |
| 5.5            | BAT-33     | userInterface, batteryLogic        | `SimpleGUIUpdater`, `OperationController`          | update(), getRemainingRuntime()                             | BB15           |
| 5.6            | BAT-34     | userInterface                      | `LEDController`                                    | stopBlinking(), turnOff()                                   |                |
| 6.1            | BAT-35     | hardwareAbstraction, batteryLogic  | `VoltageSensor`, `OperationController`             | updateBcProtectionStates()                                  | BB16           |
| 6.2            | BAT-36     | batteryLogic                       | `OperationController`                              | updateBcProtectionStates()                                  | BB10           |
| 6.3            | BAT-37     | hardwareAbstraction, batteryLogic  | `TemperatureSimulator`, `OperationController`      | updateBcProtectionStates()                                  | BB11           |

## Analyse und Optimierung der Codequalität

### 1. Vorgehen zur Codeoptimierung

Im Rahmen der Qualitätssicherung meines Softwareprojekts zur Batteriestandskontrolle eines Rasierapparats habe ich eine
umfassende Analyse und Optimierung des Codes durchgeführt. Zunächst erfolgte ein manuelles **Codereview**, um
überflüssige oder fehleranfällige Strukturen zu identifizieren und zu entfernen. Dabei lag der Fokus insbesondere auf
der **Einhaltung des Single Responsibility Prinzips (SRP)**: Jede Klasse soll genau eine Verantwortlichkeit besitzen.

Im Zuge dessen wurden die ursprünglich zu umfangreichen Klassen `BatteryStateController`, `SettingsStorage`
und `SimpleGUI` restrukturiert und in kleinere, klar abgegrenzte Einheiten aufgeteilt. So wurde sichergestellt, dass
jede Klasse eine überschaubare und gut wartbare Funktion erfüllt.

Im Anschluss daran wurden mit dem Analysewerkzeug **Metrics Reloaded** die wichtigsten **Codemetriken** erhoben. Dabei
wurde besonderes Augenmerk auf **Komplexität, Abhängigkeiten, Codeumfang und Dokumentationsabdeckung** gelegt.

### 2. Auflösung zyklischer Abhängigkeiten

Während der Analyse wurde eine **zyklische Abhängigkeit zwischen den ChargingStates und dem VoltageSimulator**
festgestellt. Zur Behebung dieses Designproblems wurde das Interface `VoltageChangeProvider` eingeführt. Dieses sorgt
für eine saubere Entkopplung der Komponenten und erhöht die Flexibilität und Testbarkeit des Systems.

### 3. Vereinfachung komplexer Methoden

Ein weiteres Augenmerk lag auf der **Reduktion methodischer Komplexität**. Methoden mit Werten über den Grenzwerten (z.
B. **Cognitive Complexity > 5**) wurden durch das Aufbrechen von Entscheidungsstrukturen und das Auslagern in
Hilfsmethoden vereinfacht.

### 4. Ergebnisse der Metrikanalyse

#### 4.1 Komplexitätsmetriken

* **Cognitive Complexity (CogC)** misst die Verständlichkeit des Codes aus menschlicher Sicht. Der Maximalwert lag hier
  bei **5**, die meisten Methoden wiesen Werte von **≤ 3** auf.
* **Essential Cyclomatic Complexity (ev(G))** gibt die strukturelle Komplexität des Kontrollflusses an. Auch hier wurde
  ein Maximalwert von **5** erreicht.
* **Cyclomatic Complexity (v(G))** nach McCabe beschreibt die Anzahl möglicher Ausführungspfade. Der Höchstwert von **6
  ** trat nur bei einer Methode auf, die Mehrheit lag bei **3 oder weniger**.
* **iv(G)** (Interval-based Cyclomatic Complexity) blieb ebenfalls unter **5**, ist jedoch weniger relevant.

➡️ Diese Werte belegen eine **niedrige methodische Komplexität**, was die Wartbarkeit, Verständlichkeit und Testbarkeit
deutlich verbessert.

* Auf Klassenebene lag der höchste **Operation Count (OCmax)** unterhalb kritischer Schwellen, was auf eine angemessene
  interne Methodengröße hinweist.
* Der **Weighted Methods per Class (WMC)**-Wert blieb **≤ 13**, meist unter **10**, was für übersichtliche und klar
  strukturierte Klassen spricht.
* Die durchschnittliche Komplexität pro Package (**v(G)avg**) lag durchgehend unter **2**, was eine **niedrige
  Komplexität auf Modularitätsebene** bestätigt.

#### 4.2 Abhängigkeitsmetriken

* Es bestehen **keine zyklischen Abhängigkeiten** zwischen Klassen, was durch die gezielte Einführung von Schnittstellen
  erreicht wurde.
* Die Werte für **Dcy (Depth of Cycles)** und **Dpt (Depth of Package Tree)** lagen jeweils unter **10**, was auf eine *
  *angemessen flache, gut strukturierte Architektur** schließen lässt.

#### 4.3 Lines-of-Code-Metriken

* Alle Methoden blieben mit **< 20 NCLOC (Non-Comment Lines of Code)** innerhalb der Clean Code-Empfehlungen.
* Obwohl einige Klassen **mehr als 50 LOC** umfassen, wurde durch die saubere Einhaltung des **Single Responsibility
  Prinzips** eine hohe Modularität sichergestellt.

#### 4.4 Dokumentationsabdeckung (JavaDoc)

* Alle **öffentlichen Klassen, Konstruktoren, Methoden und Attribute** wurden mit **JavaDoc-Kommentaren** versehen.
  Damit wird eine vollständige Dokumentationsabdeckung erzielt, wie sie in gut wartbarer Software empfohlen wird.

---

### 5. Fazit

Durch die Kombination aus **manueller Codeüberprüfung**, **Refactoring nach Clean Code-Prinzipien** und einer *
*systematischen Metrikanalyse** konnte die Codebasis entscheidend verbessert werden. Der Code weist nun eine **niedrige
Komplexität**, **saubere Modularität**, **keine zyklischen Abhängigkeiten**, sowie eine **hohe Dokumentationsqualität**
auf. Damit erfüllt das System zentrale Anforderungen an **Wartbarkeit, Testbarkeit und Lesbarkeit** und ist gut auf
zukünftige Erweiterungen vorbereitet.

