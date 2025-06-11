# Testfälle

## **Unit Tests**

### UT1 – Spannungsgrenzen: Untergrenze

* **Ziel:** Sicherstellen, dass Spannung unter 3.0 V korrekt als niedrigst möglicher SoC (0 %) angezeigt wird
* **Ausgangszustand:** DisplayState = 'OFF' | 'STATE_OF_CHARGE', OperationState = 'OPERATING', ChargingState =
  'DISCHARGING_ACTIVE'
* **Ereignis (Zustandsübergang):** Spannung fällt unter 3.0 V
* **Eingabe:** Spannung = 2.9 V
* **Erwartete Reaktion:** SoC = 0, RemainingRuntime = 0 
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Klasse:** `BatteryStateController`
* **Requirement:** 3.1

---

### UT2 – Spannungsgrenzen: Obergrenze

* **Ziel:** Sicherstellen, dass Spannung nicht über 4.2 V steigt
* **Ausgangszustand:** DisplayState = 'OFF' | 'STATE_OF_CHARGE', OperationState = 'OPERATING', ChargingState =
  'DISCHARGING_ACTIVE'
* **Ereignis (Zustandsübergang):** kein relevanter für Testfall
* **Eingabe:** Spannung = 4.3 V
* **Erwartete Reaktion:** Rückgabe- oder Korrekturwert ≤ 4.2 V, ggf. Logging/Fehler
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Klasse:** `BatteryStateController`
* **Requirement:** 3.1

---

### UT3 – Ladezustandsberechnung korrekt

* **Ziel:** Verifikation der Spannungs-zu-Prozent-Umrechnung (Interpolation)
* **Ausgangszustand:** DisplayState = 'OFF' | 'STATE_OF_CHARGE', OperationState = 'OFF' | 'OPERATING', ChargingState =
  'DISCHARGING_ACTIVE' | 'DISCHARGING_PASSIVE'
* **Ereignis (Zustandsübergang):** kein relevanter für Testfall
* **Eingabe:** Spannung = 3.6 V
* **Erwartete Reaktion:** Ladezustand ≈ 35 %
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Klasse:** `BatteryStateController.calculateStateOfCharge()`
* **Requirement:** 1.2

---

### UT4 – Niedriger Batteriestand korrekt erkannt

* **Ziel:** Prüfung, ob `isLowBattery()` bei SoC < Threshold korrekt `true` zurückliefert
* **Ausgangszustand:** LEDMode = 'OFF', OperationState = 'OFF' | 'OPERATING', ChargingState = 'DISCHARGING'
* **Ereignis (Zustandsübergang):** state of charge fällt unter Schwellwert durch Spannungsabfall in Simulator
* **Eingabe:** Spannung = 3.2 V
* **Erwartete Reaktion:** `isLowBattery()` gibt `true` zurück
* **Erwarteter Folgezustand:** LEDMode = 'WARNING'
* **Klasse:** `BatteryStateController.isLowBattery()`
* **Requirement:** 4.1

---

### UT5 – Umrechnung von Spannungswerten in Restlaufzeit korrekt durchgeführt

* **Ziel:** Prüfung, ob Spannungswerte korrekt in Restlaufzeit des Rasierers bei Betrieb (in Minuten) umgerechnet werden
* **Ausgangszustand:** OperationState = 'OPERATING', DisplayState = 'REMAINING_TIME'
* **Ereignis (Zustandsübergang):** -
* **Eingabe:** verschiedene Spannungswerte
* **Erwartete Reaktion:** Umrechnung liefert erwartete Restlaufzeit
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Klasse:** `BatteryStateController.calculateRemainingRuntime()`
* **Requirement:** 2.3

---

### UT6 – Rekalibrierung funktioniert wie vorgesehen

* **Ziel:** Prüfung der internen Logik zur Rekalibrierung
* **Ausgangszustand:** ChargingState = 'CHARGING'
* **Ereignis (Zustandsübergang):** Rekalibrierungsprozess wird manuell angestoßen
* **Eingabe:** keine
* **Erwartete Reaktion:** richtige angepasste Werte in txt file -> einlesen und testen
* **Erwarteter Folgezustand:** egal
* **Klasse:** `CalibrationController.recalibrateIfNeeded()`
* **Requirement:** 3.5

---

## **Usability Tests**

