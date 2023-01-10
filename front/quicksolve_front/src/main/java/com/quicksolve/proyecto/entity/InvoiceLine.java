package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "invoice")
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private double price;
    private int tax;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
