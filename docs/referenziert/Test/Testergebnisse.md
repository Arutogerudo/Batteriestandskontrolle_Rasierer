# Testergebnisse

## **Unit Tests**

Umgesetzt in src/tests/BatteryStateControllerTest.java.

### UT1 – Spannungsgrenzen: Untergrenze
Ziel: Sicherstellen, dass die Spannung nicht unter 3.0 V fällt, findet in Simulator statt. Bei Austausch des Simulators gegen echte Batterie, soll auch bei Werten unter 3 V eine valide Prozentzahl (zwischen 0% und 100%) als Ladezustand angegeben werden, hier 0%.

Ergebnis: Die Methode behandelt Spannungen unterhalb der definierten Untergrenze korrekt. Bei 2.9 V wurde der Rückgabewert wie erwartet korrigiert (z. B. Clamping).

Status: Bestanden

Requirement: 3.1

### UT2 – Spannungsgrenzen: Obergrenze
Ziel: Sicherstellen, dass die Spannung nicht über 4.2 V steigt, findet in Simulator statt. Bei Austausch des Simulators gegen echte Batterie, soll auch bei Werten über 4.2 V eine valide Prozentzahl (zwischen 0% und 100%) als Ladezustand angegeben werden, hier 100%.

Ergebnis: Werte über der Obergrenze wurden ordnungsgemäß erkannt und behandelt. Der Rückgabewert blieb im gültigen Bereich.

Status: Bestanden

Requirement: 3.1

### UT3 – Ladezustandsberechnung korrekt
Ziel: Verifikation der Umrechnung von Spannung in Ladezustand (Interpolation).

Eingabe: 3.6 V

Erwartung: Ladezustand ≈ 35 %, mit einer Toleranz von ±5 % laut Requirement.

Ergebnis: Die Methode calculateStateOfCharge() liefert einen Wert im erwarteten Bereich (30 %–40 %). Die Toleranz wurde korrekt berücksichtigt.

Status: Bestanden

Requirement: 1.2



## **Usability Tests**

Für die Usability-Tests wurden 5 Tester rekrutiert, davon haben 2 eine altersbedingte Leseschwäche. Es wurden 2 Personen im Alter von 55-60 und 3 Personen im Alter von 20-25 befragt.

| **Test-ID** | **Ziel**                           | **Vorgehen**                                  | **Erwartetes Ergebnis**                                       | **Tatsächliches Ergebnis**                                                                                                                        | **Requirement** | **Status** | **Bemerkung**                                         |
|-------------|------------------------------------|-----------------------------------------------|---------------------------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------|-----------------|------------|-------------------------------------------------------|
| UX1         | Keine Zusatzbedienung erforderlich | Gerät einschalten, Anzeige beobachten         | Anzeige erscheint automatisch oder nach einfachem Tastendruck | Anzeige erscheint wie erwartet nach einfachem Einschalten oder kurzem Druck                                                                       | 1.3             | ✔          | Keine zusätzliche Bedienung notwendig                 |
| UX2         | Angenehme Anzeige                  | Verschiedene Altersgruppen betrachten Anzeige | Gute Lesbarkeit, angenehmer Kontrast, kein Flackern           | Alle Tester fanden Anzeige gut lesbar; Kontrast angenehm; keine visuelle Überforderung; Flackern nicht vorhanden (Helligkeit lokal nicht testbar) | 2.7             | ✔          | Helligkeit lokal nicht messbar                        |
| UX3         | Intuitive Anzeige                  | Nutzer interpretieren Anzeige ohne Anleitung  | ≥ 90 % verstehen Anzeige ohne Erklärung                       | Anzeige wurde direkt verstanden; einzig Bedienlogik (kurz/lang drücken) erforderte Erklärung                                                      | 2.6             | ✔          | Bedienkonzept (kurz/lang) erfordert minimal Anleitung |
| UX4         | Barrierefreiheit bei Sehschwäche   | Nutzer mit Sehschwäche verwenden System       | Anzeige bleibt ohne Anstrengung gut lesbar                    | Schriftgröße und Kontrast ausreichend für Tester mit Leseschwäche; keine Einschränkungen festgestellt                                             | 2.9             | ✔          | Gut lesbar für ältere und sehgeschwächte Personen     |

## **Black-Box Tests**

| **Test-ID** | **Ziel**                            | **Vorgehen / Eingabe**                            | **Erwartetes Ergebnis**                        | **Tatsächliches Ergebnis**        | **Requirement** | **Status** | **Bemerkung**         |
|-------------|-------------------------------------|---------------------------------------------------|------------------------------------------------|-----------------------------------|-----------------|------------|-----------------------|
| BB1         | Automatische Spannungsmessung       | Spannung durch Simulator verändern                | Anzeige aktualisiert sich automatisch          | Anzeige reagiert korrekt          | 1.1             | ✔          | –                     |
| BB2         | Korrekte Anzeige des Batteriestands | Spannung = 3.9 V                                  | Anzeige zeigt \~75 %                           | Anzeige zeigt erwarteten Wert     | 2.1             | ✔          | Kalibrierung korrekt  |
| BB3         | Anzeige reagiert auf Knopfdruck     | Kurz & lang drücken, verschiedene Zustände testen | Anzeige zeigt Zustand gemäß Spezifikation      | Anzeige verhält sich wie erwartet | 2.5             | ✔          | Alle Zustände geprüft |
| BB4         | Nur eine klare Statusanzeige        | Kurzer Tastendruck                                | Eindeutige, selbsterklärende Anzeige erscheint | Anzeige klar & verständlich       | 2.2             | ✔          | -                     |
| BB5         | Sofortige Anzeigeänderung           | Knopf drücken                                     | Anzeigeänderung innerhalb < 200 ms             | Keine erkennbare Verzögerung      | 2.10            | ✔          | Reaktionszeit gut     |
