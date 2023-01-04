package com.quicksolve.proyecto.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@ToString
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

    @ManyToOne
    @JoinColumn(name = "incidence_id")
    private Incidence incidence;
}
