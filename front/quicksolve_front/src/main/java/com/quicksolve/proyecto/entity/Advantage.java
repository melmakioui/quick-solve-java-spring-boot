package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"advantageLanguages", "service"})
@ToString(exclude = {"advantageLanguages", "service"})
public class Advantage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany(mappedBy = "advantage")
    private Set<AdvantageLanguage> advantageLanguages;
}
