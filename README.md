# Introduction

Build a simple Microservice using SpringBoot to extract and store Contacts

# Design:

System bases on SpringBoot’s Microservices/Data/Restful API, and H2.

# Assumption
We allow duplication of number or name.. only id is primary key. we assume the requirement is completed.

# Installation
User needs to have maven and java8 installed in local environment in order to run this application, this application will be run on windows system.
please follow these steps:
 ```
   git clone https://github.com/eretail/springboot-contacts
   mvn clean install 
   mvn spring-boot:run
``` 

# Running the Application
To run the project:
mvn spring-boot:run

#Testing Via Postman
Create Contact POST /contacts
![image](https://user-images.githubusercontent.com/26700142/87625921-57c66e80-c6f9-11ea-8e61-45aa948df932.png)

Update individual contacts PUT /contacts/{id}
![image](https://user-images.githubusercontent.com/26700142/87626193-c4da0400-c6f9-11ea-92dd-f4ff532779cb.png)

Get Contacts GET /contacts
![image](https://user-images.githubusercontent.com/26700142/87626316-13879e00-c6fa-11ea-81f2-a24ff3dfc104.png)

Get individual contacts GET /contacts/{id}
![image](https://user-images.githubusercontent.com/26700142/87626089-96f4bf80-c6f9-11ea-90b0-a89bdd14d8f3.png)

Delete individual contacts DELETE /contacts/{id}
![image](https://user-images.githubusercontent.com/26700142/87626238-df13e200-c6f9-11ea-9e7f-33a8b05a6f8f.png)

#Logs
Lomok sl4j is used in application level log, and log level is set to info level. Hibernate SQL is turned on. 

# Current Status and Enhancement

Application is successfully up running, Full filled the requirement on server side, directly service call from standalone application.

Rest Service isoperated correctly on top of SpringBoot

CRUD function isoperated correctly on H2

Service side implementation on all functions

System has been unit test which preformed all mock service call based on the require ment

# To Be Enhanced
Because of the time limitation, I have left some features for future enhancement which is not specified in the documents.
(1) Security, it would be nice to add Spring Security in
(2) Validation, input and output validation
(3) Exception Handler along with log mechanism.
(4) Build docker image, and push to docker hub, so the application can be easily retrieved by user.
```
yingchun.li @Atlanta
```
