# Testfälle

## **Unit Tests**

### UT1 – Spannungsgrenzen: Untergrenze

* **Ziel:** Sicherstellen, dass Spannung nicht unter 3.0 V fällt
* **Ausgangszustand:** DisplayState = 'OFF' | 'STATE_OF_CHARGE', OperationState = 'OPERATING', ChargingState =
  'DISCHARGING_ACTIVE'
* **Ereignis (Zustandsübergang):** kein relevanter für Testfall
* **Eingabe:** Spannung = 2.9 V
* **Erwartete Reaktion:** Rückgabe- oder Korrekturwert ≥ 3.0 V (z.B. Clamping), ggf. Exception oder Fehlerprotokoll
* **Erwarteter Folgezustand:** siehe Ausgangszustand
* **Klasse:** `BatteryStateController`
* **Requirement:** 3.1

---

### UT2 – Spannungsgrenzen: Obergrenze

* **Ziel:** Sicherstellen, dass Spannung nicht über 4.2 V steigt
*
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
* * **Erwarteter Folgezustand:** DisplayState = 'STATE_OF_CHARGE', OperationState = 'OFF', ChargingState =
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