### UX1 – Keine Zusatzbedienung erforderlich

* **Ziel:** Bestätigung, dass **kein zusätzlicher Buttondruck** notwendig ist, um Batterieanzeige bei Nutzung zu
  erhalten
* **Ausgangszustand:** DisplayState = 'OFF', OperationState = 'OFF', ChargingState = 'DISCHARGING_PASSIVE'
* **Ereignis (Zustandsübergang):** Gerät einschalten
* **Eingabe:** langer Knopfdruck
* **Erwartete Reaktion:** Anzeige erscheint automatisch
* **Erwarteter Folgezustand:** DisplayState = 'STATE_OF_CHARGE', OperationState = 'OPERATING', ChargingState =
  'DISCHARGING_ACTIVE'
* **Requirement:** 1.3

---

### UX2 – Angenehme Anzeige

* **Ziel:** Anzeige ist optisch nicht überfordernd
* **Ausgangszustand:** DisplayState = 'STATE_OF_CHARGE', OperationState = 'OFF' | 'OPERATING', ChargingState = '
  DISCHARGING_PASSIVE' | 'DISCHARGING_ACTIVE'
* **Ereignis (Zustandsübergang):** keiner (nur wichtig, dass Anzeige an ist)
* **Vorgehen:** Verschiedene Altersgruppen beobachten Anzeige
* **Erwartete Reaktion:** Lesbarkeit der Schriftgröße, angenehmer Kontrast, kein Flackern
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Requirement:** 2.7

---

### UX3 – Intuitive Anzeige

* **Ziel:** Nutzer versteht sofort, was dargestellt wird
* **Ausgangszustand:** DisplayState = 'STATE_OF_CHARGE', OperationState = 'OFF' | 'OPERATING', ChargingState = '
  DISCHARGING_PASSIVE' | 'DISCHARGING_ACTIVE'
* **Ereignis (Zustandsübergang):** keiner (nur wichtig, dass Anzeige an ist)
* **Vorgehen:** Nutzergruppe ohne Anleitung deutet die Aussage der Anzeige
* **Erwartete Reaktion:** Ratenquote ≥ 90 %
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Requirement:** 2.6

---

### UX4 – Barrierefreie Anzeige (Sehschwäche)

* **Ziel:** Anzeige auch bei Sehschwäche (z.B. Presbyopie) lesbar
* **Ausgangszustand:** DisplayState = 'STATE_OF_CHARGE', OperationState = 'OFF' | 'OPERATING', ChargingState = '
  DISCHARGING_PASSIVE' | 'DISCHARGING_ACTIVE'
* **Ereignis (Zustandsübergang):** keiner (nur wichtig, dass Anzeige an ist)
* **Vorgehen:** Nutzer mit bekannten Sehproblemen verwenden System
* **Erwartete Reaktion:** Anzeige lesbar ohne besondere Anstrengung (große Schrift, hoher Kontrast)
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Requirement:** 2.9

---

### UX5 – Farben auch bei Farbenblindheit unterscheidbar

