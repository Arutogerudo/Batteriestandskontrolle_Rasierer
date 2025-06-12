# Sprint 3

### Sprint Planning

Zu Beginn des dritten und letzten Sprints habe ich die verbleibenden Anforderungen (Requirements) zur vollständigen
Umsetzung des Funktionsumfangs ausgewählt.

Da es sich um den letzten Sprint handelt, werden sowohl optionale Zusatzfunktionen als auch nicht-funktionale
Qualitätsanforderungen umgesetzt. Ziel ist es, ein funktionsreiches und robustes System abzuliefern, das neben der
Kernfunktionalität auch Komfort- und Schutzfunktionen bietet. Damit wird ein ausgereiftes Produkt mit vollständigem
Requirement-Abdeckungsgrad erreicht, das marktreif und anwenderfreundlich ist.

**Requirements:**

- Req. 2.3: Restlaufzeit auf Knopfdruck
- Req. 3.3: Rekalibrierung nach Ladezyklen
- Req. 3.4: Anzeigetoleranz
- Req. 3.5: Alterungsrobustheit
- Req. 3.6: Ressourcenschonende Kalibrierung
- Req. 4.2: Warnschwelle einstellbar
- Req. 5.5: mögliche Rasur während Laden signalisieren
- Req. 6.1: Unterspannungsschutz

**Sprint-Zeitraum:** 09.06.2025 - 11.06.2025

**Sprintziel:**

1. alle geplanten Designpatterns umsetzen
2. Komfort- und Schutzfunktionen integrieren
3. Vervollständigung und Qualitätsoptimierung der Batteriestandskontrolle
4. Integration aller noch offenen funktionalen und nicht-funktionalen Anforderungen
5. Produktqualität und Robustheit sichern

### Schritt 1: Recherche

