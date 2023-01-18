package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.DepartmentDTO;
import com.quicksolve.proyecto.entity.Department;
import com.quicksolve.proyecto.mapper.DepartmentMapper;
import com.quicksolve.proyecto.mapper.IncidenceMapper;
import com.quicksolve.proyecto.repository.DepartmentRepository;
import com.quicksolve.proyecto.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<DepartmentDTO> list() {
        return departmentRepository.findAll()
                .stream().map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public DepartmentDTO convertToDTO(Department department) {
        return DepartmentMapper.INSTANCE.departmentDTO(department.getDepartmentLanguage()
                .stream().filter(x->x.getLanguage().getId() == 1)//Donde idioma x sea igual a el idioma del usuario
                .findFirst()
                .get());
    }

}


