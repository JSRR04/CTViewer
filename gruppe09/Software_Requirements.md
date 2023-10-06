# Aufgabe 1
# Motivation

In den Programmen, die am Klinikum Musterstadt für die Verwaltung der Patientendaten erstellt werden, werden Fehler- und Warnmeldungen meistens über `System.out.println(...)` oder `System.err.println(...)`, manchmal auch über `System.console().format(...)` ausgegeben.

Nun soll die Ausgabe von wichtigen Systemmeldungen vereinheitlicht werden. Die Lösung soll so flexibel sein, dass später beispielsweise auch noch ein Mailversand oder das Versenden in ein zentrales Logging-Repository integriert werden könnte.

# Aufgabenstellung

1. Entwerfen Sie die Schnittstelle einer Logger-Klasse gemäß den unten stehenden Anforderungen.
2. Implementieren Sie diese Klasse. Achten Sie dabei auf Erweiterbarkeit, Wartbarkeit und Verständlichkeit sowie auf die üblichen Programmierkonventionen.
3. Schreiben Sie auch Code, der die Fähigkeiten der entwickelten Klasse demonstriert und testet.

# Anforderungen

* Zu jeder Meldung soll angegeben werden können, ob sie einen Fehler (`ERROR`), eine Warnung (`WARN`) oder eine allgemeine Information (`INFO`) darstellt. Dies soll mit einem Aufzählungstyp (`enum`) realisiert werden.
* Zu jeder Meldung soll zusätzlich angegeben werden können, von welcher Methode (bzw. Klasse) die Meldung erzeugt wurde.
* Die Meldungen sollen auf der Konsole ausgegeben *und* in eine Datei geschrieben werden. Dabei steht vor der Meldung selbst zunächst der aktuelle Zeitpunkt, dann die Meldungsart (Fehler, Warnung oder Info), die Klasse und der Methodenname sowie danach die eigentliche Meldung.
* Neue Instanzen Ihrer Logging-Klasse sollen durch den Aufrufenden *ausschließlich* durch eine sogenannte *Logger-Factory* (Design Pattern: Factory Method) erzeugt werden können.

Eine beispielhafte Log-Ausgabe sollte dann wie folgt aussehen:

```
04.10.2022, 09:12:45 INFO: de.hhn.mi.swlab.logger.LoggerDemo.main: Programm wird gestartet.
04.10.2022, 09:12:45 WARN: de.hhn.mi.swlab.logger.LoggerDemo.main: Properties nicht gefunden.
04.10.2022, 09:12:45 ERROR: de.hhn.mi.swlab.logger.MyDemo.init: Fehler beim Initalisieren des Objekts.
```

# Abgabe

Für das erfolgreiche Bestehen dieser Aufgabe sind folgende Punkte zu erfüllen:

1. Eine vollständig und korrekte Umsetzung einer Logger-Klasse und dazugehöriger Logger-Factory sowie entsprechender Interfaces gemäß der oben beschriebenen Anforderungen.
2. Beispiel-Code der die Funktionalität der entwickelten Klassen demonstriert und testet.
3. Kompilier- und lauffähiger Java-Code sowie Einhaltung der *Java Naming Conventions* und sinnvolles *Exception-Handling* sowie Dokumentation (Javadoc).

# Aufgabe 2


# Motivation

Ein Computertomographie-Datensatz (CT-Datensatz) enthält die Daten einer zusammenhängenden Serie von Querschnittsbildern eines Patienten. Hierzu gehören sowohl die Grauwerte der einzelnen Punkte der Bilder selbst sowie entsprechende die Metadaten der Serie, d. h. Daten über den Patienten sowie Daten zum Aufbau der Bildserie.

Die entsprechen Datensätze unterscheiden sich jedoch zwischen dem Klinikum *Musterstadt1* und dem Klinikum *Musterstadt2*:

1. Im Klinikum Musterstadt1 wird jeder CT-Datensatz vollständig *in einem textbasierten* Dateiformat gespeichert.
2. Im Klinikum Musterstadt2 wird jeder CT-Datensatz *in zwei verschiedenen* Dateien gespeichert.

Nach dem Zusammenschluss der beiden Kliniken sollen die CT-Datensätze krankenhausübergreifend verwendet werden. Aus diesem Grund ist es notwendig, die Dateien des einen Formats in das jeweils andere Format konvertieren zu können. Details zu den entsprechenden Datenformaten entnehmen Sie der beigefügten Spezifikation.

# Aufgabenstellung

