package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.DepartmentDTO;
import com.quicksolve.proyecto.entity.Department;
import com.quicksolve.proyecto.entity.DepartmentLanguage;
import com.quicksolve.proyecto.mapper.DepartmentMapper;
import com.quicksolve.proyecto.repository.DepartmentLanguageRepository;
import com.quicksolve.proyecto.repository.DepartmentRepository;
import com.quicksolve.proyecto.repository.IncidenceStateLanguageRepository;
import com.quicksolve.proyecto.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final Long ESPANOL_LANGUAGE_ID = 1L;
    private final Long ENGLISH_LANGUAGE_ID = 2L;


    private DepartmentRepository departmentRepository;
    private DepartmentLanguageRepository departmentLanguageRepository;

    @Autowired
    public DepartmentServiceImpl(DepartmentRepository departmentRepository, DepartmentLanguageRepository departmentLanguageRepository) {
        this.departmentRepository = departmentRepository;
        this.departmentLanguageRepository = departmentLanguageRepository;
    }

    @Override
    public List<DepartmentDTO> list() {

        List<Department> departments = departmentRepository.findAll();

        if (departments.isEmpty()) {
            return Collections.emptyList();
        }

        return departmentRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    @Override
    public DepartmentDTO findById(Long id) {
        Department department = departmentRepository.findById(id)
                .orElse(null);

        if (department == null) {
            return null;
        }

        return convertToDTO(department);
    }

    public DepartmentDTO convertToDTO(Department department) {
        DepartmentLanguage departmentLanguage = departmentLanguageRepository.findByDepartmentIdAndLanguageId(
                department.getId(),
                ESPANOL_LANGUAGE_ID
        );

        //Handle Null
        return DepartmentMapper.INSTANCE.departmentDTO(departmentLanguage);
    }

}


