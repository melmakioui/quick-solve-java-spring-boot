package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.Chat;
import com.quicksolve.proyecto.entity.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {

    List<ChatMessage> findAllByChat(Chat chat);
}