Implementieren Sie einen CT-Datenkonverter, der die Datei des Formates aus Musterstadt1 in die Dateien des Formats aus Musterstadt2 und umgekehrt konvertieren kann.

# Vorgehensweise

## Konvertierung in ein Multi Module Project

Im Rahmen von LAB-01 haben Sie ein einfaches Logging-Framework entwickelt, welches nun  eingesetzt werden soll. Inzwischen haben Sie in der Vorlesung *Software Engineering 2* das Build- und Dependency-Management Werkzeug *Apache Maven* kennengelernt.

Nutzen Sie Ihr neu erlerntes Wissen und verändern Sie Ihre bisheriges Projekt zu einem *Multi Module Project*. Achten Sie auf die Wahl einer geeigneten `groupId` und `artifactId` für Ihr Projekt. Geben Sie auch Ihre `organization` sowie alle beteiligten `developers` an. Deklarieren Sie Gemeinsamkeiten (z. B. `plugins`, `dependencies`) zwischen beiden Modulen in der Datei `pom.xml` (Parent POM) auf der obersten Ebene.

Nachfolgendes Listing zeigt eine mögliche Projekt-Struktur:

```
pom.xml
- ct-logger
-- pom.xml
-- src
--- main
---- java
---- resources
--- test
---- java
---- resources
- ct-converter
-- src
--- main
---- java
---- resources
--- test
---- java
---- resources
-- pom.xml
```

## Weitere Hinweise

