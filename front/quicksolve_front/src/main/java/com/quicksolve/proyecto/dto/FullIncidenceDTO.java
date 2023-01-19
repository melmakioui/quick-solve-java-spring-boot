package com.quicksolve.proyecto.dto;

import com.quicksolve.proyecto.entity.Department;
import com.quicksolve.proyecto.entity.IncidenceState;
import com.quicksolve.proyecto.entity.Space;
import lombok.Data;

import java.util.List;

@Data
public class FullIncidenceDTO {

    private String title;

    private String description;

    private List<String> path;

    private String email;

    private IncidenceState incidenceState;
    private Department department;
    private Space space;
}
