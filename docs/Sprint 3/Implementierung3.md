# Implementierung

## Traceability-Matrix

| Requirement-ID | Jira-Issue | Komponente                         | Klasse(n)                                          | Schnittstelle(n)                                | Testfall(e) |
|----------------|------------|------------------------------------|----------------------------------------------------|-------------------------------------------------|-------------|
| 1.1            | BAT-7      | batteryLogic, hardwareAbstraction  | `VoltageSensor`                                    | readVoltage()                                   | BB1         |
| 1.2            | BAT-8      | batteryLogic                       | `BatteryStateController`                           | calculateStateOfCharge()                        | UT3         |
| 1.3            | BAT-9      | batteryLogic                       | `BatteryStateController`                           | getDisplayState()                               | UX1         |
| 2.1            | BAT-10     | userInterface                      | `SimpleGUI`                                        | getDisplayState()                               | BB2         |
| 2.2            | BAT-11     | userInterface, hardwareAbstraction | `ButtonInput`, `InteractionHandler`                | ButtonInput()                                   | BB4         |
| 2.4            | BAT-13     | userInterface                      | `SimpleGUI`                                        |                                                 |             |
| 2.5            | BAT-14     | userInterface, hardwareAbstraction | `InteractionHandler`                               | setState()                                      | BB3         |
| 2.6            | BAT-15     | userInterface                      | `SimpleGUI`                                        |                                                 | UX3         |
| 2.7            | BAT-16     | userInterface                      | `SimpleGUI`                                        |                                                 | UX2         |
| 2.8            | BAT-17     | userInterface                      | `SimpleGUI`                                        | updateLEDState()                                | UX-5        |
| 2.9            | BAT-18     | userInterface                      | `SimpleGUI`                                        |                                                 | UX4         |
| 2.10           | BAT-19     | userInterface                      | `SimpleGUI`                                        | getDisplayState()                               | BB5         |
| 3.1            | BAT-20     | persistenceManager                 | `SettingsStorage`, `VoltageSimulator`              | calculateStateOfCharge()                        | UT1, UT2    |
| 3.2            | BAT-21     | persistenceManager                 | `SettingsStorage`                                  | readCalibVoltageToSoCFromDisc()                 |             |
| 4.1            | BAT-26     | batteryLogic, userInterface        | `BatteryStateController`, `VisualOutputController` | isLowBattery()                                  | UT-4, BB-6  |
| 4.3            | BAT-28     | userInterface                      | `LEDController`, `VisualOutputController`          | startBlinking(), setLEDState()                  | UX-6        |
| 5.1            | BAT-29     | userInterface                      | `VisualOutputController`, `LEDController`          | updateOperationState()                          | BB-7        |
| 5.2            | BAT-30     | userInterface                      | `VisualOutputController`, `LEDController`          | updateOperationState()                          | BB-8        |
| 5.3            | BAT-31     | userInterface, batteryLogic        | `VisualOutputController`, `OperationController`    | setLEDState(), updateOperationState()           | BB-9        |
| 5.4            | BAT-32     | hardwareAbstraction                | `ChargingDetection`                                | getChargingState(), listenForChargingCommands() | UX-7        |
| 5.6            | BAT-34     | userInterface                      | `LEDController`                                    | stopBlinking(), turnOff()                       |             |
| 6.2            | BAT-36     | batteryLogic                       | `OperationController`                              |                                                 | BB-10       |
| 6.3            | BAT-37     | hardwareAbstraction, batteryLogic  | `TemperatureSimulator`, `OperationController`      |                                                 | BB-11       |

## Code-Metriken

### Komplexität

Die Codemetriken wurden über das IntelliJ-Plugin "MetricsReloaded" ermittelt. Es wurden Methoden-, Klassen-, Paket-,
Modul- und Projektmetriken erfasst. Basierend auf den Methodenmetriken wurde der Code nochmals überarbeitet, um die
Komplexität und Abhängigkeiten zu reduzieren und die Lesbarkeit zu verbessern.

#### Methodenmetriken

