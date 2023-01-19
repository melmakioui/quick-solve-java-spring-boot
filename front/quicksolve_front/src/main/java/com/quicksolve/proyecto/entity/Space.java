package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"incidence"})
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "space")
    private Set<Incidence> incidence;
}
