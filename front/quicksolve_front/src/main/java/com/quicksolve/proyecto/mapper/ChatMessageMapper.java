package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.ChatMessageDTO;
import com.quicksolve.proyecto.entity.ChatMessage;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatMessageMapper {
    ChatMessageMapper INSTANCE = Mappers.getMapper(ChatMessageMapper.class);

    ChatMessageDTO messageToDTO(ChatMessage chatMessage);

    @Mapping(source = "chatDTO", target = "chat")
    ChatMessage dtoToMessage(ChatMessageDTO chatMessageDTO);
}
