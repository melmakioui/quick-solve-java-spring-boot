package com.quicksolve.proyecto.dto;

import com.quicksolve.proyecto.entity.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FullUserDTO {
    private long id;
    private String username;
    private String password;
    private String email;
    private UserType type;
    private boolean active;
    private long serviceId;
    private ServiceDTO service;
    private Set<InvoiceDTO> invoices;
    private LocalDateTime serviceExpiration;
    private DepartmentDTO department;
    private UserDataDTO data;
    private boolean oauth;

    public void addInvoice(InvoiceDTO invoice){
        this.invoices.add(invoice);
    }

    public FullUserDTO(String username, String password, String email, UserType type, boolean active, ServiceDTO service, DepartmentDTO department, UserDataDTO data, boolean oauth) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.type = type;
        this.active = active;
        this.service = service;
        this.department = department;
        this.data = data;
        this.oauth = oauth;
    }

    public FullUserDTO(String email, String username, UserDataDTO data) {
        this.email = email;
        this.username = username;
        this.data = data;
    }
}
