# 📌 API de Gestão Bancária - Teste Técnico (Pleno)

API para gestão bancária desenvolvida em Spring Boot, utilizando MongoDB como banco de dados.
Este projeto inclui transações financeiras com diferentes formas de pagamento e aplicação de taxas conforme o método de pagamento escolhido.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
- **MongoDB**
- **Docker & Docker Compose**
- **Swagger (OpenAPI)**
- **JUnit & Mockito** (para testes)
- **Lombok** (para reduzir boilerplate code)

---

## 🛠 Como Rodar o Projeto

### 🔹 Pré-requisitos
Certifique-se de ter instalado:
- **Docker e Docker Compose**
- **JDK 21**
- **Maven**

### 🔹 Clonando o Repositório
```sh
 git clone https://github.com/gitlucaslima/bankObjective.git
 cd bankObjective
```

### 🔹 Gerando o Build da Aplicação
```sh
 mvn clean package
```

### 🔹 Executando com Docker
1. **Criar a imagem Docker**:
```sh
 docker build -t app .
```

2. **Rodar a aplicação com Docker Compose**:
```sh
 docker-compose up -d --build
```

A API estará disponível em: [`http://localhost:8080`](http://localhost:8080)

---

## 📖 Documentação da API (Swagger)

Após subir o projeto, a documentação do Swagger estará disponível em:
- [`http://localhost:8080/swagger-ui.html`](http://localhost:8080/swagger-ui.html)

---

## 🗂️ Estrutura do Projeto
```bash
📂 src
 ├── 📂 main
 │   ├── 📂 java/com/objective/bankobjective
 │   │   ├── 📂 domain (Entidades e lógica de negócio)
 │   │   │   ├── 📂 services (Serviços e regras de negócio)
 │   │   │   ├── 📂 strategy (Estratégias de pagamento)
 │   │   │   ├── 📂 models (Modelos de dados)
 │   │   │   ├── ├── 📂 dtos (Objetos de transferência de dados
 │   │   ├── 📂 infrastructure (Exceções e repositórios)
 │   │   ├── 📂 resources (Requisição e resposta HTTP)
 │   │   ├── 📜 BankObjectiveApplication.java (Classe principal)
 │   ├── 📂 resources
 │   │   ├── 📜 application.properties (Configurações do Spring Boot)
 ├── 📂 test (Testes automatizados)
```

---

## 🐳 Configuração Docker

### **Docker Compose (`docker-compose.yml`)**

```yaml
services:
  api:
    build: .
    container_name: api
    restart: always
    ports:
      - "8080:8080"
    environment:
      - SPRING_APPLICATION_NAME=bankObjective
      - SPRING_DATA_MONGODB_URI=mongodb://admin:secret@mongodb:27017/bankObjective?authSource=admin
    depends_on:
      mongodb:
        condition: service_healthy
    networks:
      - bank-network

  mongodb:
    image: mongo:6.0
    container_name: db
    restart: always
    ports:
      - "27017:27017"
    environment:
      MONGO_INITDB_DATABASE: bankObjective
      MONGO_INITDB_ROOT_USERNAME: admin
      MONGO_INITDB_ROOT_PASSWORD: secret
    volumes:
      - mongodb_data:/data/db
    healthcheck:
      test: ["CMD", "mongosh", "--eval", "db.runCommand('ping').ok"]
      interval: 10s
      timeout: 5s
      retries: 3
    networks:
      - bank-network

volumes:
  mongodb_data:
    driver: local

networks:
  bank-network:
    driver: bridge
```

---

## 📮 Coleção do Postman
A coleção de testes para o **Postman** está disponível dentro do diretório do projeto.

Após clonar o repositório, importe a coleção localizada em:
```bash
bankObjective/postman_collection.json
```

Com isso, você poderá testar todos os endpoints rapidamente! 🚀

---

## 📌 Endpoints da API

### **Criar Conta**
```http
POST /conta
```
#### **Exemplo de Entrada**:
```json
{
  "numero_conta": 234,
  "saldo": 180.37
}
```
#### **Exemplo de Saída**:
```json
{
  "numero_conta": 234,
  "saldo": 180.37
}
```

### **Realizar Transação**
```http
POST /transacao
```
#### **Exemplo de Entrada**:
```json
{
  "forma_pagamento": "D",
  "numero_conta": 234,
  "valor": 10
}
```
#### **Exemplo de Saída**:
```json
{
  "numero_conta": 234,
  "saldo": 170.07
}
```

---

## 📌 Como Parar a Aplicação
Para parar os containers do Docker, execute:
```sh
 docker-compose down
```

Para parar apenas o container da API:
```sh
 docker stop api
```

Para parar apenas o banco MongoDB:
```sh
 docker stop mongodb
```

---

🚀 **Agora você está pronto para rodar e testar a API!**
