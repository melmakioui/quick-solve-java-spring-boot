package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.Department;
import com.quicksolve.proyecto.entity.DepartmentLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
