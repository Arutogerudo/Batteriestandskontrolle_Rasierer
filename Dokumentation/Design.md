# Design

## Klassendiagramm

![Klassendiagramm](./referenziert/Design/Klassendiagramm.png)

## Zustandsdiagramm Knopfinteraktion

![Zustandsdiagramm Knopfinteraktion](./referenziert/Design/Zustandsdiagramm_Knopfinteraktion.png)

## Sequenzdiagramm 

![Sequenzdiagramm Spannungsmessung zu Anzeige](./referenziert/Design/Sequenzdiagramm_Messung_zu_Anzeige.png)

## Designpatterns

| Klasse            | Design-Pattern        | Grund                         |
|-------------------|------------------------|-------------------------------|
| SettingsStorage  | Singleton  | gewährleistet zentralen und konsistenten Zugriff auf gespeicherte Werte   |
| InteractionHandler | Command | leichtere Erweiterbarkeit und Wartbarkeit, Commands können getestet und protokolliert werden ohne den Handler zu verändern |
