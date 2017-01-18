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