package com.quicksolve.proyecto.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
public class InvoiceDTO {
    private Long id;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private LocalDateTime dateHour;
    private Set<InvoiceLineDTO> lines;
}
