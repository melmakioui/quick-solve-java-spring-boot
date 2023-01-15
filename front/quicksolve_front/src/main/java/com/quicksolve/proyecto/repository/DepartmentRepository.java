package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
