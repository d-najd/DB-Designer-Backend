# DB Designer Backend

Backend using Spring for the android project in related links

![vulnerabilities](https://img.shields.io/snyk/vulnerabilities/github/d-najd/DB-Designer-Backend)
![reposize](https://img.shields.io/github/repo-size/d-najd/DB-Designer-Backend)

# Getting Started

Follow the instructions below to get a copy of the project running on your local machine

## Prerequisites

* Java JDK 17
* Docker (WORK IN PROGRESS)
* Git

## Installing

* clone the repository using [Git](https://git-scm.com/downloads)
```Git
git clone --recursive https://github.com/d-najd/DB-Designer-Backend
```
* Set datasource url of your MYSQL DB application.properties -> spring.datasource.url
* Set mysql user value into application.properties -> spring.datasource.username
* Set mysql user password value into application.properties -> spring.datasource.password

## Build and Start the project

* Go to root directory of the project
* mvn clean package 
* java -jar .\target\umldesigner-0.0.1-SNAPSHOT.jar

## Docker Setup (WORK IN PROGESS)
 there is file called db_tables_uml.txt inside the project files which has most of the infrastructure of the database, that will have to do until docker is implemented

# Features
 - OAuth 2.0 auth (work in progress)
 - Json support (work in progress)
   - Ability to generate uml diagrams from json files
 - Mysql support (work in progress)
   - Ability to generate uml diagrams from sql code
   - Ability to get sql code from a given diagram

# Demo
 Visit https://www.postman.com/collections/e3ef56648f12f06fc6e4 to see available mappings

# Downloads (WORK IN PROGRESS)
# Related Projects
- [Android App](https://github.com/d-najd/DB-Designer/)
- [Shared Repository](https://github.com/d-najd/DB-Designer-Shared/)
