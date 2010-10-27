= Content =
SIMPL Core
SIMPL Core Plugins
SIMPL ODE Extensions
SIMPL RRS
SIMPL RRS Transformation Service
SIMPL Resource Framework
SIMPL Resource Framework Web Interface

= Requirements =
Apache Tomcat 5.5/6.0

= Installation =
Extract the simpl-tomcat.zip to the Tomcat root directory (/Tomcat x.x/).

= Login =
The user for Apache Tomcat and thus the resource framework web interface is "admin", no password is set.

= Links =
SIMPL Core Web Service List: http://localhost:8080/ode/services/listServices
SIMPL RRS Web Service List: http://localhost:8080/axis2/services/listServices
SIMPL Resource Framework: http://localhost:8080/ode/processes/ResourceFrameworkService.ResourceFrameworkPort?wsdl
SIMPL Resource Framework Web Interface: http://localhost:8080/rfweb

= Problems =
On memory problems try to add "-XX:MaxPermSize=256m" to the Apache Tomcat 
windows webservice java options (Apache Monitor -> Configure... -> Java -> Java Options).

On problems with the SIMPL Core web service WSDL generation, copy the "tools.jar" from your java SDK installation 
(e.g. C:\Programme\Java\jdk1.6.0_xx\lib\) to the Apache Tomcat "webapps\ode\WEB-INF\lib\" directory.