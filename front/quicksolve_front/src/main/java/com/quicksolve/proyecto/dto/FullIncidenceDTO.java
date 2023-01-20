package com.quicksolve.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class FullIncidenceDTO {

    private long id;

    @NotBlank
    @Size(min = 5, max = 16)
    private String title;

    @NotBlank
    @Size(min = 5, max = 16)
    private String description;

    private List<String> path;

    @Email(message = "El email debe ser válido")
    private String email;

    private long incidenceStateId;
    private long departmentId;
    private long spaceId;

    private DepartmentDTO department;
    private SpaceDTO space;
    private IncidenceStateDTO incidenceState;

}
