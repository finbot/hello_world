SERVER:
apache-tomcat-7.0.28

Add to '<tomcat_home>/conf/tomcat-users.xml' following elements:
<role rolename="manager-gui"/>
<role rolename="manager-script"/>
<user username="admin" password="admin" roles="manager-script, manager-gui"/>



DATA BASE:
MySql
- for development: data_base_dev
- for jenkins: data_base_jenkins
- for production: data_base_prod



URL:
http://localhost:8080/HelloWorldDbIntegrationTests/



TO RUN INTEGRATION TESTS:
1. 
Run server

2.
Run data base 

3. Run maven with profile "dev"
mvn clean install -Pdev

ggg


