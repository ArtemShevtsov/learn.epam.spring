How to Start Application:

You can start it in 2 ways
  1. with jetty plugin:
    - go to application folder
    - run mvn jetty:run
  2. with tomcat plugin:
    - go to application folder
    - run mvn tomcat7:run-war

After that app will start on port "8090" and host "localhost"
With Spring MVC Application you can user DB Manager (which start at the same time) if you need to select data from DB directly

---------------SPRING-SOAP-SERVICE---------------
Clean All Generated code and schema:
rm -rf src/main/resources/schema/* src/main/java/learn/spring/core/entity/generated /target

Schema generation based on annotated JAXB classes:
rebuild your project (maybe you will need to exclude learn.spring.web.clients.SOAPServiceClient from sources)
schemagen -d src/main/resources/schema/ -cp target/classes/ src/main/java/learn/spring/core/entity/ws/*.java

Java classes generation based on schema:
xjc -d src/main/java/ -p learn.spring.core.entity.generated src/main/resources/schema/schema1.xsd

to view generated WSDL go to http://localhost:8090/ws/booking.wsdl

You can import Project to SOAP-UI (user file booking-soapui-project.xml) OR
use client console application in learn.spring.web.clients.SOAPServiceClient
