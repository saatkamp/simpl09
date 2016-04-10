# Maven #
Informationen zu Maven und Beschreibung der wichtigsten Befehle.

## Maven Lifecycle ##
  * **validate**: validate the project is correct and all necessary information is available
  * **compile**: compile the source code of the project
  * **test**: test the compiled source code using a suitable unit testing framework. These tests should not require the code be packaged or deployed
  * **package**: take the compiled code and package it in its distributable format, such as a JAR.
  * **integration-test**: process and deploy the package if necessary into an environment where integration tests can be run
  * **verify**: run any checks to verify the package is valid and meets quality criteria
  * **install**: install the package into the local repository, for use as a dependency in other projects locally
  * **deploy**: done in an integration or release environment, copies the final package to the remote repository for sharing with other developers and projects.
http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html

## Befehle ##
Die Befehle werden mit mvn(.bat) ausgeführt und können aneinandergereiht werden wie z.B. mvn clean package für eine saubere Neuerstellung eines Builds.

### Allgemein ###
| **Befehl** | **Beschreibung** |
|:-----------|:-----------------|
| {lifecycle-phase} | Durchläuft alle Phasen inklusive der angegebenen {lifecycle-phase}|
| clean      | Löscht alle Build Artefakte |
| -Declipse.workspace={path-to-eclipse-workspace} eclipse:add-maven-repo | Maven Repository bei Eclipse bekannt machen |
| -Dmaven.test.skip=true | Tests deaktivieren |

### Archetype Plugin ###
| **Befehl** | **Beschreibung** |
|:-----------|:-----------------|
| archetype:create -DgroupId={group-id} -DartifactId={artifact-id} | Erstellt das Maven Projekt {artifact-id} mit dem Namen {groupd-id} im Eclipse Workspace |

http://maven.apache.org/guides/mini/guide-ide-eclipse.html

### Dependency Plugin ###
| **Befehl** | **Beschreibung** |
|:-----------|:-----------------|
| dependency:resolve | Zur Überprüfung der Abhängigkeiten werden diese aufgelöst und angezeigt |

http://maven.apache.org/plugins/maven-dependency-plugin/howto.html

### Tomcat Plugin ###
| **Befehl** | **Beschreibung** |
|:-----------|:-----------------|
| tomcat:deploy | Deployment eines WAR-Projekts beim Tomcat |
| tomcat:redeploy | Überschreibt ein breits vorhandenes WAR-Projekt |
| tomcat:run | Startet das WAR-Projekt |

http://mojo.codehaus.org/tomcat-maven-plugin/deployment.html

## Repositories ##
  * http://mvnrepository.com/artifact/mysql/mysql-connector-java
  * http://repo1.maven.org/maven2/org/apache/tuscany/
  * http://people.apache.org/repo/m2-incubating-repository
  * http://people.apache.org/~antelder/tuscany/2.0-M3-RC3/maven/org/apache/tuscany/sca/

## Dependencies ##

### MySQL Connector ###
```
<dependency>
   <groupId>mysql</groupId>
   <artifactId>mysql-connector-java</artifactId>
   <version>5.1.6</version>
</dependency>
```

## Optimierungen ##

### Speichereinstellung ###
Bei Speicherproblemen mit Maven, kann mit der Systemumgebungsvariable MAVEN\_OPTS der Speicher erhöht werden.

Systemeigenschaften -> Erweitert -> Umgebungsvariablen -> Systemvariablen

**MAVEN\_OPTS** (Neu)
```
-Xmx1024m -Xms512m
```

### Sources & JavaDoc ###
Sollte Eclipse ständig vergeblich versuchen Sourcen und Javadoc herunterzuladen, kann man das ganz einfach deaktivieren.

Window -> Preferences -> Maven
```
Download Artifact Sources: deaktivieren
Download Artifact JavaDoc: deaktivieren
```