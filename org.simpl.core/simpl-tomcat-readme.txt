SIMPL Tomcat Deployment Package v1.0.2

= Content =
SIMPL Core
SIMPL Core Web Services
SIMPL Core Plug-Ins
SIMPL ODE Extensions
SIMPL RRS (Reference Resolution System)
SIMPL RRS Transformation Service
SIMPL Resource Management
SIMPL Resource Management Web Interface
SIMPL Resource Discovery

= Requirements =
Apache Tomcat 5.5/6.0 (http://tomcat.apache.org/)
PostgreSQL 8.4 (http://www.postgresql.org/)

= Installation =
1) Install Apache Tomcat. It is VERY IMPORTANT to choose a path without any spaces, and do not start it yet.
2) Install PostgreSQL
3) Extract the simpl-tomcat.zip to the Tomcat root directory (/Tomcat x.x/). Overwrite existing files and folders.
4) Customize the configuration files and start Apache Tomcat (see chapter Configuration Files)
5) Open the Resource Management Webinterface and install it (see chapter Links)

= Configuration Files =
Resource Management: webapps\axis2\WEB-INF\conf\simpl-resource-management-config.xml
Resource Management Webinterface: webapps\rmweb\WEB-INF\conf\rmweb-config.xml
RRS: webapps\axis2\WEB-INF\conf\rrs-config.xml

= Login =
The user for Apache Tomcat and thus the Resource Management Web Interface is "admin", no password is set.

= Links =
SIMPL Core Web Services: http://localhost:8080/ode/services/listServices
SIMPL RRS, Resource Management and Resource Discovery Web Services: http://localhost:8080/axis2/services/listServices
SIMPL Resource Management Web Interface: http://localhost:8080/rmweb

= Problems =
On memory problems try to add "-XX:MaxPermSize=256m" to the Apache Tomcat 
windows webservice java options (Apache Monitor -> Configure... -> Java -> Java Options).

On problems with the SIMPL Core web service WSDL generation, copy the "tools.jar" from your java SDK installation 
(e.g. C:\Programme\Java\jdk1.6.0_xx\lib\) to the Apache Tomcat "webapps\ode\WEB-INF\lib\" directory.

If the web services tell you something about JAXBContext you might have Apache Tomcat installed in a path 
including spaces, that unfortunately affects the JAX-WS web service generation.