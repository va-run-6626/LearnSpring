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
  - as mentioned in [How to Use H2 in Spring Boot](#how-to-use-h2-in-spring-boot)
```properties
    spring.h2.console.enabled=true
    spring.datasource.url=jdbc:h2:mem:dcbapp
    spring.datasource.driverClassName=org.h2.Driver
    spring.datasource.username=sa
    spring.datasource.password=password
    spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
    spring.jpa.hibernate.ddl-auto=update
```
## Step 2:
  - Create entity, service, repository, config and controller Packages
  - Add lombok dependency 
```xml
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <optional>true</optional>
    </dependency>
```
  - Create a department entity in entity package
```java
package com.LearnSpring.OneShot.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long departmentId;
    private String departmentName;
    private String departmentAddress;
    private String departmentCode;
}

```
- Create a Deprtment Controller controller in controller package
### Important Interface + Class
Creating an **interface** for a **service** and **repository** in software development, especially in frameworks like Spring Boot, follows a **standard design approach** that promotes clean architecture, flexibility, and maintainability. This approach adheres to well-known principles of software design like **SOLID** principles and **Separation of Concerns**.

### **Key Reasons for Creating Interfaces Before Implementation:**

1. **Decoupling and Flexibility:**
  - By defining an interface, the service or repository logic is decoupled from its implementation. This means the client (which could be another class or layer) only depends on the **interface** and not the **implementation**, promoting **loose coupling**.
  - It allows you to switch the implementation easily. For example, if you need to replace your repository’s database implementation (e.g., from H2 to MySQL), you can just create a new implementation of the interface without modifying the code that uses the repository.

2. **Abstraction**:
  - The interface provides an **abstract contract** that defines what a service or repository is supposed to do without exposing the internal details of how it does it. This simplifies interactions between layers (e.g., service and controller layers) by hiding unnecessary details.

3. **Testability**:
  - Interfaces make **unit testing** and **mocking** much easier. For instance, if you're testing the service layer, you can mock the repository interface without worrying about the actual repository implementation.
  - Tools like **Mockito** allow you to create mocks of interfaces so that tests focus only on the logic of the method under test, not on external dependencies like databases.

4. **Interchangeable Implementations**:
  - You can have multiple implementations of the same interface. For example:
    - A `UserRepository` interface can have different implementations for **SQL** databases, **NoSQL** databases, or even **in-memory** databases.
    - A `NotificationService` interface could have multiple implementations like **EmailNotificationService**, **SMSNotificationService**, or **PushNotificationService**.
  - This is especially useful in projects where different environments or features require different implementations.

5. **Follows SOLID Principles**:
  - **Interface Segregation Principle (ISP)**: This principle suggests that clients should not be forced to depend on interfaces they do not use. By creating smaller, specialized interfaces for each service or repository, you ensure that classes only depend on what they need.
  - **Dependency Inversion Principle (DIP)**: High-level modules (like controllers) should depend on abstractions (interfaces), not on low-level modules (implementations). This makes it easier to maintain and extend the application as the high-level modules don’t need to be aware of the implementation details.

6. **Separation of Concerns (SoC)**:
  - Defining an interface and then creating an implementation separates the contract (what the service/repository should do) from the logic (how it is done). This division helps in keeping the code clean and modular.

### Example Approach: Service and Repository Interface in Spring Boot

#### 1. **Repository Interface:**
```java
// Define the contract for repository
public interface UserRepository {
    User findUserById(Long id);
    void saveUser(User user);
}
```

#### 2. **Repository Implementation:**
```java
// Implementation of repository, could interact with a database
@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void saveUser(User user) {
        entityManager.persist(user);
    }
}
```

#### 3. **Service Interface:**
```java
// Define the service interface
public interface UserService {
    User getUserById(Long id);
    void createUser(User user);
}
```

#### 4. **Service Implementation:**
```java
// Implementation of service that interacts with the repository
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public void createUser(User user) {
        userRepository.saveUser(user);
    }
}
```

### **Advantages of This Approach:**
- **Modularity**: Each part of the codebase (controller, service, repository) only needs to know about the interfaces, not the concrete implementations.
- **Easier Refactoring**: Since the code depends on interfaces, you can change the implementation details (e.g., changing the database technology) without affecting other parts of the application.
- **Maintainability**: It’s easier to maintain and extend as business logic changes over time. Implementations can evolve independently as long as they adhere to the interface contract.
- **Scalability**: As applications grow in complexity, adding new implementations or altering existing behavior becomes simpler.

### Conclusion:
Creating an interface for a service or repository before implementing them is a **best practice** in object-oriented design that enhances the flexibility, testability, and maintainability of the codebase. By adhering to these principles, you create systems that are more adaptable to change, easier to test, and scalable for future growth.

## Step 3:
    - Create in service package
        - Interface -> IDepartmentService
        - Class for Implentation -> DepartmentServiceImpl
    - Create in repository package
        - Interface -> IDepartmentRepository
- We don't create a solid class for repo as the functions are directly given by JPA

# Save Department API
- **Note :** Find the explaination in the code itself (Comments) 
- first we create a DepartmentControllr controller to give an endpoint 
```java
package com.LearnSpring.OneShot.controller;

import com.LearnSpring.OneShot.entity.Department; // Importing the Department entity
import com.LearnSpring.OneShot.service.IDepartmentService; // Importing the Department service interface
import org.springframework.beans.factory.annotation.Autowired; // For dependency injection
import org.springframework.web.bind.annotation.PostMapping; // Mapping HTTP POST requests
import org.springframework.web.bind.annotation.RequestBody; // For handling request body
import org.springframework.web.bind.annotation.RestController; // Marks this class as a REST controller

/**
 * This is the controller class for managing department-related operations.
 * It handles incoming HTTP requests and forwards them to the service layer.
 */
@RestController
public class DepartmentController {

    // Service layer dependency to handle business logic for departments
    private IDepartmentService departmentService;

    /**
     * Constructor-based dependency injection of the department service.
     * This ensures that the controller has access to the service layer.
     *
     * @param departmentService The service interface for department-related operations.
     */
    @Autowired
    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    /**
     * HTTP POST endpoint to save a department.
     * This method accepts a department object from the request body and sends it to the service layer to be saved.
     *
     * @param department The department object received in the request body.
     * @return The saved department object.
     */
    @PostMapping("/department")
    public Department saveDepartment(@RequestBody Department department) {
        // Calling the service layer to save the department and returning the saved department
        return departmentService.saveDepartment(department);
    }
}

```
- From controller, we call in DepartmentService here we use the interface and solid class design [Important](#important-interface--class) 
- Now modify the following service package classes as the following code
  - IDepartmentService and DepartmentServiceImpl
  
```java
    package com.LearnSpring.OneShot.service;

    import com.LearnSpring.OneShot.entity.Department; // Importing the Department entity
    
    /**
     * Interface for Department service operations.
     * Defines the contract for department-related business logic.
     */
    public interface IDepartmentService {

    /**
     * Method to save a department.
     * This method is implemented by the service class to handle department saving logic.
     *
     * @param department The department object to be saved.
     * @return The saved department object.
     */
    public Department saveDepartment(Department department);
}

```
```java
package com.LearnSpring.OneShot.service;

import com.LearnSpring.OneShot.entity.Department; // Importing the Department entity
import com.LearnSpring.OneShot.repository.IDepartmentRepository; // Importing the Department repository interface
import org.springframework.beans.factory.annotation.Autowired; // For dependency injection
import org.springframework.stereotype.Service; // Marks this class as a service

/**
 * Implementation of the IDepartmentService interface.
 * Provides the business logic for department-related operations.
 */
@Service
public class DepartmentServiceImpl implements IDepartmentService {

    // Repository dependency for interacting with the database
    private IDepartmentRepository departmentRepository;

    /**
     * Constructor-based dependency injection of the department repository.
     * This ensures that the service has access to the repository for data access.
     *
     * @param departmentRepository The repository interface for department-related data operations.
     */
    @Autowired
    public DepartmentServiceImpl(IDepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    /**
     * Implements the saveDepartment method from IDepartmentService.
     * Uses the repository to save the department and return the saved entity.
     *
     * @param department The department object to be saved.
     * @return The saved department object.
     */
    @Override
    public Department saveDepartment(Department department) {
        // Delegates the saving operation to the repository
        return departmentRepository.save(department); // Uses JPA repository to persist the department
    }
}

```
- In the repository package modify the already created IDepartmentRepository as follows 
```java
package com.LearnSpring.OneShot.repository;

import com.LearnSpring.OneShot.entity.Department; // Importing the Department entity
import org.springframework.data.jpa.repository.JpaRepository; // Importing the JpaRepository interface from Spring Data JPA
import org.springframework.stereotype.Repository; // Importing the Repository annotation

/**
 * Repository interface for Department entity.
 * Extends JpaRepository to provide CRUD operations for the Department entity.
 */
@Repository
public interface IDepartmentRepository extends JpaRepository<Department, Long> {
    // No additional methods are defined here. JpaRepository provides basic CRUD operations out-of-the-box.
}

```

- Now Run the project and test the api from any of API Clients 
- This is the sample output from insomnia

![Save Department API Testing](./ReadmeImg/OutputInsomina.png)


