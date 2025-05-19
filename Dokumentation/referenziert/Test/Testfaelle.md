# Testfälle

## **Unit Tests**

### UT1 – Spannungsgrenzen: Untergrenze

* **Ziel:** Sicherstellen, dass Spannung nicht unter 3.0 V fällt
* **Eingabe:** Spannung = 2.9 V
* **Erwartet:** Rückgabe- oder Korrekturwert ≥ 3.0 V (z. B. Clamping), ggf. Exception oder Fehlerprotokoll
* **Klasse:** `BatteryStateController`
* **Requirement:** 3.1

---

### UT2 – Spannungsgrenzen: Obergrenze

* **Ziel:** Sicherstellen, dass Spannung nicht über 4.2 V steigt
* **Eingabe:** Spannung = 4.3 V
* **Erwartet:** Rückgabe- oder Korrekturwert ≤ 4.2 V, ggf. Logging/Fehler
* **Klasse:** `BatteryStateController`
* **Requirement:** 3.1

---

### UT3 – Ladezustandsberechnung korrekt

* **Ziel:** Verifikation der Spannungs-zu-Prozent-Umrechnung (Interpolation)
* **Eingabe:** Spannung = 3.6 V
* **Erwartet:** Ladezustand ≈ 35 % 
* **Klasse:** `BatteryStateController.calculateStateOfCharge()`
* **Requirement:** 1.2

---

## **Usability Tests**

### UX1 – Keine Zusatzbedienung erforderlich

* **Ziel:** Bestätigung, dass **kein zusätzlicher Buttondruck** notwendig ist, um Batterieanzeige zu erhalten
* **Vorgehen:** Gerät einschalten, auf Anzeige warten
* **Erwartet:** Anzeige erscheint automatisch oder nach **einmaligem** Knopfdruck, ohne Moduswechsel
* **Requirement:** 1.3

---

### UX2 – Angenehme Anzeige

* **Ziel:** Anzeige ist optisch nicht überfordernd
* **Vorgehen:** Verschiedene Altersgruppen beobachten Anzeige
* **Erwartet:** Lesbarkeit der Schriftgröße, angenehmer Kontrast, kein Flackern
* **Requirement:** 2.7

---

### UX3 – Intuitive Anzeige

* **Ziel:** Nutzer versteht sofort, was dargestellt wird
* **Vorgehen:** Nutzergruppe ohne Anleitung deutet die Aussage der Anzeige
* **Erwartet:** Ratenquote ≥ 90 % 
* **Requirement:** 2.6

---

### UX4 – Barrierefreie Anzeige (Sehschwäche)

* **Ziel:** Anzeige auch bei Sehschwäche (z. B. Presbyopie) lesbar
* **Vorgehen:** Nutzer mit bekannten Sehproblemen verwenden System
* **Erwartet:** Anzeige lesbar ohne besondere Anstrengung (große Schrift, hoher Kontrast)
* **Requirement:** 2.9

---

## **Black-Box Tests (Systemverhalten über Ausgabe)**

### BB1 – Automatische Spannungsmessung

* **Ziel:** Verifikation der automatischen Spannungsaktualisierung
* **Vorgehen:** Spannung durch Simulator ändern
* **Erwartet:** Anzeige ändert sich automatisch, ohne Benutzeraktion
* **Requirement:** 1.1

---

### BB2 – Aktueller Batteriestand korrekt angezeigt

* **Ziel:** Verifikation der Anzeige des richtigen Prozentsatzes
* **Eingabe:** Spannung = 3.9 V
* **Erwartet:** Anzeige zeigt \~75 % (je nach Umrechnungsmodell)
* **Requirement:** 2.1

---

### BB3 – Anzeige aktualisiert sich bei User-Aktion

* **Ziel:** Verifikation, dass die Anzeige auf Knopfdruck reagiert
* **Vorgehen:** Button drücken (lang & kurz) -> alle Interaktionen durchtesten, ob erwartete Aktion
* **Erwartet:** Anzeige wechselt Zustand (z. B. zeigt Prozentwert oder Symbol)
* **Requirement:** 2.5

---

### BB4 – Einfache Statusanzeige bei Knopfdruck

* **Ziel:** Bestätigung, dass **nur eine** klare Statusanzeige erfolgt
* **Vorgehen:** Knopf kurz drücken
* **Erwartet:** Eindeutige, selbsterklärende Anzeige 
* **Requirement:** 2.2

---

### BB5 – Keine Verzögerung bei Anzeige

* **Ziel:** Anzeige erfolgt **unmittelbar** nach Benutzeraktion
* **Vorgehen:** Knopf drücken
* **Erwartet:** Änderung innerhalb < 200 ms (visuell ohne erkennbare Verzögerung)
* **Requirement:** 2.10

