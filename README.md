# Library Management System API

## Introduction
This is a Library Management System API built using Spring Boot and MySQL. The system allows librarians to manage books, patrons, and borrowing records.

## Requirements
- Java 17 or later
-Maven 3.6.0 or later
- MySQL 5.7 or later
- ## Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/ibrahim1234566/Library-Management-System.git
   cd library-management-system
2. CREATE DATABASE library_db;
3. Update the application.properties file with your MySQL credentials:
    ```sh
   spring.datasource.url=jdbc:mysql://localhost:3306/library_db
   spring.datasource.username=root
   spring.datasource.password=your_password
4. Build and run the application:
    ```sh
   mvn clean install
   mvn spring-boot:run
5. To run the tests:
   ```sh
   mvn test
