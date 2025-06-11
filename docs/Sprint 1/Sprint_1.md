# Sprint 1

### Sprint Planning

Zu Beginn des ersten Sprints habe ich die relevanten Anforderungen (Requirements) ausgewählt. Der Fokus lag dabei auf
der Implementierung grundlegender Teilfunktionalitäten, die für den weiteren Projektverlauf essenziell sind. Konkret
wurden folgende Kernfunktionen identifiziert und berücksichtigt:

- Kalibrierung zur Umrechnung von Spannung in Prozentwerte

- Erkennung und Anzeige des Batteriestands

- Visualisierung der ermittelten Daten über eine geeignete Benutzeroberfläche

Bei der Auswahl der Anforderungen wurde bewusst auf eine realistische und zielgerichtete Planung geachtet. Anforderungen
mit der Priorität „Kann“ (Nice-to-have) wurden zunächst nicht in den Sprint aufgenommen, um die vorhandenen Ressourcen
effizient einzusetzen und eine stabile Basisfunktionalität sicherzustellen. Diese Priorisierung diente dazu, technische
Risiken frühzeitig zu minimieren und eine solide Grundlage für die folgenden Sprints zu schaffen.

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

**Sprint-Zeitraum:** 14.05.2025 - 19.05.2025

**Sprintziel:**

1. Batteriestandserkennung vollständig umgesetzt (mit entsprechenden Vorbedingungen), als Basis für alle weiteren
   Funktionalitäten.
2. Umsetzung der Anzeige.

### Schritt 1: Recherche

Im Anschluss an die Priorisierung der Anforderungen habe ich gezielt weiterführende Informationen zu bestimmten
technischen und gestalterischen Aspekten eingeholt, um eine fundierte Umsetzung sicherzustellen. Insbesondere bei
Requirements, bei denen noch Hintergrundwissen erforderlich war, wurde eine gezielte Recherche durchgeführt. Die
gewonnenen Erkenntnisse wurden in die weitere Anforderungsbeschreibung und spätere Implementierungsentscheidungen
integriert. Damit wurde sichergestellt, dass sowohl technische Präzision als auch Usability-Anforderungen von Beginn an
berücksichtigt werden.

