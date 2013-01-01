Tomcat Modules
==============

The goal of the project is to use jboss-modules in Tomcat.

Try it
------

1. Install Tomcat - I've tested with Tomcat 7.0
2. Edit bin/setclasspath.sh and add the following line at the end of the file :

     CLASSPATH="$CATALINA_HOME"/lib/*

3. Change the properties in the parent pom.xml (especially tomcat.home)
4. Run "mvn install"
5. Run Tomcat
6. Open http://localhost:8080/app-web in your favorite browser

Why ?
-----

Because modularity is cool and JBoss Modules is the way.

How does it work ?
------------------

With Tomcat, you can switch the classloader of an application by specifying a loader in the META-INF/context.xml.
The tomcat-loader module is such a loader which tells Tomcat to use a JBoss Modules classloader.

The JSPs a still loaded by the JasperLoader. The main change is that the JasperLoader is a child classloader of the main
Module. It can therefore take advantage of modularity.

Limitations and TODO
--------------------

For the moment, it only works with a simple servlet, in a separate module (alpha1), and with a JSP (alpha2).

The following features have to be implemented :
- Servlets, filters and other classes in WEB-INF/classes
- jar files in WEB-INF/lib
- Unload classes on hot deploy
- ...
