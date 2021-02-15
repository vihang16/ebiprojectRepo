# Sample Project using spring-boot

This project is sample dockerize application using spring-boot
 
 Description of the project:
 
 I have created simple Crud operations for Person entity with below assumptions.
 There will be 2 Users created on initilization of the project
 1. User
 2. Admin
 
 and below are the entity objects gets created on start of the project.
 
 1. firstName - dumm1, lastName- dumm2, age - 32
 2. firstName - Vihang, lastName- Shah, age -29
 
 
 below are the restriction/grant these 2 users will have
 1. anyone can add Person to dbs.
 2. Update, Delete and retrieve all only admin can do.
 
 below are the endpoints can be use for different operations:
 Root endpoint: /persons
 | Enpoint | Method  Supported | Function |
 |---------|:-------:| -----------------:|
 | /create | POST    | To add one more person into db|
 | /all    | GET     | To retrieve all persons details|
 | /update/:id| PUT | 	Update data about a person|
 | /delete/:id| DELETE | Delete person data from db|

		
below are the technologies I have used:

- Spring-boot - 2.4.2 RELEASE
- H2 - in memory database 1.4.200
- tomcat embed-websocket - 9.0.41
- Java 10
- Maven - 4.0.0
- Spring security - 5.4.2
- Spring - 5.3.3
- Junit-jupiter 5.7.0

How to run and test the project?
As I noticed from [this feature update](https://github.com/docker/for-linux/issues/1102) currently building from git and using from fragment section is not supported yet.so it is unable to create image from github repo.
 alternatively you can git clone the object using below command
 git clone https://github.com/vihang16/ebiprojectRepo.git or you can import project in eclipse using import from git option.
 
 once project downloads run below commnad to build the project
 mvn clean install
 
 then in windows to build the docker image for  this jar run below command
 docker build -t <tagName> <path>
	
to start the project run below commnd.
docker run -dp <your port number>:8080 <tagName used for build>
I have used alpine:edge open jdk11 as base image for docker run

