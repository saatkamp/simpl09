# Entwicklungsumgebung #

## Installationsdateien ##
  * _TortoiseSVN-1.6.4.16808-win32-svn-1.6.4.msi_, SVN Integration für Windows, http://tortoisesvn.tigris.org/
  * _jdk-6u15-windows-i586.exe_, Java Development Kit, Java-Entwicklungswerkzeuge + Runtime-Environment, http://java.sun.com/
  * _apache-tomcat-6.0.20.exe_ - Apache Tomcat, Web Server zur Ausführung von Java-Code, http://tomcat.apache.org/
  * _apache-maven-2.1.0-bin.zip_ - Apache Maven, Java Build-Management-Tool, http://maven.apache.org/
  * _apache-ode-war-1.3.2.zip_ - Apache ODE, BPEL Engine, http://ode.apache.org/
  * _axis2-1.5-war.zip_ - Apache Axis2 für Tomcat
  * _apache-tuscany-sdo-1.1.1.zip_ - Apache Tuscany SDO, Service Data Object, http://tuscany.apache.org/
  * _tuscany-das-1.0-incubating-beta2-bin.zip_ - Apache Tuscany DAS, Data Access Service, http://tuscany.apache.org/
  * _eclipse-java-galileo-SR1-win32.zip_ - Eclipse IDE for Java Developers, http://eclipse.org/
  * _eclipse\_cleanup.xml_ - Eclipse Code Clean Up Einstellungen
  * _eclipse\_formatter.xml_ - Eclipse Code Formatter Einstellungen

## 1 Installation ##
### Tortoise ###
_TortoiseSVN-1.6.4.16808-win32-svn-1.6.4.msi_ ausführen und Installationsanweisungen folgen.

### Java Development Kit ###
_jdk-6u15-windows-i586.exe_ ausführen und Installationsanweisungen folgen.

### Apache Tomcat ###
_apache-tomcat-6.0.20.exe_ ausführen und Installationsanweisungen folgen.

Installationsverzeichnis: C:\Programme\Apache\Tomcat 6.0

### Apache Maven ###
_apache-maven-2.1.0-bin.zip_ in Programmverzeichnis entpacken nach z.B. _C:\Programme\Apache\apache-maven-2.1.0_

### Apache ODE ###
_ode.war_ aus der _apache-ode-war-1.3.2.zip_ nach _C:\Programme\Apache\Tomcat 6.0\webapps_ kopieren

### Apache Axis2 ###
_axis2.war_ aus der _axis2-1.5-war.zip_ nach _C:\Programme\Apache\Tomcat 6.0\webapps_ kopieren

Verzeichnis _/servicejars_ unter _C:\Programme\Apache\Tomcat 6.0\webapps\axis2\WEB-INF_ anlegen.

### Apache Tuscany DAS ###
_tuscany-das-1.0-incubating-beta2-bin.zip_ nach z.B. C:\Programme\Apache\tuscany-das-1.0-incubating-beta2 entpacken.

### Apache Tuscany SDO ###
_apache-tuscany-sdo-1.1.1.zip_ nach z.B. C:\Programme\Apache\tuscany-sdo-1.1.1 entpacken.

### Eclipse ###
_eclipse-java-galileo-win32.zip_ entpacken nach z.B. _C:\Programme\eclipse_


## 2 Eclipse Setup ##
### 2.1 Updates ###
Help -> Check for Updates

### 2.2 Plugins ###
Help -> Install New Software -> Add Site

Name und Update-URL des Plugins eintragen, die erscheinenden Punkte auswählen und auf _Install_ klicken.
  * BPEL Designer: http://swordfish-tooling.googlecode.com/svn/trunk/org.eclipse.swordfish.third-parties/bpel/
  * Subclipse: http://subclipse.tigris.org/update_1.6.x
  * Maven: http://m2eclipse.sonatype.org/update/, Nach Auflistung "Maven Integration for AJDT (Optional)" sofort abwählen!
  * Mylyn: http://download.eclipse.org/tools/mylyn/update/incubator, Nur "Mylyn Connector: Web Templates (Advanced)" auswählen.
  * Quantum: http://quantum.sourceforge.net/update-site
  * Visual Editor: http://download.eclipse.org/tools/ve/updates/1.4/, Nur "Visual Editor" auswählen.
  * SOAP UI: http://www.soapui.org/eclipse/update
  * Eclipse SDK: http://download.eclipse.org/eclipse/updates/3.5, "Eclipse Platform SDK" auswählen
  * Eclipse EMF: http://download.eclipse.org/modeling/emf/updates/releases/, "EMF SDK 2.5.0 (EMF + XSD)" auswählen

### 2.3 Tools ###
Help -> Install New Software -> Work with: Galileo - http://download.eclipse.org/releases/galileo

-> Web, XML, and Java EE Development -> Eclipse Web Developer Tools -> Next -> Finish

### 2.4 Einstellungen ###

#### Apache Tomcat ####
File -> New -> Other -> Server (Ordner) -> _Server_ auswählen -> _Apache Tomcat 6 Server_ auswählen -> Next
```
Tomcat installation directory: C:\Programme\Apache\Tomcat 6.0
```

