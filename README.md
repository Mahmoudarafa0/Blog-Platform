<div align="center"> <h1>📝 Blog Platform API</h1> <p>A secure, production-ready RESTful Blog Backend built with Spring Boot.</p> </div>

## 📌 Overview

Blog Platform API is a backend application built using Spring Boot that provides secure blog management functionality including:

- **🔐 JWT Authentication (Admin login)**

- **📰 Post Management (Draft & Published)**

- **🗂 Category Management**

- **🏷 Tag Management**

- **🔎 Filtering posts by Category and Tag**

- **📄 Swagger API Documentation**

This project follows clean architecture principles and REST best practices.

## 🏗 Architecture
```bash
controller
 ├── AuthController
 ├── PostController
 ├── CategoryController
 └── TagController

service
repository
mapper
dto
entity
security
```
Controller Layer → Handles HTTP requests

Service Layer → Business logic

Repository Layer → Data access (JPA)

Mapper Layer → DTO ↔ Entity conversion (MapStruct)

Security Layer → JWT authentication & authorization


## 🛠 Tech Stack

- **Java 17+**

- **Spring Boot**

- **Spring Security**

- **JWT Authentication**

- **Spring Data JPA**

- **Hibernate**

- **MapStruct**

- **Maven**

- **PostgreSQL**

- **Swagger (OpenAPI 3)**

## 🔐 Authentication
Admin Login
```bash
POST /login
```
Request
```bash
{
  "email": "admin@gmail.com",
  "password": "password"
}
```
Response
```bash
{
  "token": "jwt-token",
  "expiresIn": "duration"
}
```
All protected endpoints require:
```bash
Authorization: Bearer <access-token>
```

## 📰 Post API
### Get All Published Posts
```bash
GET /posts
```
Optional Filters
```bash
GET /posts?categoryId={uuid}
GET /posts?tagId={uuid}
GET /posts?categoryId={uuid}&tagId={uuid}
```
### Get Draft Posts (Authenticated User)
```bash
GET /posts/drafts
```
Returns draft posts for the logged-in user.

### Create Post
```bash
POST /posts
{
  "title": "New Post",
  "content": "Post content...",
  "categoryId": "uuid",
  "tagIds": ["uuid"],
  "status": "PUBLISHED"
}
```

### Update Post
```bash
PUT /posts/{postId}
```

### Delete Post
```bash
DELETE /posts/{postId}
```

## 🗂 Category API
### Get All Categories
```bash
GET /categories
```
### Create Category
```bash
POST /categories
{
  "name": "Technology"
}
```
### Delete Category
```bash
DELETE /categories/{categoryId}
```
## 🏷 Tag API
### Get All Tags
```bash
GET /tags
```
### Create Tags
```bash
POST /tags
{
  "names": ["Java", "Spring Boot"]
}
```
### Delete Tag
```bash
DELETE /tags/{tagId}
```

## 🔎 Filtering Logic

- **Only PUBLISHED posts are returned in public endpoints.**
- 
- **Draft posts are only accessible by their owner.**

- **Filtering works dynamically using optional request parameters.**


## 🧪 API Documentation (Swagger)

After running the application, access Swagger UI:
```bash
http://localhost:8081/swagger-ui/index.html
```

## ⚙️ Installation & Setup
### 1️⃣ Clone the repository
```bash
git clone https://github.com/Mahmoudarafa0/Blog-Platform.git
cd Blog-Platform
```
### 2️⃣ Configure Database
Update your application.yml or application.properties:
```bash
spring.application.name=Blog-Platform
server.port=8081
spring.datasource.url=jdbc:postgresql://localhost:5432/Blog-Platform
spring.datasource.username=username
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
### 3️⃣ Build & Run
```bash
mvn clean install
mvn spring-boot:run
```














