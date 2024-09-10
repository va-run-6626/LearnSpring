package com.LearnSpring.OneShot.repository;

import com.LearnSpring.OneShot.entity.Department;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the IDepartmentRepository interface.
 * This class uses Spring Boot's DataJpaTest to set up an in-memory database and test JPA repository methods.
 */
@DataJpaTest
class IDepartmentRepositoryTest {

    @Autowired
    private IDepartmentRepository departmentRepository;  // Inject the repository to be tested

    @Autowired
    private TestEntityManager entityManager;  // Inject TestEntityManager to handle database operations in tests

    /**
     * Sets up the test environment before each test method.
     * This method creates and persists a Department entity to the database.
     */
    @BeforeEach
    void setUp() {
        // Create a new Department instance with sample data
        Department department = Department.builder()
                .departmentName("KGBE")
                .departmentAddress("India")
                .departmentCode("KGBE-08")
                .build();

        // Persist the department to the in-memory database
        entityManager.persist(department);
    }

    /**
     * Tests the findById method of the IDepartmentRepository.
     * This method retrieves a department by its ID and verifies the department name.
     */
    @Test
    public void whenFindById_ThenReturnDepartment(){
        // Retrieve the department with ID 1 from the repository
        Department department = departmentRepository.findById(1L).get();

        // Assert that the retrieved department's name matches the expected value
        assertEquals(department.getDepartmentName(), "KGBE");
    }

}
