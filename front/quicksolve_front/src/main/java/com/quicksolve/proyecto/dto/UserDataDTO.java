package com.quicksolve.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDataDTO {
    private long id;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private LocalDateTime created;
    private Long idUser;

    public UserDataDTO(String name, String firstSurname, String secondSurname, LocalDateTime created) {
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.created = created;
    }
    public UserDataDTO(String name, String firstSurname, String secondSurname, LocalDateTime created, Long idUser) {
        this.name = name;
        this.firstSurname = firstSurname;
        this.secondSurname = secondSurname;
        this.created = created;
        this.idUser = idUser;
    }
}
