# Test

## **1. Ziel der Tests**

Das Ziel der Tests ist, die Funktionalität, Zuverlässigkeit und Benutzerfreundlichkeit der LED-Warnanzeige sowie des
Ladevorgangs der Batterie zu überprüfen. Dabei soll sichergestellt werden, dass:

* Warn-LEDs zur richtigen Zeit und mit den korrekten Farben/blinkenden Mustern aktiviert werden.
* Die LED-Zustände klar und intuitiv für den Nutzer erkennbar und verständlich sind.
* Der Ladevorgang korrekt gestartet, pausiert oder beendet wird, abhängig von den Systemzuständen (z.B. korrekter
  Kabelanschluss, Batterietemperatur, Ladezustand).
* Die Sicherheit durch Abschaltung bei Überladung oder kritischen Temperaturen gewährleistet ist.
* Die Farbwahrnehmung der LEDs auch für Menschen mit Farbsehschwächen geeignet ist.

---

## **2. Testumfang**

### In-Scope:

* Funktionale Tests der LED-Anzeige auf korrekte Farb- und Blinkmuster bei verschiedenen Zuständen (Warnung,
  Ladezustand).
* Usability-Tests zur Wahrnehmbarkeit und Verständlichkeit der LED-Signale, inklusive Tests mit Farbsimulator zur
  Berücksichtigung von Farbsehschwächen.
* Blackbox-Tests zur Überprüfung der Lade- und Sicherheitsfunktionen (Start, Pause, Stopp des Ladevorgangs).
* Unit-Tests zur korrekten Erkennung von Schwellenwerten für Warnmeldungen.

### Out-of-Scope:

* Hardware-Tests der physischen LED-Komponenten.
* Tests der Batterietechnologie oder der Hardware selbst (z.B. Akkuqualität).
* Langzeittests zur Haltbarkeit der LEDs oder des Ladegeräts.
* Tests zu anderen Anzeigen oder Bedienelementen außer der LED-Warnanzeige.

---

## Code-Review Checkliste

[Code-Review Checkliste](../referenziert/Test/Code_Review_Checkliste.md)

## Definition Testfälle inkl. betroffener Requirements

[Testfälle](../referenziert/Test/Testfaelle.md)

## Dokumentation der Ergebnisse

[Ergebnisse](../referenziert/Test/Testergebnisse.md)

**Hinweise zur Testumgebung und Teststrategie:**
Die Erkennung von Ladebefehlen erfolgt über einen separaten Thread, was in seltenen Fällen dazu führt, dass Befehle
nicht sofort erkannt werden. Dieses Verhalten wird im Rahmen des Projekts als akzeptabel bewertet, da die Ladeerkennung
rein simulativ erfolgt und keine reale Hardware angesteuert wird.
Zudem wurden die Black-Box-Tests bereits während der Implementierungsphase als kontinuierliche Referenz verwendet.
Dadurch konnte der Code gezielt auf das erwartete Systemverhalten hin entwickelt werden, was zu einer hohen
Übereinstimmung zwischen Testspezifikation und tatsächlicher Funktionsweise geführt hat.
