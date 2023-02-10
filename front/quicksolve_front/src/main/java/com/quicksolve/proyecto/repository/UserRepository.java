package com.quicksolve.proyecto.repository;


import com.quicksolve.proyecto.entity.Department;
import com.quicksolve.proyecto.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);

    @Query("SELECT u from User u where u.department.id = ?1")
    List<User> getUsersByDepartment(long departmentId);

    @Query("SELECT u from User u where u.department.id IS NOT NULL")
    List<User> getUsersByAnyDepartment();
}
