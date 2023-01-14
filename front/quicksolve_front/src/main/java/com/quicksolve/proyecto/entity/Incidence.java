package com.quicksolve.proyecto.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"incidenceState", "department"})
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class Incidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String email;
    private LocalDateTime dateStart;
    private LocalDateTime dateEnd;

    @ManyToOne
    @JoinColumn(name = "incidence_state_id")
    private IncidenceState incidenceState;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = true)
    private Department department;

    @OneToMany(mappedBy = "incidence")
    private Set<IncidenceFiles> incidenceFiles;
}
