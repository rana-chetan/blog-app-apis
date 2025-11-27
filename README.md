# Blog App Backend  
Java Spring Boot based backend service for a blogging platform

![Java](https://img.shields.io/badge/Java-21-blue)  
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-Framework-green)  
![Build](https://img.shields.io/badge/Build-Maven-orange)  
![Status](https://img.shields.io/badge/Status-Active-success)

## Overview  
This project is a clean and scalable backend service created for a blog application. It is built with Java and the Spring Boot framework. The service provides structured endpoints that allow creation, updating, removal and retrieval of blog posts.

The project follows a layered structure that is easy to understand. It includes validation, proper error handling and predictable responses. The simple design makes the backend easy to maintain and extend.

## Key Features  

| Feature | Description |
|--------|-------------|
| Post Management | Create, update, remove and retrieve posts |
| Category Support | Retrieve posts by category |
| User Support | Retrieve posts created by a specific user |
| Validation | Strong request validation |
| Error Handling | Clear and meaningful error messages |
| Layered Architecture | Clean separation of controller, service and repository layers |
| Extensible | Easy to add features like JSON Web Token authentication and comments |

## Tech Stack  

| Technology | Purpose |
|------------|---------|
| Java | Main programming language |
| Spring Boot | Backend framework |
| Spring Data JPA | Database operations |
| Hibernate | Object relational mapping |
| MySQL | Relational database |
| Maven | Build and dependency management |

## How to Run  

### Step 1  
Clone the repository.

### Step 2  
Set your database configuration in the application properties file.  

### Step 3  
Build the project.  

### Step 4  
Start the application.  

You can test the endpoints using Postman or similar tools.

## Example API Usage  

### Create a Post  
Request  
POST  
`api/posts`  

### Get All Posts  
GET  
`api/posts`

### Remove a Post  
DELETE  
`api/posts/{id}`

## Why This Project Is Useful  
This project is a simple and helpful starting point for anyone learning Java backend development. It follows industry patterns and makes it possible to add new modules such as comments, image uploads, likes and an administration panel.

## Future Enhancements  

| Feature | Planned |
|--------|---------|
| JSON Web Token Authentication | Yes |
| Comments Module | Yes |
| Image Upload Support | Yes |
| Role Based Access Control | Yes |
| Search and Pagination | Yes |

## Contribution  
Contributions are welcome. You can open issues or submit pull requests to help improve the project.
