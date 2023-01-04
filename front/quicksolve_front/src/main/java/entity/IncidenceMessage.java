package entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class IncidenceMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String body;
    private int order;
    private LocalDateTime dateHour;
    private boolean isAction;

    @ManyToOne
    @JoinColumn(name = "user_incidence_id")
    private UserIncidence incidence;

    @ManyToOne
    @JoinColumn(name = "user_incidence_id")
    private UserIncidence user;
}
