package com.quicksolve.proyecto.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "chatMessages")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String sessionId;
    private String nameUser;
    private String nameTech;
    private boolean isClosed;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "chat")
    private Set<ChatMessage> chatMessages;
}
