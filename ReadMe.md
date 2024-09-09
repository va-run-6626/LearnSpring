# Learn Spring Boot

## Controller
- Here we Create the endpoint that we hit using any browser or API tool 
- it is the entry point of the system 
```java
package com.LearnSpring.OneShot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// The @RestController annotation indicates that this class will handle web requests.
// It's a specialized version of @Controller that combines @Controller and @ResponseBody, 
// allowing the methods to return data directly as HTTP responses (often in JSON format).

@RestController
public class HelloController {

    // The @GetMapping("/") annotation maps HTTP GET requests to the "/" URL path 
    // (i.e., the root path of the application) to this method.
    // When a GET request is made to this URL, the HelloWorld method is called.

    @GetMapping("/")
    public String HelloWorld() {
        // This method returns a simple string response: "First API".
        // The returned string will be sent as the response body of the GET request.
        return "First API";
    }
}

```
- this is the basics of what we are gonna learn 

## What are we gonna build ? 
![Architecture](./ReadmeImg/Arch.png)

### Three Layer Architecture
    - Three layer Architecture includes
        - Controller layer
        - Service layer
        - Repoistory layer

## What is Controller layer ?  
The **Controller layer** in software architecture is responsible for handling incoming HTTP requests from the client, processing those requests, and sending responses back. In a typical **Model-View-Controller (MVC)** architecture, the controller acts as an intermediary between the **Model** (which contains the business logic and data) and the **View** (which is responsible for the presentation of data).

In the context of **Spring Boot**, the Controller layer:

1. **Handles Requests**: The controller listens for requests sent by the client (e.g., browser, mobile app) and maps them to specific methods. It typically uses annotations like `@GetMapping`, `@PostMapping`, `@PutMapping`, etc., to specify which URL patterns and HTTP methods it handles.

2. **Processes Requests**: The controller interacts with the **Service layer** (if present) or directly with the **Model** to process the request, fetch data, or execute business logic.

3. **Returns Responses**: After processing, the controller prepares a response (which could be JSON, XML, HTML, etc.) and sends it back to the client. In REST APIs, the response is often in JSON or XML format.

### Key Concepts:
- **@RestController**: Used in Spring Boot to indicate that the class is a REST controller, which means it will return data directly (like JSON or XML) instead of rendering a view (HTML).
- **Request Mapping**: Annotations like `@GetMapping`, `@PostMapping`, `@PutMapping` are used to define which URL paths and HTTP methods the controller will handle.

### Example Workflow:
1. **Client sends a request**: `GET /api/users/123`.
2. **Controller receives the request**: The controller method corresponding to the `/api/users/{id}` endpoint is invoked.
3. **Controller interacts with the service/model**: It calls the service to retrieve user data based on the user ID.
4. **Controller sends the response**: The retrieved user data is converted to a JSON response and sent back to the client.

The Controller layer's primary responsibility is managing the flow of data and requests, allowing for a clean separation between user interaction and business logic.

## What is Service layer ?
The **Service layer** in software architecture is responsible for implementing the business logic and handling operations that involve the core functionality of the application. It acts as an intermediary between the **Controller layer** (which handles incoming requests) and the **Data Access layer** (which interacts with the database or external data sources).

### Responsibilities of the Service Layer:
1. **Business Logic**: This layer contains the business rules and logic that govern how data should be processed. It ensures that the application behaves according to the business requirements.

2. **Data Transformation**: The service layer often transforms or processes data retrieved from the database or other external sources before passing it to the controller.

3. **Transaction Management**: In many applications, the service layer is responsible for handling transactions (like saving or updating data in the database). It ensures that multiple operations either complete successfully together or are rolled back in case of errors.

4. **Decoupling Business Logic from Presentation**: The service layer decouples the business logic from the web layer (controller) and data access layer, promoting cleaner code and easier maintenance.

5. **Communication with Other Services**: In more complex systems, the service layer might communicate with other services, microservices, or external APIs to perform specific tasks.

### Key Benefits:
- **Separation of Concerns**: The controller focuses on handling HTTP requests and responses, while the service layer handles business rules, leading to a clear separation of concerns.
- **Reusability**: Business logic is centralized in the service layer, making it reusable across different parts of the application.
- **Testability**: The service layer can be easily tested in isolation from the rest of the system, allowing for better unit testing.

### Example:
Consider a banking application where you want to transfer money between accounts. The controller handles the HTTP request, but the service layer is where the core logic (like checking account balances, debiting one account, crediting another) is performed.

