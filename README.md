Simple Telnet Server
=====================


This is a simple Telnet server written in Java. It supports basic Unix commands such as: ls, mkdir, pwd, cd, rm. It is intended for novice Java developer to lar how server/client communication is implemented in Java. 


How to run the server
=======================

> Maven: mvn exec:java (The sever will be run on port 12667 by default. This can be overriden in pom.xml. If you run the server from the exectuable jar, it will use whatever defined in server.properties file)


> IDE: run ServerLauncher.java as Java application.


Running example
================

* Run the server as above explanation. 
* open a bash or cmd terminal and run: telnet localhost 12667. It should display an welcome message with all the possible commands. By default working directoy is set to 'user.home', i.e (wherever you unzipped the file)
* Limitations of some of the commands:
** LS: It only list the files within the working directory
** cd: supports cd into sub-directories of the 'user.home' directory. Full path can is also supported such as '/usr/local' or 'C:\Documents'.

How to build the jar
=====================
> mvn clean install (will run the tests too)

> Maven build jar

  * mvn clean install to build the jar
  * cd target/ then run: java -jar telnet-server.jar



How to run tests
=====================

> mvn clean test
