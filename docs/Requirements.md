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

<table>
  <thead>
    <tr>
      <th>Nr.</th>
      <th>Jira-Referenz</th>
      <th>Titel</th>
      <th>Beschreibung</th>
    </tr>
  </thead>
  <tbody>
    <tr class="muss">
      <td>1.1</td><td>BAT-7</td>
      <td>Automatische Spannungsmessung</td>
      <td>Die Spannung des Akkus wird zuverlässig gemessen.</td>
    </tr>
    <tr class="soll">
      <td>1.2</td><td>BAT-8</td>
      <td>Ladezustandsberechnung in Prozent</td>
      <td>Die Spannung wird zuverlässig in Prozent umgerechnet.</td>
    </tr>
    <tr class="soll">
      <td>2.1</td><td>BAT-10</td>
      <td>Anzeige aktueller Batteriestand</td>
      <td>Batteriestand wird beim Einschalten angezeigt.</td>
    </tr>
    <tr class="soll">
      <td>2.2</td><td>BAT-11</td>
      <td>Einfache Statusanzeige bei Knopfdruck</td>
      <td>Bei kurzem Drücken des An-/Aus-Knopfes wird der aktuelle Batteriestand angezeigt.</td>
    </tr>
    <tr class="kann">
      <td>2.3</td><td>BAT-12</td>
      <td>Restlaufzeit auf Knopfdruck</td>
      <td>Bei erneutem kurzem Drücken des An-/Aus-Knopfes wird die Restbetriebszeit angezeigt.</td>
    </tr>
    <tr class="soll">
      <td>2.5</td><td>BAT-14</td>
      <td>Anzeige bei Benutzerinteraktion aktualisieren</td>
      <td>Die Anzeige wird bei jeder Benutzerinteraktion automatisch aktualisiert.</td>
    </tr>
    <tr class="muss">
      <td>3.1</td><td>BAT-20</td>
      <td>Spannungsreferenz definieren</td>
      <td>Es werden feste Spannungswerte für leer und voll als Referenz zur Anzeige genutzt.</td>
    </tr>
    <tr class="muss">
      <td>3.2</td><td>BAT-21</td>
      <td>Initiale Kalibrierung</td>
      <td>Beim ersten vollständigen Lade- und Entladezyklus erfolgt eine Kalibrierung.</td>
    </tr>
    <tr class="soll">
      <td>3.3</td><td>BAT-22</td>
      <td>Rekalibrierung nach Ladezyklen</td>
      <td>Nach mehreren vollständigen Ladezyklen erfolgt automatisch eine neue Kalibrierung.</td>
    </tr>
    <tr class="soll">
      <td>4.1</td><td>BAT-26</td>
      <td>Warnung bei niedrigem Batteriestand</td>
      <td>Bei Unterschreiten der Warnschwelle gibt es ein visuelles und akustisches Signal als Warnung für den Nutzer.</td>
    </tr>
    <tr class="kann">
      <td>4.2</td><td>BAT-27</td>
      <td>Warnschwelle einstellbar</td>
      <td>Der Benutzer kann einstellen, ob die Warnung bei 30 % oder 10 % Restladung erfolgt.</td>
    </tr>
    <tr class="soll">
      <td>5.1</td><td>BAT-29</td>
      <td>Ladeaktivität anzeigen</td>
      <td>Der laufende Ladevorgang wird klar angezeigt.</td>
    </tr>
    <tr class="soll">
      <td>5.2</td><td>BAT-30</td>
      <td>Ladeabschluss anzeigen</td>
      <td>Es wird eindeutig angezeigt, wenn der Akku vollständig geladen ist.</td>
    </tr>
    <tr class="soll">
      <td>5.3</td><td>BAT-31</td>
      <td>Ladefehler anzeigen</td>
      <td>Der Nutzer wird visuell informiert, wenn der Ladevorgang fehlschlägt.</td>
    </tr>
    <tr class="muss">
      <td>5.4</td><td>BAT-32</td>
      <td>Ladezustand elektronisch erkennen</td>
      <td>Die Ladeelektronik erkennt, ob der Ladevorgang aktiv, abgeschlossen oder fehlgeschlagen ist.</td>
    </tr>
    <tr class="kann">
      <td>5.5</td><td>BAT-33</td>
      <td>mögliche Rasur während Laden signalisieren</td>
      <td>Während des Ladevorgangs gibt es eine visuelle Meldung, wenn eine Rasur möglich ist.</td>
    </tr>
    <tr class="muss">
      <td>6.1</td><td>BAT-35</td>
      <td>Unterspannungsschutz</td>
      <td>Das Gerät schaltet sich bei kritischem Akkustand automatisch ab, um Tiefentladung zu vermeiden.</td>
    </tr>
    <tr class="muss">
      <td>6.2</td><td>BAT-36</td>
      <td>Überladeschutz</td>
      <td>Der Ladevorgang wird automatisch bei Erreichen der maximalen Spannung abgebrochen.</td>
    </tr>
    <tr class="muss">
      <td>6.3</td><td>BAT-37</td>
      <td>Temperaturüberwachung beim Laden</td>
      <td>Die Temperatur wird während des Ladevorgangs kontrolliert.</td>
    </tr>
  </tbody>
