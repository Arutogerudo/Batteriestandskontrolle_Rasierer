<style>
  .muss {
    background-color: rgba(51, 170, 51, .4)
  }
</style>
<style>
  .soll {
    background-color: rgba(255, 174, 0, 0.4);
  }
</style>
<style>
  .kann {
    background-color: rgba(170, 51, 51, 0.4);
  }
</style>

# Requirements: Batteriestandsanzeige für Rasierapparat

**Legende:** <p class="muss">Muss-Requirement<p> <p class="soll">Soll-Requirement<p> <p class="kann">Kann-Requirement<p>

### 1. Funktionale Requirements

<table border="1" cellpadding="6" cellspacing="0">
  <thead>
    <tr>
      <th>Nr.</th>
      <th>Titel</th>
      <th>Beschreibung</th>
    </tr>
  </thead>
  <tbody>
    <tr class="muss">
      <td>1.1</td>
      <td>Automatische Spannungsmessung</td>
      <td>Die Spannung des Akkus wird zuverlässig gemessen.</td>
    </tr>
    <tr class="soll">
      <td>1.2</td>
      <td>Ladezustandsberechnung in Prozent</td>
      <td>Die Spannung wird zuverlässig in Prozent umgerechnet.</td>
    </tr>
    <tr class="soll">
      <td>2.1</td>
      <td>Batteriestand wird beim Einschalten angezeigt</td>
      <td>Während der Nutzung wird der aktuelle Batteriestand angezeigt.</td>
    </tr>
    <tr class="soll">
      <td>2.2</td>
      <td>Einfache Statusanzeige bei Knopfdruck</td>
      <td>Bei kurzem Drücken des An-/Aus-Knopfes wird der aktuelle Batteriestand angezeigt.</td>
    </tr>
    <tr class="kann">
      <td>2.3</td>
      <td>Restlaufzeit auf Knopfdruck</td>
      <td>Bei erneutem kurzem Drücken des An-/Aus-Knopfes wird die Restbetriebszeit angezeigt.</td>
    </tr>
    <tr class="muss">
      <td>2.4</td>
      <td>Anzeige bei Inaktivität</td>
      <td>Die Batteriestandskontrolle funktioniert auch nach längerer Nichtnutzung zuverlässig.</td>
    </tr>
    <tr class="soll">
      <td>2.5</td>
      <td>Anzeige bei Interaktion aktualisieren</td>
      <td>Die Anzeige wird bei jeder Benutzerinteraktion automatisch aktualisiert.</td>
    </tr>
    <tr class="muss">
      <td>3.1</td>
      <td>Spannungsreferenz definieren</td>
      <td>Es werden feste Spannungswerte für leer und voll als Referenz zur Anzeige genutzt.</td>
    </tr>
    <tr class="muss">
      <td>3.2</td>
      <td>Initiale Kalibrierung</td>
      <td>Beim ersten vollständigen Lade- und Entladezyklus erfolgt eine Kalibrierung.</td>
    </tr>
    <tr class="soll">
      <td>3.3</td>
      <td>Rekalibrierung nach Ladezyklen</td>
      <td>Nach mehreren vollständigen Ladezyklen erfolgt automatisch eine neue Kalibrierung.</td>
    </tr>
    <tr class="soll">
      <td>4.1</td>
      <td>Warnung bei niedrigem Akkustand</td>
      <td>Der Nutzer wird eindeutig gewarnt, wenn der Akkustand niedrig ist.</td>
    </tr>
    <tr class="kann">
      <td>4.2</td>
      <td>Warnschwelle einstellbar</td>
      <td>Der Benutzer kann einstellen, ob die Warnung bei 30 % oder 10 % Restladung erfolgt.</td>
    </tr>
    <tr class="soll">
      <td>4.3</td>
      <td>Warnsignal bei Unterschreitung</td>
      <td>Bei Unterschreiten der Warnschwelle gibt es ein visuelles und akustisches Signal.</td>
    </tr>
    <tr class="soll">
      <td>5.1</td>
      <td>Ladeaktivität anzeigen</td>
      <td>Der laufende Ladevorgang wird klar angezeigt.</td>
    </tr>
    <tr class="soll">
      <td>5.2</td>
      <td>Ladeabschluss anzeigen</td>
      <td>Es wird eindeutig angezeigt, wenn der Akku vollständig geladen ist.</td>
    </tr>
    <tr class="soll">
      <td>5.3</td>
      <td>Ladefehler anzeigen</td>
      <td>Der Nutzer wird visuell informiert, wenn der Ladevorgang fehlschlägt.</td>
    </tr>
    <tr class="muss">
      <td>5.4</td>
      <td>Ladezustand elektronisch erkennen</td>
      <td>Die Ladeelektronik erkennt, ob der Ladevorgang aktiv, abgeschlossen oder fehlgeschlagen ist.</td>
    </tr>
    <tr class="kann">
      <td>5.5</td>
      <td>Rasur während Laden signalisiert</td>
      <td>Während des Ladevorgangs gibt es eine visuelle Meldung, wenn eine Rasur möglich ist.</td>
    </tr>
    <tr class="muss">
      <td>6.1</td>
      <td>Unterspannungsschutz</td>
      <td>Das Gerät schaltet sich bei kritischem Akkustand automatisch ab, um Tiefentladung zu vermeiden.</td>
    </tr>
    <tr class="muss">
      <td>6.2</td>
      <td>Überladeschutz</td>
      <td>Der Ladevorgang wird automatisch bei Erreichen der maximalen Spannung abgebrochen.</td>
    </tr>
    <tr class="muss">
      <td>6.3</td>
      <td>Temperaturüberwachung beim Laden</td>
      <td>Die Temperatur wird während des Ladevorgangs kontrolliert.</td>
    </tr>
  </tbody>
