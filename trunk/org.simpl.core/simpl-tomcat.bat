@echo off
REM This batch files helps to consolidate all SIMPL files for the deployment 
REM in Apache Tomcat to a .zip file.
REM 
REM PARAMETERS
REM   %1: tomcat root folder
REM   %2: eclipse workspace folder
REM   %3: folder to consolidate the deployment files (must exist)
REM   %4: local svn folder where to put the simpl-tomcat.zip
REM
REM NOTE
REM   Directories containing spaces have to be passed in short form e.g. C:\TOMCAT~1.0 instead of C:\Tomcat 6.0
REM
REM ECLIPSE EXTERNAL TOOLS EXAMPLE
REM   Location: ${workspace_loc:/org.simpl.core/simpl-tomcat.bat}
REM   Working Directory: ${workspace_loc:/org.simpl.core}
REM   Arguments: C:\TOMCAT~1.0 C:\eclipse\workspace C:\SIMPL\deployment C:\SIMPL\SVN\deployment\simpl-tomcat.zip)

@echo off
REM create directory structure in %3
@echo on
md "%3\conf"
md "%3\common"
md "%3\common\lib"
md "%3\lib"
md "%3\webapps"
md "%3\webapps\juddiv3"
md "%3\webapps\juddiweb"
md "%3\webapps\ode"
md "%3\webapps\axis2"

@echo off
REM copy all files to %3
@echo on
REM copy "%1\webapps\ode\WEB-INF\servicejars\simpl-core-webservices.jar" "%3\webapps\ode\WEB-INF\servicejars"
REM copy "%1\webapps\ode\WEB-INF\lib\simpl-core.jar" "%3\webapps\ode\WEB-INF\lib"
REM copy "%1\webapps\ode\WEB-INF\lib\simpl-core-plugins.jar" "%3\webapps\ode\WEB-INF\lib"
REM copy "%1\webapps\ode\WEB-INF\lib\simpl-uddi-client.jar" "%3\webapps\ode\WEB-INF\lib"
REM copy "%1\webapps\ode\WEB-INF\lib\simpl-ode-extension.jar" "%3\webapps\ode\WEB-INF\lib"
REM copy "%1\webapps\ode\WEB-INF\lib\tools.jar" "%3\webapps\ode\WEB-INF\lib"
REM copy "%1\webapps\ode\WEB-INF\conf\axis2.xml" "%3\webapps\ode\WEB-INF\conf"

copy "%1\webapps\ode\WEB-INF\lib\derby-10.4.1.3.jar" "%3\common\lib"
copy "%1\webapps\ode\WEB-INF\lib\derby-10.4.1.3.jar" "%3\lib"
xcopy "%1\webapps\juddiv3\*.*" "%3\webapps\juddiv3" /s
xcopy "%1\webapps\ode\*.*" "%3\webapps\ode" /s
xcopy "%1\webapps\axis2\*.*" "%3\webapps\axis2" /s
xcopy "%1\webapps\juddiweb\*.*" "%3\webapps\juddiweb" /s

copy "%2\org.simpl.core\lib\*.*" "%3\webapps\ode\WEB-INF\lib"
copy "%2\org.simpl.core\log4j.properties" "%3\"
copy "%2\org.simpl.core\simpl-core-config.xml" "%3\webapps\ode\WEB-INF\conf"
copy "%2\org.apache.ode.simpl.ea\ode-axis2.properties" "%3\webapps\ode\WEB-INF\conf"
copy "%2\org.simpl.core\readme.txt" "%3\"
copy "%1\conf\tomcat-users.xml" "%3\conf"
copy "%4\simpl-rrs.aar" "%3\webapps\ode\WEB-INF\services"
copy "%4\simpl-rrs-transformation.aar" "%3\webapps\ode\WEB-INF\services"

@echo off
REM create simpl-tomcat.zip in %4
@echo on
C:\Programme\7-Zip\7z.exe a -r "%4\simpl-tomcat.zip" "%3\*.*"

@echo off
REM clean %3
@echo on
del "%3\*.*" /s /q
rd "%3\webapps" /s /q
rd "%3\conf" /s /q
rd "%3\common" /s /q
rd "%3\lib" /s /q