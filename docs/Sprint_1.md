# Sprint 1

### Sprint Planning

**Requirements:**

- Req. 1.1: Automatische Spannungsmessung
- Req. 1.2: Ladezustandsberechnung in Prozent
- Req. 1.3: Keine zusätzliche Bedienung
- Req. 2.1: Anzeige aktueller Batteriestand
- Req. 2.2: Einfache Statusanzeige bei Knopfdruck
- Req. 2.4: Anzeige bei längerer Nichtbenutzung
- Req. 2.5: Anzeige bei Benutzerinteraktion aktualisieren
- Req. 2.6: Intuitive Anzeige
- Req. 2.7: Angenehme Anzeige
- Req. 2.8: Barrierefreie Anzeige (Farbenblindheit)
- Req. 2.9: Barrierefreie Anzeige (Sehschwäche)
- Req. 2.10: Anzeige ohne Verzögerung
- Req. 3.1: Spannungsreferenz definieren
- Req. 3.2: Initiale Kalibrierung

**Sprintziel:**

1. Batteriestandserkennung vollständig umgesetzt (mit entsprechenden Vorbedingungen), als Basis für alle weiteren Funktionalitäten.
2. Umsetzung der Anzeige.

### Schritt 1: Recherche

[Rechercheergebnisse](./referenziert/Rechercheergebnisse.md#sprint-1)

### Schritt 2: Architektur

[Architektur](Architektur1.md)

### Schritt 3: Design

[Design](Design1.md)

### Schritt 4: Implementierung

[Implementierung](Implementierung1.md)

### Schritt 5: Test

[Test](Test1.md)

### Schritt 6: Review & Retro

**Was lief gut?**

- Alle Anforderungen wurden umgesetzt.
- Die Recherche zu Beginn, um wichtige Informationen zu sammeln, war sehr hilfreich.
- Der Zeitplan wurde eingehalten.
- Die Tests wurden erfolgreich durchgeführt.

**Was lief nicht so gut?**

- Prozessual musste viel nachgearbeitet werden. Vor allem das Klassendiagramm wurde im Verlauf der Implementierung nochmal komplett umgeschrieben.
- Der Startpunkt hat gefehlt (vorher Plan zu haben, was zu tun ist).
- Schwierig von 0 anzufangen.
- Die Tools waren noch nicht eingerichtet (z.B. IDE Wechsel, Code Metriken, ...).
- Ich habe nicht konsequent direkt die Dokumentation aktualisiert. (Dadurch fehlt jetzt erster Wurf des Klassendiagramms).
- Implementierung wurde nicht Feature für Feature angegangen, sondern eher querbeet und dadurch dann alles auf einmal.

**Was werde ich im nächsten Sprint anders machen?**

- Ich werde die Dokumentation direkt während der einzelnen Entwicklungsphasen auf dem aktuellen Stand halten.
- Ich werde die Implementierung Feature-driven angehen.
- Ich bleibe bei den jetzt festgelegten Tools.
- Ich werde prozessual sauberer arbeiten.

**Lessons Learned:**

- Ich habe gelernt, dass ich mich nicht von der Vielzahl an Anforderungen überfordern lassen sollte.
- Die richtigen Tools ermöglichen es effizient zu arbeiten, wohingegen die falschen Tools eher neue Hürden schaffen.
- Meine geplante Statusanzeige für den Ladezustand ist nicht optimal, da Personen mit einer Rot-Grün-Sehschwäche Schwierigkeiten haben, den Ladezustand zu erkennen.
