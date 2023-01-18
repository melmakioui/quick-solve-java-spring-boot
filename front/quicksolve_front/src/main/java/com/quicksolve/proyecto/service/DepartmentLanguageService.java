package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.entity.DepartmentLanguage;

import java.util.List;

public interface DepartmentLanguageService {

    List<DepartmentLanguage> list(Long idDepartment, Long idLanguage);
}

