@echo off
REM org.simpl.resource.framework
copy /Y "C:\Tomcat6.0\webapps\ode\WEB-INF\lib\simpl-core-webservices-client.jar" "C:\Tomcat6.0\webapps\axis2\WEB-INF\lib"

REM org.eclipse.simpl.communication
copy /Y "C:\Tomcat6.0\webapps\ode\WEB-INF\lib\simpl-core-webservices-client.jar" "C:\eclipse-new\workspace\org.eclipse.simpl.communication\lib"

REM org.simpl.resource.management.webinterface
copy /Y "C:\Tomcat6.0\webapps\ode\WEB-INF\lib\simpl-core-webservices-client.jar" "C:\eclipse-new\workspace\org.simpl.resource.management.webinterface\WebContent\WEB-INF\lib"