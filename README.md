# Clients Service

-Java 11

-Springboot 2.4.0

-Mysql database

-H2 database para testes

## Funcionalidades

-Cadastrar cidade

-Cadastrar cliente

-Consultar cidade pelo nome

-Consultar cidade pelo estado

-Consultar cliente pelo nome

-Consultar cliente pelo Id

-Remover cliente

-Alterar o nome do cliente

## Docker

1)Verifique se possui o docker e docker compose instalado.

2)Para instalar a imagem do mysql para execução da aplicação você deve entrar na pasta do projeto e executar o comando:
`docker-compose up -d`

3)Executa o build e cria e a imagem docker: 
`sudo JAVA_HOME=versao_jdk_java11 ./mvnw install dockerfile:build` 

Ex: `sudo JAVA_HOME=/usr/lib/jvm/java-11-openjdk-amd64 ./mvnw install dockerfile:build`

4)Subir aplicação `docker run -p 8080:8080 -t clients`

Exemplos de curl:

Cria cidade:
curl --location --request POST 'localhost:8080/city' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Blumenau",
    "state": "SC"
}'

Cria cliente:
curl --location --request POST 'localhost:8080/client' \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Renata",
    "gender": "F",
    "birth": "20/10/1987",
    "cityId":1
}'

Consulta cidade pelo nome:
curl --location --request GET 'localhost:8080/city/byName?name=Blu' \
--data-raw ''

Consulta cidade pelo Estado:
curl --location --request GET 'localhost:8080/city/byState?state=SC' \
--data-raw ''

Consultar cliente pelo nome:
curl --location --request GET 'localhost:8080/client/byName?name=Rena' \
--data-raw ''

Consultar cliente pelo Id:
curl --location --request GET 'localhost:8080/client/byId/1' \
--data-raw ''

Alterar o nome do cliente
curl --location --request PUT 'localhost:8080/client/1/updateName?name=Carla'

Remover cliente:
curl --location --request DELETE 'localhost:8080/client/1'
