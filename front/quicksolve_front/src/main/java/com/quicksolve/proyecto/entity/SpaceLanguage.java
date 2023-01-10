package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Data
@EqualsAndHashCode(exclude = {"language", "space"})
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class SpaceLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "space_id")
    private Space space;

}
