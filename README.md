# Projeto de Integração - Spring Boot e Apache Camel

Este projeto é uma seed de integração desenvolvida com Spring Boot e Apache Camel 4,
visando servir como base e padrão de desenvolvimento para projetos.
Ele tem o mínimo necessário de várias tecnologias para sustentar os mais diversos cenários que poderiam surgir em um desenvolvimento de integração.

## Tecnologias Utilizadas

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
