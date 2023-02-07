package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private double price;
    private int tax;

    @OneToMany(mappedBy = "service")
    private Set<Advantage> advantages;

    public Service(String name, double price, int tax) {
        this.name = name;
        this.price = price;
        this.tax = tax;
    }
}
