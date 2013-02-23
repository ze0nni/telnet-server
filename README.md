* Simple Telnet Server

This is a simple Telnet server written in Java. Supports basic Unix command such as: ls, mkdir, pwd, cd, rm. It is intended for novice Java developer to see how server client communication is implemented in Java. Also, it shows how to write good unit and integration tests.

* How to run the server

> Maven: mvn exec:java

> IDE: run ServerLauncher.java as Java application.

> Maven build jar

>> mvn clean install to build the jar
>> cd target/ then run: java -jar telnet-server.jar

* How to build the jar

> mvn clean install (will run the tests too)

* How to run the test

> mvn clean test
