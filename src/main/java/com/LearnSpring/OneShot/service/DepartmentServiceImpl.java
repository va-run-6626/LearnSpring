package com.LearnSpring.OneShot.service;

import com.LearnSpring.OneShot.entity.Department; // Importing the Department entity
import com.LearnSpring.OneShot.repository.IDepartmentRepository; // Importing the Department repository interface
import org.springframework.beans.factory.annotation.Autowired; // For dependency injection
import org.springframework.stereotype.Service; // Marks this class as a service

import java.util.List;
import java.util.Objects;

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

    /**
     * Implements the fetchAllDepartmentList method from IDepartmentService.
     * Retrieves all department entities from the repository.
     *
     * @return A list of all department objects.
     */
    @Override
    public List<Department> fetchAllDepartmentList() {
        // Retrieves all departments from the repository
        return departmentRepository.findAll();
    }

    /**
     * Implements the findDepartmentById method from IDepartmentService.
     * Retrieves a department entity by its ID from the repository.
     *
     * @param id The ID of the department to be retrieved.
     * @return The department object with the specified ID.
     */
    @Override
    public Department findDepartmentById(Long id) {
        // Retrieves the department by ID from the repository
        return departmentRepository.findById(id).orElse(null); // Returns null if not found
    }

    /**
     * Implements the deleteDepartmentById method from IDepartmentService.
     * Deletes the department entity with the specified ID from the repository.
     *
     * @param id The ID of the department to be deleted.
     */
    @Override
    public void deleteDepartmentById(Long id) {
        // Deletes the department by ID from the repository
        departmentRepository.deleteById(id);
    }

    /**
     * Implements the updateDepartmentById method from IDepartmentService.
     * Updates the department entity with the specified ID using the provided department data.
     *
     * @param id The ID of the department to be updated.
     * @param department The department object containing the updated data.
     * @return The updated department object.
     */
    @Override
    public Department updateDepartmentById(Long id, Department department) {
        // Retrieves the existing department from the repository
        Department fromDB = departmentRepository.findById(id).orElse(null);
        if (fromDB != null) {
            // Updates fields if they are not null or empty
            if (Objects.nonNull(department.getDepartmentName()) && !"".equalsIgnoreCase(department.getDepartmentName())) {
                fromDB.setDepartmentName(department.getDepartmentName());
            }
            if (Objects.nonNull(department.getDepartmentAddress()) && !"".equalsIgnoreCase(department.getDepartmentAddress())) {
                fromDB.setDepartmentAddress(department.getDepartmentAddress());
            }
            if (Objects.nonNull(department.getDepartmentCode()) && !"".equalsIgnoreCase(department.getDepartmentCode())) {
                fromDB.setDepartmentCode(department.getDepartmentCode());
            }
            // Saves the updated department to the repository
            return departmentRepository.save(fromDB);
        }
        return null; // Return null if department with given ID not found
    }

    /**
     * Implements the fetchDepartmentByName method from IDepartmentService.
     * Retrieves a department entity by its name from the repository.
     *
     * @param name The name of the department to be retrieved.
     * @return The department object with the specified name.
     */
    @Override
    public Department fetchDepartmentByName(String name) {
        // Retrieves the department by name from the repository
        return departmentRepository.findByDepartmentNameIgnoreCase(name);
    }
}
