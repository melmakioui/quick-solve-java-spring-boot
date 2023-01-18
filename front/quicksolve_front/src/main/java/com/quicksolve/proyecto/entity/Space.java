package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(exclude = {"incidence"})
@AllArgsConstructor
@NoArgsConstructor
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @JoinColumn(name = "incidence_id")
    private Incidence incidence;
}
