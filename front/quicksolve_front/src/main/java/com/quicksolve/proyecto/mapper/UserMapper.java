package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    FullUserDTO userToDTO (User user);
    User DTOtoUser(FullUserDTO fullUserDTO);
}
