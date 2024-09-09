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
