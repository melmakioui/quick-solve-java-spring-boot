package entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "incidence")
    private List<IncidenceFiles> incidenceFiles;
}
