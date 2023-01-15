package com.quicksolve.proyecto.dto;

import com.quicksolve.proyecto.entity.Department;
import com.quicksolve.proyecto.entity.Invoice;
import com.quicksolve.proyecto.entity.Service;
import com.quicksolve.proyecto.entity.type.UserType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class FullUserDTO {
    private long id;
    private String username;
    private String email;
    private UserType type;
    private boolean active;
    private Service service;
    private Set<Invoice> invoices;
    private Department department;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private LocalDateTime created;
}
