package com.LearnSpring.OneShot.controller;

import com.LearnSpring.OneShot.entity.Department;
import com.LearnSpring.OneShot.error.DepartmentNotFoundException;
import com.LearnSpring.OneShot.service.IDepartmentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for DepartmentController.
 * This class uses Spring Boot's WebMvcTest to test controller methods.
 */
@WebMvcTest(DepartmentController.class)
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;  // MockMvc instance to perform HTTP requests and assert responses

    @MockBean
    private IDepartmentService departmentService;  // Mock the IDepartmentService to isolate the controller tests

    private Department department;  // Department instance for test scenarios

    /**
     * Sets up the test environment before each test method.
     * This method initializes a sample Department entity to be used in tests.
     */
    @BeforeEach
    void setUp() {
        // Initialize a Department instance with sample data
        department = Department.builder()
                .departmentAddress("Hyderabad")
                .departmentCode("IT-06")
                .departmentName("IT")
                .departmentId(1L)
                .build();
    }

    /**
     * Tests the saveDepartment method of the DepartmentController.
     * This method tests the POST request to save a department.
     */
    @Test
    void saveDepartment() throws Exception {
        // Create a Department instance with input data for the test
        Department inpDept = Department.builder()
                .departmentAddress("Hyderabad")
                .departmentCode("IT-06")
                .departmentName("IT")
                .build();

        // Mock the departmentService's saveDepartment method to return the predefined department instance
        Mockito.when(departmentService.saveDepartment(inpDept)).thenReturn(department);

        /*
         * Original method of performing POST request using MockMvc
         * The request is sent to "/departments" with JSON content and the expected status is 200 OK.
         */
        mockMvc.perform(MockMvcRequestBuilders.post("/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"departmentName\":\"IT\",\n" +
                                "\t\"departmentAddress\":\"Hyderabad\",\n" +
                                "\t\"departmentCode\":\"IT-06\"\n" +
                                "}"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        /*
         * Refactored method with on-demand static input for simplicity
         * The request is sent to "/departments" with JSON content and the expected status is 200 OK.
         */
        mockMvc.perform(post("/departments")
                        .contentType(APPLICATION_JSON)
                        .content("{\n" +
                                "\t\"departmentName\":\"IT\",\n" +
                                "\t\"departmentAddress\":\"Hyderabad\",\n" +
                                "\t\"departmentCode\":\"IT-06\"\n" +
                                "}"))
                .andExpect(status().isOk());
    }

    /**
     * Tests the findDepartmentById method of the DepartmentController.
     * This method tests the GET request to retrieve a department by its ID.
     */
    @Test
    void findDepartmentById() throws Exception {
        // Mock the departmentService's findDepartmentById method to return the predefined department instance
        Mockito.when(departmentService.findDepartmentById(1L)).thenReturn(department);

        // Perform GET request to "/departments/1" and expect the response status to be 200 OK
        // Also, verify that the department name in the response matches the expected value
        mockMvc.perform(get("/departments/1")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.departmentName").value(department.getDepartmentName()));
    }
}
