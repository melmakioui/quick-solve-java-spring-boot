package entity;


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
    @JoinColumn(name = "user_id")
    private User tech;

    @ManyToOne
    @JoinColumn(name = "incidence_message_id")
    private IncidenceMessage incidenceMessage;
}
