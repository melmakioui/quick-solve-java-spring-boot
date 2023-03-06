package com.quicksolve.proyecto.service;

import com.quicksolve.proyecto.dto.ChatDTO;
import com.quicksolve.proyecto.dto.ChatMessageDTO;

import java.util.List;

public interface ChatService {

    List<ChatDTO> showAllChats();
    ChatDTO showChatBy(String sessionId);
    List<ChatMessageDTO> showAllMessagesOf(long chatId);
    void saveChat(ChatDTO chat);
    void sendMessageToGroup(String to, ChatMessageDTO messageDTO);
}
