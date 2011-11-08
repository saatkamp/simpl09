@echo off
REM org.simpl.core
copy /Y "C:\Tomcat6.0\webapps\axis2\WEB-INF\lib\simpl-resource-management-client.jar" "C:\Tomcat6.0\webapps\ode\WEB-INF\lib"

REM org.simpl.resource.management.webinterface
copy /Y "C:\Tomcat6.0\webapps\axis2\WEB-INF\lib\simpl-resource-management-client.jar" "C:\Workspaces\workspace_simpl\org.simpl.resource.management.webinterface\WebContent\WEB-INF\lib"

REM org.eclipse.simpl.resource.management.communication
copy /Y "C:\Tomcat6.0\webapps\axis2\WEB-INF\lib\simpl-resource-management-client.jar" "C:\Workspaces\workspace_simpl\org.eclipse.simpl.communication\lib"