package com.quicksolve.proyecto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {

    private long id;
    private String sessionId;
    private String nameUser;
    private String nameTech;
    private boolean isClosed;

    private List<ChatMessageDTO> chatMessageDTOS;

    public ChatDTO(String sessionId, String user) {
        this.sessionId = sessionId;
        this.nameUser = user;
        this.isClosed = false;
    }
}
