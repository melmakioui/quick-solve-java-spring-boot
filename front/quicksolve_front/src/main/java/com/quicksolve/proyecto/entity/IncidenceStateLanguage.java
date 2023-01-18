package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"status", "language"})
@AllArgsConstructor
@NoArgsConstructor
public class IncidenceStateLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String statusName;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "incidence_state_id")
    private IncidenceState status;
}