</table>


### 2. Nicht-funktionale Requirements

<table border="1" cellpadding="6" cellspacing="0">
  <thead>
    <tr>
      <th>Nr.</th>
      <th>Titel</th>
      <th>Beschreibung</th>
    </tr>
  </thead>
  <tbody>
    <tr class="soll">
      <td>1.3</td>
      <td>Keine zusätzliche Bedienung</td>
      <td>Die Batteriestandsanzeige erfordert keine zusätzliche Interaktion zur Rasur.</td>
    </tr>
    <tr class="soll">
      <td>2.6</td>
      <td>Intuitive Anzeige</td>
      <td>Der Batteriestand wird in einer für den Nutzer klar verständlichen Form dargestellt.</td>
    </tr>
    <tr class="soll">
      <td>2.7</td>
      <td>Keine störende Helligkeit</td>
      <td>Die Anzeige ist nicht zu hell oder visuell aufdringlich.</td>
    </tr>
    <tr class="kann">
      <td>2.8</td>
      <td>Barrierefreie Anzeige (Farbenblindheit)</td>
      <td>Die Anzeige ist auch für farbenblinde Nutzer eindeutig erkennbar.</td>
    </tr>
    <tr class="kann">
      <td>2.9</td>
      <td>Barrierefreie Anzeige (Sehschwäche)</td>
      <td>Die Anzeige ist auch für Nutzer mit eingeschränktem Sehvermögen gut lesbar.</td>
    </tr>
    <tr class="soll">
      <td>2.10</td>
      <td>Anzeige ohne Verzögerung</td>
      <td>Die Anzeige erfolgt innerhalb von &lt;1 Sekunde nach Benutzerinteraktion.</td>
    </tr>
    <tr class="muss">
      <td>3.4</td>
      <td>Anzeigetoleranz</td>
      <td>Die Prozentanzeige muss mit einer Toleranz von ±5 % gegenüber der realen Kapazität übereinstimmen.</td>
    </tr>
    <tr class="muss">
      <td>3.5</td>
      <td>Alterungsrobustheit</td>
      <td>Auch bei &lt;80 % Akkukapazität nach mehreren Jahren bleibt die Anzeige zuverlässig.</td>
    </tr>
    <tr class="kann">
      <td>3.6</td>
      <td>Ressourcenschonende Kalibrierung</td>
      <td>Die Kalibrierung darf nur minimal Rechen- und Speicherressourcen beanspruchen.</td>
    </tr>
    <tr class="soll">
      <td>4.4</td>
      <td>Wahrnehmbarkeit der Warnung</td>
      <td>Die Warnsignale müssen in typischen Alltagssituationen gut wahrnehmbar sein (z. B. im Bad oder bei Geräuschkulisse).</td>
    </tr>
    <tr class="soll">
      <td>5.6</td>
      <td>Ladeanzeige energieeffizient</td>
      <td>Die Ladezustandsanzeige muss stromsparend sein und den Ladevorgang nicht negativ beeinflussen.</td>
    </tr>
  </tbody>
</table>


### 3. Abhängigkeiten & Konflikte zwischen Requirements

