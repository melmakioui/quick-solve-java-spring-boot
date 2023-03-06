package com.quicksolve.proyecto.repository;

import com.quicksolve.proyecto.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat, Long> {

    List<Chat> findAllByIsClosedIsFalse();
    Chat findBySessionIdAndIsClosedIsFalse(String sessionId);
}
