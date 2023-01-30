package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "user.id", target = "id")
    FullUserDTO userToDTO (User user);
    User DTOtoUser(FullUserDTO fullUserDTO);
    void updateUserFromDto(FullUserDTO dto, @MappingTarget User entity);
}
