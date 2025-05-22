# Test

## **1. Ziel der Tests**

Das Ziel der Tests ist die Verifikation und Validierung des Batteriesystems hinsichtlich funktionaler Korrektheit,
Benutzerfreundlichkeit sowie robuster Systemreaktion auf externe und interne Eingaben. Die Tests stellen sicher, dass:

* Spannungen innerhalb zulässiger technischer Grenzen verarbeitet werden.
* Die Umrechnung von Spannung in Ladezustandsanzeige korrekt erfolgt.
* Die Benutzeroberfläche intuitiv, schnell und zugänglich ist.
* Die Systemanzeige auf physische Benutzereingaben korrekt und zeitnah reagiert.

---

## **2. Testarten und Abdeckung**

### 2.1 Unit Tests

Ziel: Prüfung der kleinstmöglichen Testeinheiten (z. B. Methoden in `BatteryStateController`), um fehlerhafte Logik
frühzeitig zu erkennen.

Beispielsweise getestet:

* Clamping/Begrenzung von Spannungen (UT1, UT2)
* Interpolation bei Ladezustandsberechnung (UT3)

### 2.2 Usability Tests

Ziel: Bewertung der Benutzerfreundlichkeit und Barrierefreiheit der Anzeige. Diese Tests konzentrieren sich auf reale
Nutzerinteraktionen mit dem System (UX1–UX4).

### 2.3 Black-Box/Systemtests

Ziel: Prüfung des sichtbaren Systemverhaltens ohne Kenntnis der internen Implementierung. Diese Tests stellen sicher,
dass Benutzerinteraktionen und automatisierte Prozesse wie erwartet funktionieren (BB1–BB5).

---

## **3. Teststrategie**

Die Teststrategie kombiniert **strukturierte manuelle Tests** mit **automatisierten Unit-Tests**, um folgende Ziele zu
erreichen:

* **Automatisierte Tests** für alle testbaren Codeeinheiten (insbesondere `BatteryStateController`)
* **Manuelle Tests** für Usability-Aspekte, UI-Wahrnehmung und Timing (UX1–UX4, BB1–BB5)
* **Iterative Tests** nach jeder Änderung an Anzeige- oder Kontrolllogik
* **Regressionstests** nach Anpassungen an der Ladezustandsberechnung

### Testumgebung:

* Simulierte Spannungsquellen für Systemtests
* Hardware-Emulatoren oder Prototypen für UI-Tests
* JUnit 4/5 für automatisierte Tests
* Optionaler Einsatz von Logging- und Timing-Tools für Reaktionszeitanalyse

---

## **4. Testumfang**

### In-Scope:

* Berechnung und Anzeige des Ladezustands auf Basis von Spannung
* Begrenzung von Eingangsspannungen auf zulässigen Bereich
* Reaktion auf Benutzereingaben (Knopfdrücke)
* Darstellung und Verhalten der Anzeige

### Out-of-Scope:

* Hardwareseitige Energieversorgung oder Batterieverhalten außerhalb des Softwarebereichs
* Langzeitverhalten (z. B. Alterung der Batterie)
* Fehlerverhalten bei Sensordefekten (sofern nicht spezifiziert)

## Code-Review Checkliste

[Code-Review Checkliste](../referenziert/Test/Code_Review_Checkliste.md)

## Auffälligkeiten bei Code-Review

Die wichtigsten Punkte aus der Code-Review Checkliste wurden in die automatischen Inspections von IntelliJ integriert,
sodass der Code-Review-Prozess weitestgehend automatisiert werden kann. Anhand der Inspection Ergebnisse wurde der Code
gecleant. Außerdem wurden komplexere Methoden (z.B. calculateStateOfCharge) überarbeitet, um die lesbarkeit und
wartbarkeit zu erhöhen.

## Definition Testfälle inkl. betroffener Requirements

[Testfälle](../referenziert/Test/Testfaelle.md)

## Dokumentation der Ergebnisse

[Ergebnisse](../referenziert/Test/Testergebnisse.md)
