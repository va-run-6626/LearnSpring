package com.LearnSpring.OneShot.controller;

import com.LearnSpring.OneShot.entity.Department; // Importing the Department entity
import com.LearnSpring.OneShot.service.IDepartmentService; // Importing the Department service interface
import org.springframework.beans.factory.annotation.Autowired; // For dependency injection
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/departments")
    public Department saveDepartment(@RequestBody Department department) {
        // Calling the service layer to save the department and returning the saved department
        return departmentService.saveDepartment(department);
    }

    /**
     * HTTP GET endpoint to fetch all departments.
     * This method retrieves a list of all department objects from the service layer.
     *
     * @return A list of all department objects.
     */
    @GetMapping("/departments")
    public List<Department> fetchAllDepartmentList() {
        // Calling the service layer to retrieve all departments and returning the list
        return departmentService.fetchAllDepartmentList();
    }

    /**
     * HTTP GET endpoint to fetch a department by its ID.
     * This method retrieves a department object with the specified ID from the service layer.
     *
     * @param id The ID of the department to be retrieved.
     * @return The department object with the specified ID.
     */
    @GetMapping("/departments/{id}")
    public Department findDepartmentById(@PathVariable("id") Long id) {
        // Calling the service layer to retrieve the department by ID and returning it
        return departmentService.findDepartmentById(id);
    }

    /**
     * HTTP DELETE endpoint to delete a department by its ID.
     * This method deletes the department with the specified ID from the service layer.
     *
     * @param id The ID of the department to be deleted.
     * @return A success message indicating the department has been deleted.
     */
    @DeleteMapping("/departments/{id}")
    public String deleteDepartmentById(@PathVariable("id") Long id) {
        // Calling the service layer to delete the department by ID
        departmentService.deleteDepartmentById(id);
        // Returning a success message
        return "Department Deleted Successfully!";
    }

    /**
     * HTTP PUT endpoint to update a department by its ID.
     * This method updates the department with the specified ID using the data provided in the request body.
     *
     * @param id The ID of the department to be updated.
     * @param department The department object containing the updated data.
     * @return The updated department object.
     */
    @PutMapping("/departments/{id}")
    public Department updateDepartmentById(@PathVariable("id") Long id, @RequestBody Department department) {
        // Calling the service layer to update the department by ID with the provided data
        return departmentService.updateDepartmentById(id, department);
    }

    /**
     * HTTP GET endpoint to fetch a department by its name.
     * This method retrieves a department object with the specified name from the service layer.
     *
     * @param name The name of the department to be retrieved.
     * @return The department object with the specified name.
     */
    @GetMapping("/departments/name/{departmentName}")
    public Department fetchDepartmentByName(@PathVariable("departmentName") String name) {
        // Calling the service layer to retrieve the department by name and returning it
        return departmentService.fetchDepartmentByName(name);
    }
}
