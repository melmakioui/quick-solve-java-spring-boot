package com.quicksolve.proyecto.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HistoryDTO {
    private long id;
    private long incidenceId;
    private long techId;
    private long departmentId;
    private long stateId;
    private long spaceId;

    private String title;
    private String description;
    private String stateName;
    private String techName;
    private String departmentName;
    private String spaceName;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private LocalDateTime changeDate;
}