</table>




### 2. Nicht-funktionale Requirements

<table>
  <thead>
    <tr>
      <th>Nr.</th>
      <th>Jira-Referenz</th>
      <th>Titel</th>
      <th>Beschreibung</th>
    </tr>
  </thead>
  <tbody>
    <tr class="soll">
      <td>1.3</td><td>BAT-9</td>
      <td>Keine zusätzliche Bedienung</td>
      <td>Die Batteriestandserkennung erfordert keine zusätzliche Interaktion zur Rasur.</td>
    </tr>
    <tr class="muss">
      <td>2.4</td><td>BAT-13</td>
      <td>Anzeige bei längerer Nichtbenutzung</td>
      <td>Die Batteriestandskontrolle funktioniert auch nach längerer Nichtnutzung zuverlässig.</td>
    </tr>
    <tr class="soll">
      <td>2.6</td><td>BAT-15</td>
      <td>Intuitive Anzeige</td>
      <td>Der Batteriestand wird in einer für den Nutzer klar verständlichen Form dargestellt.</td>
    </tr>
    <tr class="soll">
      <td>2.7</td><td>BAT-16</td>
      <td>Angenehme Anzeige</td>
      <td>Die Anzeige ist nicht zu hell oder visuell aufdringlich.</td>
    </tr>
    <tr class="kann">
      <td>2.8</td><td>BAT-17</td>
      <td>Barrierefreie Anzeige (Farbenblindheit)</td>
      <td>Die Anzeige ist auch für farbenblinde Nutzer eindeutig erkennbar.</td>
    </tr>
    <tr class="kann">
      <td>2.9</td><td>BAT-18</td>
      <td>Barrierefreie Anzeige (Sehschwäche)</td>
      <td>Die Anzeige ist auch für Nutzer mit eingeschränktem Sehvermögen gut lesbar.</td>
    </tr>
    <tr class="soll">
      <td>2.10</td><td>BAT-19</td>
      <td>Anzeige ohne Verzögerungen</td>
      <td>Die Anzeige erfolgt innerhalb von &lt;1 Sekunde nach Benutzerinteraktion.</td>
    </tr>
    <tr class="muss">
      <td>3.4</td><td>BAT-23</td>
      <td>Anzeigetoleranz</td>
      <td>Die Prozentanzeige muss mit einer Toleranz von ±5 % gegenüber der realen Kapazität übereinstimmen.</td>
    </tr>
    <tr class="muss">
      <td>3.5</td><td>BAT-24</td>
      <td>Alterungsrobustheit</td>
      <td>Auch bei &lt;80 % Akkukapazität nach mehreren Jahren bleibt die Anzeige zuverlässig.</td>
    </tr>
    <tr class="kann">
      <td>3.6</td><td>BAT-25</td>
      <td>Ressourcenschonende Kalibrierung</td>
      <td>Die Kalibrierung darf nur minimal Rechen- und Speicherressourcen beanspruchen.</td>
    </tr>
    <tr class="soll">
      <td>4.3</td><td>BAT-28</td>
      <td>Wahrnehmbarkeit der Warnung</td>
      <td>Die Warnsignale müssen in typischen Alltagssituationen gut wahrnehmbar sein (z. B. im Bad oder bei Geräuschkulisse).</td>
    </tr>
    <tr class="soll">
      <td>5.6</td><td>BAT-34</td>
      <td>Ladeanzeige energieeffizient</td>
      <td>Die Ladezustandsanzeige muss stromsparend sein und den Ladevorgang nicht negativ beeinflussen.</td>
    </tr>
  </tbody>
</table>


### 3. Abhängigkeiten zwischen Requirements

