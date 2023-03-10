package com.quicksolve.proyecto.entity;

import com.quicksolve.proyecto.entity.type.UserType;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(exclude = {"service", "department", "invoices"})
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

//    @NotBlank
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType type;

    @Column(columnDefinition = "boolean default false")
    private boolean isActive;

    private boolean isRecovering;
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    private LocalDateTime serviceExpiration;

    @Column(columnDefinition = "boolean default false")
    private boolean oauth;

    @OneToMany(mappedBy = "user")
    private Set<Invoice> invoices;

    @ManyToOne
    @JoinColumn(name = "department_id")
    @ToString.Exclude
    private Department department;
}
