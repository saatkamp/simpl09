README FILE:

1. INSTALLATION OF JUDDI
	a) Download juddi-tomcat-3.0.1.zip
	b) Extract juddi-tomcat and copy the juddiv3 directory into "tomcat\webapps"
	
2. INSTALLATION OF JUDDI WEBINTERFACE
	a) Copy juddiweb.war to "tomcat\webapps"
	b) Write: <role rolename="secure"/>
		 in your "tomcat\conf\tomcat-users.xml"
	c) Write: <user username="your username" password="your password" roles="secure"/> 
		in your "tomcat\conf\tomcat-users.xml" to be able to access the jUDDI web interface
	
	