package com.quicksolve.proyecto.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class FullIncidenceDTO {

    private long id;

    @NotBlank
    @Size(min = 3, max = 50, message = "El titulo debe tener entre 3 y 50 caracteres")
    private String title;

    @NotBlank
    @Size(min = 10, message = "El campo debe tener al menos 10 caracteres")
    private String description;

    private Set<FileDTO> incidenceFiles;
    private List<IncidenceMessageDTO> messages; //sort segun el order

    @Email(message = "El email debe ser válido")
    @NotBlank
    private String email;

    private String daysAgo;
    private long incidenceStateId;
    private long departmentId;
    private long spaceId;

    private DepartmentDTO department;
    private SpaceDTO space;
    private IncidenceStateDTO incidenceState;

}
