package com.quicksolve.proyecto.service.implementation;

import com.quicksolve.proyecto.dto.ChatDTO;
import com.quicksolve.proyecto.dto.ChatMessageDTO;
import com.quicksolve.proyecto.entity.Chat;
import com.quicksolve.proyecto.entity.ChatMessage;
import com.quicksolve.proyecto.mapper.ChatMapper;
import com.quicksolve.proyecto.mapper.ChatMessageMapper;
import com.quicksolve.proyecto.repository.ChatMessageRepository;
import com.quicksolve.proyecto.repository.ChatRepository;
import com.quicksolve.proyecto.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private ChatRepository chatRepo;

    @Autowired
    private ChatMessageRepository chatMessageRepo;
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public List<ChatDTO> showAllChats() {
        return chatRepo.findAllByIsClosedIsFalse()
                .stream()
                .map(this::toChatDTO)
                .toList();
    }

    @Override
    public ChatDTO showChatBy(String sessionId) {
        Chat chat = chatRepo.findBySessionIdAndIsClosedIsFalse(sessionId);
        System.out.println(chat.getChatMessages());
        return ChatMapper.INSTANCE.chatToDTO(chatRepo.findBySessionIdAndIsClosedIsFalse(sessionId));
    }

    @Override
    public void saveChat(ChatDTO chat){
        chatRepo.save(ChatMapper.INSTANCE.dtoToChat(chat));
    }

    @Override
    public void sendMessageToGroup(String to, ChatMessageDTO messageDTO) {
        messageDTO.setChatDTO(ChatMapper.INSTANCE.chatToDTO(chatRepo.findBySessionIdAndIsClosedIsFalse(to)));
        chatMessageRepo.save(ChatMessageMapper.INSTANCE.dtoToMessage(messageDTO));
        simpMessagingTemplate.convertAndSend("/topic/group/" + to, messageDTO);
    }

    @Override
    public List<ChatMessageDTO> showAllMessagesOf(long chatId) {
        return chatMessageRepo.findAllByChat(chatRepo.getReferenceById(chatId))
                .stream()
                .map(this::toChatMessageDTO)
                .toList();
    }

    private ChatMessageDTO toChatMessageDTO(ChatMessage chatMessage){
        return ChatMessageMapper.INSTANCE.messageToDTO(chatMessage);
    }

    private ChatDTO toChatDTO(Chat chat){
        return ChatMapper.INSTANCE.chatToDTO(chat);
    }
}
