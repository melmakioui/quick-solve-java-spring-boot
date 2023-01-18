package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"departmentLanguage"})
@ToString(exclude = {"departmentLanguage"})
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToMany(mappedBy = "department")
    private Set<User> users;

    @OneToMany(mappedBy = "department")
    private Set<DepartmentLanguage> departmentLanguage;

}
