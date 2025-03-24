# Syllabus Management System

A comprehensive system for managing university syllabi, courses, instructors, programs, and pathways.

## Features

### Course Management
- Create, view, edit, and delete courses
- Manage course details including code, name, credits, and description
- Associate courses with instructors

### Instructor Management
- Add, view, edit, and delete instructor profiles
- Track instructor information and contact details
- Assign instructors to courses

### Program Management
- Create and manage academic programs
- Define program structure and requirements
- View program details and associated courses

### Pathway Management
- Create and manage academic pathways (e.g., Major Core, Electives)
- Organize courses within different pathways
- Associate pathways with programs

### Course-Pathway Management
- Assign courses to programs through specific pathways
- Define course position within program curriculum (year and semester)
- Edit and remove course assignments

### Curriculum Planning
- Visualize complete program curriculum
- Organize courses by year, semester, and pathway
- Plan and adjust academic pathways

### User Authentication & Authorization
- Secure login and user management
- Role-based access control (Admin, Faculty, etc.)
- Protected routes and operations

### Responsive User Interface
- Bootstrap-based responsive design
- Intuitive navigation with breadcrumbs
- Interactive tables with sorting and filtering

## Project Structure

The application is organized into a multi-module Maven project:

- **syllabus-parent**: The parent module that manages dependencies and configurations
- **syllabus-core**: Contains domain models and DTOs
- **syllabus-data**: Contains repositories and database access
- **syllabus-service**: Contains business logic and services
- **syllabus-web**: Contains controllers, views, and web configuration

## Docker Deployment

### Prerequisites

- Docker and Docker Compose installed on your system
- Git (optional, for cloning the repository)

### Deployment Steps

1. Clone or download this repository:
   ```
   git clone <repository-url>
   cd syllabus-management
   ```

2. Build and start the containers:
   ```
   docker-compose up -d
   ```

3. The application will be available at:
   ```
   http://localhost:8080
   ```

4. To stop the containers:
   ```
   docker-compose down
   ```

5. To stop the containers and remove volumes (this will delete all data):
   ```
   docker-compose down -v
   ```

### Configuration

The Docker Compose setup includes:

- MySQL 8.0 database
- Spring Boot application

Default database credentials:
- Database: digit
- Username: syllabus
- Password: password

You can modify these settings in the `docker-compose.yml` file.

### Troubleshooting

- If the application fails to connect to the database, ensure the database container is healthy:
  ```
  docker ps
  ```

- Check logs from the application:
  ```
  docker logs syllabus-app
  ```

- Check logs from the database:
  ```
  docker logs syllabus-db
  ```

## Manual Development Setup

### Prerequisites

- JDK 21
- Maven
- MySQL 8.0

### Configuration

1. Create a MySQL database named `digit`
2. Update `syllabus-web/src/main/resources/application.properties` with your database credentials if needed

### Running Locally

```
mvn clean install
java -jar syllabus-web/target/syllabus-web-0.0.1-SNAPSHOT.jar
```

Or using Maven:

```
mvn clean install
mvn spring-boot:run -pl syllabus-web
```

The application will be available at `http://localhost:8080`

## Technologies Used

- **Backend**: Spring Boot 3.4, Spring Data JPA, Spring Security
- **Frontend**: Thymeleaf, Bootstrap 5, JavaScript
- **Database**: MySQL 8.0
- **Build Tool**: Maven
- **Deployment**: Docker, Docker Compose