* Entwerfen Sie eine Schnittstelle für eine Klasse, die einen CT-Datensatz in einem bestimmten Format einlesen bzw. schreiben kann. Dokumentieren Sie Ihre Schnittstelle und implementieren Sie diese Klasse.
* Entwerfen Sie eine Schnittstelle für eine Klasse zur Konvertierung eines CT-Datensatz in das jeweils andere Format und implementieren Sie diese Klasse.
* Verwenden Sie für das Logging (z. B. im Fehlerfall) Ihre Logger-Implementierung aus LAB-01.
* Schreiben Sie JUnit Tests, um die Funktionalität der zuvor geschriebenen Klassen zu demonstrieren und zu testen.
    * Verwenden Sie in diesem Kontext das [Maven Surefire Plugin](https://maven.apache.org/surefire/maven-surefire-plugin/), um die JUnit Tests automatisch während eines Builds auszuführen.
    * Verwenden Sie `git lfs` für das Hinzufügen der Binärdateien zu Ihrem Projekt.
* Versuchen Sie nun, die Dateien in beide Richtungen zu konvertieren. Sie können hierfür die bereitgestellten Beispieldatei(en) als Ausgangsdatei(en) für die Konvertierung verwenden.

## Schnittstellenentwurf

Für den Entwurf Ihrer Dienstschnittstellen sollten Sie sich zudem an den folgenden Randbedingungen orientieren, die entsprechend der gegebenen Nummerierung zu priorisieren sind. Natürlich müssen die Schnittstellen ohnehin so entworfen sein, dass eine Implementierung überhaupt die gestellten (funktionalen) Anforderungen erfüllen kann. Darüber hinaus gilt:

* Machen Sie es den Nutzern der Schnittstelle (also denjenigen, die gegen Ihre Schnittstelle programmieren) so schwer wie möglich Ihre Schnittstelle falsch zu benutzen.
* Machen Sie es den Nutzern so schwer wie möglich mit Ihrer Schnittstelle inkonsistente Objekt- bzw. Systemzustände zu erzeugen.
* Machen Sie es den Nutzern so leicht wie möglich Ihre Schnittstelle zu benutzen.
* Zeigen Sie den Nutzern so klar wie möglich, was sie damit tun können bzw. dürfen und was nicht.
* Machen Sie es den Nutzern so leicht wie möglich Ihre Schnittstelle zu verstehen.

## Kommentieren Ihres Programmcodes

Das Kommentieren von Programmen und Schnittstellen ist für deren Verständnis unerlässlich. Es gibt allerdings feine Unterschiede zwischen sinnvollen und weniger sinnvollen Kommentaren und manchmal ist weniger sogar mehr. Die (vielleicht nur wenigen aber) wichtigen Kommentare sind dafür meist umso schwieriger zu verfassen.

Zwei Beispiele für weniger sinnvolles Schnittstellenkommentare:

```java
/**
* This class extends class Z.
* ...
*/
```

```java
public class Y extends Z {
/**
* Gets X and returns it.
* @return return X or {@code null} if X is {@code null}
*/
public X getX();
}
```

> Bitte melden Sie sich, wenn Ihnen nicht klar ist, warum Sie solche Kommentare weglassen können bzw. sogar weglassen sollen!

Beispiel für einen sinnvollen Kommentar:

```java
public class Y extends Z {
/**
* ...
* @param x must not be {@code null}.
* @throws IllegalArgumentException if <code>x</code> is {@code null}.
*/
public void setX(X x);
}
```

Auch Informationen über den Autor einer Klasse oder das Erstellungsdatum sind häufig unnötig in Klassenkommentaren, wenn man ein Versionsverwaltungssystem konsequent benutzt, da diese Informationen dort bereits automatisch hinterlegt werden.

Die resultierenden Kommentare sollten, sauber formuliert und formatiert sein (also gutes, grammatikalisch und orthographisch korrektes **Englisch**). Formulieren Sie knapp aber präzise. Verwenden Sie Standardformulierungen für häufig in ähnlicher Form wiederkehrende Aussagen.

Nutzen Sie die Formatierungsmöglichkeiten von Javadoc voll aus, zum Beispiel:

* `{@code null}` oder `<code>arg1 == 1</code>`, um Java-Code auch in Dokumentationsformulierungen zu zitieren,

* `{@link AClassName#aMethodName(int)}` um Programmentitäten in Dokumentationsformulierungen zu zitieren bzw. zu referenzieren. IntelliJ IDEA kann Sie dabei beim Editieren unterstützen.

## Manuelles Testen der Konvertierung

Sie können das Ergebnis der Konvertierung auch *manuell* testen, d. h. um
die Eingabe des Konverters mit seiner Ausgabe – im jeweils anderen Format – zu vergleichen. Hierzu können Sie das Programm `swlab1-validator-LATEST.jar` verwenden.

Platzieren Sie die `.ct`-Datei, die `.txt`-Datei und die `.bin`-Datei, die geprüft werden sollen, gemeinsam in ein Verzeichnis. Die `.txt`-Datei und die `.bin`-Datei müssen den gleichen Dateinamen besitzen (vgl. Beispieldaten).

Starten Sie das Programm durch den Aufruf `java -jar swlab1-validator-LATEST.jar` in einer Kommandozeile. Es erscheint eine grafische Oberfläche.

* Klicken Sie auf den oberen Laden-Button und wählen Sie die zu prüfende `.txt`-Datei.
* Klicken Sie auf den unteren Laden-Button und wählen Sie die zu prüfende `.ct`-Datei.
* Klicken Sie auf den Start-Button.

Verlief die Konvertierung der CT-Daten durch den Konverter erfolgreich, so sind die jeweils eingelesenen Datensätze identisch und das Fenster wird *grün*.

Gibt es ein Problem bei der Konvertierung, d. h. die beiden Datensätze unterscheiden sich, wird das Fenster `rot` und es erscheint ein Hinweis, in welchem Teil der Daten (z.B. Name, ID) ein Unterschied zwischen den zwei CT-Datensätzen gefunden wurde. Der Hinweis *Voxel* bedeutet, dass ein Unterschied bei den Intensitätswerten der Bilder festgestellt wurde.

## Anhänge

Der Aufgabenstellung liegt Folgendes bei:

* Beispieldateien:
    * `dataViewer1.ct` -- Beispiel einer textbasierten Datei mit den kompletten Daten eines CT-Datensatzes.
    * `dataViewer2.txt` -- Beispiel einer textbasierten Datei mit den Metadaten eines CT-Datensatzes.
    * `dataViewer2.bin` -- Beispiel einer Binärdatei mit den Grauwerten der Bildserie eines CT-Datensatzes.


# Abgabe

Für das erfolgreiche Bestehen dieser Aufgabe sind folgende Punkte zu erfüllen:

1. Ein vollständiges Apache Maven Multi Module Project, welches Ihr Logging-Framework sowie den entwickelten CT-Datenkonverter enthält.
2. Eine vollständig und korrekte Umsetzung eines CT-Datenkonverters sowie der entsprechenden Interfaces gemäß der oben beschriebenen Anforderungen.
3. JUnit Tests, welche die Funktionalität der zu entwickelten Klassen demonstrieren und testen. Diese Tests müssen über das Maven Surefire Plugin während eines Maven Builds automatisch ausgeführt werden.
4. Kompilier- und lauffähiger Java-Code sowie Einhaltung der *Java Naming Conventions* und sinnvolles *Exception-Handling* sowie Dokumentation (Javadoc).
