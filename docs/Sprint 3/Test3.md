# Test

## **1. Ziel der Tests**

Das Ziel der Tests ist die Sicherstellung der korrekten Funktionalität, Anzeigeverhalten, Benutzerfreundlichkeit und
Reaktionslogik des Systems in verschiedenen Szenarien. Insbesondere soll geprüft werden:

* Die Korrektheit von Umrechnungslogiken bei unterschiedlichen Spannungswerten.
* Die zuverlässige Auslösung von Funktionen bei bestimmten Systemzuständen.
* Die Anzeige- und Signalverhalten der Benutzeroberfläche (z. B. LEDs, Textanzeigen).
* Die Lesbarkeit und Bedienbarkeit aus Sicht der Nutzer (Usability).
* Die Robustheit und Korrektheit interner Funktionen (z. B. Rekalibrierung).

---

## **2. Testumfang**

### In-Scope:

Die folgenden Testarten und -bereiche sind Bestandteil der Tests:

* **Unit Tests**

    * Umrechnung bei unterschiedlichen Spannungswerten (Req. 2.3)
    * Rekalibrierung (Req. 3.5)

* **Blackbox Tests**

    * Anzeige bei spezifischen Spannungswerten (Req. 2.3)
    * Auslösung von Operationen zum richtigen Zeitpunkt (Req. 3.3)
    * LED-Blinkverhalten bei Schwellwert (Req. 4.2)
    * Anzeige von Signalsymbolen bei ausreichendem SoC (Req. 5.5)
    * LED-Dauerleuchten bei kritischer Spannung (Req. 6.1)

* **Usability Tests**

    * Lesbarkeit der Anzeige unter verschiedenen Bedingungen (Req. 2.3)

### Out-of-Scope:

Die folgenden Aspekte werden im Rahmen dieses Testplans **nicht** berücksichtigt:

* Performance-Tests (z. B. Antwortzeiten bei extremen Lasten)
* Sicherheitstests (z. B. gegen Manipulation oder externe Eingriffe)
* Kompatibilitätstests mit externen Systemen oder Schnittstellen
* Langzeittests zur Haltbarkeit und Lebensdauer der Komponenten

---

## Code-Review Checkliste

[Code-Review Checkliste](../referenziert/Test/Code_Review_Checkliste.md)

## Definition Testfälle inkl. betroffener Requirements

[Testfälle](../referenziert/Test/Testfaelle.md)

## Testdurchführung

Im Rahmen der Testdurchführung wurden verschiedene Testarten eingesetzt, darunter Unit Tests, Blackbox Tests und
Usability Tests. Dabei traten zwei zentrale Probleme auf: Beim erneuten Ausführen des Blackbox-Tests BB 6 wurde
festgestellt, dass die Warnung bei Unterschreiten der SoC-Warnschwelle nicht korrekt angezeigt wurde, sofern die
Schwelle nicht zuvor manuell über den Einstellbutton angepasst wurde. Zur Behebung dieses Fehlers wurde die Logik
im `BatteryThresholdManager` überarbeitet, sodass das Setzen und Auslesen des Schwellenwerts nun stets über den
persistent gespeicherten `LowBatteryThreshold` erfolgt.

Zudem führten die Tests UT 5 und BB 13 zur Identifikation eines konzeptionellen Fehlers in der Kalibrierungs- und
Rekalibrierungslogik der verbleibenden Laufzeit. Es wurde erkannt, dass die verbleibende Laufzeit linear zur zeitlich
linear abfallenden Spannung verlaufen muss. Zur Korrektur wurde die Speicherung der Kalibrierungsdaten angepasst:
Anstelle einer vollständigen Verlaufsspeicherung wird nun ausschließlich die maximale Restlaufzeit bei maximaler
Spannung persistiert und für den Gültigkeitsbereich von 3 V bis 4,2 V linear interpoliert.

Nach Implementierung dieser Änderungen wurden sämtliche zuvor definierten Testfälle – einschließlich der aus früheren
Sprints – erneut ausgeführt. Alle Tests zeigten das erwartete Verhalten, wodurch die vorgenommenen Anpassungen
erfolgreich validiert wurden.

## Dokumentation der Ergebnisse

[Ergebnisse](../referenziert/Test/Testergebnisse.md)
