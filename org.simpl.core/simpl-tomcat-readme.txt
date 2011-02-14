= Content =
SIMPL Core
SIMPL Core Plug-Ins
SIMPL ODE Extensions
SIMPL RRS (Reference Resolution System)
SIMPL RRS Transformation Service
SIMPL Resource Management
SIMPL Resource Management Web Interface

= Requirements =
Apache Tomcat 5.5/6.0 (http://tomcat.apache.org/)
PostgreSQL 8.4 (http://www.postgresql.org/)

= Installation =
Extract the simpl-tomcat.zip to the Tomcat root directory (/Tomcat x.x/).

= Configuration =
Resource Management: webapps\axis2\WEB-INF\conf\simpl-resource-management-config.xml
Resource Management Webinterface: webapps\rmweb\WEB-INF\conf\rmweb-config.xml
RRS: webapps\axis2\WEB-INF\conf\rrs-config.xml

= Login =
The user for Apache Tomcat and thus the Resource Management Web Interface is "admin", no password is set.

= Links =
SIMPL Core Web Services: http://localhost:8080/ode/services/listServices
SIMPL RRS and Resource Management Web Services: http://localhost:8080/axis2/services/listServices
SIMPL Resource Management Web Interface: http://localhost:8080/rmweb

= Problems =
On memory problems try to add "-XX:MaxPermSize=256m" to the Apache Tomcat 
windows webservice java options (Apache Monitor -> Configure... -> Java -> Java Options).

On problems with the SIMPL Core web service WSDL generation, copy the "tools.jar" from your java SDK installation 
(e.g. C:\Programme\Java\jdk1.6.0_xx\lib\) to the Apache Tomcat "webapps\ode\WEB-INF\lib\" directory.