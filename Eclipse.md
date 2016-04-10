# Eclipse #
Hier befinden sich alle zusätzlichen Informationen und Einstellungen zum bequemen und effektiven Arbeiten mit Eclipse.

## Einstellungen ##

### SVN Labels ###
Die SVN Labels neben den Dateinamen im Package Explorer können angepasst werden.

Window -> Preferences -> Team -> SVN -> Label Decorations

Bsp: Nur Dateinamen anzeigen
```
File Format: {added_flag}{dirty_flag}{name}
Folder Format: {external_flag}{added_flag}{dirty_flag}{name}
Project Format: {dirty_flag}{name}
```

### SVN Menüleiste ###
Window -> Customize Perspective -> Command Groups Availability -> SVN aktivieren<br>
Window -> Customize Perspective -> Menu Visibility -> SVN aktivieren<br>
<br>
<h3>Code Folding</h3>
Das automatische Zusammenfalten von Code kann deaktiviert werden.<br>
<br>
Window -> Preferences -> Java -> Editor -> Folding -> <i>Enable folding</i> deaktivieren<br>
<br>
<h3>Source Clean Up</h3>
Um den Code im gesamten Projekt nach dem Code Style zu formatieren und zusätzlich bestimmte Konventionen zu erzwingen, kann über das gesamte Projekt ein Clean Up gemacht werden. Anschließend öffnet sich ein Fenster in dem die Änderungen in einer Gegenüberstellung (wie in SVN) gezeigt werden, bevor sie übernommen werden können.<br>
<br>
Source -> Clean Up<br>
<br>
<h2>HotKeys</h2>
<i>Strg + Shift + F</i><br>
Im Editor: Formatierung des Codes nach eingestelltem Code Style.<br>
<br>
<i>Strg + Shift + O</i><br>
Im Editor: Organisierung der Imports innerhalb der Klasse (Interface).<br>
Auf ausgewähltes Projekt: Organisierung der Imports über das ganze Projekt.<br>
<br>
<i>Strg + Shift + G</i><br>
Auf ausgewählte Funktion: Liefert eine Liste mit allen Stellen wo die Funktion verwendet wird.<br>
<br>
<h2>Optimierungen</h2>

<h3>Speichereinstellung</h3>
Wer viel Speicher hat, kann Eclipse noch mit folgenden Einstellungen beschleunigen.<br>
<br>
<b>C:\eclipse\eclipse.ini</b>
<pre><code>-Xms512m<br>
-Xmx512m<br>
-XX:PermSize=128M<br>
-XX:MaxPermSize=128M<br>
</code></pre>

<h3>Author</h3>
Bei der Erstellung von Kommentaren wird standardmäßig der Windows Benutzername als @author verwendet. In der eclipse.ini kann man diesen beliebig anpassen.<br>
<br>
<b>C:\eclipse\eclipse.ini</b>
<pre><code>-Duser.name=Max Mustermann &lt;max.mustermann@muster.de&gt;<br>
</code></pre>