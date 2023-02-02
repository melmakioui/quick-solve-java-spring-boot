package com.quicksolve.proyecto.dto;

import lombok.Data;

import java.util.List;

@Data
public class ServiceDTO {

    private Long id;
    private String name;
    private double price;
    private int tax;

    private List<AdvantageDTO> advantages;
}
