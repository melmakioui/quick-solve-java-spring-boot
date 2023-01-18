package com.quicksolve.proyecto.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "incidence")
@AllArgsConstructor
@NoArgsConstructor
public class IncidenceFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "incidence_id")
    private Incidence incidence;
}
