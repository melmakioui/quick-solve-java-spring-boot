package com.quicksolve.proyecto.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class IncidenceMessageDTO {
    private long id;

    @NotBlank
    private String body; //Cuerpo del mensaje
    private int orderr;
    private LocalDateTime dateHour;
    private boolean isAction; //si ha hecho check en el checkbox de "accion realizada"
    private String userNameUser;
    private String userNameTech;

}