```java
@Service
public class AccountService {

    // Injecting a repository that interacts with the database
    @Autowired
    private AccountRepository accountRepository;

    // Business logic for transferring money between accounts
    public void transferMoney(long fromAccountId, long toAccountId, double amount) {
        // Fetch account details
        Account fromAccount = accountRepository.findById(fromAccountId);
        Account toAccount = accountRepository.findById(toAccountId);

        // Perform business logic: check if there is enough balance
        if (fromAccount.getBalance() < amount) {
            throw new InsufficientFundsException("Not enough balance");
        }

        // Debit from one account and credit the other
        fromAccount.debit(amount);
        toAccount.credit(amount);

        // Save the updated account data
        accountRepository.save(fromAccount);
        accountRepository.save(toAccount);
    }
}
```

### Key Annotations in Spring Boot:
- **@Service**: Marks a class as a service, indicating that it contains business logic.
- **@Transactional**: Used to ensure that a group of operations within the service layer are treated as a single transaction (if one fails, all are rolled back).

In summary, the **Service layer** is crucial for maintaining the core business logic and ensuring a clean, maintainable, and testable code structure.

## What is Repository layer ?
The **Repository layer** (also known as the **Data Access Layer**) in software architecture is responsible for interacting with the database or any other data storage mechanism. It provides an abstraction over the data access logic, allowing the service layer to access data without directly dealing with database queries. In **Spring Data JPA**, the repository layer is commonly used to perform CRUD operations (Create, Read, Update, Delete) without writing any SQL queries.

### Responsibilities of the Repository Layer:
1. **Data Access Abstraction**: It provides a clean separation between business logic (service layer) and data access logic. The repository hides the underlying database interaction.

2. **CRUD Operations**: The repository layer typically provides methods to create, read, update, and delete data in the database.

3. **Query Customization**: You can define custom queries using JPQL (Java Persistence Query Language) or native SQL queries.

4. **Pagination and Sorting**: The repository can handle pagination and sorting of data directly.

### Example in Spring Boot:
In Spring, a repository interface extends `JpaRepository` (or other repository interfaces) to automatically provide data access methods without implementing them manually.

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Additional custom query methods can be defined here
    User findByUsername(String username); // Query method to find a user by username
}
```

- **`JpaRepository<T, ID>`** is a JPA-specific extension of the `Repository` interface in Spring Data. It includes methods like `findAll()`, `save()`, `deleteById()`, etc.

---

### **H2 Database**
**H2** is an open-source, lightweight, and fast in-memory database often used for development, testing, or small-scale applications. It can be embedded in Java applications, making it convenient for development environments where you need a quick database setup without configuring a full-fledged database like MySQL, PostgreSQL, etc.

#### Key Features of H2:
1. **In-memory Database**: Data is stored in memory, so it is lost when the application shuts down (unless you configure persistent storage).
2. **Easy Integration with Spring Boot**: H2 can be integrated with Spring Boot easily for prototyping and testing.
3. **Web Console**: H2 provides a web-based console where you can run SQL queries and inspect the database schema and data.

#### How to Use H2 in Spring Boot:
- Add H2 dependency in `pom.xml`:
```xml
<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>
```

- Configure H2 in `application.properties`:
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=password
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
```

- Access H2 Console:
    - Go to `http://localhost:8080/h2-console` to access the H2 database's web console.

---

### **Java Persistence API (JPA)**
**Java Persistence API (JPA)** is a specification in Java that provides a way to manage relational data using object-relational mapping (ORM). It allows developers to map Java objects to database tables and work with relational data using Java objects instead of writing SQL directly.

#### Key Concepts of JPA:
1. **Entity**: A Java class mapped to a database table. Each instance of the entity corresponds to a row in the table.
2. **EntityManager**: Used for managing database operations such as persisting (saving), removing (deleting), and querying entities.
3. **ORM (Object-Relational Mapping)**: It automatically handles the mapping between Java objects and database tables using annotations or XML.

#### Example of JPA Entity:
```java
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private Long id;
    private String username;
    private String email;

    // Getters and setters
}
```

#### Annotations in JPA:
- **@Entity**: Marks the class as a JPA entity, meaning it will be mapped to a database table.
- **@Id**: Specifies the primary key of the entity.
- **@Table**: Specifies the table name if different from the class name.
- **@Column**: Specifies details for columns (optional).

---

### **Relationship Between Repository, H2, and JPA**
1. **JPA**: Defines the standard for working with relational data in Java, including how Java objects (entities) are mapped to database tables.

2. **H2**: Can be used as the underlying database for storing entities when working with JPA, especially in a development environment.

3. **Repository Layer**: Works with JPA to abstract the interaction with the database (H2, MySQL, etc.). The `JpaRepository` interface is often used in Spring applications to perform data access tasks without writing boilerplate code.

In a Spring Boot project, JPA handles the interaction between your entities and the database (like H2), while the repository layer provides methods to query and manipulate that data.

# Now we can Start with our code
## Step 1: 
  - Add Configurarions for **H2** and **JPA**
  - 