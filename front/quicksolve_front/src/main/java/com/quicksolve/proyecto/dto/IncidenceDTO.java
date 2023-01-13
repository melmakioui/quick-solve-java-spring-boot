package com.quicksolve.proyecto.dto;

import lombok.Data;

@Data
public class IncidenceDTO {
    private Long id;
    private String title;
    private String description;
    private String email;
    private String department;
    private String status;
}
