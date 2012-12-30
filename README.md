Tomcat Modules
==============

The goal of the project is to use jboss-modules in Tomcat.

Try it
------

1. Install Tomcat - I'm currently using Tomcat 7.0
2. Change bin/setclasspath.sh and add the following line at the end of the file :
  CLASSPATH="$CATALINA_HOME"/lib/*
3. Change the properties in the parent pom.xml (especially tomcat.home)
4. Run "mvn install"
5. Run Tomcat
6. Open http://localhost:8080/app-web in your favorite browser

Why ?
-----

Because modularity is cool, Jigsaw is postponed and OSGi is OSGi.


How it works ?
--------------

With Tomcat, you can switch the classloader of an application by specifying a loader in the META-INF/context.xml.
The tomcat-loader module is such a loader which tell Tomcat to use a JBoss Modules classloader.

Limitations and TODO
--------------------

For the moment, it only works with a simple servlet, in a separate module.

The following features have to be implemented :
- JSP
- Servlets, filters and other classes in WEB-INF/classes
- jar files in WEB-INF/lib
- ...