| Requirement-Nr.          | Zusammenhang / Abhängigkeit / Konfliktbeschreibung                                                                                                                                                             |
|--------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1.1 → 1.2, 1.3           | Die Spannungsmessung (1.1) ist Voraussetzung für die Umrechnung in Prozent (1.2) und muss dabei automatisch & benutzerfrei (1.3) erfolgen.                                                                     |
| 3.1 → 1.2, 3.2, 5.5      | Die Definition der Referenzspannungen (3.1) ist Grundlage für die Prozentanzeige (1.2) und das Signalisieren, dass wieder eine Rasur möglich ist, (5.5) und Voraussetzung für die initiale Kalibrierung (3.2). |
| 3.2 → 3.3                | Die Definition der initialen Kalibrierung (3.2) ist Vorraussetzung für die regelmäßige Rekalibrierung (3.3).                                                                                                   |
| 3.3 → 3.4                | Regelmäßige Rekalibrierung (3.3) ist notwendig, um die Anzeigetoleranz von ±5 % (3.4) dauerhaft zu gewährleisten.                                                                                              |
| 5.4 → 5.1, 5.2, 5.3, 5.5 | Die Anzeige des Ladezustands (5.1, 5.2, 5.3, 5.5) basiert auf der Erkennung durch die Elektronik (5.4).                                                                                                        |
| 1.2 → 2                  | Der Ladezustand der Batterie muss zuverlässig in Prozent umgerechnet werden (1.2), um die adäquate Anzeige zu ermöglichen (2).                                                                                 |
| 2.1 → 2.*                | Die generelle Anzeige des Batteriezustandes (2.1) bildet die Grundlage für alle weiterführenden Anzeigen (2.3) bzw. die Anzeige als Folge von konkreten Aktionen (2.2, 2.5).                                   |
| 3 → 2.1                  | Die Spannungsreferenz und die richtige Kalibrierung (3) ist Vorraussetzung für eine korrekte Anzeige (2.1).                                                                                                    |
| 1.1, 3.1 → 6.1, 6.2      | Die definierte Spannungsreferenz (3.1) und die zuverlässige Messung der Akkuspannung (1.1) ist Vorraussetzung für einen gelungenen Unterspannungs- (6.1) und Überladeschutz (6.2).                             |
| 6, 3.3 → 3.5             | Die Umsetzung der Schutzfunktionen für die Sicherheit und die Langlebigkeit der Batterie (6), sowie die regelmäßige Rekalibrierung der Spannungsreferenz (3.3) sind maßgeblich für die Altersrobustheit (3.5). |

### 4. Konflikte zwischen Requirements

| Requirement-Nr. | Zusammenhang / Abhängigkeit / Konfliktbeschreibung                                                                                       |
|-----------------|------------------------------------------------------------------------------------------------------------------------------------------|
| 2.10 ↔ 3.6, 5.6 | Ressourcenschonung (3.6)  oder energieeffizienter Ladeanzeige (5.6) kann mit schneller Anzeigeaktualisierung (2.10) in Konflikt stehen . |
| 2.7 ↔ 4.3       | Nicht störende Helligkeit (2.7) kann im Widerspruch stehen zu gut wahrnehmbaren Warnungen (4.4).                                         |

### 5. Zusammengehörigkeiten zwischen Requirements

| Requirement-Nr. | Beschreibung / Begründung                                                                                                                                                                                                             |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| 1.1, 1.2, 3.1   | Spannungsmessung (1.1), Umrechnung in Prozent (1.2) und Referenzwerte (3.1) bilden die Grundlage der Anzeige.                                                                                                                         |
| 1.2, 3.4, 3.5   | Prozentanzeige (1.2) ist nur sinnvoll, wenn sie innerhalb einer definierten Toleranz (3.4) und auch bei alternden Akkus (3.5) stabil funktioniert.                                                                                    |
| 4.1, 4.2        | Die Einstellung der Warnschwelle (4.2) ist nur sinnvoll, wenn es auch eine Warnung bei niedrigem Akkustand (4.1) gibt und diese ist von der Warnschwelle abhängig.                                                                    |
| 6.2, 6.3, 5     | Der Überladeschutz (6.2) und die Temperaturüberwachung beim Laden (6.3) hängen mit dem Ladevorgang und seiner Anzeige im generellen (5) zusammen.                                                                                     |
| 6.1, 1          | Der Unterspannungsschutz (6.1) hängt eng mit der Erkennung des aktuellen Batteriestandes (1) zusammen, da sonst keine Unterspannung ausgemacht werden kann.                                                                           |
| 2.6, 4.3        | Die intuitive Batteriestandsanzeige (2.6) hängt mit der guten Wahrnehmbarkeit der Warnung bei niedrigem Batteriestand (4.4) zusammen, da beide dasselbe Ziel verfolgen. Für den Nutzer gut erkennbar und leicht verständlich zu sein. |