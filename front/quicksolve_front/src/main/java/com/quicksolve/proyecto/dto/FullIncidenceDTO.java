package com.quicksolve.proyecto.dto;

import lombok.Data;

import java.util.List;

@Data
public class FullIncidenceDTO {

    private long id;

    private String title;

    private String description;

    private List<String> path;

    private String email;

    private long incidenceStateId;
    private long departmentId;
    private long spaceId;
    private DepartmentDTO department;
    private SpaceDTO space;
    private IncidenceStateDTO incidenceState;

}
