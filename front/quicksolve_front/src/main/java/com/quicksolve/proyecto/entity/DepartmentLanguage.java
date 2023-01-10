package com.quicksolve.proyecto.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

@Entity
@Data
@EqualsAndHashCode(exclude = {"language", "department"})
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class DepartmentLanguage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

}
