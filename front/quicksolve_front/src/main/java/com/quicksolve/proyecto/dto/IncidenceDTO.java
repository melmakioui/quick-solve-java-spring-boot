package com.quicksolve.proyecto.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidenceDTO {
    private Long id;
    private String title;
    private String description;
    private String email;
    private LocalDateTime dateStart;
    private Long departmentId;
    private String department;
    private Long statusId;
    private String status;
}
