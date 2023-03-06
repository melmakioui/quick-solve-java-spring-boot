package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.ChatDTO;
import com.quicksolve.proyecto.entity.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChatMapper {
    ChatMapper INSTANCE = Mappers.getMapper(ChatMapper.class);

    @Mapping(source = "chatMessages", target = "chatMessageDTOS")
    ChatDTO chatToDTO(Chat chat);
    Chat dtoToChat(ChatDTO chatDTO);
}
