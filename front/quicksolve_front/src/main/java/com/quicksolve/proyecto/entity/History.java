package com.quicksolve.proyecto.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.EqualsExclude;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsExclude
    @EqualsAndHashCode.Exclude
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
    @EqualsAndHashCode.Exclude
    @EqualsExclude
    private LocalDateTime changeDate;

}
