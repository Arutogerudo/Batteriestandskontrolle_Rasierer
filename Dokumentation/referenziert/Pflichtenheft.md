# Pflichtenheft: Batteriestandskontrolle für Rasierapparat

### 1. **Anzeige des Batteriestands**

- Implementierung einer gut sichtbaren, intuitiv verständlichen Anzeige (z. B. Prozentwert, Balkenanzeige mit Farbcodes).
- Anzeige darf nicht überladen oder komplex wirken.
- Verzögerungsfreie Aktualisierung (max. 200 ms Reaktionszeit).

### 2. **Ladezustandsanzeige**

- Zwei Ladeanzeigen:
    - „Ladevorgang aktiv“ (z. B. blinkendes Symbol oder Animation),
    - „Ladevorgang abgeschlossen“ (z. B. dauerhaft leuchtendes Symbol).
- Zustandserkennung über Ladeelektronik.

### 3. **Warnfunktion bei niedrigem Akkustand**

- Benutzerdefinierbare Warnschwelle (10 % oder 30 %).
- Visuelles und akustisches Signal bei Unterschreitung.

### 4. **Zugänglichkeit der Anzeige**

- Akkustand abrufbar per Knopfdruck auch bei ausgeschaltetem Gerät.
- Anzeige muss barrierefrei für Farbenblinde und ältere Nutzer gestaltet sein (z. B. durch Symbole oder kontrastreiche Schrift).

### 5. **Information bei Ladefehlern**

- Fehlermeldung bei Ladeproblem (z. B. „Akku lädt nicht“).
- Ursachen z. B. Temperatur, Defekt, Feuchtigkeit.

### 6. **Robustheit und Zuverlässigkeit**

- Anzeige muss auch unter folgenden Bedingungen korrekt arbeiten:
    - Luftfeuchtigkeit: 20 %–90 %
    - Temperatur: 0 °C–45 °C
    - Nach längerer Nichtbenutzung

### 7. **Anzeige der Restbetriebsdauer**

- Dynamische Berechnung und Anzeige der verbleibenden Rasurzeit in Minuten.
- Berechnungsalgorithmus auf Basis von Nutzungshistorie und aktuellem Energieverbrauch.

### 8. **Komfort und Ergonomie**

- Keine zusätzliche Komplexität durch die Anzeige im Bedienprozess.
- Displayhelligkeit automatisch oder manuell dimmbar (z. B. für Nachtgebrauch).

### 9. **Batterie**

- Akku-Typ: Lithium-Ionen (Li-Ion)
- Spannung: 3,7 Volt
- Kapazität: zwischen 680 mAh und 750 mAh
- Ladung:
  - Vollständige Ladung: ca. 60 Minuten
  - Schnellladung: 5 Minuten für eine vollständige Rasur
  - Lademöglichkeiten: Über mitgeliefertes Netzkabel, Ladestation oder Reinigungsstation
- Betrieb:
  - Maximale Laufzeit: ca. 50 Minuten bei kontinuierlicher Nutzung
  - Bereitschaftsdauer: Bis zu 3 Wochen bei durchschnittlicher Nutzung
  - Betrieb: Ausschließlich kabellos; Nutzung während des Ladevorgangs nicht möglich

### 10. **Batteriestandserkennung**

| Ladezustand | Spannung (typisch) |
|-------------|--------------------|
| 100 %       | ca. 4,20 V     |
| 50 %        | ca. 3,75 V     |
| 0 %         | ca. 3,00 V     |
| Tiefentladen| < 2,75 V (kritisch, sollte vermieden werden) |

### 11. **Schutzmechanismen für Langlebigkeit und Sicherheit**

- Kapazitätsverlust: Die volle Ladung (4,2 V) liefert im Alter weniger Energie (mAh), somit nimmt die nutzbare Zeit zwischen 4,2 V und 3,0 V ab.
- Unterspannungsschutz (abschalten vor Tiefentladung, meist bei ~3,0 V)
- Ladeschutz (nicht über 4,2 V)
- Temperaturkontrolle beim Laden