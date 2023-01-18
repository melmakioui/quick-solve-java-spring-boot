package com.quicksolve.proyecto.dto;

import com.quicksolve.proyecto.entity.Department;
import com.quicksolve.proyecto.entity.Space;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class IncidenceDepartmentDTO {

    @NotBlank(message = "El campo no puede estar vacío")
    @Size(min = 13, max = 60, message = "El titulo debe tener entre 13 y 60 caracteres")
    private String title;

    @NotBlank (message = "El campo no puede estar vacío")
    @Size(min = 13, max = 512)
    private String description;

    private List<String> path;

    @NotBlank
    @Email(message = "El email debe ser valido")
    @Size(min = 13, max = 60, message = "El email debe tener entre 13 y 60 caracteres")
    private String email;

    private Department department;
    private Space space;
}
