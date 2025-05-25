# Architektur

## Architekturmuster festlegen

**Schichtenarchitektur**:

- Trennung der Verantwortlichkeiten (Hardwarenahe-Funktionen wie Spannung messen, Anwendungslogik wie Kalibrierung und
  Ladezustandsberechnung, Benutzeroberfläche)
- Jede Schicht ist unabhängig testbar und austauschbar
- Segmente nach funktionaler Rolle gruppiert → Kapselung und Entkopplung der Anwendung
- Jede Schicht darf nur die direkt darunterliegende Schicht ansprechen.
  -Es gibt keine Rückwärtssprünge oder Querkommunikation.

## Komponentendiagramm

![Komponentendiagramm](../referenziert/Architektur/Komponentendiagramm.png)

| **Komponente**      | **Requirements**                                                                          | **Jira-Referenzen**                                                    |
|---------------------|-------------------------------------------------------------------------------------------|------------------------------------------------------------------------|
| userInterface       | Req. 2.1, Req. 2.2, Req. 2.4, Req. 2.5, Req. 2.6, Req. 2.7, Req. 2.8, Req. 2.9, Req. 2.10 | BAT-10, BAT-11, BAT-13, BAT-14, BAT-15, BAT-16, BAT-17, BAT-18, BAT-19 |
| batteryLogic        | Req. 1.1, Req. 1.2, Req. 1.3                                                              | BAT-7, BAT-8, BAT-9                                                    |
| hardwareAbstraction | Req. 1.1, Req. 2.2, Req. 2.5                                                              | BAT-7, BAT-11, BAT-14                                                  |
| persistenceManager  | Req. 3.1, Req. 3.2                                                                        | BAT-20, BAT-21                                                         |

**Verantwortlichkeiten der Komponenten:**

| **Komponente**      | **Rolle**                  | **Verantwortlichkeiten**                                                 |
|---------------------|----------------------------|--------------------------------------------------------------------------|
| userInterface       | Präsentationsschicht       | Anzeige von Zuständen, LED-Steuerung, Barrierefreiheit, User-Interaktion |
| batteryLogic        | Business-Logik             | Ladezustand berechnen, User-Interaktionen handeln                        |
| hardwareAbstraction | Hardware-Interface         | Zugriff auf Sensoren / Simulatoren, LEDs abstrahieren                    |
| persistenceManager  | Speicher- / Konfig-Schicht | Kalibrierung, Spannungsreferenz, persistente Werte speichern/laden       |

## Schnittstellendefinition

| **Ziel**              | **Quelle**            | **Schnittstellen**                                                                                                                                                                                                                                                                                                                                |
|-----------------------|-----------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| `userInterface`       | `batteryLogic`        | [updateOperationState()](../referenziert/Architektur/Schnittstellendokumentation.md#methode-updateoperationstate), [getDisplayState()](../referenziert/Architektur/Schnittstellendokumentation.md#methode-getdisplaystate), [calculateStateOfCharge()](../referenziert/Architektur/Schnittstellendokumentation.md#methode-calculatestateofcharge) |
| `userInterface`       | `hardwareAbstraction` | [ButtonInput()](../referenziert/Architektur/Schnittstellendokumentation.md#methode-buttoninput)                                                                                                                                                                                                                                                   |
| `batteryLogic`        | `hardwareAbstraction` | [readVoltage()](../referenziert/Architektur/Schnittstellendokumentation.md#methode-readvoltage), [setState()](../referenziert/Architektur/Schnittstellendokumentation.md#methode-setstate)                                                                                                                                                        |
| `batteryLogic`        | `persistenceManager`  | [readCalibVoltageToSoCFromDisc()](../referenziert/Architektur/Schnittstellendokumentation.md#methode-readcalibvoltagetosocfromdisc), [readLowBatteryThresholdFromDisc()](../referenziert/Architektur/Schnittstellendokumentation.md#methode-readlowbatterythresholdfromdisc)                                                                      |
| `hardwareAbstraction` | `persistenceManager`  | [loadCalibrationData()](../referenziert/Architektur/Schnittstellendokumentation.md#methode-loadcalibrationdata)                                                                                                                                                                                                                                   |

## Technologiestack

| Kategorie                | Technologie / Tool            | Begründung                                                                                                   |
|--------------------------|-------------------------------|--------------------------------------------------------------------------------------------------------------|
| Sprache                  | Java Temurin 17               | persönliche Erfahrung -> erhöhte Produktivität & geringere Fehleranfälligkeit, einfache Wartung              |
| Buildsystem              | --keins--                     | kein Build                                                                                                   |
| Versionskontrolle        | Git + GitHub                  | Standard                                                                                                     |
| Organisation, Tracking   | Jira                          | bereits Vorerfahrungen, sehr viele Funktionalitäten, Unternehmensstandard, Integration in github möglich     |
| IDE                      | IntelliJ                      | Leicht, modular, gut kompatibel für Code in Java und Doku in .md                                             |
| Ausgabe                  | Konsole                       | einfachste Lösung und in meinem Projekt kein Fokus                                                           |
| Dokumentation            | Markdown + draw.io / PlantUML | Für Anforderungen & Architektur, gute und einfache Tools für Modellierung und Dokumentation, IDE Integration |
| Codeanalyse              | MetricsReloaded               | CI/CD integrierbar, ideal für Clean Code, Integration mit Github ermöglicht automatische Prüfung             |
| Test-Framework           | JUnit                         | Standard für Java, einfach, erweiterbar                                                                      |
| Frameworks, Bibliotheken |                               |                                                                                                              |

**Änderung der IDE von VS Code zu IntelliJ IDEA**, da VS Code nicht alle benötigten Funktionen für Java bietet:

- In IntelliJ IDEA ist die **Code-Analyse über das Plugin Metrics Reloaded** direkt integrierbar.
- Bessere Unterstützung bei Code-Review durch Warnings.