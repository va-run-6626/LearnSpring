package com.LearnSpring.OneShot.service;

import com.LearnSpring.OneShot.entity.Department; // Importing the Department entity
import com.LearnSpring.OneShot.error.DepartmentNotFoundException;

import java.util.List;

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

    /**
     * Method to fetch all departments.
     * This method retrieves a list of all department objects.
     *
     * @return A list of all department objects.
     */
    public List<Department> fetchAllDepartmentList();

    /**
     * Method to find a department by its ID.
     * This method retrieves a department object with the specified ID.
     *
     * @param id The ID of the department to be retrieved.
     * @return The department object with the specified ID.
     */
    public Department findDepartmentById(Long id) throws DepartmentNotFoundException;

    /**
     * Method to delete a department by its ID.
     * This method deletes the department with the specified ID.
     *
     * @param id The ID of the department to be deleted.
     */
    public void deleteDepartmentById(Long id);

    /**
     * Method to update a department by its ID.
     * This method updates the department with the specified ID using the provided department object.
     *
     * @param id The ID of the department to be updated.
     * @param department The department object containing the updated data.
     * @return The updated department object.
     */
    public Department updateDepartmentById(Long id, Department department);

    /**
     * Method to fetch a department by its name.
     * This method retrieves a department object with the specified name.
     *
     * @param name The name of the department to be retrieved.
     * @return The department object with the specified name.
     */
    public Department fetchDepartmentByName(String name);
}
