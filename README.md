# Student Management System

A RESTful backend application for managing student records, built with **Java, Spring Boot, MySQL and Maven**. It supports full CRUD operations, input validation, centralized error handling, pagination, sorting, filtering, and interactive API documentation with Swagger.

## Features

- Create, read, update and delete students
- Input validation (required fields, email format, age range)
- Duplicate email prevention
- Pagination and sorting (`page`, `size`, `sortBy`, `direction`)
- Filter students by course
- Global exception handling with meaningful HTTP status codes (400, 404)
- Interactive API documentation with Swagger UI
- Layered architecture: Controller, Service, Repository, Model

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Spring Boot | Application framework |
| Spring Web | REST API |
| Spring Data JPA / Hibernate | Database access (ORM) |
| MySQL | Relational database |
| Bean Validation | Request validation |
| springdoc-openapi | Swagger UI documentation |
| Maven | Build and dependency management |
| Postman | API testing |

## Project Structure

```
src/main/java/com/sahil/student_management/
├── controller/     StudentController.java
├── service/        StudentService.java
├── repository/     StudentRepository.java
├── model/          Student.java
├── exception/      GlobalExceptionHandler.java, ResourceNotFoundException.java
└── StudentManagementApplication.java
```

## Getting Started

### Prerequisites

- JDK 17 or higher
- Maven 3.8+ (or use the included `mvnw` wrapper)
- MySQL 8.x

### 1. Clone the repository

```bash
git clone https://github.com/Sahil-860/student-management-system.git
cd student-management-system
```

### 2. Create the database

```sql
CREATE DATABASE student_db;
```

The `students` table is created automatically by Hibernate on the first run.

### 3. Set your database credentials

The app reads credentials from environment variables.

**Windows (PowerShell):**
```powershell
$env:DB_USERNAME="root"
$env:DB_PASSWORD="your_mysql_password"
```

**Mac / Linux:**
```bash
export DB_USERNAME=root
export DB_PASSWORD=your_mysql_password
```

### 4. Run the application

```bash
mvn spring-boot:run
```

The API starts at `http://localhost:8080`.

## API Documentation

Swagger UI: `http://localhost:8080/swagger-ui/index.html`

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/students` | Create a student |
| GET | `/api/students` | List students (paginated) |
| GET | `/api/students/{id}` | Get a student by id |
| PUT | `/api/students/{id}` | Update a student |
| DELETE | `/api/students/{id}` | Delete a student |

### Query parameters for `GET /api/students`

| Parameter | Default | Description |
|---|---|---|
| `page` | 0 | Page number (starts at 0) |
| `size` | 10 | Items per page (max 50) |
| `sortBy` | id | One of: id, firstName, lastName, email, course, age |
| `direction` | asc | `asc` or `desc` |
| `course` | none | Filter by course name (case-insensitive) |

Example: `GET /api/students?course=Computer Science&page=0&size=5&sortBy=age&direction=desc`

### Sample request

`POST /api/students`

```json
{
  "firstName": "Aarav",
  "lastName": "Sharma",
  "email": "aarav.sharma@example.com",
  "course": "Computer Science",
  "age": 20
}
```

### Sample response (201 Created)

```json
{
  "id": 1,
  "firstName": "Aarav",
  "lastName": "Sharma",
  "email": "aarav.sharma@example.com",
  "course": "Computer Science",
  "age": 20
}
```

### Sample validation error (400 Bad Request)

```json
{
  "timestamp": "2026-10-21T10:15:30",
  "status": 400,
  "errors": {
    "email": "Invalid email format"
  }
}
```

### HTTP status codes used

| Code | Meaning |
|---|---|
| 200 | OK |
| 201 | Created |
| 204 | Deleted (no content) |
| 400 | Validation error, duplicate email, or invalid sort field |
| 404 | Student not found |

## Screenshots

### Swagger UI
![Swagger UI](docs/screenshots/swagger-ui.png)

### Create student (Postman)
![Create student](docs/screenshots/postman-create.png)

### Pagination (Postman)
![Pagination](docs/screenshots/postman-pagination.png)

### Validation error (Postman)
![Validation error](docs/screenshots/postman-validation.png)

### Data in MySQL
![MySQL table](docs/screenshots/mysql-table.png)

## Testing

A Postman collection is included in the `Postman/` folder. Import it into Postman to test all endpoints.

## Future Improvements

- DTO layer to separate API models from entities
- Unit and integration tests (JUnit, Mockito)
- Search by name or email
- Course entity with relationships
- Authentication and authorization with Spring Security and JWT

## Author

**Sahil Katiyar**
GitHub:(https://github.com/Sahil-860)
LinkedIn:(https://www.linkedin.com/in/sahil-katiyar-449353261/)