#### Apache ODE ####
File -> New -> Other -> Server (Ordner) -> _Server_ auswählen -> _Ode v1.x Server_ auswählen -> Next
```
ODE's home directory: C:\Programme\Tomcat 6.0\webapps\ode
Tomcat's home directory: C:\Programme\Apache\Tomcat 6.0
```

#### Apache Axis2 ####
External Tools Configuration -> New Program
```
Name: wsgen
Location: C:\Programme\Java\jdk1.6.0_15\bin\wsgen.exe
Working Directory: ${project_loc}
Arguments: -cp "${project_loc}\bin" ${java_type_name} -wsdl -servicename {http://localhost:8080}${string_prompt} -r ${project_loc}
```

```
Name: wsimport
Location: C:\Programme\Java\jdk1.6.0_15\bin\wsimport.exe
Working Directory: ${project_loc}
Arguments: -keep -s "${project_loc}\src" -d "${project_loc}\bin" -p [PACKAGE NAME] -B-XautoNameResolution [URL TO WSDL]
```

#### Apache Tuscany ####
Window -> Preferences -> Java -> Build Path -> User Libraries

-> New -> _TUSCANY SDO_ eingeben -> OK -> Add JARs -> _C:\Programme\Apache\tuscany-sdo-1.1.1\lib_ alle JARs auswählen und öffnen
-> New -> _TUSCANY DAS_ eingeben -> OK -> Add JARs -> _C:\Programme\Apache\tuscany-das-1.0-incubating-beta2\lib_ alle JARs auswählen und öffnen

##### JDK #####
Eclipse muss für das Maven-Plugin mit dem JDK starten. Dazu kann man entweder die eclipse.ini anpassen oder eine angepasste Verknüpfung zu Eclipse erstellen.

**C:\eclipse\eclipse.ini**

Oben einfügen
```
-vm 
C:\Programme\Java\jdk1.6.0_15\bin
```

**Verknüpfung zu eclipse.exe**
```
Ziel: C:\eclipse\eclipse.exe -vm C:\Programme\Java\jdk1.6.0_15\bin
```

#### Web Services ####
Window -> Preferences -> Web Services -> Server und Runtime
```
Server: Tomcat v1.6 Server
Web service runtime: Apache Axis2
```

#### Google Code Issue Task List ####
Window -> Show View -> Task List

Window -> Show View -> Other -> Tasks -> Task Repositories

Rechter Mausklick in Task Repositories -> Add Task Repository -> _Web Template (Advanced)_ auswählen -> Next -> _Eclipse Outliner (Google Code)_ auswählen und folgendes ändern:

```
Server: http://code.google.com/p/simpl09/issues
Anonymous: true
```

Die Frage ob eine Query erstellt werden soll mit _Ja_ beantworten.

Advanced Configuration -> Query Pattern

Für den Entwickler reicht es lediglich die eigenen Tasks zu sehen, dazu kann die Query folgendermaßen angepasst werden.

```
{Owner}

ersetzen durch

{Deine Email bei Google Code (bei Gmail nur den Benutzernamen verwenden!)}
```

-> Finish

#### Code Style ####
Window -> Preferences -> Java -> Code Style -> Clean Up -> Import: _eclipse\_cleanup.xml_<br>
Window -> Preferences -> Java -> Code Style -> Formatter -> Import: <i>eclipse_formatter.xml</i>

<h2>3 Windows Einstellungen</h2>
<h3>3.1 Umgebungsvariablen</h3>
Systemeigenschaften -> Erweitert -> Umgebungsvariablen -> Systemvariablen<br>
<br>
<b>JAVA_HOME</b> (Neu)<br>
<pre><code>C:\Programme\Java\jdk1.6.0_15<br>
</code></pre>

<b>M2</b> (Neu)<br>
<pre><code>%M2_HOME%\bin<br>
</code></pre>

<b>M2_HOME</b> (Neu)<br>
<pre><code>C:\Programme\Apache\apache-maven-2.1.0<br>
</code></pre>

<b>PATH oder Path</b> (Bearbeiten)<br>
<pre><code>;%M2%;%JAVA_HOME%\bin<br>
</code></pre>

<blockquote><i>bei Problemen %M2% ersetzen</i></blockquote>

<pre><code>;C:\Programme\Apache\apache-maven-2.1.0\bin;%JAVA_HOME%\bin<br>
</code></pre>

Bei Problemen mit Maven Build, kann mit MAVEN_OPTS der Speicher erhöht werden.<br>
<br>
<b>MAVEN_OPTS</b> (Neu)<br>
<pre><code>-Xmx512m<br>
</code></pre>

<h2>4 Test</h2>
<h3>4.1 Umgebungsvariablen</h3>
Kommandozeile (cmd.exe)<br>
<pre><code>java<br>
mvn -v<br>
</code></pre>
Befehle müssen gefunden werden.<br>
<br>
<h3>4.2 Maven Testprojekt</h3>
<h4>In Eclipse</h4>
File -> New -> Other -> Maven -> Maven Project -> Next -> Next<br>
<pre><code>Group Id: test<br>
Artifact Id: test<br>
</code></pre>

<h4>In Kommandozeile</h4>
<pre><code>mvn archetype:create -DgroupId=test -DartifactId=test<br>
</code></pre>