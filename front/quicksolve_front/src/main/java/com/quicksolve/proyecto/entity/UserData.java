package com.quicksolve.proyecto.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(exclude = "user")
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class UserData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private LocalDateTime created;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserData(String name, String firstSurname, String secondSurname, LocalDateTime created, User user) {
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.created = created;
        this.user = user;
    }
}
