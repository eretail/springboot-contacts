# Introduction

Build a simple Microservice using SpringBoot to extract and store Contacts

# Design:

System bases on SpringBoot’s Microservices/Data/Restful API, and H2.

# Assumption
We allow duplication of number or name.. only id is primary key

# Installation
User needs to have maven and java8 installed in local environment in order to run this application, this application will be run on windows system.

If you have dockerhub installed locally, you can run following command to run the application

```
docker pull eretailservice/test
docker run -i -t eretailservice/test
```

If you don't have docker installed, do as following: 

First, user needs to be at a work directory, download, install.bat and run.bat. 

also, during the installation and running, the script will change directory, in case you need rerun, please make sure you are at right directory.

```
install.bat
```

![image](https://user-images.githubusercontent.com/26700142/68820059-18bab780-0658-11ea-9f88-aa1b1adb0730.png)

```
run.bat
```
![image](https://user-images.githubusercontent.com/26700142/68820729-4dc80980-065a-11ea-9596-638d920ea910.png)

# Usage

# Current Status and Enhancement

Application is successfully up running, Full fill the requirement on server side, directly service call from standalone application.

Rest Service is operated correctly on top of SpringBoot

CRUD function is operated correctly on H2

Service side implementation on all functions

```
yingchun.li @Atlanta
```