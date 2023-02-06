package com.quicksolve.proyecto.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"user", "tech", "incidence"})
@AllArgsConstructor
@NoArgsConstructor

public class UserIncidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "tech_id")
    private User tech;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "incidence_id")
    private Incidence incidence;
}