| Requirement-Nr.     | Zusammenhang / Abhängigkeit / Konfliktbeschreibung                                                                 |
|-------------------------------|---------------------------------------------------------------------------------------------------------------------|
| 1.1 → 1.2, 1.3                 | Die Spannungsmessung (1.1) ist Voraussetzung für die Umrechnung in Prozent (1.2) und muss dabei automatisch & benutzerfrei (1.3) erfolgen. |
| 2. → 3.4, 3.5                 | Eine exakte Prozentberechnung (2.*) bedingt die Einhaltung der Toleranz (3.4) und muss auch bei Alterung (3.5) robust bleiben. |
| 3.1 → 1.2, 3.2, 3.3            | Die Definition der Referenzspannungen (3.1) ist Grundlage für die Prozentanzeige (1.2) und Voraussetzung für Kalibrierungen (3.2, 3.3). |
| 3.2, 3.3 → 3.4                 | Initiale und automatische Kalibrierung (3.2, 3.3) sind notwendig, um die Anzeigetoleranz von ±5 % (3.4) dauerhaft zu gewährleisten. |
| 3.6 ↔ 2.10, 5.6                | Ressourcenschonung (3.6) kann in Konflikt stehen mit schneller Anzeigeaktualisierung (2.10) oder energieeffizienter Ladeanzeige (5.6), je nach Hardware. |
| 2.5 → 2.10                     | Die Anzeigeaktualisierung bei Interaktion (2.5) setzt eine Reaktionszeit <1s (2.10) voraus. |
| 2.2, 2.3 → 2.6                 | Die Zusatzanzeigen auf Knopfdruck (2.2, 2.3) müssen intuitiv und verständlich (2.6) gestaltet sein. |
| 2.7 ↔ 2.10, 4.4                | Nicht störende Helligkeit (2.7) kann im Widerspruch stehen zu gut wahrnehmbaren Warnungen (4.4) und der Sichtbarkeit bei Interaktion (2.10). |
| 4.1 → 4.2, 4.3, 4.4            | Die Warnung bei niedrigem Akkustand (4.1) setzt konfigurierbare Schwellen (4.2) und eindeutige Signale (4.3, 4.4) voraus. |
| 5.1, 5.2 → 5.4                 | Die Anzeige des Ladezustands (5.1, 5.2) basiert auf der Erkennung durch die Elektronik (5.4). |
| 5.1 → 5.6                      | Energieeffiziente Ladeanzeige (5.6) darf die Sichtbarkeit der Ladevorgänge (5.1) nicht einschränken. |
| 5.3 → 5.4                      | Eine korrekte Ladefehleranzeige (5.3) setzt voraus, dass Ladefehler von der Elektronik zuverlässig erkannt werden (5.4). |


### 4. Zusammengehörigkeiten zwischen Requirements

| Requirement-Nr.       | Beschreibung / Begründung                                                                                         |
|--------------------------------|-------------------------------------------------------------------------------------------------------------------|
| 1.1, 1.2, 3.1                   | Spannungsmessung (1.1), Umrechnung in Prozent (1.2) und Referenzwerte (3.1) bilden die Grundlage der Anzeige. |
| 1.2, 3.4, 3.5                  | Prozentanzeige (1.2) ist nur sinnvoll, wenn sie innerhalb einer definierten Toleranz (3.4) und auch bei alternden Akkus (3.5) stabil funktioniert. |
| 2.2, 2.3, 2.5, 2.10            | Anzeige nach Knopfdruck (2.2, 2.3), regelmäßige Aktualisierung (2.5) und schnelle Reaktionszeit (2.10) gehören zur interaktiven Anzeigeeinheit. |
| 2.6, 2.7, 2.8, 2.9             | Anforderungen an die Benutzerfreundlichkeit und Barrierefreiheit der Anzeige – sollten bei Design & Hardware gemeinsam betrachtet werden. |
| 3.2, 3.3, 3.6                  | Initiale und regelmäßige Kalibrierung (3.2, 3.3) und deren ressourcenschonende Umsetzung (3.6) sind gemeinsam umzusetzen, da sie technisch eng verknüpft sind. |
| 4.1, 4.2, 4.3, 4.4             | Die Niedrigbatteriewarnung (4.1) ist nur vollständig umsetzbar mit konfigurierbarer Schwelle (4.2), klarer Darstellung (4.3) und Wahrnehmbarkeit (4.4). |
| 5.1, 5.2, 5.4, 5.6             | Ladeanzeige (5.1, 5.2), Erkennung des Ladezustands durch Hardware (5.4) und energieeffiziente Umsetzung (5.6) bilden eine zusammenhängende Ladelogik. |
| 5.3, 5.4                       | Die Erkennung von Ladeproblemen (5.4) ist Voraussetzung für deren Anzeige (5.3). Beide müssen gemeinsam entwickelt werden. |
| 6.1, 6.2, 6.3, 6.4             | Alle Schutzmechanismen gegen Tiefentladung, Überladung und Überhitzung (6.1–6.3) müssen zusammenhängend und fehlertolerant (6.4) implementiert werden. |