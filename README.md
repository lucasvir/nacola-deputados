# Na Cola - API
[![NPM](https://img.shields.io/npm/l/react)](https://github.com/lucasvir/jornadamilhas/blob/main/LICENSE)

# :scroll: Descrição

API REST criada em Java 17 para uma aplicação web que agrega informações de parlamentares referente a unidade federativo do usuário.

Conta com gerenciamento e ciração de úsuarios, autenticação via JWT Token, sistema de persistência dos deputados através de consulta da API da camara dos deputados. 

## :wrench: Funcionalidades

- *Login*
- *Autenticação JWT Token*
- *Crud de usuário*
- *Crud de deputados*

## :toolbox: Tecnologias

- Java
- Spring Boot
- JPA / Hibernate
- H2 Database 
- Json Web Token
- Swagger
- Maven

## :construction: EXEC

> Pré-requisitos:
> - instalar o Java ([Oracle](https://www.oracle.com/java/technologies/downloads/)).
> - instalar o Maven (build) ([Maven](https://maven.apache.org/install.html)).


Clonar repositório
```bash
git clone https://github.com/lucasvir/nacola-deputados.git
```

Acessar diretório
```bash
cd nacola-deputados
```

Criar banco de dados para persistência e para testes
```bash
sudo -u postgres psql
CREATE DATABASE <nomedb> OWNER <nomeusuario>;
CREATE DATABASE test_db OWNER <nomeusuario>;
```

Fazer o build
```bash
mvn verify
```

Inicializar aplicação setando as variáveis de ambiente
> DATASOURCE_USERNAME: nome do usuário do banco de dados <br />
> DATASOURCE_PASSWORD: senha de acesso ao banco de dados <br />
> SECURITY_JWT_SECRET: secret para api de geração dos token <br />

*exemplo:*
```bash
java -DSECURITY_JWT_SECRET=$2a$12$5H0JqHT2eZJdIauXvxIMOuNjeCHKHRMDQLFztQAeGQs5eAGCANzje -DDATASOURCE_USERNAME=<nomeusuario> -DDATASOURCE_PASSWORD=<senhausuario> -jar target/api-0.0.1-SNAPSHOT.jar
```
