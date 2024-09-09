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
