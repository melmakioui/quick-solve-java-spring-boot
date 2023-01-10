package com.quicksolve.proyecto.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Data
@EqualsAndHashCode(exclude = "incidence")
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class IncidenceFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String filePath;

    @ManyToOne
    @JoinColumn(name = "incidence_id")
    private Incidence incidence;
}
