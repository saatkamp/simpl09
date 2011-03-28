@echo off
REM This batch files helps to consolidate all SIMPL files for the deployment 
REM in Apache Tomcat to a .zip file.
REM 
REM PARAMETERS
REM   %1: tomcat root folder
REM   %2: folder to consolidate the deployment files (must exist)
REM   %3: 7-Zip executable
REM
REM NOTE
REM   Directories containing spaces have to be passed in short form e.g. C:\TOMCAT~1.0 instead of C:\Tomcat 6.0
REM
REM ECLIPSE EXTERNAL TOOLS EXAMPLE
REM   Location: ${workspace_loc:/org.simpl.core/simpl-tomcat.bat}
REM   Working Directory: ${workspace_loc:/org.simpl.core}
REM   Arguments: C:\Tomcat6.0 ${workspace_loc:/org.simpl.core}/deployment C:\Programme\7-Zip\7z.exe

@echo off
REM create directory structure in %2
@echo on
md "%2\conf"
md "%2\common"
md "%2\common\lib"
md "%2\lib"
md "%2\webapps"
md "%2\webapps\rmweb"
md "%2\webapps\ode"
md "%2\webapps\axis2"

@echo off
REM copy all files to %2
@echo on
copy "%1\webapps\ode\WEB-INF\lib\derby-10.4.1.3.jar" "%2\lib"
copy "%1\webapps\ode\WEB-INF\lib\derby-10.4.1.3.jar" "%2\common\lib"
copy "%1\conf\tomcat-users.xml" "%2\conf"
xcopy "%1\webapps\ode\*.*" "%2\webapps\ode" /E
xcopy "%1\webapps\axis2\*.*" "%2\webapps\axis2" /E
xcopy "%1\webapps\rmweb\*.*" "%2\webapps\rmweb" /E
xcopy "%1\simplDB\*.*" "%2\simplDB\" /E
xcopy "%1\simplDB\*.*" "%2\bin\simplDB\" /E
copy "%2\..\log4j.properties" "%2"
copy "%2\..\log4j.properties" "%2\bin"
copy "%2\..\simpl-tomcat-readme.txt" "%2"

@echo off
REM create simpl-tomcat.zip in %2
@echo on
"%3" a -r "%2\simpl-tomcat.zip" "%2\conf" "%2\common" "%2\lib" "%2\webapps" "%2\simplDB" "%2\log4j.properties" "%2\simpl-tomcat-readme.txt"

@echo off
REM clean %2
@echo on
del "%2\bin" /s /q
del "%2\conf" /s /q
del "%2\common" /s /q
del "%2\lib" /s /q
del "%2\webapps" /s /q
del "%2\simplDB" /s /q
del "%2\log4j.properties"
del "%2\simpl-tomcat-readme.txt"

rd "%2\bin" /s /q
rd "%2\conf" /s /q
rd "%2\common" /s /q
rd "%2\lib" /s /q
rd "%2\webapps" /s /q
rd "%2\simplDB" /s /q