package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class IncidenceState {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "status")
    private Set<IncidenceStateLanguage> incidenceStateLanguage ;

}
