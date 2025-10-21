# 🧩 Workshop Spring Boot com MongoDB

![Java](https://img.shields.io/badge/Java-17-orange?logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.6-brightgreen?logo=springboot)
![MongoDB](https://img.shields.io/badge/MongoDB-Database-green?logo=mongodb)
![License](https://img.shields.io/badge/license-MIT-blue)

## 📖 Sobre o Projeto

Este projeto foi desenvolvido como parte de um **workshop de integração entre Spring Boot e MongoDB**, com o objetivo de construir uma **API RESTful completa** utilizando **Java 17**, **Spring Boot 3.5.6** e **Spring Data MongoDB**.

A aplicação simula uma **rede social simples**, com entidades relacionadas entre si (`User`, `Post`, `Comment`) e suporte a **consultas personalizadas**, **tratamento global de exceções** e **estrutura em camadas** (Resource → Service → Repository → Domain → DTO).

---

## 🧱 Estrutura do Projeto

```
src/
├── main/
│ ├── java/com/project/workshopmongo/
│ │ ├── config/ # Classe de instância inicial (popula o banco)
│ │ ├── domain/ # Entidades (User, Post)
│ │ ├── dto/ # DTOs (AuthorDTO, CommentDTO, UserDTO)
│ │ ├── repository/ # Interfaces MongoRepository
│ │ ├── resources/ # Controladores REST (UserResource, PostResource)
│ │ ├── services/ # Camada de serviço (UserService, PostService)
│ │ ├── resources/util/ # Classe utilitária URL (decode e parse de datas)
│ │ ├── services/exception/ # Exceções e tratamento global (ObjectNotFound, Handler)
│ │ └── WorkshopmongoApplication.java # Classe principal
│ └── resources/
│ └── application.properties
└── test/
└── java/... (testes unitários)
```

---

## ⚙️ Tecnologias Utilizadas

- ☕ **Java 17**
- 🌱 **Spring Boot 3.5.6**
- 🧩 **Spring Data MongoDB**
- 🌍 **API RESTful**
- 🧾 **Maven**
- 💾 **MongoDB**
- 🧱 **Arquitetura em camadas (MVC)**
- 🧰 **DTO Pattern e tratamento global de exceções**

---

## 🚀 Funcionalidades

### 👤 Usuários (`/users`)
- `GET /users` → Lista todos os usuários  
- `GET /users/{id}` → Busca usuário por ID  
- `POST /users` → Cria um novo usuário  
- `PUT /users/{id}` → Atualiza um usuário existente  
- `DELETE /users/{id}` → Remove um usuário  
- `GET /users/{id}/posts` → Retorna os posts de um usuário

### 📝 Posts (`/posts`)
- `GET /posts/{id}` → Busca post por ID  
- `GET /posts/titlesearch?text=` → Busca posts pelo título  
- `GET /posts/fullsearch?text=&minDate=&maxDate=` → Busca completa (texto + intervalo de datas)

---

## 🧩 Relacionamentos

- **User ↔ Post** → Um usuário pode ter vários posts (`@DBRef`)  
- **Post ↔ CommentDTO** → Comentários embutidos dentro do documento `Post`  
- **AuthorDTO** → Representa o autor de um post ou comentário (dados resumidos do usuário)

---

## ⚠️ Tratamento de Exceções

Os erros da aplicação são tratados de forma global com `@ControllerAdvice`.

Exemplo de resposta JSON para erro 404:
```json
{
  "timestamp": 1690000000000,
  "status": 404,
  "error": "Não encontrado",
  "message": "Objeto não encontrado!",
  "path": "/posts/123"
}
```
---

## 🧰 Dependências Principais (pom.xml)
```
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-mongodb</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## 💾 Banco de Dados MongoDB

- **Host:** `localhost:27017`  
- **Banco:** `workshopmongo`  
- **Dados iniciais populados via `Instantiation.java`, incluindo:**
  - 👤 **Usuários:** Antonella, Ricardo e Eduardo  
  - 📝 **Posts:** “Partiu Viagem”, “Bom Dia”  
  - 💬 **Comentários:** associados a cada post (ex.: “Boa viagem mano!”, “Aproveite”, “Tenha um ótimo dia!”)

---

 ## 🧠 Exemplo de Consulta (Postman)

**Requisição:**
GET http://localhost:8080/posts/fullsearch?text=viagem&minDate=2025-03-20&maxDate=2025-03-23


**Descrição:**  
Esta rota realiza uma **busca completa (full search)**, retornando posts que contenham o texto informado nos campos **título**, **corpo** ou **comentários**, dentro do intervalo de datas definido pelos parâmetros `minDate` e `maxDate`.

---

### 🔙 Exemplo de resposta:

```json
[
  {
    "id": "67169b9a8fb9d25f3c1d8b20",
    "date": "2025-03-21T00:00:00.000+00:00",
    "title": "Partiu viagem",
    "body": "Vou viajar para São Paulo. Abraços!",
    "author": { "id": "67169b9a8fb9d25f3c1d8b10", "name": "Antonella Vieira" },
    "comments": [
      { "text": "Boa viagem mano!", "date": "2025-03-21", "author": { "name": "Eduardo Peçanha" } },
      { "text": "Aproveite", "date": "2025-03-22", "author": { "name": "Ricardo Oliveira" } }
    ]
  }
]
```

---

## 🧪 Como Executar o Projeto

### 🔧 Pré-requisitos

- ☕ **Java 17+**  
- 🧩 **Maven 3.9+**  
- 🍃 **MongoDB** rodando localmente em `localhost:27017`

---

### 🚀 Passos para executar

```bash
# Clonar o repositório
git clone https://github.com/SEU-USUARIO/workshopmongo.git
cd workshopmongo

# Executar com Maven
./mvnw spring-boot:run
```