[Rechercheergebnisse](../referenziert/Rechercheergebnisse.md#sprint-3)

### Schritt 2: Architektur

Zu Beginn des Sprints wurden die geplanten Anforderungen analysiert und den jeweils verantwortlichen Komponenten im
System zugeordnet. Auf dieser Grundlage wurde eine erste Planung der notwendigen öffentlichen Schnittstellen
vorgenommen, um eine saubere und modulare Umsetzung zu ermöglichen.

Die konkrete Schnittstellendefinition erfolgte bewusst nach der Implementierung, um Spielraum für technische Anpassungen
und Refactoring während der Entwicklung zu lassen. Dieses Vorgehen fördert eine praxisnahe und realitätsgerechte
Architektur, da Schnittstellen so nicht nur theoretisch geplant, sondern an den tatsächlichen Implementierungsbedarf
angepasst werden können.

[Architektur](Architektur3.md)

### Schritt 3: Design

Bei allen Klassendiagrammen wurde bewusst auf die Kardinalitäten verzichtet. Hier handelt es sich lediglich um interne
Logik und das Ergänzen der Klassendiagramme um jegliche Kardinalitäten erzeugt einen enormen Overhead ohne wirklichen
Mehrwert im Verständnis.

[Design](Design3.md)

### Schritt 4: Implementierung

Im Implementierungsprozess traten mehrere Besonderheiten auf, die im Rahmen dieses Sprints gezielt adressiert wurden.

Für die Zählung der Ladezyklen, welche als Grundlage für die Rekalibrierung der Restlaufzeit dient, wurde ein
vereinfachter Ansatz gewählt: Ein Ladezyklus wird gezählt, wenn der SoC (State of Charge) von unter 20 % auf über 80 %
ansteigt. Diese Vereinfachung ist für den konkreten Anwendungsfall – akkubetriebene Rasierapparate – praxisnah und
realistisch, da diese Geräte typischerweise erst bei nahezu leerem Akku und dann vollständig aufgeladen werden.

Des Weiteren wurden in diesem Sprint die im vorangegangenen Sprint geplanten Designpatterns systematisch geprüft. Auf
Basis der jeweiligen Einsatzkontexte wurde begründet entschieden, welche Patterns sinnvoll implementiert werden und
welche verworfen werden können, um die Komplexität nicht unnötig zu erhöhen.

Zum Abschluss der Implementierungsphase erfolgte eine umfassende Prüfung aller Klassen im Hinblick auf das
Single-Responsibility-Prinzip. Dabei wurden drei Klassen identifiziert, bei denen die Verantwortlichkeiten nicht klar
getrennt waren. Diese Klassen wurden entsprechend in kleinere, spezialisierte Klassen aufgeteilt, um die Wartbarkeit und
Erweiterbarkeit des Codes langfristig zu verbessern.

[Implementierung](Implementierung3.md)

### Schritt 5: Test

In der Testphase wurde ein systematisches, mehrstufiges Vorgehen verfolgt, um die Qualität und Funktionalität der
Implementierung ganzheitlich sicherzustellen. Zunächst wurden auf Basis der Anforderungen gezielt Testfälle definiert,
die verschiedene Testarten abdecken – darunter Unit Tests zur Prüfung einzelner Komponenten, Blackbox Tests zur
Validierung des Systemverhaltens aus Anwendersicht sowie Usability Tests zur Bewertung der Nutzerfreundlichkeit.

Die Tests wurden iterativ durchgeführt: Nach jeder identifizierten Abweichung oder jedem Fehler wurden gezielte
Anpassungen im Code vorgenommen, anschließend erfolgte eine erneute Ausführung aller relevanten Testfälle, auch aus
früheren Sprints. Dieses inkrementelle Vorgehen stellte sicher, dass sowohl neue als auch bestehende Funktionalitäten
stabil und korrekt arbeiten. Die Testphase diente damit nicht nur der Fehlererkennung, sondern auch der kontinuierlichen
Qualitätssicherung und Verifikation der Gesamtsystemlogik.

[Test](Test3.md)

### Schritt 6: Review & Retro

**Was lief gut?**

- Alle Anforderungen wurden umgesetzt.
- Die Recherche zu Beginn, um wichtige Informationen zu sammeln, war sehr hilfreich.
- Der Zeitplan wurde eingehalten.
- Die Implementierung wurde strukturiert und feature-driven durchgeführt, was die Übersichtlichkeit verbessert hat.
- Über die Zeit haben sich gute Abläufe entwickelt, die die Arbeit deutlich effizienter machen.
- Die Codemetriken wurden sehr intensiv analysiert.
- Die Testphase wurde konsequent und sehr umfassend durchgeführt.
- Die Dokumentation wurde konsequent aktualisiert und ist vollständig.
- "Fehler" der vorangegangenen Sprints (Nicht-Einhaltung des Single-Responsibility Prinzips) wurden ausgemerzt.

**Was lief nicht so gut?**

- Aus der Design- und Architekturphase wurden nicht alle Aspekte 1:1 in der Implementierung umgesetzt und mussten
  nachträglich angepasst werden.
- Es gab einen eindeutigen Logikfehler in der Implementierungsphase. Dieser ist erst in der Testphase aufgefallen. Die
  Anpassung des Codes war relativ aufwändig, da die Komponenten (Restlaufzeit, Rekalibrierung) bereits mit anderen
  verknüpft waren.

**Was würde ich im nächsten Sprint anders machen?**

- Obwohl die Design- und Architekturphase in diesem Sprint schon gründlicher als in den vorangegangenen Sprints
  durchgeführt wurde, sollten sie noch gründlicher durchgeführt werden, um spätere Anpassungen zu vermeiden.

**Lessons Learned:**

- Die Design- bzw. Architekturphase ist die mit Abstand wichtigste Phase, wenn hier gründlich gearbeitet wird, ist die
  Implementierung ein reines Coden ohne erneut über die Logik nachzudenken. Dann passiert es seltener, dass viel
  nachgearbeitet oder rückwirkend angepasst werden muss.
