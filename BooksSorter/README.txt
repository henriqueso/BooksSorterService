Books Sorter Service
====================


What is this?
-------------
This is a web application that provides a service for ordering a list of books.


Configuration
-------------
It's developed in Java language and was tested on Glassfish 4.0 although it is not platform dependent.

In summary, these are the versions of language, frameworks, platform.
java.version  = 7
junit.version = 4.11
log4j.version = 1.2.16
glassfish.version = 4.0


Installation
------------
As a web application, it generates a .war file to be deployed on any JavaEE web server.

In order to generate the .war file, simply run maven goal 'install' and it'll generate the .war file on the configured local maven repository.
Ex: <user_home>/.m2/repository/br/com/henriqueso/training/book-sorter/0.0.1-SNAPSHOT


Testing
------------
The application can be tested through its unit test or using Soap UI tool importing the BooksSorterService-soapui-project.xml project.