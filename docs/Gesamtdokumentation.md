## Hinweise zu verwendeten Hilfsmitteln

Ich versichere, dass ich die vorliegende Arbeit **eigenständig und ohne unzulässige Hilfe Dritter** angefertigt habe.
Alle verwendeten Quellen und Hilfsmittel sind im Folgenden vollständig aufgeführt und beschrieben. Inhalte, die aus
externen Quellen wörtlich oder sinngemäß übernommen wurden, sind entsprechend kenntlich gemacht.

### Fachliche Grundlagen

Als Grundlage für die inhaltliche und methodische Umsetzung des Projekts dienten die **Vorlesungsfolien
„Softwareengineering“ von Prof. Karsten Becker**. Ergänzend habe ich die **YouTube-Tutorialreihe „Softwareengineering“
von Morpheus Tutorials** ([Link zur Playlist](https://www.youtube.com/playlist?list=PLNmsVeXQZj7qNMn6zimfu4JPUklG-4Uu4))
verwendet, um meinen gesamten Softwareentwicklungsprozess zu strukturieren – insbesondere zur **Unterteilung in Phasen**
(Requirements Engineering, Architektur, Design, Implementierung, Test) und zur **Planung iterativer Entwicklungsschritte
**.

### Technische Tools und Dokumentation

Zur effizienten Nutzung der verwendeten Werkzeuge habe ich jeweils die **offiziellen Dokumentationen** der folgenden
Tools herangezogen:

* **Jira** (Projektmanagement und Aufgabenverwaltung)
* **GitHub** (Versionsverwaltung, Code-Hosting)
* **PlantUMLParser** (generative Erstellung und Analyse von UML-Diagrammen)
* **MetricsReloaded** (Analyse von Code-Metriken im Rahmen von Clean Code)

### Codemetriken und Clean Code

Für die Bewertung der Codequalität und die Analyse von Metriken habe ich auf die **Clean Code-Prinzipien**
zurückgegriffen, wie sie in den **Vorlesungen „Programmieren 1 & 2“ von Prof. Andreas Berl** vermittelt wurden. Diese
dienten als Bewertungsmaßstab für Lesbarkeit, Wartbarkeit und Struktur des Quellcodes.

### Nutzung von ChatGPT

**ChatGPT** wurde in verschiedenen Phasen des Projekts unterstützend eingesetzt:

1. **Recherche allgemein gültiger Informationen**, sofern keine spezifischen Quellen angegeben sind.
2. **Formulierung von Dokumentationstexten**: Eigene Inhalte und Notizen wurden in einfacher Sprache formuliert und
   durch ChatGPT sprachlich überarbeitet.
3. **Implementierungsunterstützung**, insbesondere bei:
    * Fehlersuche und Debugging
    * Erstellung von JUnit-Tests
    * Umsetzung des Command Design Patterns
    * JavaDoc
4. **Testdokumentation**: Formulierung von Testfällen und Ergebnissen anhand selbst definierter Inhalte.
5. **Architektur- und Designphase**:

    * Unterstützung bei der Schnittstellendokumentation
    * Überprüfung von Design Pattern-Einsatzmöglichkeiten
    * Umwandlung von manuell erstellten Zustandsdiagrammen in PlantUML-Code
    * Ideen für die Erweiterung von Klassendiagrammen basierend auf neuen Requirements
6. **Sprint Planning**:

    * Erstellung von Subtasks zu definierten Requirements
    * Strukturierung geplanter Aufgabenpakete auf Grundlage funktionaler Anforderungen
    * Vorschläge wurden gesichtet, angepasst oder verworfen

Die Verwendung von ChatGPT diente ausschließlich der inhaltlichen Unterstützung auf Basis meiner eigenen Vorarbeit und
wurde reflektiert und eigenverantwortlich eingesetzt.

## Requirement Engineering

Im Rahmen eines Presprints wurde ein strukturierter Requirement Engineering Prozess durchgeführt, um die grundlegenden
Anforderungen und Aufgaben für das geplante Softwareprojekt zur Batteriestandskontrolle für einen Rasierapparat zu
definieren. Ziel war es, frühzeitig einen klaren Rahmen zu schaffen und zentrale Funktionen sowie Anforderungen zu
identifizieren.

Dieser Presprint besteht aus Stakeholderanalyse, Lasten- und Pflichtenheft, Use Cases, Teilfunktionalitäten und der
Ableitung sowie Priorisierung konkreter Requirements.

[Requirement Engineering](Requirement_Engineering.md)

## Generelles zu Sprints

Zunächst wird das Sprint-Planning durchgeführt, dazu wurden passende Requirements ausgewählt, sodass das Ergebnis des
Sprints eine funktionsfähige Produktversion ist. Außerdem wurde das Sprintziel und der Startpunkt festgelegt. Die
Sprints sind keine klassischen Sprints im Sinne von: Sie haben immer dieselbe Dauer (Dadurch bedingt, dass ich an diesem
Projekt parallel zu vielen anderen Projekten, Vorlesungen, einem Urlaub und anderen Terminen gearbeitet habe. So wurden
die Sprints in freien Zeitfenstern und dann so zusammenhängend wie möglich bearbeitet.). Sie haben regelmäßige Events
zur Abstimmung (Dadurch bedingt, dass es sich hier um eine Einzel- und keine Teamarbeit handelt, gab es keine
Möglichkeit zu sinnvollen Austauschen und externen Feedbackschleifen.). Allerdings wurde im Sinne von klassischen
Sprints der Fokus auf Inkremente gelegt, also dass jeder Sprint eine funktionsfähige Produktversion als Ergebnis
liefert.

Anschließend werden in Jira die requirementspezifischen Subtasks angelegt. Dazu werden die Requirements und die
einzelnen Schritten der Phasen der SW-Entwicklung (Architektur, Design, Implementierung, Test) nebeneinandergelegt und
überlegt, welche Schritte besonders wichtig für welches Requirement sind. Z.B.:

- Benötige ich noch weitere Informationen, um das Requirement sinnvoll und praxisnah umzusetzen? -> Recherche
- Benötige ich eine neue Klasse für Requirement XY? -> Klassendiagramm muss aktualisiert werden
- Benötige ich eine neue öffentliche Schnittstelle um dieses Requirement umzusetzen? -> Schnittstellendoku erweitern
- Was muss ich testen, um die korrekte Implementierung des Requirements zu gewährleisten?

Anhand dieser und vieler weiterer Fragen, sind die Subtasks aufgestellt worden. Diese sind bewusst nicht voll
ausformuliert, sondern dienen lediglich als Orientierung bzw. Reminder an die Gedanken, die ich mir zu diesen Fragen
gemacht habe. Dieses Vorgehen ist hier ausreichend, da es sich um eine Einzelarbeit handelt: Ich lese den Titel der
Subtask und weiß was zu tun ist. Alles Weitere wäre in diesem Rahmen Overhead gewesen.

Das Jira Board wird während des Sprints so genutzt, dass die Subtasks nach Requirements dargestellt werden. Je Phase der
SW-Entwicklung wurde anhand der zugewiesenen Labels gefiltert und so konnte sehr zielgerichtet an den einzelnen Phasen
gearbeitet werden.

Insgesamt liegt in der Bearbeitung des Sprints ein besonderer Fokus auf der Recherche (wissen was umzusetzen und wie
üblich, welche Standards und Vorgaben sind zu beachten). Die Architektur- und Designphase wurden sich detaillierte
Gedanken zur sinnvollen Umsetzung der einzelnen Requirements gemacht. Dabei wurde geklärt, ob und wenn ja welche
Klassen, Schnittstellen oder auch Zustandsautomaten, etc. benötigt werden. In der Implementierungsphase erfolgte eine
verfeinerte Ausarbeitung, wie diese Planungen richtig und sauber umgesetzt werden können. Dabei haben sich oft
Optimierungen, Erweiterungen oder auch Kürzungen der Pläne ergeben, welche dann in der Architektur- und Designphase
nachgezogen werden.

## Sprint 1

[Sprint 1](Sprint%201/Sprint_1.md)

## Sprint 2

[Sprint 2](Sprint%202/Sprint_2.md)

## Sprint 3

[Sprint 3](Sprint%203/Sprint_3.md)

## Finale Traceability-Matrix

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

## zusätzliche Notizen

[zusätzliche Notizen](referenziert/zusätzlicheNotizen.pdf)

## Lessons_Learned

Nach Abschluss des Projekts wurde eine Retrospektive durchgeführt, um den Entwicklungsprozess kritisch zu reflektieren.
Dabei konnten wertvolle Erkenntnisse für zukünftige Softwareprojekte gewonnen werden.

Im Großen und Ganzen ist hier festgehalten, was ich für mich aus dem Projekt mitgenommen habe.

[Lessons Learned](Lessons_Learned.md)
