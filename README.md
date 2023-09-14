# Clients Service

-Java 11

-Springboot 2.4.0

-Mysql database

-H2 database for tests

## Funcionalities

-Add city

-Add client

-Find the city by name

-Find the city by state

-Find client by name

-Find client by the ID

-Delete client

-Update client name

## Docker

1)Verify if you have Docker or Docker Comose installed.

2)To install the MySQL image to execute the project you have to enter in the main folder of the project and execute this command:
`docker-compose up -d`

3)Execute the build and create the docker image: 
`sudo JAVA_HOME=versao_jdk_java11 ./mvnw install dockerfile:build` 

Ex: `sudo JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64 ./mvnw install dockerfile:build`

4)Run the application `docker run -p 8080:8080 -t clients`

cURL examples:

Create city:
curl --location --request POST 'localhost:8080/city' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Blumenau",
    "state": "SC"
}'

Create client:
curl --location --request POST 'localhost:8080/client' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Renata",
    "gender": "F",
    "birth": "20/10/1987",
    "cityId":1
}'

Find the city by name:
curl --location --request GET 'localhost:8080/city/byName?name=Blu' \
--data-raw ''

Find city by state:
curl --location --request GET 'localhost:8080/city/byState?state=SC' \
--data-raw ''

Find client by name:
curl --location --request GET 'localhost:8080/client/byName?name=Rena' \
--data-raw ''

Find client by ID:
curl --location --request GET 'localhost:8080/client/byId/1' \
--data-raw ''

Update client name:
curl --location --request PUT 'localhost:8080/client/1/updateName?name=Carla'

Delete client:
curl --location --request DELETE 'localhost:8080/client/1'
