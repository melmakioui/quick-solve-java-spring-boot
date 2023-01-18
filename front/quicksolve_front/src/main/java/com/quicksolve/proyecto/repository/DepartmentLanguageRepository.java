package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.DepartmentLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentLanguageRepository extends JpaRepository<DepartmentLanguage, Long> {

    List<DepartmentLanguage> findAllByDepartmentIdAndLanguageId(Long departmentId, Long languageId);
    List<DepartmentLanguage> findByDepartment_IdAndLanguage_Id(Long departmentId, Long languageId);
}
