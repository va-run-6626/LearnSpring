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
