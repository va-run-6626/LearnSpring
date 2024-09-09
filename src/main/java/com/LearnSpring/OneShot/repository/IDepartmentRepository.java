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

    /**
     * Finds a department by its name.
     * The method is automatically implemented by Spring Data JPA based on the method name convention.
     *
     * @param departmentName The name of the department to be retrieved.
     * @return The department object with the specified name.
     */
    public Department findByDepartmentName(String departmentName);

    /**
     * Finds a department by its name, ignoring case.
     * The method is automatically implemented by Spring Data JPA based on the method name convention.
     *
     * @param departmentName The name of the department to be retrieved.
     * @return The department object with the specified name, case-insensitive.
     */
    public Department findByDepartmentNameIgnoreCase(String departmentName);
}
