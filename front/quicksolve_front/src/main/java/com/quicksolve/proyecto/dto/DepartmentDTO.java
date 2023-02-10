package com.quicksolve.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDTO {
    private long id;
    private String name;
    private String method;

    public DepartmentDTO(long id){
        this.id = id;
    }
}
