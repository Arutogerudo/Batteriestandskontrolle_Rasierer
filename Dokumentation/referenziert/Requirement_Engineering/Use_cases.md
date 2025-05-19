# Use Cases: Batteriestandskontrolle für Rasierapparat

### 1. **Tägliche Rasur vor der Arbeit** - Akteur: Endnutzer

**Ziel:** Frühzeitig gewarnt werden, wenn Akku leer geht, so dass rechtzeitig wieder aufgeladen werden kann. Dazu muss die Anzeige gut erkennbar und angenehm zu sehen sein.

**Interaktionen:** 
- Endnutzer entnimmt Rasierer (evlt. vom Ladegerät).
- Akkustandsanzeige (z. B. LED, Display) leuchtet beim Einschalten oder kurz bei Entnahme.
- Bei niedrigem Akkustand blinkt Warnsymbol oder rote LED.
- Falls während der Nutzung die Schwelle für die Warnung bei geringer Ladung unterschritten wird, gibt es ein akustisches Signal.

**Vorbedingungen:**
- Gerät wurde korrekt geladen und ist betriebsbereit.
- Akku ist nicht vollständig entladen.

**Nachbedingungen:**
- Nutzer kann Entscheidung treffen: nach der Rasur laden oder nicht.
- Gerät zeigt Status auch während der Nutzung an.

### 2. **Aufladen, wenn Akku vor geplanter Rasur leer ist** - Akteur: Endnutzer

**Ziel:** Den Akku schnellstmöglich aufladen. Dabei den Ladevorgang beobachten und sehen, wann das Gerät einsatzbereit ist.

**Interaktionen:**
- Nutzer verbindet Rasierer mit Ladegerät.
- Lade-LED oder Display zeigt Ladezustand (blinkend bei Ladevorgang).
- Bei Schnellladung (z. B. 5 min) erfolgt Rückmeldung (z. B. „1 Rasur möglich“).

**Vorbedingungen:**
- Gerät vollständig oder teilweise entladen.
- Netzteil ist verfügbar und funktionsfähig.

**Nachbedingungen:**
- Gerät ist teilweise oder vollständig geladen.
- Anzeige bestätigt Ladefortschritt und evtl. Bereitschaft zur Nutzung.

### 3. **Sporadische Rasur alle paar Wochen** - Akteur: Endnutzer

**Ziel:** Rasur erfolgreich durchführen, ohne dass der Akku mitten im Vorgang leer wird. Dazu muss der Akku geprüft werden, evtl. ohne den Rasierer einzuschalten.

**Interaktionen:**
- Nutzer drückt kurz auf Ein-/Aus-Schalter → Gerät zeigt Batteriestatus ohne vollständigen Betrieb.

**Vorbedingungen:**
- Gerät wurde längere Zeit nicht genutzt.
- Akku kann in jedem beliebigen Zustand sein.

**Nachbedingungen:**
- Nutzer hat Gewissheit über Batteriestand.
- Entscheidung: Rasur beginnen oder vorab aufladen.

### 4. **Rasur im Urlaub** - Akteur: Endnutzer

**Ziel:** Die Anzeige, insbesondere die mit der geschätzen restlichen Betriebsdauer, muss zuverlässig sein. Der Akteur plant den Akkuverbrauch im Voraus und möchte in der Regel keine Ladegeräte mitnehmen.

**Interaktionen:**
- Anzeige zeigt geschätzte Minuten oder Rasuren (z. B. „Noch 2 Rasuren möglich“).
- Nutzer plant vor dem Urlaub anhand dieser Info.

**Vorbedingungen:**
- Rasierer ist aufgeladen vor Urlaubsbeginn.
- Prognosefunktion ist aktiviert bzw. funktionsfähig.

**Nachbedingungen:**
- Nutzer kann bewusst entscheiden, ob Ladegerät nötig ist.
- Anzeige passt sich ggf. dynamisch dem Verbrauchsverhalten an.

### 5. **Batteriezustand erkennen** - Akteur: Gerätelogik

**Ziel:** Die Spannung des Akku wird gemessen und zuverlässig in Prozent und restlicher Betriebsdauer umgerechnet, um eine zuverlässige Anzeige zu gewährleisten

**Interaktionen:**
- Gerät misst Batteriespannung kontinuierlich oder zyklisch.
- Algorithmen interpretieren Spannung und internen Widerstand → Prozentwert + ggf. Restlaufzeit.
- Anzeige wird bei jeder Benutzerinteraktion aktualisiert.

**Vorbedingungen:**
- Sensorik und Steuerung (z. B. Mikrocontroller) sind betriebsbereit.
- Akku nicht defekt oder außerhalb Messbereich.

**Nachbedingungen:**
- Validierter und benutzerfreundlicher Wert wird angezeigt (LED, Display, App).
- Optional: Werte werden zur Verlaufsanalyse gespeichert.