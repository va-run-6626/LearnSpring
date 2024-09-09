package com.LearnSpring.OneShot.service;

import com.LearnSpring.OneShot.entity.Department; // Importing Department entity
import com.LearnSpring.OneShot.repository.IDepartmentRepository; // Importing Department repository interface
import org.junit.jupiter.api.BeforeEach; // JUnit for setup methods
import org.junit.jupiter.api.DisplayName; // Annotation to give meaningful test names
import org.junit.jupiter.api.Test; // JUnit annotation for unit tests
import org.mockito.Mockito; // Mockito for mocking behavior
import org.springframework.beans.factory.annotation.Autowired; // Injecting dependencies in test context
import org.springframework.boot.test.context.SpringBootTest; // Annotation to load Spring Boot application context for testing
import org.springframework.boot.test.mock.mockito.MockBean; // Annotation to mock Spring beans

import static org.junit.jupiter.api.Assertions.*; // Importing JUnit assertions

@SpringBootTest // This annotation loads the Spring Boot application context for integration testing
class IDepartmentServiceTest {

    // Mock the repository dependency for the service layer
    @MockBean
    private IDepartmentRepository departmentRepository;

    // Autowire the service we want to test
    @Autowired
    private IDepartmentService departmentService;

    /**
     * This method runs before each test to set up mock data.
     * It uses Mockito to mock the behavior of the department repository.
     */
    @BeforeEach
    void setUp() {
        // Arrange: Setting up a department object for the test scenario
        Department department = Department.builder()
                .departmentId(1L)
                .departmentName("IT")
                .departmentCode("IT-30")
                .departmentAddress("India")
                .build();

        // Arrange: Mocking the repository method to return the created department when a valid name is passed
        Mockito.when(departmentRepository.findByDepartmentNameIgnoreCase("IT")).thenReturn(department);
    }

    /**
     * This test method verifies that the departmentService fetches a department by its name.
     * It follows the AAA (Arrange, Act, Assert) pattern.
     */
    @Test
    @DisplayName("Get Department Based On Valid Department Name") // Giving a descriptive name to the test
    public void whenValidDepartmentName_thenDepartmentShouldFound(){
        // Act: Defining the department name to be fetched
        String departmentName = "IT";

        // Act: Calling the service method to fetch the department by name
        Department found = departmentService.fetchDepartmentByName(departmentName);

        // Assert: Verifying that the department returned has the expected department name
        assertEquals(departmentName, found.getDepartmentName());
    }
}
