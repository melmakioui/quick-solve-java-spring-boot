package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncidenceState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "status")
    private Set<IncidenceStateLanguage> incidenceStateLanguage ;

}
