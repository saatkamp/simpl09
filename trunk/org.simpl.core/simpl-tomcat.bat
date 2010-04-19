@echo off
REM PARAMETER
REM   %1: Tomcat Verzeichnis
REM   %2: Eclipse org.simpl.core Projekt Verzeichnis
REM   %3: Deployment Verzeichnis
REM ANMERKUNG
REM   Bei Verzeichnissen mit Leerzeichen muss die Kurzform genommen werden z.B. C:\TOMCAT~1.0 anstelle von C:\Tomcat 6.0
REM ECLIPSE EXTERNAL TOOLS BEISPIEL
REM   Location: ${workspace_loc:/org.simpl.core/simpl-tomcat.bat}
REM   Working Directory: ${workspace_loc:/org.simpl.core}
REM   Arguments: C:\TOMCAT~1.0 C:\eclipse\workspace C:\SIMPL\deployment

@echo on
copy "%1\webapps\ode\WEB-INF\servicejars\simpl-core-webservices.jar" %3\webapps\ode\WEB-INF\servicejars
copy "%1\webapps\ode\WEB-INF\lib\simpl-core.jar" %3\webapps\ode\WEB-INF\lib
copy "%1\webapps\ode\WEB-INF\lib\simpl-core-plugins.jar" %3\webapps\ode\WEB-INF\lib
copy "%1\webapps\ode\WEB-INF\lib\simpl-ode-ea.jar" %3\webapps\ode\WEB-INF\lib
copy "%1\webapps\ode\WEB-INF\lib\simpl-uddi-client.jar" %3\webapps\ode\WEB-INF\lib
copy "%1\webapps\ode\WEB-INF\lib\tools.jar" %3\webapps\ode\WEB-INF\lib
copy %2\org.simpl.core\lib\*.* %3\webapps\ode\WEB-INF\lib
copy %2\org.simpl.uddi.client\lib\*.* %3\webapps\ode\WEB-INF\lib
del %3\webapps\ode\WEB-INF\lib\log4j-1.2.15.jar
del %3\webapps\ode\WEB-INF\lib\derby-10.4.1.3.jar
copy %2\org.simpl.core\log4j.properties %3\
copy %2\org.simpl.core\simpl-core-config.xml %3\webapps\ode\WEB-INF\conf
copy "%1\webapps\ode\WEB-INF\conf\axis2.xml" %3\webapps\ode\WEB-INF\conf

C:\Programme\7-Zip\7z.exe a -r "C:\Dokumente und Einstellungen\admin\Eigene Dateien\Uni\STUPRO A\SVN\deployment\simpl-tomcat.zip" %3\*.*