Es wurde die kognitive Komplexität (CogC), die zyklomatische Komplexität (v(G)) sowie strukturelle Metriken wie ev(G)
und iv(G) berechnet. Der maximale CogC-Wert liegt bei 6 (updateLEDState), was für eine gute Lesbarkeit und
Verständlichkeit aller Methoden spricht. Die zyklomatische Komplexität (v(G)) ist maximal 6 und nur 6 Methoden haben
Werte > 3. Das zeigt, dass die meisten Methoden sehr gut und alle zumindest im grünen Bereich sind. Die strukturellen
Komplexitäten iv(G) und ev(G), ist maximal 3, was für eine gute Modularität spricht.

#### weitere Metriken

Die durchgeführten Metrik-Analysen auf Klassen-, Paket-, Modul- und Projektebene zeigen, dass sich alle bewerteten
Kennzahlen im grünen Bereich befinden. Dies umfasst insbesondere die Klassenmetriken WMC, OCavg und OCmax, welche auf
eine ausgewogene Methodenanzahl sowie eine geringe bis moderate Komplexität einzelner Methoden hinweisen.

Auch die Cyclomatic Complexity auf Modul- und Projektebene, gemessen über v(G)avg und v(G)tot, bestätigt die gute
Strukturierung der Software. Die Komplexität verteilt sich gleichmäßig über alle Ebenen, ohne auffällige Hotspots oder
kritische Ausreißer.

Zusätzlich belegen die positiven Werte der Paket- und Modulmetriken eine hohe Kohäsion und eine geringe Kopplung
zwischen Komponenten, was die Wartbarkeit und Erweiterbarkeit der Gesamtarchitektur unterstützt.

### Abhängigkeiten der Pakete und Klassen

Die Analyse der Abhängigkeitsmetriken mit MetricsReloaded zeigt, dass die Software eine insgesamt flache und gut
strukturierte Architektur aufweist. Zyklische Abhängigkeiten treten nur in wenigen Klassen auf, insbesondere innerhalb
der batteryLogic.*Command-Komponenten und dem InteractionHandler. Die durchschnittliche Abhängigkeits-Tiefe (Dpt = 2,65)
sowie der geringe Anteil zyklischer Klassen (PDcy = 1,09) bestätigen eine wartbare Modulstruktur. Optimierungspotenzial
besteht in der Reduktion der Zyklen innerhalb der Logik-Komponenten.

Die zyklische Abhängigkeit zwischen dem InteractionHandler und den verschiedenen Command-Klassen (ShortPressCommand,
LongPressCommand, InactivityCommand) wurde durch die Einführung eines CommandContext-Interfaces aufgelöst. Dadurch
kennen die Commands nur noch eine abstrakte Schnittstelle, nicht aber die konkrete Handler-Implementierung. Dies
reduziert die Kopplung, verbessert die Testbarkeit und beseitigt zyklische Abhängigkeiten gemäß den Metriken.

Im Rahmen der Weiterentwicklung des OperationController wurde eine Entkopplung der Abhängigkeiten durch Einführung von
Interfaces und den Einsatz von Dependency Injection umgesetzt. Ziel war es, die Klasse flexibler, testbarer und
wartbarer zu gestalten. Durch die Verwendung von Schnittstellen ist der Controller nun nicht mehr direkt an konkrete
Implementierungen gebunden, was eine einfache Austauschbarkeit einzelner Komponenten ermöglicht. Zudem verbessert sich
dadurch die Testbarkeit erheblich, da Abhängigkeiten im Testumfeld leicht durch Mocks ersetzt werden können. Insgesamt
trägt dieses Vorgehen zu einer sauberen, modularen und zukunftssicheren Softwarearchitektur bei.

### Codezeilen

Die Codezeilen wurden über das IntelliJ-Plugin "MetricsReloaded" ermittelt. Die Analyse zeigt, dass die maximale
Methodenlänge 28 Zeilen beträgt, was für eine gute Lesbarkeit und Verständlichkeit spricht. Die meisten Methoden sind
deutlich kürzer, was die Wartbarkeit erhöht. Die Klassenlänge variiert zwischen 10 und 110 Zeilen, was
ebenfalls im grünen Bereich liegt. Alle Klassen zusammen haben 900 Zeilen Code, was für ein überschaubares und
verständliches Projekt spricht.