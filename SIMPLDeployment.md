# Deployment #
Hier werden die Schritte erklärt, die für das Deployment (die Installation) des SIMPL Rahmenwerks notwendig sind.

## Dateien ##
Die Dateien des SIMPL Rahmenwerks werden in [Downloads](http://code.google.com/p/simpl09/downloads/list) bereitgestellt.

#### simpl-tomcat.zip ####
Enthält Apache ODE mit dem SIMPL Core und seinen Web Services, sowie die SIMPL Extension Activities, Konfigurationsdateien und weitere abhängige Libraries. Außerdem enthalten sind noch Apache jUDDI als Datenquellen-Registry, ein Web-Interface zur Verwaltung dieser und Apache Axis2 als Service-Umgebung für das ebenfalls mitgelieferte Reference Resolution System.

```
bin\log4j.properties
common\lib\derby-10.4.1.3.jar
conf\tomcat-users.xml
lib\derby-10.4.1.3.jar
webapps\axis2\*
webapps\juddiv3\*
webapps\juddiweb\*
webapps\ode\*
```

#### deployment\simpl-eclipse.zip ####
Enthält alle Plug-Ins des erweiterten Eclipse BPEL Designers und alle SIMPL Eclipse Plug-Ins.

```
Eclipse BPEL Designer:
plugins\org.eclipse.bpel.apache.ode.deploy.model_0.4.0.jar
plugins\org.eclipse.bpel.apache.ode.deploy.ui_0.4.0.jar
plugins\org.eclipse.bpel.apache.ode.runtime_0.4.0.jar
plugins\org.eclipse.bpel.common.model_0.4.0.jar
plugins\org.eclipse.bpel.common.ui_0.4.0.jar
plugins\org.eclipse.bpel.model_0.4.0.jar
plugins\org.eclipse.bpel.runtimes_0.4.0.jar
plugins\org.eclipse.bpel.ui_0.4.0.jar
plugins\org.eclipse.bpel.validator.junit_1.0.0.jar
plugins\org.eclipse.bpel.validator_0.4.0.jar
plugins\org.eclipse.bpel.wsil.model_0.4.0.jar
plugins\org.eclipse.bpel.xpath10_0.4.0.jar

SIMPL:
plugins\org.eclipse.bpel.simpl.model_0.1.2.jar
plugins\org.eclipse.bpel.simpl.ui.sql_0.1.4.jar
plugins\org.eclipse.bpel.simpl.ui_0.1.4.jar
plugins\org.eclipse.simpl.communication_0.1.0.jar
plugins\org.eclipse.simpl.core.auditing_0.1.1.jar
plugins\org.eclipse.simpl.core_0.1.1.jar
plugins\org.eclipse.simpl.rrs.transformation_0.1.0.jar
plugins\org.eclipse.simpl.rrs.ui_0.1.0.jar
plugins\org.eclipse.simpl.uddi_0.1.0.jar
```


---


## Eclipse ##
### 1. Eclipse installieren ###
  * Herunterladen der Eclipse IDE for Java EE Developers von http://www.eclipse.org/.
  * Entpacken des Archivs, z.B. nach C:\eclipse

### 2. SIMPL Eclipse Bundle installieren ###
  * Herunterladen des **simpl-eclipse.zip** Archivs von http://code.google.com/p/simpl09/downloads/list.
  * Entpacken des SIMPL Eclipse Bundles in das Eclipse-Verzeichnis (z.B. nach C:\eclipse\)

-> Eclipse erst nach der Apache Tomcat Installation wieder starten.


---


## Apache Tomcat ##
### 1. Apache Tomcat installieren (5.5 oder 6.x) ###
http://tomcat.apache.org/


### 2. SIMPL Tomcat Bundle installieren ###
  * Herunterladen des **simpl-tomcat.zip** Archivs von http://code.google.com/p/simpl09/downloads/list.
  * Entpacken des SIMPL Tomcat Bundles in das Tomcat-Verzeichnis (z.B. C:\Tomcat\)
  * Mit entpackte _readme.txt_ für weitere Anweisungen/Informationen lesen

-> Tomcat starten

-> Prüfen ob die SIMPL Core Services aktiviert sind: http://localhost:8080/ode/services/listServices


---


## Probleme und Lösungen ##

1. Die SIMPL Web Services stehen zwar bereit, die WSDLs können aber nicht generiert und benutzt werden.
  * Für die Generierung wird die _tools.jar_ aus dem Java Development Toolkit (JDK) benötigt. Diese wird aus dem JDK 1.6.0\_14 in der _simpl-tomcat.zip_ mitgeliefert. Je nachdem welche Version des Java Runtime Environment (JRE) auf dem Betriebssystem installiert ist, kann es unter Umständen nötig sein die _tools.jar_ aus der entsprechend anderen Version des JDK zu verwenden. Diese muss unter _/Tomcat x.x/webapps/ode/WEB-INF/lib/_ die bereits vorhandene _tools.jar_ ersetzen.

2. Der BPEL Eclipse Designer kann nicht geöffnet werden, ein Fehler wird angezeigt
  * Apache Tomcat ist nicht gestartet
  * Die _simpl-tomcat.zip_ wurde nicht deployt
  * Die _simpl-eclipse.zip_ wurde nicht deployt
  * Es besteht Problem Nr. 1