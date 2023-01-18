package com.quicksolve.proyecto.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"incidenceState", "department"})
@AllArgsConstructor
@NoArgsConstructor
public class Incidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Size(min = 3, max = 50, message = "El titulo debe tener entre 3 y 50 caracteres")
    @NotBlank(message = "El campo no puede estar vacio")
    private String title;
    @Column(columnDefinition = "TEXT")


    @Size(min = 10, message = "El campo debe tener al menos 10 caracteres")
    @NotBlank(message = "El campo no puede estar vacio")
    private String description;
    @NotBlank(message = "El campo no puede estar vacio")
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

    @OneToOne
    @JoinColumn(name = "space_id")
    private Space space;
}
