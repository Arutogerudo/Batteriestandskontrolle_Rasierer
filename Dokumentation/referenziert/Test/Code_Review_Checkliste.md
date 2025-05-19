# **Code-Review Checkliste**

### 🔹 **1. Klassenstruktur & Verantwortlichkeiten**

* [ ] Hat jede Klasse **genau eine klar definierte Aufgabe**?
* [ ] Gibt es **Utility- oder Helferklassen**, die ggf. besser modularisiert gehören?
* [ ] Enthält keine Klasse „zu viel“ – z. B. Datenhaltung **und** Verarbeitung **und** Darstellung?

---

### 🔹 **2. Methodenstruktur & Lesbarkeit**

* [ ] Erfüllt jede Methode **nur eine Aufgabe** (Single Responsibility)?
* [ ] Ist die Methode **kurz und gut verständlich** (max. ca. 10–20 Zeilen)?
* [ ] Haben Methoden einen **aussagekräftigen Namen** (Verb + Objekt)?
* [ ] Sind verschachtelte Bedingungen und Schleifen minimal und verständlich?
* [ ] Gibt es **keine versteckten Seiteneffekte** (z. B. Methoden, die Felder verändern, obwohl das nicht erkennbar ist)?

---

### 🔹 **3. Benennung & Verständlichkeit**

* [ ] Sind **Variablen- und Methodennamen sprechend** und selbstdokumentierend?
* [ ] Gibt es **keine Abkürzungen oder kryptische Namen** (`btnX`, `val2`, etc.)?
* [ ] Wurden **magische Zahlen** durch **Konstanten oder Enums** ersetzt?
* [ ] Ist die **Namenskonvention konsistent** (CamelCase, Klassen groß, Methoden klein)?

---

### 🔹 **4. Abhängigkeiten & Kopplung**

* [ ] Sind die Klassen **möglichst lose gekoppelt** (z. B. durch Interfaces, Dependency Injection)?
* [ ] Vermeidet der Code **zirkuläre Abhängigkeiten**?

---

### 🔹 **5. Wartbarkeit & Erweiterbarkeit**

* [ ] Ist der Code so aufgebaut, dass **neue Funktionen leicht ergänzt** werden können?
* [ ] Gibt es **keine Duplizierung von Code** (Copy-Paste, redundante Bedingungen)?
* [ ] Werden **Konstanten zentral verwaltet**?
* [ ] Ist **Fehlerbehandlung vorhanden**, aber nicht übertrieben komplex?

---

### 🔹 **6. Technische Sauberkeit**

* [ ] Wird **kein Dead Code** (nicht verwendete Variablen, Methoden, Importe) mitgeführt?
* [ ] Ist der **Zugriffsbereich sinnvoll eingeschränkt** (`private` wo möglich)?
* [ ] Wurde **Thread-Sicherheit beachtet**, falls Threads verwendet werden?
* [ ] Wird auf **NullPointer-Sicherheit** geachtet?

---

### 🔹 **7. Tests & Verifikation**

* [ ] Gibt es (zumindest manuelle) Testszenarien oder automatisierte Unit-Tests?
* [ ] Wird **Verhalten anhand von Ausgabe oder GUI-Status überprüft**?

---

### 🔹 **8. Dokumentation & Kommentare**

* [ ] Sind **komplizierte Algorithmen oder Sonderfälle kommentiert**?
* [ ] Gibt es **keine unnötigen Kommentare**, die nur wiederholen, was der Code ohnehin aussagt?
* [ ] Wurde **JavaDoc** für öffentliche Methoden/Klassen sinnvoll eingesetzt?
