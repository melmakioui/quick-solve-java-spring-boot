package com.quicksolve.proyecto.entity;

import com.quicksolve.proyecto.entity.type.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"service", "department"})
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    @Email(message = "El email debe ser valido")
    @Size(message = "El email debe tener entre 3 y 50 caracteres", min = 3, max = 50)
    private String email;

    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(columnDefinition = "boolean default false")
    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @OneToMany(mappedBy = "user")
    @ToString.Exclude
    private Set<Invoice> invoices;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    private Department department;
}
