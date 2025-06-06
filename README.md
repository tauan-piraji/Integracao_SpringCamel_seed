# Projeto de Integração - Spring Boot e Apache Camel

Este projeto é uma seed de integração desenvolvida com Spring Boot e Apache Camel 4,
visando servir como base e padrão de desenvolvimento para projetos.
Ele tem o mínimo necessário de várias tecnologias para sustentar os mais diversos cenários que poderiam surgir em um desenvolvimento de integração.

---

## 📁 **Estrutura do Projeto**
```plaintext
Integracao_SpringCamel_seed/
├── src/
│   ├── main/
│   │   ├── java/integrador/seed/
│   │   |   ├──  busines/
│   │   |   |   ├──  oracleRepositories/                 # Repositories Oracle
│   │   |   |   ├──  processors/                         # processamento dos dados nas rotas camel
│   │   |   |   ├──  repositories/                       # Repositories MySQL
│   │   |   |   ├──  services/                           # Services de comunicação com o banco
│   │   |   ├──  camel/
│   │   |   |   ├──  CamelRouter.java                    # Rotas Camel
│   │   |   |   ├──  ConsumoRabbitMqRouter.java          # Consumo do RabbitMQ
│   │   |   |   ├──  DeadletterRouter.java               # Retentativar e envio para DeadLetter
│   │   |   |   ├──  StrpRouter.java                     # Comunicação com arquivo por sftp
│   │   |   ├──  core/
│   │   |   |   ├──  DbConfig/                           # Configuração dos bancos oracles e MySql
│   │   |   |   ├──  rabbitmq/                           # Configuração da mensageria
│   │   |   |   ├──  AbstractDeadLetterProcessor.java    # Controle de fila RabbitMq
│   │   |   |   ├──  CamelConfig.java                    # Configurações do camel
│   │   |   ├──  models/
│   │   |   |   ├──  dtos/                               # Entidades costumizadas
│   │   |   |   ├──  entities/                           # Entidades MySql
│   │   |   |   ├──  entitiesOracle/                     # Entidades Oracle
│   │   |   |   ├──  enums/                              # Enums para controle refinado
│   │   |   ├──  Application.java                        # Main java
│   │   ├── resources/ 
│   │   |   ├── spring/  
│   │   |   |   ├── camel-context.xml                    # Definição das rotas da API
│   │   |   ├── application.yaml                         # Variáveis de ambiente
│   ├── configuration/                                   # Configurações
├── .gitignore                                           # Arquivos ignorados pelo Git
├── Dockerfile                                           # Compacta e executa em container
├── local.sh                                             # Script execucao
├── README.md                                            # Documentação do projeto
└── pom.xml                                              # Controle de versão
```

---

## 🛠 **Tecnologias Utilizadas**

| Tecnologia             | Logotipo                                                                   |
| ---------------------- | -------------------------------------------------------------------------- |
| **Java 17**            | ![Java](https://img.shields.io/badge/Java-17-blue)                         |
| **Spring Boot**        | ![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-brightgreen) |
| **Apache Camel**       | ![Camel](https://img.shields.io/badge/Camel-4.2.0-orange)                  |
| **QueryDSL**           | ![QueryDSL](https://img.shields.io/badge/QueryDSL-5.1.0-lightgrey)         |
| **MapStruct**          | ![MapStruct](https://img.shields.io/badge/MapStruct-1.5.3.Final-yellow)    |
| **Jackson**            | ![Jackson](https://img.shields.io/badge/Jackson-2.15.3-blueviolet)         |
| **Lombok**             | ![Lombok](https://img.shields.io/badge/Lombok-1.18.24-red)                 |
| **MSSQL JDBC Driver**  | ![MSSQL](https://img.shields.io/badge/SQL_Server-JDBC-blue)                |
| **Oracle JDBC Driver** | ![Oracle](https://img.shields.io/badge/Oracle-JDBC-orange)                 |


### Spring Boot

Spring Boot é um framework que facilita a configuração e o desenvolvimento de aplicações Java, oferecendo funionalidades prontas para uso, como injeção de dependência, segurança, endpoints web e integração com diversos serviços.

### Apache Camel

Apache Camel é um framework de integração que facilita a construção de rotas de integração, transformando dados entre diferentes formatos e transportes de maneira fluida. No projeto, utilizamos:

- camel-spring-boot-starter: Inicializador do Apache Camel com suporte ao Spring Boot.
- camel-jackson-starter: Para serialização/deserialização de JSON usando Jackson.
- camel-openapi-java-starter: Integração com OpenAPI para documentar e consumir APIs.
- camel-servlet-starter: Para expor rotas Camel como serviços baseados em Servlet.
- camel-quartz-starter e camel-timer-starter: Para agendar tarefas em intervalos de tempo fixos.
- camel-rabbitmq-starter: Para integração com o RabbitMQ, usado para fila de mensagens.
- camel-platform-http-starter e camel-http: Para fazer chamadas HTTP e manipular requisições HTTP em rotas Camel.
- camel-sql: Para execução de operações SQL nas rotas.
- camel-jsch e camel-smb: Para comunicação com servidores via FTP/SFTP/SMB.

### Banco de dados

O projeto suporta conexão com dois bancos simultaneamente, e tem as dependências necessárias para conexão com mysql e oracle.

- spring-boot-starter-data-jpa e spring-boot-starter-jdbc: Para o uso de JPA (Java Persistence API) e JDBC (Java Database Connectivity) no acesso a bancos de dados.
- mssql-jdbc: Driver JDBC para conexão com Microsoft SQL Server.
- ojdbc8: Driver JDBC para conexão com Oracle Database.

### QueryDSL

QueryDSL é um framework para construção de consultas dinâmicas em SQL:

- querydsl-jpa: Suporte a JPA para a construção de consultas SQL.
- querydsl-apt: Gera código para facilitar a criação de queries baseadas em tipos.

### Manipulação de Dados

- jackson-datatype-jsr310: Suporte para a manipulação de tipos de dados Java 8, como LocalDate, com Jackson.
- camel-jsonpath-starter: Para busca e manipulação de dados JSON usando JSONPath.
### Lombok

- lombok: Biblioteca que gera automaticamente getters, setters, construtores e outras funcionalidades, facilitando a codificação de classes.
### MapStruct

- mapstruct: Para mapeamento automático entre objetos Java.
- mapstruct-processor: Processador que gera código de mapeamento durante a compilação.