* **Ziel:** Bewertung der Anzeige mit Farbsimulator für verschiedene Typen der Farbenblindheit
* **Ausgangszustand:** jeden LEDMode-Zustand einmal testen
* **Ereignis (Zustandsübergang):** keiner
* **Vorgehen:** Tests mit Simulationssoftware (https://www.color-blindness.com/coblis-color-blindness-simulator/)
* **Erwartete Reaktion:** Nutzer können Farben eindeutig unterscheiden (ggf. nur durch Helligkeit/Blinkmuster)
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Requirement:** 2.8

---

### UX6 – Blinkende rote Warnanzeige ist eindeutig wahrnehmbar

* **Ziel:** Nutzer erkennt visuelle Warnung bei niedrigem Ladezustand zuverlässig
* **Ausgangszustand:** LEDMode = 'OFF', OperationState = 'OFF' | 'OPERATING', ChargingState = 'DISCHARGING'
* **Ereignis (Zustandsübergang):** state of charge fällt unter Schwellwert (10 %)
* **Vorgehen:** Nutzer beobachten das System in typischer Umgebung
* **Erwartete Reaktion:** Rote blinkende LED wird bemerkt und als Warnung verstanden
* **Erwarteter Folgezustand:** LEDMode = 'WARNING'
* **Requirement:** 4.3

---

### UX7 – LED-Zustände sind intuitiv unterscheidbar

* **Ziel:** Bewertung, ob LED-Zustände (rot blinkend, blau leuchtend, gelb blinkend, aus) verständlich sind
* **Ausgangszustand:** Gerät wird in allen Ladezuständen durchlaufen
* **Ereignis (Zustandsübergang):** Wechsel der Lademodi
* **Vorgehen:** Nutzer erhalten kurze Einführung, danach Interpretation der LED ohne Erklärung
* **Erwartete Reaktion:** Ratenquote ≥ 95 %, Nutzer erkennen Zustand korrekt
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Requirement:** 5.4

---

### UX8 – Anzeige bei Restlaufzeit gut lesbar

* **Ziel:** Bewertung der Lesbarkeit der Restlaufzeit-Anzeige bei Spannungsänderung
* **Ausgangszustand:** OperationState = 'OPERATING', DisplayState = 'REMAINING_TIME'
* **Ereignis (Zustandsübergang):** Anzeige verändert sich durch Spannungsänderung
* **Vorgehen:** Nutzer betrachten die Anzeige nach Änderung, bewerten Klarheit und Lesbarkeit
* **Erwartete Reaktion:** ≥ 90 % der Testpersonen empfinden Anzeige als eindeutig und gut lesbar
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Requirement:** 2.3

---

## **Black-Box Tests (Systemverhalten über Ausgabe)**

### BB1 – Automatische Spannungsmessung

* **Ziel:** Verifikation der automatischen Spannungsaktualisierung
* **Ausgangszustand:** DisplayState = egal, OperationState = egal, ChargingState = egal
* **Ereignis (Zustandsübergang):** keiner (nur wichtig, dass sich Spannung ändert)
* **Vorgehen:** Spannung durch Simulator ändern
* **Erwartete Reaktion:** print Statement auf gemessene Spannung zeigt sich ändernde Werte (je nach ChargingState)
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Requirement:** 1.1

---

### BB2 – Aktueller Batteriestand korrekt angezeigt

* **Ziel:** Verifikation der Anzeige des richtigen Prozentsatzes
* **Ausgangszustand:** DisplayState = 'STATE_OF_CHARGE', OperationState = egal, ChargingState = egal
* **Ereignis (Zustandsübergang):** keiner (nur wichtig, dass Anzeige an ist)
* **Eingabe:** Spannung = 3.9 V
* **Erwartete Reaktion:** Anzeige zeigt \~75 %
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Requirement:** 2.1

---

### BB3 – Anzeige aktualisiert sich bei User-Aktion

* **Ziel:** Verifikation, dass die Anzeige auf Knopfdruck reagiert
* **Ausgangszustand:** DisplayState = 'OFF', OperationState = 'OFF', ChargingState = 'DISCHARGING_PASSIVE'
* **Ereignis (Zustandsübergang):** kurzer Knopfdruck oder langer Knopfdruck
* **Vorgehen:** Button drücken (lang & kurz) -> alle Interaktionen durchtesten, ob erwartete Aktion
* **Erwartete Reaktion:** Anzeige wechselt Zustand (zeigt Ladezustand in Prozent an)
* **Erwarteter Folgezustand:** DisplayState = 'STATE_OF_CHARGE', OperationState = 'OFF' | 'OPERATING', ChargingState =
  'DISCHARGING_PASSIVE' | 'DISCHARGING_ACTIVE'
* **Requirement:** 2.5

---

### BB4 – Einfache Statusanzeige bei Knopfdruck

* **Ziel:** Bestätigung, dass **nur eine** klare Statusanzeige erfolgt
* **Ausgangszustand:** DisplayState = 'OFF', OperationState = 'OFF', ChargingState = 'DISCHARGING_PASSIVE'
* **Ereignis (Zustandsübergang):** kurzer Knopfdruck
* **Vorgehen:** Knopf kurz drücken
* **Erwartete Reaktion:** Eindeutige, selbsterklärende Anzeige
*
    * **Erwarteter Folgezustand:** DisplayState = 'STATE_OF_CHARGE', OperationState = 'OFF', ChargingState =
      'DISCHARGING_PASSIVE'
* **Requirement:** 2.2

---

### BB5 – Keine Verzögerung bei Anzeige

* **Ziel:** Anzeige erfolgt **unmittelbar** nach Benutzeraktion
* **Ausgangszustand:** DisplayState = 'OFF', OperationState = 'OFF', ChargingState = 'DISCHARGING_PASSIVE'
* **Ereignis (Zustandsübergang):** kurzer Knopfdruck oder langer Knopfdruck
* **Vorgehen:** Knopf drücken, schauen ob Anzeige sofort aktiv wird (ohne erkennbare Verzögerung)
* **Erwartete Reaktion:** Änderung innerhalb < 200 ms (visuell ohne erkennbare Verzögerung)
* **Erwarteter Folgezustand:** DisplayState = 'STATE_OF_CHARGE', OperationState = 'OFF' | 'OPERATING', ChargingState =
  'DISCHARGING_PASSIVE' | 'DISCHARGING_ACTIVE'
* **Requirement:** 2.10

---

### BB6 – Warnung bei niedrigem SoC durch blinkende rote LED

* **Ziel:** Prüfung, ob die Warn-LED bei SoC < Schwellwert zuverlässig blinkt
* **Ausgangszustand:** LEDMode = 'OFF', OperationState = 'OFF' | 'OPERATING', ChargingState = 'DISCHARGING'
* **Ereignis (Zustandsübergang):** Spannung fällt unter Schwelle (z.B. auf 8 %)
* **Eingabe:** Spannung simuliert über Simulator
* **Erwartete Reaktion:** LED blinkt rot
* **Erwarteter Folgezustand:** LEDMode = 'WARNING'
* **Requirement:** 4.1

---

### BB7 – Ladeanzeige: langsam gelbes Blinken beim Laden

* **Ziel:** Prüfung der Ladeaktivitätsanzeige bei aktivem Ladevorgang
* **Ausgangszustand:** kein Ladevorgang aktiv, LEDMode = 'OFF' | 'WARNING'
* **Ereignis (Zustandsübergang):** `start`-Kommando per Konsole
* **Vorgehen:** LED beobachten, während Ladevorgang startet
* **Erwartete Reaktion:** LED blinkt gelb im langsamen Takt
* **Erwarteter Folgezustand:** ChargingState = `CHARGING`
* **Requirement:** 5.1

---

### BB8 – Ladeanzeige: dauerhaft blau bei vollem Akku

* **Ziel:** Prüfung der Ladeabschlussanzeige
* **Ausgangszustand:** ChargingState = `CHARGING`, LEDMode = 'CHARGING'
* **Ereignis (Zustandsübergang):** Spannung steigt über Ladeschwelle (z.B. 4.2 V)
* **Vorgehen:** Ladevorgang aktiv halten, bis Akku voll
* **Erwartete Reaktion:** LED leuchtet durchgängig blau
* **Erwarteter Folgezustand:** LEDMode = `FULL_CHARGE`
* **Requirement:** 5.2

---

### BB9 – Kein Ladevorgang bei Ladefehler (Kabel nicht erkannt)

* **Ziel:** System erkennt, dass kein korrektes Laden möglich ist
* **Ausgangszustand:** ChargingState = `DISCHARGING'
* **Ereignis (Zustandsübergang):** `start`-Kommando, aber keine physische Verbindung (oder falsches Kommando)
* **Vorgehen:**
* **Erwartete Reaktion:** keine Reaktion oder Fehleranzeige, kein Ladevorgang sichtbar
* **Erwarteter Folgezustand:** ChargingState bleibt `DISCHARGING'
* **Requirement:** 5.3

---

### BB10 – Überladeschutz: Ladevorgang wird gestoppt bei 100 %

* **Ziel:** Prüfung, ob der Ladevorgang bei Erreichen der maximalen Spannung beendet wird
* **Ausgangszustand:** Akku bei ca. 95 %
* **Ereignis (Zustandsübergang):** Spannung steigt über Maximalwert (4.2 V)
* **Vorgehen:** Ladevorgang laufen lassen
* **Erwartete Reaktion:** ChargingState wird auf 'DISCHARGING_PASSIVE' geändert, aber LED bleibt an
* **Erwarteter Folgezustand:** LEDMode = `FULL_CHARGE`, ChargingState = `DISCHARGING_PASSIVE`
* **Requirement:** 6.2

---

### BB11 – Temperaturüberwachung: Ladevorgang pausiert bei Überhitzung

* **Ziel:** Prüfung, ob Ladeprozess unterbrochen wird, wenn die Temperatur zu hoch ist
* **Ausgangszustand:** ChargingState = `CHARGING`, Temperatur im Simulator im sicheren Bereich
* **Ereignis (Zustandsübergang):** Temperatur steigt über sicheren Wert
* **Vorgehen:** Temperatur im Simulator erhöhen
* **Erwartete Reaktion:** Ladevorgang pausiert, LED blinkt weiterhin gelb, aber Anzeige zeigt keine Steigung des
  Ladeprozents
* **Erwarteter Folgezustand:** ChargingState = `DISCHARGING_PASSIVE`
* **Requirement:** 6.3

---

### BB12 – Anzeige Restlaufzeit erscheint nach entsprechender Knopfinteraktion

* **Ziel:** Prüfung, ob Anzeige der Restlaufzeit erscheint, wenn bei Anzeige des SoC`s erneut der Button kurz gedrückt wird
* **Ausgangszustand:** DisplayState = 'STATE_OF_CHARGE'
* **Ereignis (Zustandsübergang):** ShortPressButtonCommand.execute()
* **Vorgehen:** Knopf wird einmal kurz gedrückt
* **Erwartete Reaktion:** Anzeige wechselt auf Restlaufleit
* **Erwarteter Folgezustand:** DisplayState = 'REMAINING_TIME'
* **Requirement:** 2.3

---

### BB13 – Auslösung der Rekalibrierung nach 250 Zyklen

* **Ziel:** Prüfung, ob Rekalibrierung zum richtigen Zeitpunkt ausgelöst wird
* **Ausgangszustand:** ChargingState = 'CHARGING'
* **Ereignis (Zustandsübergang):** 250. mal von unter 20% bis über 80% aufgeladen
* **Vorgehen:** Ladezyklen auf 249 setzen, einmal ent- und wieder aufladen -> calibration txt file & entsprechende
  Variablen über Debuggerprüfen
* **Erwartete Reaktion:** korrekt berechnete angepasste Werte in txt file & richtiges Einlesen in Variablen
* **Erwarteter Folgezustand:** egal
* **Requirement:** 3.3

---

### BB14 – LED blinkt rot bei Grenzwertüberschreitung

* **Ziel:** Prüfung, ob LED bei eingestelltem Schwellwert wie erwartet blinkt
* **Ausgangszustand:** LEDMode = `OFF`, SoC > Schwellwert
* **Ereignis (Zustandsübergang):** SoC sinkt unter eingestellten kritischen Schwellwert
* **Vorgehen:** Betrieb des Rasierers für Spannungsabfall, für beide möglichen Schwellwerte testen
* **Erwartete Reaktion:** LED blinkt rot mit definierter Frequenz
* **Erwarteter Folgezustand:** LEDMode = `WARNING`
* **Requirement:** 4.2

---

### BB15 – Anzeige des Signals bei mindestens einer möglichen Rasur

* **Ziel:** Prüfung, ob passendes Symbol erscheint, wenn mindestens eine Rasur möglich ist (calculateRemainingRuntime(voltage) >= 5)
* **Ausgangszustand:** SoC = 0%
* **Ereignis (Zustandsübergang):** voltage steigt, so dass calculateRemainingRuntime(voltage) >= 5
* **Vorgehen:** Spannung im Simulator erhöhen (Laden)
* **Erwartete Reaktion:** Anzeige-Symbol (z. B. Rasur-Icon) wird eingeblendet
* **Erwarteter Folgezustand:** ChargingState = 'CHARGING'
* **Requirement:** 5.5

---

### BB16 – Rote LED bei kritischer Spannung leuchtet dauerhaft

* **Ziel:** Prüfung, ob LED bei kritischer Spannung dauerhaft rot leuchtet
* **Ausgangszustand:** Spannung im sicheren Bereich
* **Ereignis (Zustandsübergang):** Spannung sinkt unter kritischen Grenzwert
* **Vorgehen:** Spannung im Simulator schrittweise absenken (OperationState = 'OPERATING')
* **Erwartete Reaktion:** LED beginnt dauerhaft rot zu leuchten, wenn voltage < 2.8
* **Erwarteter Folgezustand:** LEDMode = `UNDERVOLTAGE`
* **Requirement:** 6.1

