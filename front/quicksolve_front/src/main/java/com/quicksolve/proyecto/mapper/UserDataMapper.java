package com.quicksolve.proyecto.mapper;

import com.quicksolve.proyecto.dto.FullUserDTO;
import com.quicksolve.proyecto.entity.User;
import com.quicksolve.proyecto.entity.UserData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserDataMapper {
    UserDataMapper INSTANCE = Mappers.getMapper(UserDataMapper.class);

    @Mapping(source = "user.id", target = "id")
    @Mapping(source = "user.username", target = "username")
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "user.type", target = "type")
    @Mapping(source = "user.active", target = "active")
    @Mapping(source = "user.service", target = "service")
    @Mapping(source = "user.invoices", target = "invoices")
    @Mapping(source = "user.department", target = "department")
    @Mapping(source = "userData.name", target = "name")
    @Mapping(source = "userData.firstSurname", target = "firstSurname")
    @Mapping(source = "userData.secondSurname", target = "secondSurname")
    @Mapping(source = "userData.created", target = "created")
    FullUserDTO userDTO (UserData userData, User user);
}
