package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "chat")
@ToString(exclude = "chat")
public class ChatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String message;
    private String sentBy;

    @ManyToOne
    @JoinColumn(name = "chat_id")
    private Chat chat;
}