[Rechercheergebnisse](../referenziert/Rechercheergebnisse.md#sprint-1)

### Schritt 2: Architektur

Nach Abschluss der Anforderungsanalyse und Informationsbeschaffung habe ich mich im nächsten Schritt mit der
Softwarearchitektur des Projekts beschäftigt. Ziel war es, eine geeignete strukturelle Grundlage für die spätere
Implementierung zu schaffen.

Zunächst habe ich verschiedene Architekturmuster analysiert und verglichen (z. B. Schichtenarchitektur,
komponentenbasierte Architektur, Microservices). Basierend auf den funktionalen Anforderungen, der geplanten Skalierung
sowie den nicht-funktionalen Anforderungen wie Wartbarkeit und Erweiterbarkeit fiel die Wahl auf ein geeignetes
Architekturmuster, das diesen Kriterien am besten entspricht.

Auf Grundlage des gewählten Architekturmusters habe ich ein Komponentendiagramm erstellt, das die Struktur des Systems
und die wichtigsten funktionalen Bausteine visualisiert. Die Darstellung zeigt die zentralen Komponenten sowie deren
Abhängigkeiten und ermöglicht eine klare Abgrenzung der Verantwortlichkeiten innerhalb des Systems.

Im Anschluss wurden die notwendigen Schnittstellen zwischen den Komponenten definiert. Dabei wurde besonderer Wert auf
eine saubere Trennung von Verantwortlichkeiten sowie auf klare, dokumentierte Kommunikationswege gelegt. Diese
Schnittstellen bilden die Grundlage für eine modulare und gut wartbare Implementierung.

Abschließend habe ich den Technologiestack für das Projekt definiert. Dieser umfasst sowohl die Programmiersprachen und
Frameworks als auch Entwicklungsumgebungen, Tools zur Versionskontrolle und ggf. Bibliotheken zur Umsetzung spezifischer
Anforderungen. Die Auswahl erfolgte auf Basis von Projektzielen, persönlicher Erfahrung sowie der Eignung der
Technologien für die geplante Systemarchitektur.

[Architektur](Architektur1.md)

### Schritt 3: Design

Im Anschluss an die Architekturdefinition wurde der Entwurfsprozess auf Klassenebene fortgeführt. Ziel war es, zentrale
Klassen und deren Interaktionen zu identifizieren sowie die Systemlogik anhand geeigneter UML-Diagramme zu modellieren.

Basierend auf den zuvor definierten Anforderungen und Komponenten wurde ein erstes Klassendiagramm erstellt. Dabei
wurden die für die Umsetzung der Kernfunktionalitäten relevanten Klassen identifiziert und modelliert. Jede Klasse wurde
einer der zuvor definierten Komponenten zugeordnet, um eine klare Strukturierung und Wiedererkennbarkeit zur
Architekturebene zu gewährleisten.
Zudem wurden öffentliche Schnittstellen (Methoden und Attribute) zwischen den Klassen direkt im Diagramm aufgenommen, um
die Kommunikation und Datenflüsse zwischen den Klassen transparent darzustellen.

Für die Benutzerinteraktion mit dem System wurde ein Zustandsdiagramm erstellt, das den Ablauf und die Zustandswechsel
bei der Bedienung über einen Button beschreibt. Dieses Diagramm hilft, das Verhalten der Benutzerschnittstelle (UI)
nachvollziehbar zu machen und die zugrundeliegende Logik der Zustandsänderungen eindeutig zu definieren.

Zur Darstellung des internen Ablaufs – insbesondere vom Zeitpunkt der Spannungsmessung bis zur Anzeige des
Batteriestands – wurde ein Sequenzdiagramm entworfen. Es beschreibt die zeitliche Abfolge der Methodenaufrufe und
Interaktionen zwischen den beteiligten Objekten und zeigt, wie die Daten durch das System fließen.

Abschließend wurde überprüft, ob sich für einzelne Klassen oder Strukturmuster etablierte Design Patterns sinnvoll
anwenden lassen (z. B. Singleton, Factory, Observer). Dabei wurde besonderes Augenmerk auf Wiederverwendbarkeit,
Erweiterbarkeit und die Reduzierung von Kopplung gelegt. Mögliche Pattern wurden identifiziert und deren Einsatz
sorgfältig abgewogen, um die Struktur des Systems nachhaltig zu verbessern.

Bei allen Klassendiagrammen wurde bewusst auf die Kardinalitäten verzichtet. Hier handelt es sich lediglich um interne
Logik und das Ergänzen der Klassendiagramme um jegliche Kardinalitäten erzeugt einen enormen Overhead ohne wirklichen
Mehrwert im Verständnis.

[Design](Design1.md)

### Schritt 4: Implementierung

Nach Abschluss der Designphase begann die Umsetzung der Anwendung gemäß der zuvor definierten Architektur- und
Entwurfsdokumente. Dabei wurde iterativ und testgetrieben vorgegangen, um frühzeitig funktionale Korrektheit und
Konsistenz sicherzustellen.

Zunächst wurden die in der Designphase entworfenen Klassen, Zustandsautomaten und Schnittstellen gemäß dem
Klassendiagramm und den modellierten Abläufen implementiert. Der Fokus lag auf der sauberen Umsetzung der öffentlichen
Schnittstellen und der klaren Trennung zwischen interner Logik und extern zugänglichen Funktionen.

Im Anschluss wurden die einzelnen Module schrittweise integriert, um die funktionale Gesamtheit des Systems
herzustellen. Dabei ergaben sich während der praktischen Umsetzung kleinere Anpassungen:

Einige zusätzliche Hilfsklassen und Schnittstellen wurden eingeführt, um Wiederverwendbarkeit und Verständlichkeit zu
verbessern.

Andere ursprünglich geplante Klassen oder Schnittstellen erwiesen sich als überflüssig und wurden konsequent entfernt,
um den Code schlank und wartbar zu halten.

Parallel zur Integration wurden auch die nicht-öffentlichen internen Abläufe vollständig implementiert, um die
Systemlogik zu vervollständigen.

Bereits während der Implementierung wurden kontinuierlich manuelle Tests durchgeführt – sowohl über die grafische
Benutzeroberfläche (GUI) als auch über die Kommandozeile. Dabei wurden gezielt Teilsysteme geprüft, ob sie die
erwarteten Ergebnisse liefern, um Fehler frühzeitig zu identifizieren und zu beheben.

Nach der Implementierung wurde das Klassendiagramm aktualisiert, um die tatsächlich umgesetzten Strukturen korrekt
abzubilden. Auch die Bezeichnungen im Zustands- und Sequenzdiagramm wurden überprüft und angepasst, um mit der
Implementierung konsistent zu sein.

Zur Sicherstellung der Nachvollziehbarkeit und Vollständigkeit wurde eine Traceability-Matrix erstellt. Diese stellt die
Beziehungen zwischen den ursprünglichen Requirements, den zugehörigen Klassen und Schnittstellen sowie (zukünftig) den
Testfällen systematisch dar.

Darüber hinaus wurden Code-Metriken analysiert, um die Qualität des Codes anhand objektiver Kennzahlen (z. B.
Klassenzahl, Methodenanzahl, Kopplung, Kohäsion, Komplexität) zu bewerten und potenzielle Verbesserungsbereiche
frühzeitig zu erkennen.

[Implementierung](Implementierung1.md)

### Schritt 5: Test

Nach Abschluss der Implementierung habe ich zunächst alle während der Entwicklung durchgeführten Tests als formale
Testfälle dokumentiert. Darauf aufbauend habe ich das übergeordnete Ziel des Testens, die geplanten Testarten sowie
deren Abdeckung definiert und eine Teststrategie festgelegt. Um die interne Codequalität sicherzustellen, habe ich eine
Checkliste für das Codereview erstellt und dieses mithilfe der automatischen Inspections von IntelliJ durchgeführt. Der
Code wurde basierend auf den Ergebnissen überarbeitet. Anschließend habe ich gezielt weitere Testfälle ergänzt, um die
vollständige Abdeckung aller Hauptanforderungen sicherzustellen. Abschließend wurden die Testergebnisse überblicksartig
dokumentiert und mit den definierten Requirements in der Traceability Matrix verknüpft.

[Test](Test1.md)

### Schritt 6: Review & Retro

**Was lief gut?**

- Alle Anforderungen wurden umgesetzt.
- Die Recherche zu Beginn, um wichtige Informationen zu sammeln, war sehr hilfreich.
- Der Zeitplan wurde eingehalten.
- Die Tests wurden erfolgreich durchgeführt.

**Was lief nicht so gut?**

- Prozessual musste viel nachgearbeitet werden. Vor allem das Klassendiagramm wurde im Verlauf der Implementierung
  nochmal komplett umgeschrieben.
- Der Startpunkt hat gefehlt (vorher Plan zu haben, was zu tun ist).
- Schwierig von 0 anzufangen.
- Die Tools waren noch nicht eingerichtet (z.B. IDE Wechsel, Code Metriken, ...).
- Ich habe nicht konsequent direkt die Dokumentation aktualisiert. (Dadurch fehlt jetzt erster Entwurf des
  Klassendiagramms).
- Implementierung wurde nicht Feature für Feature angegangen, sondern eher querbeet und dadurch dann alles auf einmal.
  (Hat eigentlich ganz gut funktioniert, da das Projekt nicht so groß ist.)

**Was werde ich im nächsten Sprint anders machen?**

- Ich werde die Dokumentation direkt während der einzelnen Entwicklungsphasen auf dem aktuellen Stand halten und mich am
  entwickelten Vorgehen von Sprint 1 orientieren.
- Ich bleibe bei den jetzt festgelegten Tools.
- Ich werde prozessual sauberer arbeiten.

**Lessons Learned:**

- Ich habe gelernt, dass ich mich nicht von der Vielzahl an Anforderungen überfordern lassen sollte.
- Die richtigen Tools ermöglichen es effizient zu arbeiten, wohingegen die falschen Tools eher neue Hürden schaffen.
- Meine geplante Statusanzeige für den Ladezustand ist nicht optimal, da Personen mit einer Rot-Grün-Sehschwäche
  Schwierigkeiten haben, den Ladezustand zu erkennen.
