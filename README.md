# 🚗 Cars API

API REST desenvolvida em **Java 17 + Spring Boot 3**, com banco de dados relacional, seguindo boas práticas de modelagem, versionamento e arquitetura limpa.

O sistema gerencia **carros, modelos e marcas**, permitindo operações de CRUD e filtros inteligentes (como listar carros por marca).

---

## 🧠 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3**
    - Spring Web
    - Spring Data JPA
- **H2 Database** (ambiente de desenvolvimento)
- **Lombok**
- **Hibernate**
- **Docker** (para deploy)
- **Render.com** (infraestrutura de hospedagem)

---

## 📦 Estrutura de Entidades

### `Brand`
Representa a marca do carro.

| Campo | Tipo | Descrição |
|--------|------|------------|
| id | Long | Identificador único |
| name | String | Nome da marca |

---

### `Model`
Representa o modelo do carro e está vinculado a uma marca.

| Campo | Tipo | Descrição |
|--------|------|------------|
| id | Long | Identificador único |
| name | String | Nome do modelo |
| fipe | Double | Valor FIPE do modelo |
| brand | Brand | Relação ManyToOne com `Brand` |

---

### `Car`
Representa um carro específico.

| Campo | Tipo | Descrição |
|--------|------|------------|
| id | Long | Identificador único |
| year | Integer | Ano do carro |
| fuel | String | Tipo de combustível |
| doorCount | Integer | Número de portas |
| color | String | Cor |
| model | Model | Relação ManyToOne com `Model` |
| createdAt | Instant | Data de criação automática |
| updatedAt | Instant | Data de atualização automática |

---
## Execução Local
✅ Pré-requisitos

- **Java 17**
- **Maven ou IntelliJ com SDK configurado**
- **Docker (opcional, para containerização)**

## Rodar localmente
# Clonar o repositório
### `git clone https://github.com/JohnnyBoySou/back_java_spring_boot.git`

# Entrar na pasta
### `cd cars-api`

# Executar o projeto
### `./mvnw spring-boot:run`


O servidor subirá em:

http://localhost:8080


Banco de dados H2 acessível em:

http://localhost:8080/h2-console


🐳 Deploy com Docker (Render ou local)
### `FROM openjdk:17-jdk-slim `
### `WORKDIR /app`
### `COPY target/*.jar app.jar`
### `EXPOSE 8080`
### `ENTRYPOINT ["java", "-jar", "app.jar"]`

Build e run:

### `mvn clean package -DskipTests`
### `docker build -t cars-api .`
### `docker run -p 8080:8080 cars-api`

📄 Licença

Este projeto é open source sob a licença MIT.
