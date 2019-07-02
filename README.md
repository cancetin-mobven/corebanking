CoreBanking rest api: openpayd assigment using Spring mvc
==============================================================
Author:Can Cetin  
Technologies:  JPA, JSON, Spring, JUnit, Mockito 
Summary: The `CoreBanking` rest application is an example Spring mvc application using JPA 2.0 and Spring 4.x.  
 
Source: <https://github.com/cancetin-mobven/corebanking>  

What is it?
-----------

The Corebanking application includes  RESTful service that offers the following functions: 
1. Create a new client 
2. List all clients 
3. Get client details by ID 
4. Create a new client account 
5. Effect a transfer between 2 accounts 
6. List client accounts 
7. List account transactions 
 

* The controllers map the respective urls to methods using `@RequestMapping(url)`.

* To return JSON, the rest controller uses `@ResponseBody`.


System Requirements
-------------------

The application this project produces is designed to be run on Tomcat 7.0.94 

To run the quickstart with the provided build script, you need the following:

1. Java 1.8, to run Tomcat and Maven. You can choose from the following:
    * OpenJDK
    * Oracle Java SE
    * Oracle JRockit

2. Maven 3.0.0 or later, to build and deploy the examples
    * If you have not yet installed Maven, see the [Maven Getting Started Guide](http://maven.apache.org/guides/getting-started/index.html) for details.
    * If you have installed Maven, you can check the version by typing the following in a command line:

            mvn --version 

3. The Apache Tomcat distribution ZIP.
    *The Tomcat Apache web server is free software that can be downloaded from their website. 
		It is required that there is a JDK available on the user’s machine and that the JAVA_HOME environment variable is set correctly.
		https://tomcat.apache.org/tomcat-7.0-doc/setup.html

4.Corebanking uses postgresql for datasourse. 
If you have not yet done so, you must keep instructions 
http://www.postgresqltutorial.com/install-postgresql/		


Start the Apache Tomcat Server
-------------------------

You can start the Tomcat server by simply running the startup script located at $CATALINA_HOME\bin\startup. 
There is a .bat and a .sh in every installation.

Choose the appropriate option depending on whether you are using a Windows or Unix based operating system.

 
Build and Deploy the corebanking
-------------------------

_NOTE: The following build command assumes you have configured your Maven user settings. If you have not, you must include 
Maven setting arguments on the command line. See [Build and Deploy the Quickstarts](../README.md#build-and-deploy-the-quickstarts) 
for complete instructions and additional options._

1. Make sure you have started the Apache tomcat as described above.
2. Open a command line and navigate to the root directory of this quickstart.
3. Type this command to build:

        mvn clean install

4. You will deploy `target/OnlineBanking.war` to the /webapps/.. running instance of the server.


Access the application
----------------------

The application will be running at the following URL: <http://localhost:8080/corebanking/>.

you can import the postman collection link for test scenerious;

https://www.getpostman.com/collections/51e7b8b164251214562e

Swagger link for rest documentation;

http://localhost:8080/corebanking/swagger-ui.html#

Debug the Application
---------------------

If you want to debug the source code or look at the Javadocs of any library in the project, run either of the following 
commands to pull them into your local repository. The IDE should then detect them.

        mvn dependency:sources
        mvn dependency:resolve -Dclassifier=javadoc
