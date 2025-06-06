# Sprint 2

### Sprint Planning

Zu Beginn des zweiten Sprints habe ich die relevanten Anforderungen (Requirements) ausgewählt. Konkret
wurde die folgende Kernfunktion identifiziert und berücksichtigt:

- Ladeerkennung und -statusanzeige

Bei der Auswahl der Anforderungen wurde bewusst auf eine realistische und zielgerichtete Planung geachtet. Anforderungen
mit der Priorität „Kann“ (Nice-to-have) wurden zunächst nicht in den Sprint aufgenommen, um die vorhandenen Ressourcen
effizient einzusetzen und erstmal die Hauptfunktionalität umzusetzen. Mit der Ladeerkennung und -statusanzeige und damit
verbundenen Schutzmechanismen wird die Basis der letzten Teilfunktionalitäten umgesetzt, sodass ein das
Minimal-Marketable-Product steht. Sprint 3 kann dann für weitere Extras und zur Verfeinerung genutzt werden.

**Requirements:**

- Req. 2.8: Barrierefreie Anzeige (Farbenblindheit)
- Req. 4.1: Warnung bei niedrigem Batteriestand
- Req. 4.3: Wahrnehmbarkeit der Warnung
- Req. 5.1: Ladeaktivität anzeigen
- Req. 5.2: Ladeabschluss anzeigen
- Req. 5.3: Ladefehler anzeigen
- Req. 5.4: Ladezustand elektronisch erkennen
- Req. 5.6: Ladeanzeige energieeffizient
- Req. 6.2: Überladeschutz
- Req. 6.3: Temperaturüberwachung beim Laden

**Sprint-Zeitraum:** 22.05.2025 - 12.06.2025

**Sprintziel:**

1. Alle Aspekte des Ladens vollständig umgesetzt
2. Gute Integration in bereits vorhandenen Code
3. Prozessual sauber arbeiten

### Schritt 1: Recherche

[Rechercheergebnisse](../referenziert/Rechercheergebnisse.md#sprint-2)

### Schritt 2: Architektur

[Architektur](Architektur2.md)

### Schritt 3: Design

[Design](Design2.md)

### Schritt 4: Implementierung

Einige zusätzliche Hilfsklassen und Schnittstellen wurden eingeführt, um Wiederverwendbarkeit und Verständlichkeit zu
verbessern.

Bereits während der Implementierung wurden kontinuierlich manuelle Tests durchgeführt – sowohl über die grafische
Benutzeroberfläche (GUI) als auch über die Kommandozeile. Dabei wurden gezielt Teilsysteme geprüft, ob sie die
erwarteten Ergebnisse liefern, um Fehler frühzeitig zu identifizieren und zu beheben.

Nach der Implementierung wurde das Klassendiagramm aktualisiert, um die tatsächlich umgesetzten Strukturen korrekt
abzubilden. Auch die Bezeichnungen im Zustands- und Sequenzdiagramm wurden überprüft und angepasst, um mit der
Implementierung konsistent zu sein, vor allem in den Bezeichnungen.

[Implementierung](Implementierung2.md)

### Schritt 5: Test

Nach Abschluss der Implementierung habe ich zunächst alle während der Entwicklung durchgeführten Tests als formale
Testfälle dokumentiert. Darauf aufbauend habe ich das übergeordnete Ziel des Testens, die geplanten Testarten sowie
deren Abdeckung definiert und eine Teststrategie festgelegt. Um die interne Codequalität sicherzustellen, habe ich eine
das Codereview durchgeführt. Der Code wurde basierend auf den Ergebnissen überarbeitet. Anschließend habe ich gezielt
weitere Testfälle ergänzt, um die vollständige Abdeckung aller Hauptanforderungen sicherzustellen.

[Test](Test2.md)

# TODO: Testfälle ausführen (Unit-Tests, User-Tests, GUI-Tests, ...)
# TODO: Testfälle dokumentieren (Ergebnisse, Abdeckung, ...)

### Schritt 6: Review & Retro

# TODO: Review durchführen

**Was lief gut?**

- Alle Anforderungen wurden umgesetzt.
- Die Recherche zu Beginn, um wichtige Informationen zu sammeln, war sehr hilfreich.
- Der Zeitplan wurde eingehalten.
- Die Implementierung wurde strukturiert und feature-driven durchgeführt, was die Übersichtlichkeit
  verbessert hat.
- Die neuen Tools haben zu mehr Effizienz und Klarheit beigetragen.
- Fehler aus Sprint 1 entdeckt und besser gemacht: nicht nur Kompexitätsmetriken, sondern auch Abhängigkeiten,
  Codezeilen und Testabdeckung berücksichtigt.

**Was lief nicht so gut?**

-

**Was werde ich im nächsten Sprint anders machen?**

-

**Lessons Learned:**

- 
