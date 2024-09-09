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
    // No additional methods are defined here. JpaRepository provides basic CRUD operations out-of-the-box.
}
