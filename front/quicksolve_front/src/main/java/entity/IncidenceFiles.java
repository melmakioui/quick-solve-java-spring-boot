package entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
