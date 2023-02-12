package com.quicksolve.proyecto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private long incidenceId;
    private long techId;
    private long departmentId;
    private long stateId;
    private long spaceId;
    private String title;
    private String description;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;
    private LocalDateTime changeDate;

}
