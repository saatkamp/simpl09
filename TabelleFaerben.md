# Einleitung #

Diese Seite liefert einen kurzen Überblick für das Einfärben von Tabellen in LyX bzw. LaTeX und einige Beispiele dazu.

# Einfärben von Tabellen #
## Mit dem Paket colortbl ##

  1. Im LATEX-Vorspann unter Dokument->Einstellungen->LATEX-Vorspann die Paketeinbindung \usepackage{colortbl} hinzufügen.
  1. In der gewünschten Zelle in einer TEX-Code-Umgebung (Schalter TEX drücken oder im Menü: Einfügen->TEX-Code oder Tastenkürzel: STRG+L) einen der folgenden LATEX-Befehle eingeben:
    * \rowcolor{Farbname}: Füllt die gesamte Zeile mit der entsprechenden Farbe (siehe Tabelle 1 Zeile 1)
    * \cellcolor{Farbname}: Füllt eine Zelle mit der entsprechenden Farbe (siehe Tabelle 1 Zelle 2 in Spalte 1)
  1. Um ganze Spalten einzufärben muss man die Spalte mit all ihren Zellen markieren und dann per Rechtsklick auf Einstellungen im Tab “Tabellen-Einstellungen” muss im Textfeld “LATEX-Argument” folgendes eingegeben werden: >{\columncolor{Farbnahme}}Ausrichtung
    * >{}: Sorgt dafür das der darin enthaltene Befehl vor der Definition der Spalte ausgeführt wird
    * \columncolor{Farbnahme}: Füllt die gesamte Spalte mit der entsprechenden Farbe (siehe Tabelle 1 Spalte 2)
    * Ausrichtung: Textausrichtung {l,c,r}, wobei “l” für links, “r” für rechts und “c” für zentriert steht
  1. Bis jetzt können nur die Standardfarbwerte red, green, yellow, blue, cyan, magenta, black und white benutzt werden, möchte man andere Farben benutzen, muss man diese im Latex-Vorspann (siehe oben) definieren:
    * Der Befehl \definecolor{Farbname}{Farbmodell}{Farbwerte} definiert eine neue Farbe mit dem Namen, der bei Farbname angegeben wird
    * Das Farbmodell kann eins der folgenden sein:
      * cmyk: cyan, magenta, yellow, black
      * rgb: red, green blue
      * gray: gray
    * Farbwerte sind durch Komma getrennte Zahlen zwischen 0 und 1, die die entsprechende Farbe für das Farbmodell beschreiben
    * z.B. \definecolor{grau}{rgb}{0.5,0.5,0.5} (siehe Tabelle 1 Zelle 3 in Spalte 1)
    * oder \definecolor{dunkelgruen}{cmyk}{0.5, 0, 1, 0.5} (siehe Tabelle 1 Zelle 4 in Spalte 1)
    * oder \definecolor{hellgrau}{gray}{0.8} (siehe Tabelle 1 Zelle 5 in Spalte 1)
  1. Die grau/hellgrau Darstellung könnte dann einfach mit der abwechselnden Angabe von \rowcolor{grau} und \rowcolor{hellgrau} (siehe Tabelle 1 Zeile 6 bis 9)

http://groups.google.com/group/simpl09/web/Tabelle1.PNG

## Mit dem Paket xcolor ##

  1. Eine alternative Möglichkeit mit einem Befehl automatisch die abwechselnden Farben anzugeben, liefert das xcolor Paket. Es wird auch im LATEXVorspann eingebunden mit \usepackage [[table](table.md)]{xcolor}
  1. Dann muss nur am Anfang der Gleitobjekt-Umgebung, d.h. vor die Tabelle beispielweise folgender TEX-Code eingefügt werden: \rowcolors{2}{gray!35}{white}
    * Die 2 steht für die Zeilennummer der Tabelle, ab der zum ersten mal gefärbt wird, d.h. in diesem Fall ist Zeile 2 die erste eingefärbte Zelle der Tabelle
    * gray!35 steht für die Farbe Grau mit 35% Opazität/Antitransparenz und färbt alle geraden Zeilennummern
      * Die Opazität kann dabei von 0 bis 100 angegeben werden: 0 steht für völlig transparent (0% Farbe) und 100 für völlig opak/undurchsichtig (100% Farbe)
      * die Angabe keines Opazitätswertes bedeutet immer 100%, also die volle Farbintensität.
    * white steht hier für die Farbe aller ungeraden Zeilennummern
  1. Farben werden genauso wie in Abschnitt 1 definiert

http://groups.google.com/group/simpl09/web/Tabelle2.PNG