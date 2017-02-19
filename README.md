# SimpleBankEmployeesDashboard
-------------------------------
###Challenge description

The given application is a (highly simplified) administration dashboard for bank employees.

Employees are able to:

● view issued bank accounts and credit cards

● lock or unlock issued bank accounts and credit cards

● add new bank accounts or credit cards

All employee’s actions are logged into an audit table.

The administration dashboard is a Java web application based on Spring Boot . You will need to have Maven installed.


-------------------------------

###Getting started

The starting source code is attached to the e­mail.

The application can be built on the command line with mvn clean install.

It can also be imported into Eclipse or any other IDE of your choice as a Maven project and

then executed with javatest.DeveloperTestApplicationas the main entry point.

The UI can then be opened in the browser at http://localhost:8080.

The application uses an in­memory H2 database which is populated at startup time using data

from src/main/resources/sql/data.sql. It creates a few bank accounts and credit

cards with transaction data.


-------------------------------
These properties in application.properties help for debugging SQLs and transactions:

● spring.jpa.show­sql=true

● logging.level.org.springframework.transaction=DEBUG

● logging.level.org.springframework.orm.jpa=DEBUG