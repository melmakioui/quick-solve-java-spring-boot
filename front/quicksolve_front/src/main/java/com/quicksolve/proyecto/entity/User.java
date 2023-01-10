package com.quicksolve.proyecto.entity;

import com.quicksolve.proyecto.entity.type.UserType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"service", "department"})
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;
    private String password;

    @Enumerated(EnumType.ORDINAL)
    private UserType type;

    @Value("true")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<Invoice> invoices;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
}
