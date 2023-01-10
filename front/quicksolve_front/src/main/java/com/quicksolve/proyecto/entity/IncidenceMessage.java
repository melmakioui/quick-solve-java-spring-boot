package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(exclude = {"incidence", "user"})
@AllArgsConstructor
@NoArgsConstructor
public class IncidenceMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;
    private int orderr;
    private LocalDateTime dateHour;
    private boolean isAction;

    @ManyToOne
    @JoinColumn(name = "incidence_id")
    private UserIncidence incidence;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserIncidence user;
}
