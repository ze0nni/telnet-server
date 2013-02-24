Simple Telnet Server
=====================


This is a simple Telnet server written in Java. It supports basic Unix commands such as: ls, mkdir, pwd, cd, rm. It is intended for novice Java developer to lar how server/client communication is implemented in Java. 


How to run the server
=======================

> Maven: mvn exec:java


> IDE: run ServerLauncher.java as Java application.


How to build the jar
=====================
> mvn clean install (will run the tests too)

> Maven build jar

  * mvn clean install to build the jar
  * cd target/ then run: java -jar telnet-server.jar



How to run tests
=====================

> mvn clean test
