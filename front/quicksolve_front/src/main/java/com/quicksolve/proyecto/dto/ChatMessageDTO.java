package com.quicksolve.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatMessageDTO {
    private long id;

    private String message;
    private String sentBy;
    private ChatDTO chatDTO;
}
