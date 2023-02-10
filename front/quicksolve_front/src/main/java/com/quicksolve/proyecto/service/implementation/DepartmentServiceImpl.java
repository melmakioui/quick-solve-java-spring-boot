package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.DepartmentDTO;
import com.quicksolve.proyecto.entity.Department;
import com.quicksolve.proyecto.entity.DepartmentLanguage;
import com.quicksolve.proyecto.mapper.DepartmentMapper;
import com.quicksolve.proyecto.repository.DepartmentLanguageRepository;
import com.quicksolve.proyecto.repository.DepartmentRepository;
import com.quicksolve.proyecto.repository.LanguageRepository;
import com.quicksolve.proyecto.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    public DepartmentRepository departmentRepository;
    @Autowired
    public DepartmentLanguageRepository departmentLanguageRepository;
    @Autowired
    public LanguageRepository languageRepository;


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
        Locale locale = LocaleContextHolder.getLocale();
        long languageId = languageRepository.findByName(locale.getLanguage()).getId();

        DepartmentLanguage departmentLanguage = departmentLanguageRepository.findByDepartmentIdAndLanguageId(
                department.getId(),
                languageId
        );

        DepartmentDTO departmentDTO = DepartmentMapper.INSTANCE.departmentDTO(departmentLanguage);
        departmentDTO.setMethod(department.getType().name);
        return departmentDTO;
    }
}


