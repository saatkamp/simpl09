### Webservice erstellen ###
File -> New -> Java Project
```
Project name: Test
```

Projekt _Test_ auswählen -> File -> New -> Class
```
Name: TestService
```

TestService.java
```
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name="TestService", targetNamespace="http://localhost:8080/TestService", wsdlLocation="TestService.wsdl")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public class TestService {
  public String sayTest() {
    return "Test";
  }
}
```

_TestService.java_ auswählen -> External Tools -> _wsgen_
```
Please input a value: TestService
```
-> Projekt aktualisieren (F5)

### Webservice deployen ###
Beim Deployen wird das Projekt als JAR-Datei direkt in die Axis2 Laufzeitumgebung exportiert.

**JAR Export das erste mal einrichten**

Projekt auswählen -> File -> Export -> Java/JAR file -> alle _.wsdl_ und _.java_ auswählen
```
JAR file: C:\Programme\Apache\Tomcat 6.0\webapps\axis2\WEB-INF\servicejars\Test.jar
Overwrite existing files without warning: aktivieren
```
-> Next
```
Save the description of this JAR in the workspace: aktivieren
Description file: /Test/deploy.jardesc
```
-> Finish

**anschließend vereinfachter Export**

Rechter Mausklick auf deploy.jardesc -> Create JAR

### Webservice testen ###
Tomcat Server neustarten falls ein bestehender Webservice aktualisiert wurde. Neue Services können Hot-deployt werden.

http://localhost:8080/axis2/services/listServices

### Webservice Client erzeugen ###
Projekt auswählen -> External Tools -> _wsimport_
-> Projekt aktualisieren (F5)
-> Der Client befindet sich im Paket PACKAGE\_NAME, der als Argument angegeben wurde (siehe [Entwicklungsumgebung](http://code.google.com/p/simpl09/wiki/Entwicklungsumgebung)).