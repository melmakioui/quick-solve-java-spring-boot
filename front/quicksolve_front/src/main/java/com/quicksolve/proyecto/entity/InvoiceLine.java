package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@EqualsAndHashCode(exclude = "invoice")
@ToString(exclude = "invoice")
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

    public InvoiceLine(String name, double price, int tax, Invoice invoice) {
        this.name = name;
        this.price = price;
        this.tax = tax;
        this.invoice = invoice;
    }
}
