package com.quicksolve.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceDTO {

    private Long id;
    private String name;
    private double price;
    private int tax;

    private List<AdvantageDTO> advantages;
}
