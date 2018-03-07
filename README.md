# Spring boot application for a sample Ticketing Service

This application ccontains 3 modules under ticket-service:

* ticket-service-common: has common code (utils, enums, dtos etc.)
* ticket-service-main: has services, rest controllers and other main code
* ticket-service-integration-tests: REST service integration tests using Spring's Rest Template as the     Rest client.

# Unit Testing:
* I have used junit, and mockito to write unit tests and mock out dependencies.
* Wrote couple of unit tests as example (e.g. TicketServiceImplTest, TicketServiceRepositoryImplTest), but didn't worry about full code coverage

# REST Service Endpoints:
* Created 3 very basic endpoints for the 3 services (under TicketController.java)

# Integration Testing:
* Created Web Service Integration tests to test each service. 
* There is also a very basic End-End Integration test (under FindHoldAndReserveE2EIntegrationTest)

# Environment Specific Configuration:
* Used Spring and .properties file for a very basic environment specific configuration

# Running the application:

1. Checkout/download the project from git
2. cd into the ticket-service directory
3. To build and run unit tests: ```mvn clean install```
4. cd into ticket-service-main project
5. Start the application using: ```mvn spring-boot:run```
   This starts up the application on port "8080" by default (I have tested using 8080).

   If you don't have 8080 available, then run using 
   mvn spring-boot:run -Dserver.port=<port_num>
   (or)
   you could also update "server.port" property under src/main/resources/ticketservice-config.properties

6. cd into ticket-service-integration-tests and run:
   ```mvn integration-test -DskipTest=false```

