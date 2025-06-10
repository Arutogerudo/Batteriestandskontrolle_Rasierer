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

## Dokumentation der Ergebnisse

[Ergebnisse](../referenziert/Test/Testergebnisse.md)

**Hinweise zur Testumgebung und Teststrategie:**
Die Black-Box-Tests wurden bereits während der Implementierungsphase als kontinuierliche Referenz verwendet.
Dadurch konnte der Code gezielt auf das erwartete Systemverhalten hin entwickelt werden, was zu einer hohen
Übereinstimmung zwischen Testspezifikation und tatsächlicher Funktionsweise geführt hat.
