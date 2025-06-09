# Design

## Designentscheidungen

### UI-Konzept zur Einstellung der Warnschwelle

An einem realen Rasierer würde die Einstellung der Warnschwelle über einen Kippschalter oder Schieberegler erfolgen.
Damit kann dann sehr einfach zwischen den beiden geplanten Warnschwellen 10% und 30% umgeschaltet werden. Falls eine
genauere Einstellung geplant/gewünscht wäre, könnte ein Drehregler verwendet werden. All diese Benutzerschnittstellen
befinden sich idealerweise an der Seite des Geräts, um eine einfache Bedienung zu ermöglichen.

In diesem SW-Projekt ohne die Möglichkeit, eine physische Benutzerschnittstelle zu implementieren, wird die Einstellung
der Warnschwelle über einen JToggle Button in der GUI realisiert. Der Button kann zwischen den beiden Zuständen "10%"
und "30%" umgeschaltet werden. Die Einstellung wird in der Klasse `SettingsStorage` gespeichert und kann jederzeit
abgerufen werden.

### Bedingung dafür, dass eine Rasur möglich

Eine Rasur dauert in der Regel 5-10 Minuten. Daher ist eine Rasur möglich, wenn der Ladezustand des Akkus zwischen 10%
und 100% liegt. Das heißt, dass der Akku auf mindestens 10% geladen sein muss, um eine Rasur zu ermöglichen.

### Signal dafür, dass eine Rasur möglich ist

Einblenden eines Icons eines Rasierers mit einem Haken dran auf dem Display unter der Prozentanzeige.

## Klassendiagramm

![Klassendiagramm](../referenziert/Design/Klassendiagramm3.png)

## Zustandsdiagramm Knopfinteraktion

![Zustandsdiagramm Knopfinteraktion](../referenziert/Design/Zustandsdiagramm_Knopfinteraktion_updatet.png)

Legende:

* "1": 1× kurz gedrückt
* "2": 1× lang gedrückt
* "3": 5s kein Drücken && nicht in Betrieb | 1× lang gedrückt && in Betrieb

## Zustandsdiagramm ChargingStates inkl. Schutzmechanismen

![Zustandsdiagramm ChargingStates](../referenziert/Design/Zustandsdiagramm_ChargingStates.png)

Legende:

* "1": Betrieb des Rasierers (OperationState = OPERATING)
* "2": Ladevorgang gestartet
* "3": Ladung voll (100%)
* "4": zu hohe Temperatur (>= 45°C)
* "5": kritische Unterspannung unterschritten (< 2,8 V) -> Aktion: Dauerleuchten LED in rot & in Stromsparmodus
* "6": Ladevorgang beendet (Kabel entfernt)
* "7": Temperatur in Ordnung (< 45°C)

## Designpatterns

| Klasse                   | Design-Pattern | Grund                                                                                                                      |
|--------------------------|----------------|----------------------------------------------------------------------------------------------------------------------------|
| `SettingsStorage`        | Singleton      | Gewährleistet zentralen und konsistenten Zugriff auf gespeicherte Werte                                                    |
| `InteractionHandler`     | Command        | Leichtere Erweiterbarkeit und Wartbarkeit, Commands können getestet und protokolliert werden ohne den Handler zu verändern |
| `BatteryStateController` | Observer       | Notifiziert andere Komponenten (LED, GUI), wenn sich Ladezustand oder Fehlermeldungen ändern                               |
| `VisualOutputController` | Observer       | Reagiert auf Änderungen im Ladezustand, z.B. um Anzeige zu aktualisieren                                                   |
| `LEDController`          | Observer       | Blinkt oder ändert Farbe, wenn sich Ladeaktivität oder Fehlerstatus ändert                                                 |
| `SimpleGUI`              | Observer       | Aktualisiert Textanzeigen bei Änderungen im SoC oder Ladezustand                                                           |
| `VoltageSimulator`       | State          | Verwaltet Ladezustände wie `Charging`, `Idle`, `Overheated` – jeweils mit unterschiedlichem Verhalten                      |
| `OperationController`    | State          | System hat verschiedene Betriebsmodi (z.B. Laden, Anzeige, Fehler), die eigenes Verhalten ausprägen                        |
| `CalibrationManager`     | Observer       | ermöglicht lose Kopplung zwischen der Logik zur Ladezyklusverfolgung und den reaktiven Komponenten                         |
