package com.quicksolve.proyecto.dto;

import lombok.Data;

@Data
public class InvoiceLineDTO {

    private Long id;
    private String name;
    private double price;
    private int tax;
}
